import facade.Application;

public class ProiectPOO {

    public static void main(String[] args) {
        if(args == null) {
            System.out.println("Nothing to read here");
        }else{
            Application application=new Application(args);
            application.run();
            //application.showHistory();
        }
    }
}
