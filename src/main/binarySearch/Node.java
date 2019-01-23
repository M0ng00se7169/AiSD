package main.binarySearch;

public class Node implements Cloneable {

    /** Wartość reprezentowana przez węzeł */
    private Object value;

    private Node parent;
    private Node smaller;
    private Node larger;

    public Node(Object value) {
        this(value, null, null);
    }

    public Node(Object value, Node smaller, Node larger) {
        setValue(value);
        setSmaller(smaller);
        setLarger(larger);

        if (smaller != null) {
            smaller.setParent(this);
        }

        if (larger != null) {
            larger.setParent(this);
        }
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        assert value != null : "Podano pustą wartość";
        this.value = value;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getSmaller() {
        return smaller;
    }

    public void setSmaller(Node smaller) {
        this.smaller = smaller;
    }

    public Node getLarger() {
        return larger;
    }

    public void setLarger(Node larger) {
        this.larger = larger;
    }

    public boolean isSmaller() {
        return getParent() != null && this == getParent().getSmaller();
    }

    public boolean isLarger() {
        return getParent() != null && this == getParent().getLarger();
    }

    public Node minimum() {
        Node node = this;

        while (node.getSmaller() != null) {
            node = node.getSmaller();
        }

        return node;
    }

    public Node maximum() {
        Node node = this;

        while (node.getLarger() != null) {
            node = node.getLarger();
        }

        return node;
    }

    public Node successor() {
        if (getLarger() != null) {
            return getLarger().minimum();
        }

        Node node = this;

        while (node.isLarger()) {
            node = node.getParent();
        }

        return node.getParent();
    }

    public Node predecessor() {
        if (getSmaller() != null) {
            return getSmaller().maximum();
        }

        Node node = this;

        while (node.isSmaller()) {
            node = node.getParent();
        }

        return node.getParent();
    }

    public int size() {
        return size(this);
    }

    public int height() {
        return height(this) - 1;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || object.getClass() != getClass()) {
            return false;
        }

        Node other = (Node) object;

        return getValue().equals(other.getValue())
                && equalsSmaller(other.getSmaller())
                && equalsLarger(other.getLarger());
    }

    private int size(Node node) {
        if (node == null)
            return 0;
        return 1 + size(node.getSmaller()) + size(node.getLarger());
    }

    private int height(Node node) {
        if (node == null)
            return 0;
        return height(node.successor());
    }

    private boolean equalsLarger(Node larger) {
        return getLarger() == null
                && larger == null || getLarger() != null
                && getLarger().equals(larger);
    }

    private boolean equalsSmaller(Node smaller) {
        return getSmaller() == null
                && smaller == null || getSmaller() != null
                && getSmaller().equals(smaller);
    }
}
