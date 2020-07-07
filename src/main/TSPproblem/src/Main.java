import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final String Z5_DATA_1 = "D:\\Programming\\Java Projects\\Zadanie5\\src\\z5data1.csv";
    public static final String Z5_DATA_2 = "D:\\Programming\\Java Projects\\Zadanie5\\src\\z5data2.csv";

    public static String[][] strings;
    public static String alphabet = "abcdefghijklmnopq";
    private final int N, start;
    private final int[][] odleglosc;
    private List<Integer> trasa = new ArrayList<>();
    private int minOdleglosc = Integer.MAX_VALUE;
    private boolean solver = false;

    public Main(int[][] odleglosc) {
        this(0, odleglosc);
    }

    // Konstruktor klasy Main
    public Main(int start, int[][] odleglosc) {
        N = odleglosc.length;
        if (start < 0 || start >= N) throw new IllegalArgumentException("Nieprawidłowy początek!");
        this.start = start;
        this.odleglosc = odleglosc;
    }

    /**
     * Metoda zwracająca listę miast cykla Hamiltona z najmniejsza odlegloscią
     * @return - trasa
     */
    public List<Integer> getTrasa() {
        if (!solver) {
            solve();
        }
        return trasa;
    }

    /**
     * Metoda zwracająca minimalna odleglość zagadnienia komiwojażera
     * @return - minimalna odległość
     */
    public int getMinOdleglosc() {
        if (!solver) {
            solve();
        }
        return minOdleglosc;
    }

    // Metoda rozwiązująca problem komiwojażera i strategie optymalizacyjna 
    // I znajdująca najmniejsza możliwa scieżke
    public void solve() {
        if (solver) return;

        final int END_STATE = (1 << N) - 1; // END_STATE sluży do przechowywania liczby miast, które zostaną odwidzone
        int[][] memo = new int[N][1 << N];  // Tablica do przechowywania wszystkich przeszukanych sciezek

        // Wypełniam tabelę memo odległościami z grafu
        for (int i = 0; i < N; i++) {
            if (i == start) continue;
            memo[i][(1 << start) | (1 << i)] = odleglosc[start][i];
        }

        // Przechodze po grafie i znajduje minimalna sciezke
        // zapisuje odleglosci minimalne do tablicy i krokowo znajduje
        // minimalną z dostepnych
        for (int r = 3; r <= N; r++) {
            for (int subset : combinations(r, N)) {
                if (notIn(start, subset)) continue;
                for (int next = 0; next < N; next++) {
                    if (next == start || notIn(next, subset)) continue;
                    int subsetWithoutNext = subset ^ (1 << next);
                    int minDist = Integer.MAX_VALUE;
                    for (int i = 0; i < N; i++) {
                        if (i == start || i == next || notIn(i, subset)) continue;
                        int newDist = memo[i][subsetWithoutNext] + odleglosc[i][next];
                        if (newDist < minDist) {
                            minDist = newDist;
                        }
                    }
                    memo[next][subset] = minDist;
                }
            }
        }

        // Złączam z powrotem trase do poczatkowego punktu i aktualizuje minimalna droge
        for (int i = 0; i < N; i++) {
            if (i == start) continue;
            int odleg = memo[i][END_STATE] + odleglosc[i][start];

            if (odleg < minOdleglosc) {
                minOdleglosc = odleg;
            }
        }

        int lastIndex = start;
        int state = END_STATE;
        trasa.add(start);   // Dodaje punkt do listy która zawiera trasę

        // Rekonstruje sciezke "komiwojazera" z tablicy memo
        for (int i = 1; i < N; i++) {
            int index = -1;
            for (int j = 0; j < N; j++) {
                if (j == start || notIn(j, state)) continue;
                if (index == -1) index = j;
                int prevDist = memo[index][state] + odleglosc[index][lastIndex];
                int newDist = memo[j][state] + odleglosc[j][lastIndex];

                if (newDist < prevDist) {
                    index = j;
                }
            }

            trasa.add(index);
            state = state ^ (1 << index);
            lastIndex = index;
        }
        trasa.add(start);

        solver = true;
    }

    // Metoda pomocnicza do funkcji solver,
    // która sprawdza czy element jest zawarty w subset
    private boolean notIn(int elem, int subset) {
        return ((1 << elem) & subset) == 0;
    }

    // Metoda pomocnicza, słuzaca do generowania list subset
    // zwraca liste intow
    public List<Integer> combinations(int r, int n) {
        List<Integer> subsets = new ArrayList<>();
        combinations(0,0,r, n, subsets);
        return subsets;
    }

    /**
     * Aby znależć wszystkie kombinacje rozmiaru r, musimy
     * powtarzać się dopóki nie będziemy mieli wybrane elementy
     * r (aka r = 0), w przeciwnym razie jeśli r != 0, to nadal
     * musimy wybrać element, który znajduję się po pozycji naszego ostatniego
     * wybranego elementu.
     */
    private void combinations(int set, int at, int r, int n, List<Integer> subsets) {
        // Wyjdzie z funkcji gdy nie zostanie elementów do wyboru
        int elementsLeftToPick = n - at;
        if (elementsLeftToPick < r) return;

        // Wybraliśmy 'r' elementy, więc został znalieziony potrzebny subset!
        if (r == 0) {
            subsets.add(set);
        } else {
            for (int i = at; i < n; i++) {
                // Próba dodanie tego elementu
                set ^= (1 << i);
                combinations(set, i + 1, r - 1, n, subsets);
                set ^= (1 << i);
            }
        }
    }

    // Metoda do czytania grafu z pliku
    public static int[][] create2DIntMatrixFromFile(String filename){
        File myCSVfile = new File(filename);
        List<String[]> lista = new ArrayList<>();
        String line = "";
        String CSVsplitter = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(myCSVfile))) {
            while ((line = br.readLine()) != null) {
                String[] lineTokens = line.split(CSVsplitter);
                lista.add(lineTokens);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        strings = lista.toArray(new String[lista.size()][]);
        int[][] matrix = new int[strings.length - 1][strings[0].length - 1];

        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = Integer.parseInt(strings[1+i][1+j]);
            }
        }

        return matrix;
    }

    public static void main(String[] args) {
        int[][] tab = create2DIntMatrixFromFile(Z5_DATA_2);
        System.out.print("Wybierz miasto poczatku i konca: ");

        Scanner in = new Scanner(System.in);
        char start = in.next().charAt(0);

        Main test = new Main(alphabet.indexOf(start), tab);
        System.out.print("Najkrótsza trasa: ");
        for (int i : test.getTrasa()) {
            System.out.print(alphabet.charAt(i) + "--->");
        }
        System.out.println("\nNajmniejsza odleglosc: " + test.getMinOdleglosc());
    }
}
