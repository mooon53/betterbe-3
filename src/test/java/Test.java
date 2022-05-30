import controller.Database;

public class Test {
    public static void main(String[] args) {
        System.out.println(Database.getOptions(1L));
        System.out.println(Database.getRules(1L));
    }
}
