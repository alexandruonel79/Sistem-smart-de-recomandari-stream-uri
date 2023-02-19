package strategy;

import streams.streamType.PiesaMuzicala;
import streams.streamType.streamGenre.pieseMuzicale.*;

public class GeneratePiesaMuzicala implements Generate<PiesaMuzicala>{
    public GeneratePiesaMuzicala() {
    }

    @Override
    public PiesaMuzicala getObject(int id,long noOfStreams,int streamerId,long length,long dateAdded,String name,int streamGenre) {

        switch (streamGenre){
            case 1:
                return new Pop(id,noOfStreams,streamerId,length,dateAdded,name);
            case 2:
                return new Latin(id,noOfStreams,streamerId,length,dateAdded,name);
            case 3:
                return new House(id,noOfStreams,streamerId,length,dateAdded,name);
            case 4:
                return new Dance(id,noOfStreams,streamerId,length,dateAdded,name);
            case 5:
                return new Trap(id,noOfStreams,streamerId,length,dateAdded,name);
            default:
                return null;
        }
    }
}
