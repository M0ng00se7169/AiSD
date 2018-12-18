package main.sort;

public class CallCountingComparator implements Comparator {
    private final Comparator comparator;
    private int callCount;

    /**
     * Konstruktor
     * @param comparator - Comparator
     */
    public CallCountingComparator(Comparator comparator) {
        assert comparator != null : "nie określono komparatora";

        this.comparator = comparator;
        this.callCount = 0;
    }

    @Override
    public int compare(Object left, Object right) throws ClassCastException {
        ++callCount;
        return comparator.compare(left, right);
    }

    public int getCallCount() {
        return callCount;
    }
}
