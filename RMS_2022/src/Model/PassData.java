package Model;

public class PassData {
    static int anzhel;

    public PassData(){
        anzhel = 1 ;
    }

    public static int getAnzhel () {
        return anzhel;
    }

    public static void setAnzhel (int anzhel) {
        PassData.anzhel = anzhel;
    }
}
