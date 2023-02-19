package streams.streamType.streamGenre.podcasturi;

import streams.streamType.Podcast;

public class Documentary extends Podcast {
    public Documentary(Integer id,Long noOfStreams,Integer streamerId,Long length,Long dateAdded,String name) {
        super(id,noOfStreams,streamerId,length,dateAdded,name);
    }
}
