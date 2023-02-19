package command;

import db.Database;

public class ListStreams implements Command {
    private Database database;
    private Integer id;
    public ListStreams(Database database,Integer id) {
        this.database=database;
        this.id=id;
    }
    @Override
    public void execute(){
        database.listStreams(id);
    }
}