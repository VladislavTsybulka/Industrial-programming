import java.util.ArrayList;
import java.util.List;

public class UndoableStringBuilder {
    private StringBuilder stringBuilder;
    private List<Command> commandHistory;

    public UndoableStringBuilder() {
        stringBuilder = new StringBuilder();
        commandHistory = new ArrayList<>();
    }

    public void append(String str) {
        stringBuilder.append(str);
        commandHistory.add(new AppendCommand(str));
    }

    public void delete(int start, int end) {
        String deleted = stringBuilder.substring(start, end);
        stringBuilder.delete(start, end);
        commandHistory.add(new DeleteCommand(start, deleted));
    }

    public void undo() {
        if (!commandHistory.isEmpty()) {
            Command lastCommand = commandHistory.remove(commandHistory.size() - 1);
            lastCommand.undo();
        }
    }

    public String toString() {
        return stringBuilder.toString();
    }

    private interface Command {
        void undo();
    }

    private class AppendCommand implements Command {
        private String str;

        AppendCommand(String str) {
            this.str = str;
        }

        @Override
        public void undo() {
            int length = stringBuilder.length();
            stringBuilder.delete(length - str.length(), length);
        }
    }

    private class DeleteCommand implements Command {
        private int start;
        private String deleted;

        DeleteCommand(int start, String deleted) {
            this.start = start;
            this.deleted = deleted;
        }

        @Override
        public void undo() {
            stringBuilder.insert(start, deleted);
        }
    }
}
