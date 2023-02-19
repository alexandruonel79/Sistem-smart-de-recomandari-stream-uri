package streams.streamType;

import streams.Stream;

public class AudioBook extends Stream {
    public AudioBook(Integer id,Long noOfStreams,Integer streamerId,Long length,Long dateAdded,String name) {
        setId(id);
        setNoOfStreams(noOfStreams);
        setStreamerId(streamerId);
        setLength(length);
        setDateAdded(dateAdded);
        setName(name);
    }
}
