package factory;

import stream.Streamer;
import stream.streamerType.AutorAudioBook;
import stream.streamerType.GazdaPodcast;
import stream.streamerType.Muzician;

public class FactoryStreamer {
    private static FactoryStreamer uniqueInstance;

    public FactoryStreamer() {
    }

    public static FactoryStreamer getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new FactoryStreamer();

        return uniqueInstance;
    }

    public Streamer createStreamer(String[] info) {
        int type = Integer.parseInt(info[0]);
        Integer id = Integer.valueOf(info[1]);
        String name = info[2];


        switch (type) {
            case 1:
                return new Muzician(id, name);
            case 2:
                return new GazdaPodcast(id, name);
            case 3:
                return new AutorAudioBook(id, name);
            default:
                return null;
        }
    }
}
