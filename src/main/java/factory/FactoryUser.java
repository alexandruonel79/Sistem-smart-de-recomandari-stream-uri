package factory;

import user.User;

import java.util.ArrayList;
import java.util.List;

public class FactoryUser {
    private static FactoryUser uniqueInstance;

    public FactoryUser() {
    }

    public static FactoryUser getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new FactoryUser();

        return uniqueInstance;
    }

    public User createUser(String[] info) {
        int id = Integer.parseInt(info[0]);
        String name = info[1];
        List<Integer> streams=new ArrayList<>();

        for(int i=0;i<info[2].split(" ").length;i++)
            streams.add(Integer.parseInt(info[2].split(" ")[i]));

        return new User(id,name,streams);
    }
}
