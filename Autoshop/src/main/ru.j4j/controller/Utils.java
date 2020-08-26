package controller;

public class Utils {

    public static int getOr(String s) {
        int num;
        try {
            num = Integer.valueOf(s);
        } catch (Exception e) {
            return 0;
        }
        return num;
    }
}
