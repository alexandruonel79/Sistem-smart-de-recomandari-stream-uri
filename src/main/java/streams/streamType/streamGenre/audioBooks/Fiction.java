package streams.streamType.streamGenre.audioBooks;

import streams.streamType.AudioBook;

public class Fiction extends AudioBook {
    public Fiction(Integer id,Long noOfStreams,Integer streamerId,Long length,Long dateAdded,String name) {
        super(id,noOfStreams,streamerId,length,dateAdded,name);
    }
}
