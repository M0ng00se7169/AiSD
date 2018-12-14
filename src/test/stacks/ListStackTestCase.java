package test.stacks;

import main.stacks.EmptyStackException;
import main.stacks.ListStack;
import main.stacks.Stack;

public class ListStackTestCase extends AbstractStackTestCase {

    @Override
    protected Stack createStack() {
        return new ListStack();
    }

    public void testPushAndPop() {
        Stack stack = createStack();

        stack.push(VALUE_B);
        stack.push(VALUE_A);
        stack.push(VALUE_C);

        assertEquals(3, stack.size());
        assertFalse(stack.isEmpty());

        assertSame(VALUE_C, stack.pop());
        assertEquals(2, stack.size());
        assertFalse(stack.isEmpty());

        assertSame(VALUE_A, stack.pop());
        assertEquals(1, stack.size());
        assertFalse(stack.isEmpty());

        assertSame(VALUE_B, stack.pop());
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
    }

    public void testCantPopFromAnEmptyStack() {
        Stack stack = createStack();

        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());

        try {
            stack.pop();
            fail(); // zachowanie nieoczekiwane
        } catch (EmptyStackException e) {
            // zachowanie oczekiwane
        }
    }

    public void testPeek() {
        Stack stack = createStack();

        stack.push(VALUE_C);
        stack.push(VALUE_A);
        assertEquals(2, stack.size());

        assertSame(VALUE_A, stack.peek());
        assertEquals(2, stack.size());
    }

    public void testCantPeekIntoAnEmptyStack() {
        Stack stack = createStack();

        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());

        try {
            stack.peek();
            fail(); // zachowanie nieoczekiwane
        } catch (EmptyStackException e) {
            // zachowaie oczekiwane
        }
    }

    public void testClear() {
        Stack stack = createStack();

        stack.push(VALUE_A);
        stack.push(VALUE_B);
        stack.push(VALUE_C);

        assertFalse(stack.isEmpty());

        stack.clear();

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());

        try {
            stack.pop();
            fail(); // zachowanie nieoczekiwane
        } catch (EmptyStackException e) {
            // zachowanie oczekiwane
        }
    }
}
