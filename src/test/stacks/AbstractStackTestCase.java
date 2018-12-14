package test.stacks;

import junit.framework.TestCase;
import main.stacks.Stack;

public abstract class AbstractStackTestCase extends TestCase {
    protected static final Object VALUE_A = "A";
    protected static final Object VALUE_B = "B";
    protected static final Object VALUE_C = "C";

    protected abstract Stack createStack();
}
