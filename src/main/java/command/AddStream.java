package command;

import db.Database;
import streams.Stream;

public class AddStream implements Command {
    private Database database;
    private Stream toAdd;
    public AddStream(Database database,Stream toAdd) {
        this.database=database;
        this.toAdd=toAdd;
    }
    @Override
    public void execute(){
        database.addStream(toAdd);
    }
}
