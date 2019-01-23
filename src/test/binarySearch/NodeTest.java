package test.binarySearch;

import junit.framework.TestCase;
import main.binarySearch.Node;

public class NodeTest extends TestCase {
    private Node a;
    private Node d;
    private Node f;
    private Node h;
    private Node i;
    private Node k;
    private Node l;
    private Node m;
    private Node p;

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
    }

    public void testMinimum() {
        assertSame(a, a.minimum());
        assertSame(a, d.minimum());
        assertSame(f, f.minimum());
        assertSame(h, h.minimum());
        assertSame(a, i.minimum());
        assertSame(k, k.minimum());
        assertSame(k, l.minimum());
        assertSame(m, m.minimum());
        assertSame(p, p.minimum());
    }

    public void testMaximum() {
        assertSame(a, a.maximum());
        assertSame(h, d.maximum());
        assertSame(h, f.maximum());
        assertSame(h, h.maximum());
        assertSame(p, i.maximum());
        assertSame(k, k.maximum());
        assertSame(p, l.maximum());
        assertSame(p, m.maximum());
        assertSame(p, p.maximum());
    }

    public void testSuccessor() {
        assertSame(d, a.successor());
        assertSame(f, d.successor());
        assertSame(h, f.successor());
        assertSame(i, h.successor());
        assertSame(k, i.successor());
        assertSame(l, k.successor());
        assertSame(m, l.successor());
        assertSame(p, m.successor());
        assertNull(p.successor());
    }

    public void testPredecessor() {
        assertNull(a.predecessor());
        assertSame(a, d.predecessor());
        assertSame(d, f.predecessor());
        assertSame(f, h.predecessor());
        assertSame(h, i.predecessor());
        assertSame(i, k.predecessor());
        assertSame(k, l.predecessor());
        assertSame(l, m.predecessor());
        assertSame(m, p.predecessor());
    }

    public void testIsSmaller() {
        assertTrue(a.isSmaller());
        assertTrue(d.isSmaller());
        assertFalse(f.isSmaller());
        assertFalse(h.isSmaller());
        assertFalse(i.isSmaller());
        assertTrue(k.isSmaller());
        assertFalse(l.isSmaller());
        assertFalse(m.isSmaller());
        assertFalse(p.isSmaller());
    }

    public void testIsLarger() {
        assertFalse(a.isLarger());
        assertFalse(d.isLarger());
        assertTrue(f.isLarger());
        assertTrue(h.isLarger());
        assertFalse(i.isLarger());
        assertFalse(k.isLarger());
        assertTrue(l.isLarger());
        assertTrue(m.isLarger());
        assertTrue(p.isLarger());
    }

    public void testSize() {
        assertEquals(1, a.size());
        assertEquals(4, d.size());
        assertEquals(2, f.size());
        assertEquals(1, h.size());
        assertEquals(9, i.size());
        assertEquals(1, k.size());
        assertEquals(4, l.size());
        assertEquals(2, m.size());
        assertEquals(1, p.size());
    }

    public void testEquals() {
        this.a = new Node("A");
        this.h = new Node("H");
        this.k = new Node("K");
        this.p = new Node("P");
        this.f = new Node("F", null, h);
        this.m = new Node("M", null, p);
        this.d = new Node("D", a, f);
        this.l = new Node("L", k, m);
        this.i = new Node("I", d, l);

        assertEquals(a, a);
        assertEquals(d, d);
        assertEquals(f, f);
        assertEquals(h, h);
        assertEquals(i, i);
        assertEquals(k, k);
        assertEquals(l, l);
        assertEquals(m, m);
        assertEquals(p, p);

        assertFalse(i.equals(null));
        assertFalse(f.equals(d));
    }
}
