package main.sort;

public class ReverseComparator implements Comparator {
    private final Comparator comparator;

    public ReverseComparator(Comparator comparator) {
        assert comparator != null: "Nie okreslono oryginalnego komparatora";
        this.comparator = comparator;
    }

    @Override
    public int compare(Object left, Object right) throws ClassCastException {
        return comparator.compare(right, left);
    }
}
