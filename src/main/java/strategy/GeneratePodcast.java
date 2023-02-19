package strategy;

import streams.streamType.Podcast;
import streams.streamType.streamGenre.podcasturi.Celebrities;
import streams.streamType.streamGenre.podcasturi.Documentary;
import streams.streamType.streamGenre.podcasturi.Tech;

public class GeneratePodcast implements Generate<Podcast>{
    public GeneratePodcast() {
    }

    @Override
    public Podcast getObject(int id,long noOfStreams,int streamerId,long length,long dateAdded,String name,int streamGenre) {

        switch (streamGenre){
            case 1:
                return new Documentary(id,noOfStreams,streamerId,length,dateAdded,name);
            case 2:
                return new Celebrities(id,noOfStreams,streamerId,length,dateAdded,name);
            case 3:
                return new Tech(id,noOfStreams,streamerId,length,dateAdded,name);
            default:
                return null;
        }
    }
}
