package streams;

import com.google.gson.JsonObject;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.TimeZone;

public abstract class Stream {
    private int streamType;
    private int id;
    private int streamGenre;
    private Long noOfStreams;
    private int streamerId;
    private Long length;
    private Long dateAdded;
    private String name;
    public int getStreamType() {
        return streamType;
    }

    public void setStreamType(int streamType) {
        this.streamType = streamType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStreamGenre() {
        return streamGenre;
    }

    public void setStreamGenre(int streamGenre) {
        this.streamGenre = streamGenre;
    }

    public Long getNoOfStreams() {
        return noOfStreams;
    }

    public void setNoOfStreams(Long noOfStreams) {
        this.noOfStreams = noOfStreams;
    }

    public int getStreamerId() {
        return streamerId;
    }

    public void setStreamerId(int streamerId) {
        this.streamerId = streamerId;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public Long getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Long dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JsonObject createJsonStream(String streamerName){
        Duration lengthDuration = Duration.ofSeconds(length);

        long hours = lengthDuration.toHours();
        lengthDuration = lengthDuration.minusHours(hours);
        long minutes = lengthDuration.toMinutes();
        lengthDuration = lengthDuration.minusMinutes(minutes);
        long seconds = lengthDuration.getSeconds();

        String duration;
        if (hours == 0) {
            duration = String.format("%02d:%02d", minutes, seconds);
        } else {
            duration = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        }
        JsonObject send=new JsonObject();
        send.addProperty("id",Integer.toString(id));
        send.addProperty("name",name);
        send.addProperty("streamerName",streamerName);
        send.addProperty("noOfListenings",Long.toString(noOfStreams));
        send.addProperty("length",duration);

        Date date = new java.util.Date(dateAdded *1000);

        SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        send.addProperty("dateAdded",sdf.format(date));

        return send;
    }
    public void listen(){
        noOfStreams++;
    }

}
