package main.binarySearch;

import main.lists.List;

public class ListInserter {

    /** Pomocnicza wyszukiwarka elementów */
    private final ListSearcher searcher;

    /**
     * Konstruktor
     * @param searcher - Pomocnicza wyszukiwarka elementów
     */
    public ListInserter(ListSearcher searcher) {
        assert searcher != null : "Nie określono wyszukiwarki";
        this.searcher = searcher;
    }

    /**
     * Wstawianie wartości do listy z zachowaniem jej posortowania
     *
     * @param list - lista, do której wstawiane są elementy
     * @param value - wstawiana wartość
     *
     * @return - Pozycja, na którą element został wstawiony
     */
    public int insert(List list, Object value) {
        assert list != null : "Nie określono listy";

        int index = ((int) searcher.search(list, value));

        if (index < 0) {
            index = -(index + 1);
        }

        list.insert(index, value);

        return index;
    }
}
