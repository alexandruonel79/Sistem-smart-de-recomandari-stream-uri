package streams;

import java.util.Comparator;

public class StreamSort implements Comparator<Stream> {
    public int compare(Stream s1, Stream s2) {
        return Long.compare(s1.getNoOfStreams(), s2.getNoOfStreams());
    }
}
