import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;

public class Main {
    public static final String SMALL_FILE_PATH = "D:\\Programming\\Java Projects\\Zadanie1\\src\\z1data1.csv";
    public static final String BIG_FILE_PATH = "D:\\Programming\\Java Projects\\Zadanie1\\src\\z1data2.csv";

    public static ArrayList<Integer> list;

    public void readFileToList(String filename) throws IOException {
        File myCSVfile = new File(filename);
        list = new ArrayList<>();
        String line = "";
        String CSVsplitter = ",";
        int lineCounter = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(myCSVfile))) {
            while ((line = br.readLine()) != null) {
                String[] lineTokens = line.split(CSVsplitter);
                for (String singleToken : lineTokens) {
                    list.add(Integer.parseInt(singleToken));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("ArrayList Before sorting: ");

        for (Integer integer : list) {
            System.out.print(integer + " ");
            lineCounter++;
            if (lineCounter % 11 == 0) {
                System.out.println();
            }
        }

        System.out.println("\n\n" + "ArrayList After BubbleSort:");
    }

    public static void cocktailSort(ArrayList<Integer> list) {
        boolean swapped = true;
        int start = 0;
        int end = list.size();

        while (swapped) {
            swapped = false;
            for (int i = start; i < end - 1; ++i) {
                if (list.get(i) > list.get(i+1)) {
                    int temp = list.get(i);
                    list.set(i, list.get(i+1));
                    list.set(i+1, temp);
                    swapped = true;
                }
            }
            if (!swapped)
                break;

            swapped = false;
            end--;

            for (int i = end - 1; i >= start; i--) {
                if (list.get(i) > list.get(i+1)) {
                    int temp = list.get(i);
                    list.set(i, list.get(i+1));
                    list.set(i+1, temp);
                    swapped = true;
                }
            }
            start++;
        }
    }

    public static double mediana(ArrayList<Integer> list) {
        double mediana = 0, srednia = 0.0;
        cocktailSort(list);
        if (list.size() % 2 == 0) {
            srednia = list.get(list.size() / 2) + list.get((list.size() / 2) - 1);
            mediana = srednia / 2.0;
        } else {
            mediana = list.get(list.size() / 2);
        }
        System.out.println("\nMediana zbioru: " + mediana);

        return mediana;
    }

    public static void maxMin(ArrayList<Integer> list) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        ListIterator<Integer> itr = list.listIterator();

        while (itr.hasNext()) {
            int elem = itr.next();
            if (elem > max)
                max = elem;
            if (elem < min)
                min = elem;
        }

        System.out.println("Maksymalna wartosc zbioru: " + max);
        System.out.println("Minimalna wartosc zbioru: " + min);
    }

    public static int liczebnosc(ArrayList<Integer> list) {
        return list.size();
    }

    public static void printList(ArrayList<Integer> list) {
        int lineCounter = 0;
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
            lineCounter++;
            if (lineCounter % 10 == 0) {
                System.out.print("\n");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Main test = new Main();
        test.readFileToList(SMALL_FILE_PATH);
        cocktailSort(list);
        printList(list);
        System.out.println();
        mediana(list);
        maxMin(list);
        liczebnosc(list);
        System.out.println("\n");
        test.readFileToList(BIG_FILE_PATH);
        cocktailSort(list);
        printList(list);
        mediana(list);
        maxMin(list);
        liczebnosc(list);
    }
}
