import dao.Dao;

public class GetOptionsTest {
    public static void main(String[] args) {
        System.out.println(Dao.getAllOptions(1L));
        System.out.println(Dao.getRules(1L));
    }
}
