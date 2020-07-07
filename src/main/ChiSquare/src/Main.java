import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final String Z4_DATA_1 = "D:\\Programming\\Java Projects\\Zadanie4\\src\\z4data1.csv";
    public static final String Z4_DATA_2 = "D:\\Programming\\Java Projects\\Zadanie4\\src\\z4data2.csv";
    public static final String Z4_DATA_3 = "D:\\Programming\\Java Projects\\Zadanie4\\src\\z4data3.csv";
    public static final String Z4_DATA_4 = "D:\\Programming\\Java Projects\\Zadanie4\\src\\z4data4.csv";

    public String[][] strings;

    public int[][] create2DIntMatrixFromFile(String filename){
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

    public double sumCalculations(int[][] tab) {
        int rowDim = strings.length + 1;
        int colDim = strings[0].length + 1;

        String[][] newTab = new String[rowDim][colDim];
        for (int i = 0; i < rowDim; i++){
            for (int j = 0; j < colDim; j++) {
                newTab[i][j] = " ";
            }
        }

        int[] rowSum = new int[tab.length];     // Tablica zawierająca sume wierszy
        int[] colSum = new int[tab[0].length];  // Tablica zawierająca sume kolumn
        int total = 0;                          // Suma wszyskich elementów

        // Obliczam sume czastkowe i całe
        for (int row = 0; row < tab.length; row++) {
            for (int col = 0; col < tab[0].length; col++) {
                rowSum[row] += tab[row][col];
                colSum[col] += tab[row][col];
                total += tab[row][col];
            }
        }

        double[][] expected = new double[tab.length][tab[0].length];
        double chiKwadrat = 0.0d;
        // Obliczam wartości oczekiwane oraz wartość Chi Kwadrat;
        for (int row = 0; row < tab.length; row++) {
            for (int col = 0; col < tab[0].length; col++) {
                // Wzór na wyliczenie wartości oczekiwanej
                expected[row][col] = Math.round((double) rowSum[row] * colSum[col] / total * 100.0) / 100.0;
                // Wartość CHI KWADRAT
                chiKwadrat += Math.pow(tab[row][col] - expected[row][col], 2) / expected[row][col];
            }
        }
        // Wypełniam nową tablice z sumami wartościami z pliku
        for (int i = 0; i < strings.length; i++) {
            System.arraycopy(strings[i], 0, newTab[i], 0, strings[i].length);
        }
        System.out.println("Tablica O wraz z sumami cząstkowymi:");
        // Wydruk czytelny tablicy O wraz z sumami cząstkowymi
        newTab[0][strings[0].length] = "Suma";
        newTab[strings.length][0] = "Suma";
        newTab[strings.length][strings[0].length] = Integer.toString(total);
        // Dodaje kolumne do tablicy zawierającą sume wierszow
        for (int i = 0; i < rowSum.length; i++){
            newTab[1+i][strings[0].length] = Integer.toString(rowSum[i]);
        }
        // Dodaje wierz do tablicy zawierającą sume kolumn
        for (int i = 0; i < colSum.length; i++){
            newTab[strings.length][1+i] = Integer.toString(colSum[i]);
        }
        printMatrix(newTab);

        // Wydruk wartości oczekiwanych
        for (int i = 0; i < expected.length; i++){
            for (int j = 0; j < expected[0].length; j++) {
                strings[1+i][1+j] = Double.toString(expected[i][j]); // Wypełniam tablice wartosciami oczekiwanymi
            }
        }
        System.out.println("\n" + "Tablica z wartościami oczekiwanymi E:");
        printMatrix(strings);  // Wydruk tablicy wartości oczekiwanych
        return Math.round(chiKwadrat * 100.0) / 100.0;
    }

    public double probability(double chi, int level) {
        if (chi > 1000 || level > 1000) {
            double q = norm((Math.pow(chi/level, (double) 1/3)+(double)2/(9*level)-1)/Math.sqrt((double)2/(9*level)))/2;
            if (chi > level) {
                return  q;
            }
            return 1 - q;
        }
        double p = Math.exp(-0.5*chi);
        if ((level % 2) == 1) {
            p *= Math.sqrt(2*chi/Math.PI);
        }
        double k = level;
        while (k >= 2) {
            p = p * chi / k;
            k -= 2;
        }
        double t = p, a = level;
        while (t > 1e-15 * p) {
            a += 2;
            t = t * chi / a;
            p += t;
        }
        return 1 - p;
    }

    /**
     * Funkcja pomocnicza dla funkcji probability
     * @return -
     */
    private double norm(double z) {
        double q = z * z;
        if (Math.abs(q) > 7)
            return (1-1/q+3/(q*q))*Math.exp(-q/2)/(Math.abs(z)*Math.sqrt(Math.PI/2));
        else
            return probability(q, 1);
    }

    public int levelFreedom(int[][] tab) {
        return (tab.length - 1) * (tab[0].length - 1);
    }

    private void printMatrix(String[][] tabs) {
        for (int i = 0; i < tabs.length; i++) {
            for (int j = 0; j < tabs[i].length; j++) {
                System.out.printf("%-15s", tabs[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Main test = new Main();
        int[][] tab = test.create2DIntMatrixFromFile(Z4_DATA_4);

        double chi = test.sumCalculations(tab);
        int level = test.levelFreedom(tab);
        double p = test.probability(chi, level);

        System.out.println("\nStatystyka chi kwadrat: " + chi);
        System.out.println("Stopni swobody: " + level);
        System.out.println("Wartość prawdopodobieństwa: " + p);
    }
}
