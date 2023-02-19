package command;

import db.Database;
import readFiles.ReadStreams;
import streams.Recommandations.RStreams;

public class RecommendStreams implements Command{
    private RStreams recommendStreams;
    private String streamType;
    private int userId;
    public RecommendStreams(RStreams recommendStreams,int userId,String streamType) {
        this.recommendStreams=recommendStreams;
        this.streamType=streamType;
        this.userId=userId;
    }
    @Override
    public void execute(){
        recommendStreams.recommend(userId,streamType);
    }
}
