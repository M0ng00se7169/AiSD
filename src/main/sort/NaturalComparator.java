package main.sort;

public class NaturalComparator implements Comparator {
    public static final NaturalComparator INSTANCE = new NaturalComparator();

    /**
     * Konstruktor prywatny, nie jest możliwe samodzielne tworzenie instancji
     */
    private NaturalComparator() {}



    @Override
    public int compare(Object left, Object right) throws ClassCastException {
        assert left != null : "nie określono lewego obiektu";
        return ((Comparable)left).compareTo(right);
    }
}
