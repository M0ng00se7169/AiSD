import java.io.*;
import java.util.*;

public class Main {
    // Scieżka do pliku z danymi
    public static final String Z3_DATA_1 = "D:\\Programming\\Java Projects\\Zadanie3\\src\\z3data1.txt";

    public static ArrayList<String> lista;
    public static Element[] tablica;            // Tablica Obiektów
    public static String[] wyrazy;

    /**
     * Czyta dane z pliku do tablicy obiektów
     * każdy z których jest postaci {Wyraz, Miejsce wystąpienia, Hash Kod}
     * @param filename - File path
     */
    public void readFileToList(String filename) {
        File myCSVfile = new File(filename);
        lista = new ArrayList<>();
        String line = "";
        String CSVsplitter = " ";

        try (BufferedReader br = new BufferedReader(new FileReader(myCSVfile))) {
            while ((line = br.readLine()) != null) {
                String[] lineTokens = line.split(CSVsplitter);
                Collections.addAll(lista, lineTokens);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        wyrazy = lista.toArray(String[]::new);
        tablica = new Element[wyrazy.length];

        // Wypelniam tablice elementów wyrazami, miejscami
        // wystąpienia i funkcją haszująca
        for (int i = 0; i < wyrazy.length; i++){
            Element e = new Element(wyrazy[i], 1+i, wyrazy[i].hashCode());
            tablica[i] = e;
        }
    }

    /**
     * Metoda sortująca nazywana Cocktail Sort
     * @param list - zbior liczb całkowitych do posortowania
     */
    public static void cocktailSort(Element[] list) {
        boolean swapped = true;
        int start = 0;
        int end = list.length;
        int swapCount = 0;   // Licznik zamian elementów listy miejscami
        int comparesCount = 0; // Licznik porównań elementów między sobą

        while (swapped) {
            swapped = false;
            for (int i = start; i < end - 1; ++i) {
                // Za każdym przejściem musimy porównywać sąsiednie elementy listy
                // Więc licznik będzie wzrastał o 1 z każdą nową iteracją
                comparesCount++;
                if (list[i].getHashCode() > list[i+1].getHashCode()) {
                    Element temp = list[i];
                    list[i] = list[i+1];
                    list[i+1] = temp;
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
                if (list[i].getHashCode() > list[i+1].getHashCode()) {
                    Element temp = list[i];
                    list[i] = list[i+1];
                    list[i+1] = temp;
                    // Zwiększamy licznik zamiany gdy zamiana miała miejsce być
                    swapCount++;
                    swapped = true;
                }
            }
            start++;
        }

        //System.out.format("%-20d%-20d%2c", comparesCount, swapCount, '\n');
    }

    /**
     * Metoda wyszukiwania sekwencyjnego wyrazu w tekscie
     * @param wyraz - Klucz szukania wyrazu w tekscie.
     *
     * Zwraca miejsce znajdowania pierwszego wystąpienia
     * wyrazu w tekscie lub 0 w przypadku nie znalezienia.
     */
    public static void sequenceSearch(String wyraz) {
        int countCompares = 0;
        int key = 0;
        boolean contains = false;
        for (Element e : tablica) {
            if (e.getWyraz().equals(wyraz)) {
                key = e.key;
                countCompares = e.key;
                contains = true;
                break;
            }
        }
        if (!contains) {
            System.out.format("%-30s%-16d%-2s", "0", tablica.length, "\n");
        } else {
            System.out.format("%-30d%-16d%-2s", key, countCompares, "\n");
        }
    }

    public static void searchBinary(String value) {
        int count = (int) Math.ceil((Math.log(tablica.length)/Math.log(2)));  // Ilosc porownan O = log2(size tab)
        // Sortujemy liste metoda cocktailSort po hashCode
        cocktailSort(tablica);
        int res = binarySearch(tablica, value, 0, tablica.length - 1);
        if (res < 0) {
            System.out.format("%-30s%-16d%-2s", "0", count, "\n");    // Nie znaleziono w liscie
        } else
            System.out.format("%-30s%-16d%-2s", res, count, "\n");
    }


    private static int binarySearch(Element[] tab, String value, int lower, int upper) {
        if (lower > upper) return -(lower + 1);
        int index = (lower + upper) / 2;
        if (value.hashCode() < tab[index].getHashCode()) {
            return binarySearch(tab, value, lower, upper - 1);
        }
        if (value.hashCode() > tab[index].getHashCode()) {
            return binarySearch(tab, value, lower + 1, upper);
        }
        return tab[index].getKey();
    }

    public static void main(String[] args) {
        Main test = new Main();
        try {
            PrintStream fileOut = new PrintStream("./src/wynik.txt");
            System.setOut(fileOut);  // Przypisuję wyjście standartowe do wyjścia pliku 'wynik.txt'
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        test.readFileToList(Z3_DATA_1);
        System.out.format("%-37s%-30s%-30s%-16s", "Nazwa metody", "Nazwa wyrazu", "Miejsce w wyrazie", "Liczba porównań\n");
        System.out.format("%-37s%-30s", "Sekwencyjna", "mars");
        sequenceSearch("mars");
        System.out.format("%-36s%-30s", "Binarne", "mars");
        searchBinary("mars");
        System.out.format("%-36s%-30s", "Sekwencyjna", "ogilvy");
        sequenceSearch("ogilvy");
        System.out.format("%-36s%-30s", "Binarne", "ogilvy");
        searchBinary("ogilvy");
        System.out.format("%-36s%-30s", "Sekwencyjna", "sky");
        sequenceSearch("sky");
        System.out.format("%-36s%-30s", "Binarne", "sky");
        searchBinary("mars");
        System.out.format("%-36s%-30s", "Sekwencyjna", "meteor");
        sequenceSearch("meteor");
        System.out.format("%-36s%-30s", "Binarne", "meteor");
        searchBinary("meteor");
        // Przypisuję wyjście standartowe z powrotem
        System.setOut(System.out);
    }

    // Obiekt do tablicy obiektów struktury danych
    static class Element {
        private String wyraz;
        private int key;
        private int hashCode;

        public Element(String wyraz, int key, int hashCode) {
            this.wyraz = wyraz;
            this.key = key;
            this.hashCode = hashCode;
        }

        public String getWyraz() {
            return wyraz;
        }

        public int getKey() {
            return key;
        }

        public int getHashCode() {
            return hashCode;
        }

        @Override
        public String toString() {
            return "Element{" +
                    "wyraz='" + wyraz + '\'' +
                    ", key=" + key +
                    ", hashCode=" + hashCode +
                    '}';
        }
    }
}
