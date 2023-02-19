package factory;

import strategy.Generate;
import strategy.GenerateAudioBook;
import strategy.GeneratePiesaMuzicala;
import strategy.GeneratePodcast;
import streams.Stream;
import streams.streamType.AudioBook;
import streams.streamType.PiesaMuzicala;
import streams.streamType.Podcast;

public class FactoryStream {
    private static FactoryStream uniqueInstance;

    public FactoryStream() {
    }

    public static FactoryStream getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new FactoryStream();

        return uniqueInstance;
    }

    public Stream createStream(String[] info) {
        int streamType = Integer.parseInt(info[0]);
        int id = Integer.parseInt(info[1]);
        int streamGenre = Integer.parseInt(info[2]);
        long noOfStreams = Long.parseLong(info[3]);
        int streamerId = Integer.parseInt(info[4]);
        long length = Long.parseLong(info[5]);
        long dateAdded = Long.parseLong(info[6]);
        String name = info[7];

        if (streamType == 1) {
            Generate<PiesaMuzicala> generate = new GeneratePiesaMuzicala();
            return generate.getObject(id, noOfStreams, streamerId, length, dateAdded, name, streamGenre);
        } else if (streamType == 2) {
            Generate<Podcast> generate = new GeneratePodcast();
            return generate.getObject(id, noOfStreams, streamerId, length, dateAdded, name, streamGenre);
        } else if (streamType == 3) {
            Generate<AudioBook> generate = new GenerateAudioBook();
            return generate.getObject(id, noOfStreams, streamerId, length, dateAdded, name, streamGenre);
        }
        return null;
    }
}
