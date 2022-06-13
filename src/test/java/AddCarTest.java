import dao.Dao;
import model.Car;

public class AddCarTest {
    public static void main(String[] args) {
        Car car = new Car(24L, 1999L, 499.99, "Mazda", "rx7");
        Dao.addCar(car);
        System.out.println(Dao.getOptions(24L));
    }
}
