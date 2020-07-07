import java.io.*;
import java.util.ArrayList;

public class Main {
    // Dokładne ścieżki do plików z datą formatu
    public static final String Z2_DATA_11 = "D:\\Programming\\Java Projects\\Zadanie2\\src\\z2data11.csv";
    public static final String Z2_DATA_12 = "D:\\Programming\\Java Projects\\Zadanie2\\src\\z2data12.csv";
    public static final String Z2_DATA_13 = "D:\\Programming\\Java Projects\\Zadanie2\\src\\z2data13.csv";
    public static final String Z2_DATA_21 = "D:\\Programming\\Java Projects\\Zadanie2\\src\\z2data21.csv";
    public static final String Z2_DATA_22 = "D:\\Programming\\Java Projects\\Zadanie2\\src\\z2data22.csv";
    public static final String Z2_DATA_23 = "D:\\Programming\\Java Projects\\Zadanie2\\src\\z2data23.csv";
    public static final String Z2_DATA_31 = "D:\\Programming\\Java Projects\\Zadanie2\\src\\z2data31.csv";
    public static final String Z2_DATA_32 = "D:\\Programming\\Java Projects\\Zadanie2\\src\\z2data32.csv";
    public static final String Z2_DATA_33 = "D:\\Programming\\Java Projects\\Zadanie2\\src\\z2data33.csv";

    public static ArrayList<Integer> listCocktail;   // Kopia listy dla sortowania metodą Cocktail Sort
    public static ArrayList<Integer> listComb;       // Kopia listy dla sortowania metodą Comb Sort

    public void readFileToList(String filename) {
        File myCSVfile = new File(filename);
        listCocktail = new ArrayList<>();   // Inicjolizowanie obiektu listy cocktail sort
        listComb = new ArrayList<>();       // Inicjolizowanie obiektu listy cocktail comb
        String line = "";
        String CSVsplitter = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(myCSVfile))) {
            while ((line = br.readLine()) != null) {
                String[] lineTokens = line.split(CSVsplitter);
                for (String singleToken : lineTokens) {
                    listCocktail.add(Integer.parseInt(singleToken));  // Uzupełnianie listy liczbami z pliku
                    listComb.add(Integer.parseInt(singleToken));      // Uzupełnianie listy liczbami z pliku
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda sortująca nazywana Cocktail Sort
     * @param list - zbior liczb całkowitych do posortowania
     */
    public static void cocktailSort(ArrayList<Integer> list) {
        boolean swapped = true;
        int start = 0;
        int end = list.size();
        int swapCount = 0;   // Licznik zamian elementów listy miejscami
        int comparesCount = 0; // Licznik porównań elementów między sobą

        while (swapped) {
            swapped = false;
            for (int i = start; i < end - 1; ++i) {
                // Za każdym przejściem musimy porównywać sąsiednie elementy listy
                // Więc licznik będzie wzrastał o 1 z każdą nową iteracją
                comparesCount++;
                if (list.get(i) > list.get(i+1)) {
                    swap(list, i);
                    swapped = true;
                    // Zwiększamy licznik zamiany gdy zamiana miała miejsce być
                    swapCount++;
                }
            }
            if (!swapped)
                break;

            swapped = false;
            end--;

            for (int i = end - 1; i >= start; i--) {
                // Za każdym przejściem musimy porównywać sąsiednie elementy listy
                // Więc licznik będzie wzrastał o 1 z każdą nową iteracją
                comparesCount++;
                if (list.get(i) > list.get(i+1)) {
                    swap(list, i);
                    // Zwiększamy licznik zamiany gdy zamiana miała miejsce być
                    swapCount++;
                    swapped = true;
                }
            }
            start++;
        }

        System.out.format("%-20d%-20d%2c", comparesCount, swapCount, '\n');
    }

    /**
     * Metoda sortująca nazywana Cocktail Sort
     * @param list: zbior liczb całkowitych do posortowania
     */
    public static void combSort(ArrayList<Integer> list) {
        int n = list.size();
        // Inicjalizacja rozpiętości
        int gap = n;
        int swapCount = 0;   // Licznik zamian elementów listy miejscami
        int comparesCount = 0; // Licznik porównań elementów między sobą
        boolean swapped = true;
        // Pętla wykonuje się dopóki rozpiętość > 1 i ostatnia iteracja wyzwała zamianę
        while (gap != 1 || swapped) {
            // Ustalenie następnej rozpiętości
            gap = getNextGap(gap);
            // Ustawiam swapped jako false, aby sprawdzić czy nadeszła zamiana czy nie
            swapped = false;

            for (int i = 0; i < n - gap; i++) {
                comparesCount++;    // z każdą iteracją ilość porównań zwiększam o 1
                if (list.get(i) > list.get(i+gap)) {
                    swapCount++;    // jak zacodzi zamiana miejscami, zwiększam o 1
                    swap(list, i);  // funkcja pomocnicza swap
                    swapped = true; // Zmieniam wartość logiczną zamiany na true
                }
            }
        }
        System.out.format("%-20d%-20d%2c", comparesCount, swapCount, '\n');
    }

    /**
     * Prywatna metoda pomocnicza do sortowania Comb Sort
     * @param gap - Rozpiętość; w kolejnych iteracjach pętli jest dzilona przez 1.3
     * @return - zwraca rozpiętość.
     */
    private static int getNextGap(int gap) {
        gap = (gap * 10) / 13;
        if (gap < 1)
            return 1;
        return gap;
    }

    /**
     * Prywatna metoda dla zamiany elementów listy miejscami
     * @param list - Lista intów
     * @param i - indeks elementu w liscie
     */
    private static void swap(ArrayList<Integer> list, int i) {
        int temp = list.get(i);
        list.set(i, list.get(i+1));
        list.set(i+1, temp);
    }

    /**
     * Metoda zwracająca liczebność odpowiedniego zbioru
     * @param list: Zbiór liczb całkowitych
     * @return list.size()
     */
    public static int liczebnosc(ArrayList<Integer> list) {
        return list.size();
    }

    public static void main(String[] args) {
        Main test = new Main();
        try {
            PrintStream fileOut = new PrintStream("./src/wynik.txt");
            System.setOut(fileOut);  // Przypisuję wyjście standartowe do wyjścia pliku 'wynik.txt'
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.format("%-20s%-20s%-20s%-20s%-20s", "Nazwa pliku", "Liczebność", "Metoda", "Liczba porównań", "Liczba zamian");
        System.out.println();
        test.readFileToList(Z2_DATA_11);
        System.out.format("%-20s%-20d%-20s","Z2_DATA_11", liczebnosc(listCocktail), "Cocktail Sort");
        cocktailSort(listCocktail);
        System.out.format("%-20s%-20d%-20s","Z2_DATA_11", liczebnosc(listComb), "Comb Sort");
        combSort(listComb);
        test.readFileToList(Z2_DATA_12);
        System.out.format("%-20s%-20d%-20s","Z2_DATA_12", liczebnosc(listCocktail), "Cocktail Sort");
        cocktailSort(listCocktail);
        System.out.format("%-20s%-20d%-20s","Z2_DATA_12", liczebnosc(listComb), "Comb Sort");
        combSort(listComb);
        test.readFileToList(Z2_DATA_13);
        System.out.format("%-20s%-20d%-20s","Z2_DATA_13", liczebnosc(listCocktail), "Cocktail Sort");
        cocktailSort(listCocktail);
        System.out.format("%-20s%-20d%-20s","Z2_DATA_13", liczebnosc(listComb), "Comb Sort");
        combSort(listComb);
        test.readFileToList(Z2_DATA_21);
        System.out.format("%-20s%-20d%-20s","Z2_DATA_21", liczebnosc(listCocktail), "Cocktail Sort");
        cocktailSort(listCocktail);
        System.out.format("%-20s%-20d%-20s","Z2_DATA_21", liczebnosc(listComb), "Comb Sort");
        combSort(listComb);
        test.readFileToList(Z2_DATA_22);
        System.out.format("%-20s%-20d%-20s","Z2_DATA_22", liczebnosc(listCocktail), "Cocktail Sort");
        cocktailSort(listCocktail);
        System.out.format("%-20s%-20d%-20s","Z2_DATA_22", liczebnosc(listComb), "Comb Sort");
        combSort(listComb);
        test.readFileToList(Z2_DATA_23);
        System.out.format("%-20s%-20d%-20s","Z2_DATA_23", liczebnosc(listCocktail), "Cocktail Sort");
        cocktailSort(listCocktail);
        System.out.format("%-20s%-20d%-20s","Z2_DATA_23", liczebnosc(listComb), "Comb Sort");
        combSort(listComb);
        test.readFileToList(Z2_DATA_31);
        System.out.format("%-20s%-20d%-20s","Z2_DATA_31", liczebnosc(listCocktail), "Cocktail Sort");
        cocktailSort(listCocktail);
        System.out.format("%-20s%-20d%-20s","Z2_DATA_31", liczebnosc(listComb), "Comb Sort");
        combSort(listComb);
        test.readFileToList(Z2_DATA_32);
        System.out.format("%-20s%-20d%-20s","Z2_DATA_32", liczebnosc(listCocktail), "Cocktail Sort");
        cocktailSort(listCocktail);
        System.out.format("%-20s%-20d%-20s","Z2_DATA_32", liczebnosc(listComb), "Comb Sort");
        combSort(listComb);
        test.readFileToList(Z2_DATA_33);
        System.out.format("%-20s%-20d%-20s","Z2_DATA_33", liczebnosc(listCocktail), "Cocktail Sort");
        cocktailSort(listCocktail);
        System.out.format("%-20s%-20d%-20s","Z2_DATA_33", liczebnosc(listComb), "Comb Sort");
        combSort(listComb);
        // Przypisuję wyjście standartowe z powrotem
        System.setOut(System.out);
    }
}

