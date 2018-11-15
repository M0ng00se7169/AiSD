package main.iteration;
import java.io.File;

public class RecursiveDirectoryTreePrinter {
    private static final String SPACES = " ";

    public static void main(String[] args) {
        assert args != null : "nie podano nazwy katalogu! Sprobuj ponownie";

//        if (args.length != 2) {
//            System.err.println("Wywolanie: RecursiveDirectoryTreePrinter <katalog>");
//            System.exit(-1);
//        }
        try {
            print(new File("src"), " ");
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

    }

    private static void print(File file, String indent) {
        assert file != null : "nie okreslono obiektu File";
        assert indent != null : "nie okreslono wciecia";

        System.out.println(indent);
        System.out.println(file.getName());

        if (file.isDirectory()) {
            print(new ArrayIterator(file.listFiles()), indent + SPACES);
        }
    }

    private static void print(Iterator files, String indent) {
        assert files != null : "Nie okreslono listy plikow";

        for (files.first(); !files.isDone(); files.next()) {
            print((File) files.current(), indent);
        }
    }
}
