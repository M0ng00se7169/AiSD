package main.sort;

import main.lists.List;

public class SelectionSortListSorter implements ListSorter{

    private final Comparator comparator;

    public SelectionSortListSorter(Comparator comparator) {
        assert comparator != null : "nie określono komparatora";
        this.comparator = comparator;
    }

    @Override
    public List sort(List list) {
        assert list != null : "nie określono listy";

        int size = list.size();

        for (int i = 0; i < size - 1; ++i) {
            int smallest = i;
            for (int check = i + 1; check < size; ++check) {
                if (comparator.compare(list.get(check), list.get(smallest)) > 0) {
                    smallest = check;
                }
            }
            swap(list, smallest, i);
        }

        return list;
    }

    private void swap(List list, int smallest, int i) {
        if (smallest == i) {
            return;
        }
        Object temp = list.get(smallest);
        list.set(smallest, list.get(i));
        list.set(i, temp);
    }
}
