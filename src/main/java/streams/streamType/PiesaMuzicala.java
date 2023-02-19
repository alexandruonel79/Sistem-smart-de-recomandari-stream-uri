package streams.streamType;

import streams.Stream;

public class PiesaMuzicala  extends Stream {
    public PiesaMuzicala(Integer id,Long noOfStreams,Integer streamerId,Long length,Long dateAdded,String name) {
        setId(id);
        setNoOfStreams(noOfStreams);
        setStreamerId(streamerId);
        setLength(length);
        setDateAdded(dateAdded);
        setName(name);
    }
}
