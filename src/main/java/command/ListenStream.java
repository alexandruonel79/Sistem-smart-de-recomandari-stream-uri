package command;

import user.User;

public class ListenStream implements Command{
    private User user;
    private int streamId;
    public ListenStream(User user,int streamId) {
        this.user=user;
        this.streamId=streamId;
    }
    @Override
    public void execute(){
        user.ListenStream(streamId);
    }
}
