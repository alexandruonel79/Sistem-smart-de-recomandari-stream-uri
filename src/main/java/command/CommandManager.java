package command;

import java.util.List;

public class CommandManager {
    private List<Command> commands;

    public CommandManager(List<Command> commands) {
        this.commands = commands;
    }

    public void execCommand() {
        if (!commands.isEmpty()) {
            Command actual = commands.get(0);
            actual.execute();
            commands.remove(0);
        } else
            System.out.println("Nu sunt comenzi de executat");
    }

    public void execAllCommands() {
        for (Command command : commands) {
            command.execute();
        }
    }

    public void cancelCommand() {
        if (!commands.isEmpty())
            commands.remove(commands.size() - 1);
        else
            System.out.println("Nu sunt comenzi");
    }

    public void showHistory() {
        for (Command command : commands) {
            if (command instanceof DeleteStream) {
                System.out.println("DeleteStream");
            } else if (command instanceof ListenStream) {
                System.out.println("ListenStream");
            } else if (command instanceof ListStreams) {
                System.out.println("ListStreams");
            } else if (command instanceof ListUserListeningHistory) {
                System.out.println("ListUserListeningHistory");
            } else if (command instanceof RecommendStreams) {
                System.out.println("RecommendStreams");
            } else if (command instanceof RecommendSurprise) {
                System.out.println("RecommendSurprise");
            }
        }
    }
}
