package streams;

import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

public class StreamSortSurprise implements Comparator<Stream> {

    @Override
    public int compare(Stream s1, Stream s2) {
        Date date1 = new java.util.Date(s1.getDateAdded() * 1000);
        Date date2 = new java.util.Date(s2.getDateAdded() * 1000);
        if (date1.compareTo(date2) == 0) {
            if (s1.getNoOfStreams() < s2.getNoOfStreams())
                return 1;
            else if (s1.getNoOfStreams() > s2.getNoOfStreams())
                return -1;
            else if (Objects.equals(s1.getNoOfStreams(), s2.getNoOfStreams())) {
                return 0;
            }
        } else if (date1.compareTo(date2) < 0) {
            return 1;
        } else if (date1.compareTo(date2) > 0) {
            return -1;
        }
        return -1;
    }
}
