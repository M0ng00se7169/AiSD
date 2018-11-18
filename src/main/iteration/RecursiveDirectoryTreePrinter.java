package main.iteration;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class RecursiveDirectoryTreePrinter {
    private static final String SPACES = "---|";

    @Contract("null -> fail")
    public static void main(String[] args) {
        assert args != null : "nie podano nazwy katalogu! Sprobuj ponownie";

        try {
            print(new File("src"), "");
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    @Contract("_, null -> fail")
    private static void print(@NotNull File file, String indent) {
        assert indent != null : "nie okreslono wciecia";

        System.out.print(indent);
        System.out.println(file.getName());

        if (file.isDirectory()) {
            print(new ArrayIterator(file.listFiles()), indent + SPACES);
        }
    }

    private static void print(@NotNull Iterator files, String indent) {

        for (files.first(); !files.isDone(); files.next()) {
            print((File) files.current(), indent);
        }
    }
}
