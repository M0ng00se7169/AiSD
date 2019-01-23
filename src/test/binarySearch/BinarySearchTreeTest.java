package test.binarySearch;

import junit.framework.TestCase;
import main.binarySearch.BinarySearchTree;
import main.binarySearch.Node;
import main.sort.CompoundComparator;
import main.sort.NaturalComparator;

public class BinarySearchTreeTest extends TestCase {
    private Node a;
    private Node d;
    private Node f;
    private Node h;
    private Node i;
    private Node k;
    private Node l;
    private Node m;
    private Node p;
    private Node root;
    private BinarySearchTree tree;

    protected void setUp() throws Exception {
        super.setUp();

        this.a = new Node("A");
        this.h = new Node("H");
        this.k = new Node("K");
        this.p = new Node("P");
        this.f = new Node("F", null, h);
        this.m = new Node("M", null, p);
        this.d = new Node("D", a, f);
        this.l = new Node("L", k, m);
        this.i = new Node("I", d, l);
        root = i;

        tree = new BinarySearchTree(new CompoundComparator());

        tree.insert(i.getValue());
        tree.insert(d.getValue());
        tree.insert(l.getValue());
        tree.insert(a.getValue());
        tree.insert(f.getValue());
        tree.insert(k.getValue());
        tree.insert(m.getValue());
        tree.insert(h.getValue());
        tree.insert(p.getValue());
    }

    public void testInsert() {
        assertEquals(root, tree.getRoot());
    }

    public void testSearch() {
        assertEquals(a, tree.search(a.getValue()));
        assertEquals(d, tree.search(d.getValue()));
        assertEquals(f, tree.search(f.getValue()));
        assertEquals(h, tree.search(h.getValue()));
        assertEquals(i, tree.search(i.getValue()));
        assertEquals(k, tree.search(k.getValue()));
        assertEquals(l, tree.search(l.getValue()));
        assertEquals(m, tree.search(m.getValue()));
        assertEquals(p, tree.search(p.getValue()));

        assertNull(tree.search("NIEISTNIEJĄCY"));
    }

    public void testDeleteLeafNode() {
        Node deleted = tree.delete(h.getValue());

        assertNotNull(deleted);
        assertEquals(h.getValue(),deleted.getValue());

        f.setLarger(null);
        assertEquals(root, tree.getRoot());
    }

    public void testDeleteNodeWithOneChild() {
        Node deleted = tree.delete(m.getValue());
        assertNotNull(deleted);
        assertEquals(m.getValue(), deleted.getValue());

        l.setLarger(p);
        assertEquals(root, tree.getRoot());
    }

    public void testDeleteNodeWithTwoChildren() {
        Node deleted = tree.delete(i.getValue());
        assertNotNull(deleted);
        assertEquals(i.getValue(), deleted.getValue());

        i.setValue(k.getValue());
        l.setValue(null);
        assertEquals(root, tree.getRoot());
    }

    public void testDeleteRootNodeUntilTreeIsEmpty() {
        while (tree.getRoot() != null) {
            Object key = tree.getRoot().getValue();
            Node deleted = tree.delete(key);
            assertNotNull(deleted);
            assertEquals(key, deleted.getValue());
        }
    }
}
