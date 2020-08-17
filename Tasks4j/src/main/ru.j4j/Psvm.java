
import data.TaskDB;

public class Psvm {
    public static void main(String[] args) {
        TaskDB.getInstance().findAllForUserId(18).stream().forEach(System.out::println);
    }
}
