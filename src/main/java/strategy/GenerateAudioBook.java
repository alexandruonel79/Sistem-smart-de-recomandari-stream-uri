package strategy;

import streams.streamType.AudioBook;
import streams.streamType.streamGenre.audioBooks.Children;
import streams.streamType.streamGenre.audioBooks.Fiction;
import streams.streamType.streamGenre.audioBooks.PersonalDevelopment;

public class GenerateAudioBook implements Generate<AudioBook>{

    public GenerateAudioBook() {
    }

    @Override
    public AudioBook getObject(int id,long noOfStreams,int streamerId,long length,long dateAdded,String name,int streamGenre) {
        switch (streamGenre){
            case 1:
                return new Fiction(id,noOfStreams,streamerId,length,dateAdded,name);
            case 2:
                return new PersonalDevelopment(id,noOfStreams,streamerId,length,dateAdded,name);
            case 3:
                return new Children(id,noOfStreams,streamerId,length,dateAdded,name);
            default:
                return null;
        }
    }
}
