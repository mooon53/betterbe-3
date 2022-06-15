import dao.Dao;

public class timeline {
    public static void main(String[] args) {
        System.out.println(Dao.getHistoricalData(1L));
    }
}
