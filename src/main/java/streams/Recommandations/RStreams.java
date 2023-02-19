package streams.Recommandations;

import com.google.gson.JsonArray;
import db.Database;
import stream.Streamer;
import streams.Stream;
import streams.StreamSort;
import streams.streamType.AudioBook;
import streams.streamType.PiesaMuzicala;
import streams.streamType.Podcast;
import user.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RStreams {
    private Database database;

    public RStreams(Database database) {
        this.database = database;
    }
    public int noRepeatingStreams(List<Integer> streams, int id) {
        for (Integer streamId : streams) {
            if (streamId == id)
                return 0;
        }
        return 1;
    }
    public void sortStreams(Set<Stream> streams) {
        List<Stream> streamList = new ArrayList<>(streams);

        streamList.sort(new StreamSort());
        JsonArray jsonArray = new JsonArray();

        for (int i = 0; i < 5; i++) {
            if (streamList.size() > i) {
                String streamerName = database.findStreamer(streamList.get(i).getStreamerId()).getName();
                jsonArray.add(streamList.get(i).createJsonStream(streamerName));
            }
        }
        if (jsonArray.size() != 0)
            System.out.println(jsonArray);

    }

    public void recommend(int userId, String streamType) {
        User user = database.findUser(userId);
        Set<Stream> matchingStreams = new HashSet<>();

        for (Integer streamId : user.getStreams()) {
            Streamer streamer = database.findStreamer(database.findStream(streamId).getStreamerId());

            for (Stream stream : database.getStreams()) {
                switch (streamType) {
                    case "SONG":
                        if (stream.getStreamerId() == streamer.getId() && noRepeatingStreams(user.getStreams(), stream.getId()) == 1
                                && stream instanceof PiesaMuzicala) {
                            matchingStreams.add(stream);
                        }
                        break;
                    case "PODCAST":
                        if (stream.getStreamerId() == streamer.getId() && noRepeatingStreams(user.getStreams(), stream.getId()) == 1
                                && stream instanceof Podcast) {
                            matchingStreams.add(stream);
                        }
                        break;
                    case "AUDIOBOOK":
                        if (stream.getStreamerId() == streamer.getId() && noRepeatingStreams(user.getStreams(), stream.getId()) == 1
                                && stream instanceof AudioBook) {
                            matchingStreams.add(stream);
                        }
                        break;
                }

            }
        }
        sortStreams(matchingStreams);

    }
}
