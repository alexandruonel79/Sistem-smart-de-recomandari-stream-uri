package command;

import db.Database;
import factory.FactoryStream;
import stream.Streamer;
import streams.Recommandations.RStreams;
import streams.Recommandations.RSurprise;
import streams.Stream;
import user.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ParseCommands {
    private String filePath = "src/main/resources/";
    private List<Streamer> streamers;
    private List<Stream> streams;
    private List<User> users;

    public ParseCommands(String filePath, List<Streamer> streamers, List<Stream> streams, List<User> users) {
        this.filePath = this.filePath + filePath;
        this.streamers = streamers;
        this.streams = streams;
        this.users = users;
    }

    public List<Command> parse() {
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            Database.resetDatabase();
            Database database = Database.getInstance(streams, streamers, users);
            List<Command> commands = new ArrayList<>();

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if ((data.split(" ")[1]).equals("LIST")) {
                    int id = Integer.parseInt(data.split(" ")[0]);
                    Command listStreams = new ListStreams(database, id);
                    listStreams.execute();
                    Command listUserListeningHistory = new ListUserListeningHistory(database, id);
                    listUserListeningHistory.execute();
                    commands.add(listStreams);
                    commands.add(listUserListeningHistory);
                } else if ((data.split(" ")[1]).equals("ADD")) {

                    FactoryStream factoryStream = FactoryStream.getInstance();
                    String[] info = new String[8];

                    String streamerId = data.split(" ")[0];
                    String streamType = data.split(" ")[2];
                    String id = data.split(" ")[3];
                    String streamGenre = data.split(" ")[4];
                    String length = data.split(" ")[5];
                    String name = "";

                    for (int i = 6; i < data.split(" ").length - 1; i++)
                        name += data.split(" ")[i] + " ";

                    name += data.split(" ")[data.split(" ").length - 1];

                    info[0] = streamType;
                    info[1] = id;
                    info[2] = streamGenre;
                    info[3] = "0";
                    info[4] = streamerId;
                    info[5] = length;
                    LocalDateTime now = LocalDateTime.now();
                    Date date = Date.from(now.toInstant(ZoneOffset.UTC));
                    String timestamp = Long.toString((date.getTime() / 1000));
                    info[6] = timestamp;
                    info[7] = name;
                    Stream stream = factoryStream.createStream(info);
                    Command addStream = new AddStream(database, stream);
                    addStream.execute();
                    commands.add(addStream);
                } else if ((data.split(" ")[1]).equals("DELETE")) {
                    int streamerId = Integer.parseInt(data.split(" ")[0]);
                    int streamId = Integer.parseInt(data.split(" ")[2]);
                    Command deleteStream = new DeleteStream(database, streamId, streamerId);
                    deleteStream.execute();
                    commands.add(deleteStream);
                } else if ((data.split(" ")[1]).equals("LISTEN")) {
                    int userId = Integer.parseInt(data.split(" ")[0]);
                    int streamId = Integer.parseInt(data.split(" ")[2]);
                    Command listenStream = new ListenStream(database.findUser(userId), streamId);
                    listenStream.execute();
                    commands.add(listenStream);
                } else if ((data.split(" ")[1]).equals("RECOMMEND")) {
                    int userId = Integer.parseInt(data.split(" ")[0]);
                    String streamType = data.split(" ")[2];
                    Command recommendStreams = new RecommendStreams(new RStreams(database), userId, streamType);
                    recommendStreams.execute();
                    commands.add(recommendStreams);
                } else if ((data.split(" ")[1]).equals("SURPRISE")) {
                    int userId = Integer.parseInt(data.split(" ")[0]);
                    String streamType = data.split(" ")[2];
                    Command recommendSurprise = new RecommendSurprise(new RSurprise(database), userId, streamType);
                    recommendSurprise.execute();
                    commands.add(recommendSurprise);
                }

            }
            myReader.close();
            return commands;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}