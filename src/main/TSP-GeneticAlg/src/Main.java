import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static final String Z6_DATA = "D:\\Programming\\Java Projects\\Zadanie6\\src\\z6data.csv";

    public static String alphabet = "abcdefghijklmno";
    public static int[] szafy = {0,1,2,3,5,4,6,7,8,9,10,11,12,13,14};
    public static String[][] strings;
    public static ArrayList<int[]> popul;
    public static int[][] czestotliwosc;
    public static double[] probabity;
    public static double mfc = 0.0;

    public static int[][] create2DIntMatrixFromFile(String filename) {
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
        int[][] matrix = new int[strings.length][strings[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (strings[i][j].equals("X")) {
                    strings[i][j] = "0";
                }
                matrix[i][j] = Integer.parseInt(strings[i][j]);
            }
        }

        return matrix;
    }

    public void setup(int population, int rok, int iteracji) {
        popul = generatePopulation(population); // Funkcja do generowania losowej populacji
        sort(popul);   // Funkcja do sortowania według wartości funkcji celu (rosnaco)

        int devideTen = iteracji / 10;
        for (int i = 0; i < iteracji/devideTen; i++) {
            mfc = medianaFunkcjiCelu(popul);
            System.out.println(mfc);

            probabity = calcProbability(popul);
            System.out.println("Numer pokolenia: " + (1+i));
            printFiveBest(popul);

            nextGen(popul, rok);
        }
    }

    /**
     * Funkcja do generowania następnego pokolenia
     * @param tab - lista z osobników
     * @param rok - parametr rokujący
     */
    private void nextGen(ArrayList<int[]> tab, int rok) {
        ArrayList<int[]> temp  = generatePopulation(rok);

        for (int i = 1; i < temp.size() - 1; i++) {
            tab.set((tab.size() - i), temp.get(i - 1));
        }
        sort(tab);
    }

    /**
     * Metoda pomocnicza do drukowania najlepszych 5
     * osobników dla danej iteracji czy pokolenia
     * @param tab - tablica osobników
     */
    private void printFiveBest(ArrayList<int[]> tab) {
        System.out.println("Najlepsze 5: ");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < tab.get(i).length - 1; j++) {
                System.out.print(alphabet.charAt(tab.get(i)[j]) + "-->");
            }
            System.out.print(alphabet.charAt(tab.get(i)[0]) + "  FC: " + (tab.get(i)[tab.get(i).length - 1]) + "\n");
        }
    }

    /**
     * Metoda generująca w sposób losowy populacje osobników
     * @param population - rozmiar populacji
     * @return - zwraca wygenerowaną populację
     */
    public ArrayList<int[]> generatePopulation(int population) {
        ArrayList<int[]> temp = new ArrayList<>();
        for (int i = 0; i < population; i++) {
            int[] p = mutate(szafy, population*10);
            int fc = funkcjaCelu(p);
            int[] newP = Arrays.copyOf(p, p.length + 1);
            newP[newP.length - 1] = fc;
            temp.add(newP);
        }
        sort(temp);
        return temp;
    }

    /**
     * Metoda pomocnicza służąca do sortowania listy osobników rosnąco
     * według wartości funkcji celu, która jest na ostatnim miejscu w każdej tablicy
     * @param tab - Tablica do posortowania
     */
    private void sort(ArrayList<int[]> tab) {
        tab.sort((o1, o2) -> {
            if (o1[o1.length - 1] == o2[o2.length - 1]) return 0;
            return (o1[o1.length - 1] > o2[o2.length - 1]) ? 1 : -1;
        });
    }

    /**
     * Funkcja służąca do wyliczania wartości funkcji celu
     * Jest równa sumie iloczynów częstotliwości i odległości szaf między sobą
     * @param tab - tablica do wylicznia wartości FC
     * @return - wartość FC
     */
    private int funkcjaCelu(int[] tab) {
        int sum = 0;

        czestotliwosc = create2DIntMatrixFromFile(Z6_DATA);
        for (int i = 0; i < tab.length - 1; i++) {
            sum += czestotliwosc[tab[i]][tab[i+1]] * Math.abs(tab[i+1] - tab[i]);
        }
        // Takim sposobem znajduje sumaryczna przebyta droge
        sum += czestotliwosc[tab[tab.length - 1]][tab[0]] * Math.abs(tab[0] - tab[tab.length - 1]);
        return sum;
    }

    /**
     * Funkcja służąca do znalezienia średniej wartości FC dla danego pokolenia
     * @param tab - tablica jako parametr
     * @return - średnia wartość FC dla danego pokolenia
     */
    private double medianaFunkcjiCelu(ArrayList<int[]> tab) {
        double mfc = 0.0;
        double sum = 0;
        for (int[] elem : tab) {
            sum += elem[elem.length - 1];
        }
        mfc = sum / tab.size();
        return mfc;
    }

    /**
     * Funkcja pomocnicza licząca prawdopobieństwo dodania osobników
     * do nowego pokolenia
     * @param tab - tablica do obsługi
     * @return - zwraca tablicę prawdopodobieństwa
     */
    private double[] calcProbability(ArrayList<int[]> tab) {
        double mfc = medianaFunkcjiCelu(tab);
        double[] probability = new double[tab.size()];

        for (int i = 0; i < tab.size(); i++) {
            probability[i] = mfc / tab.get(i)[tab.get(0).length - 1];
        }

        return probability;
    }

    // Funkcja umożliwiająca mutacje danego osobnika
    private int[] mutate(int[] array, int times) {
        for (int i = 0; i < times; i++) {
            int indeksPierwszy = (int) Math.round(Math.random() * (array.length - 1));
            int indeksDrugi = (int) Math.round(Math.random() * (array.length - 1));
                return swap(array, indeksPierwszy, indeksDrugi);
        }
        return array;
    }

    /**
     * Metoda wspomocnicza do zamiany elementów miejscami
     * @param array - tablica
     * @param first - indeks pierwszego elementu do zamiany
     * @param second - indeks drugiego elementu do zamiany
     */
    private int[] swap(int[] array, int first, int second) {
        int temp = array[first];
        array[first] = array[second];
        array[second] = temp;
        return array;
    }

    public static void main(String[] args) {
        Main test = new Main();

        Scanner in = new Scanner(System.in);
        int population = in.nextInt();
        int rok = in.nextInt();
        int iteracji = in.nextInt();

        test.setup(population, rok, iteracji);
    }
}
