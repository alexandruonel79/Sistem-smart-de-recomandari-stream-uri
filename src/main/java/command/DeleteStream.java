package command;

import db.Database;
import streams.Stream;

public class DeleteStream implements Command{
    private Database database;
    private int streamId;
    private int streamerId;
    public DeleteStream(Database database,int streamId,int streamerId) {
        this.database=database;
        this.streamId=streamId;
        this.streamerId=streamerId;
    }
    @Override
    public void execute(){
        database.deleteStream(streamId,streamerId);
    }
}
