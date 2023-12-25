import org.junit.Test;
import static org.junit.Assert.*;

public class UndoableStringBuilderTest {
    @Test
    public void testAppendAndUndo() {
        UndoableStringBuilder builder = new UndoableStringBuilder();
        builder.append("Hello");
        builder.append(" World");
        assertEquals("Hello World", builder.toString());

        builder.undo();
        assertEquals("Hello", builder.toString());

        builder.undo();
        assertEquals("", builder.toString());
    }

    @Test
    public void testDeleteAndUndo() {
        UndoableStringBuilder builder = new UndoableStringBuilder();
        builder.append("Hello World");
        builder.delete(6, 11);
        assertEquals("Hello ", builder.toString());

        builder.undo();
        assertEquals("Hello World", builder.toString());

        builder.undo();
        assertEquals("", builder.toString());
    }
}
