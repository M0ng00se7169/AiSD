package main.sort;

import main.iteration.Iterator;
import main.lists.ArrayList;
import main.lists.List;

public class CompoundComparator implements Comparator {
    /** Lista komparatorów cząstkowych */
    private final List comparators = new ArrayList();

    public void addComparator(Comparator comparator) {
        assert comparator != null : "nie określono komparatora";
        assert comparator != this : "komparator nie może być własnym komparatorem cząstkowym";

        comparators.add(comparator);
    }

    public int compare(Object left, Object right) {
        int result = 0;
        Iterator iterator = comparators.iterator();

        for (iterator.first(); !iterator.isDone(); iterator.next()) {
            result = ((Comparator) iterator.current()).compare(left, right);
            if (result != 0) {
                break;
            }
        }

        return result;
    }
}
