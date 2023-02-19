package command;

import db.Database;
import streams.Recommandations.RSurprise;

public class RecommendSurprise implements Command{
    private RSurprise rSurprise;
    private String streamType;
    private int userId;
    public RecommendSurprise(RSurprise rSurprise,int userId,String streamType) {
        this.rSurprise=rSurprise;
        this.streamType=streamType;
        this.userId=userId;
    }
    @Override
    public void execute(){
        rSurprise.recommendSurprise(userId,streamType);
    }
}
