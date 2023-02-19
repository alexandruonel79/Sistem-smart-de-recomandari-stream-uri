package command;

import db.Database;

public class ListUserListeningHistory implements Command{
    private Database database;
    private Integer id;
    public ListUserListeningHistory(Database database,Integer id) {
        this.database=database;
        this.id=id;
    }
    @Override
    public void execute(){
        database.listUserListeningHistory(id);
    }
}
