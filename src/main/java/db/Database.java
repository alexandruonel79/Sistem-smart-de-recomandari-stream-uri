package db;

import com.google.gson.JsonArray;
import stream.Streamer;
import streams.Stream;
import user.User;

import java.util.*;

//receiver
public class Database {
    private static Database database;
    private List<Stream> streams;
    private List<Streamer> streamers;
    private List<User> users;

    private Database(List<Stream> streams, List<Streamer> streamers, List<User> users) {
        this.streams = streams;
        this.streamers = streamers;
        this.users = users;
    }

    public static Database getInstance(List<Stream> streams, List<Streamer> streamers, List<User> users) {
        if (database == null)
            database = new Database(streams, streamers, users);
        return database;
    }

    public static Database getAcces() {
        return database;
    }

    public static void resetDatabase() {
        database = null;
    }

    public List<Stream> getStreams() {
        return streams;
    }

    public List<Streamer> getStreamers() {
        return streamers;
    }

    public List<User> getUsers() {
        return users;
    }

    public void addStream(Stream toAdd) {
        streams.add(toAdd);
    }

    public Streamer findStreamer(int id) {
        for (Streamer streamer : streamers) {
            if (streamer.getId() == id)
                return streamer;
        }
        return null;
    }

    public Stream findStream(int id) {
        for (Stream stream : streams) {
            if (stream.getId() == id)
                return stream;
        }
        return null;
    }

    public User findUser(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public void listStreams(int id) {
        JsonArray allStreams = new JsonArray();

         for(int i=streams.size()-1;i>=0;i--){
            if (streams.get(i).getStreamerId() == id) {
                String streamerName = findStreamer(id).getName();

                if (streamerName == null)
                    System.out.println("stream parentless");
                else
                    allStreams.add(streams.get(i).createJsonStream(streamerName));
            }
        }

        if (allStreams.size() != 0)
            System.out.println(allStreams);

    }

    public void listUserListeningHistory(int id) {
        User user = findUser(id);
        if (user != null) {
            user.listUserListeningHistory();
        }
    }

    public void deleteStream(int streamId, int streamerId) {
        for (int i = 0; i < streams.size(); i++) {
            if (streams.get(i).getId() == streamId) {
                if (streams.get(i).getStreamerId() == streamerId)//verificam daca e creatorul streamului
                    streams.remove(i);
            }
        }
    }
}
