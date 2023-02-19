package streams.Recommandations;

import com.google.gson.JsonArray;
import db.Database;
import streams.Stream;
import streams.StreamSortSurprise;
import streams.streamType.AudioBook;
import streams.streamType.PiesaMuzicala;
import streams.streamType.Podcast;
import user.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RSurprise {
    private Database database;

    public RSurprise(Database database) {
        this.database = database;
    }
    public List<Stream> nonListenedStreams(List<Integer> streamsIds, String streamType) {
        List<Stream> nonListened = new ArrayList<>();
        Set<Integer> bannedStreamers = new HashSet<>();

        for (Integer streamId : streamsIds) {
            Stream potential = database.findStream(streamId);
            switch (streamType) {
                case "SONG":
                    if (potential instanceof PiesaMuzicala) {
                        bannedStreamers.add(potential.getStreamerId());
                    }
                    break;
                case "PODCAST":
                    if (potential instanceof Podcast) {
                        bannedStreamers.add(potential.getStreamerId());
                    }
                    break;
                case "AUDIOBOOK":
                    if (potential instanceof AudioBook) {
                        bannedStreamers.add(potential.getStreamerId());
                    }
                    break;
            }
        }
        int count = 0;

        for (Stream stream : database.getStreams()) {
            if (!bannedStreamers.contains(stream.getStreamerId())) {
                nonListened.add(stream);
                count++;
            }
        }
        if (count > 0)
            return nonListened;
        else
            return Collections.emptyList();
    }

    public void recommendSurprise(int userId, String streamType) {
        User user = database.findUser(userId);
        List<Stream> sortedStreams = nonListenedStreams(user.getStreams(), streamType);
        sortedStreams.sort(new StreamSortSurprise());
        JsonArray matchingStreams = new JsonArray();
        for (int i = 0; i < 3 && sortedStreams.size() > i + 1; i++) {
            String streamerName = database.findStreamer(sortedStreams.get(i).getStreamerId()).getName();
            matchingStreams.add(sortedStreams.get(i).createJsonStream(streamerName));
        }
        System.out.println(matchingStreams);

    }
}
