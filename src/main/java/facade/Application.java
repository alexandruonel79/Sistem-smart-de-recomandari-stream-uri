package facade;

import command.CommandManager;
import command.ParseCommands;
import readFiles.FileReadTemplate;
import readFiles.ReadStreamers;
import readFiles.ReadStreams;
import readFiles.ReadUsers;
import stream.Streamer;
import streams.Stream;
import user.User;

import java.util.List;

public class Application {
    private final String[] args;
    private CommandManager commandManager;

    public Application(String[] args) {
        this.args = args;
    }

    public void run(){
        FileReadTemplate<Streamer> readStreamers = new ReadStreamers();
        List<Streamer> streamers = readStreamers.readFromFile("src/main/resources/"+args[0]);
        FileReadTemplate<Stream> readStreams = new ReadStreams();
        List<Stream> streams = readStreams.readFromFile("src/main/resources/"+args[1]);
        FileReadTemplate<User> readUsers=new ReadUsers();
        List<User> users=readUsers.readFromFile("src/main/resources/"+args[2]);

        ParseCommands parseCommands=new ParseCommands(args[3],streamers,streams,users);
        commandManager=new CommandManager(parseCommands.parse());
    }
    public void showHistory(){
        commandManager.showHistory();
    }
}
