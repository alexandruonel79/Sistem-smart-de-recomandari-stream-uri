package user;

import com.google.gson.JsonArray;
import db.Database;
import streams.Stream;

import java.util.List;

public class User {
    private int id;
    private String name;
    private List<Integer> streams;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getStreams() {
        return streams;
    }

    public User(int id, String name, List<Integer> streams) {
        this.id = id;
        this.name = name;
        this.streams = streams;
    }
    public void listUserListeningHistory(){
        Database acces=Database.getAcces();
        if(acces!=null){
            JsonArray allStreams = new JsonArray();

            for (Integer streamId : streams) {
                Stream stream = acces.findStream(streamId);
                String streamerName = acces.findStreamer(stream.getStreamerId()).getName();
                allStreams.add(stream.createJsonStream(streamerName));
            }

            if (allStreams.size() != 0)
                System.out.println(allStreams);
        }
    }
    public void ListenStream(int streamId) {
        Database access=Database.getAcces();
        if(access!=null){
            Stream stream=access.findStream(streamId);
            stream.listen();
        }
        this.getStreams().add(streamId);
    }

}
