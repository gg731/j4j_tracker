package data;

import model.Brand;
import model.Car;
import model.Driver;
import model.Model;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;

import static org.junit.Assert.*;

public class AutoDBTest {

    @Test
    public void add() {
        Car car = new Car("Car-1");
        AutoDB.getInst().add(car);

        assertThat(car, is(AutoDB.getInst().findById(Car.class, car.getId())));
    }

    @Test
    public void getAll() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("Car-1"));
        cars.add(new Car("Car-2"));
        cars.add(new Car("Car-3"));

        cars.stream().forEach(c -> AutoDB.getInst().add(c));

        assertThat(cars, is(AutoDB.getInst().getAll(Car.class)));
    }

    @Test
    public void findDriverByLogin() {
        Driver driver = new Driver("123", "pass");
        AutoDB.getInst().add(driver);

        assertThat(driver, is(AutoDB.getInst().findDriverByLogin("123")));
    }

    @Test
    public void update() {
        Car car = new Car("Car-1");
        AutoDB.getInst().add(car);

        car.setName("NewCar-1");
        AutoDB.getInst().update(car);

        assertThat(car.getName(), is(AutoDB.getInst().findById(Car.class, car.getId()).getName()));

    }

    @Test
    public void sortByDate() {
        Brand brand = new Brand("Brand-1");
        Model model = new Model("Model-1", brand);
        Car car1 = new Car("Car-1", model, 123, new Date(1));
        Car car2 = new Car("Car-2", model, 123, new Date(3));
        Car car3 = new Car("Car-3", model, 123, new Date());
        AutoDB.getInst().add(car1);
        AutoDB.getInst().add(car2);
        AutoDB.getInst().add(car3);

        assertThat(AutoDB.getInst().findById(Car.class, car3.getId()),
                is(AutoDB.getInst().sortByDate().get(0)));
    }

    @Test
    public void sortByPhoto() {
        Brand brand = new Brand("Brand-1");
        Model model = new Model("Model-1", brand);
        Car car1 = new Car("Car-1", model, 123, new Date(1));
        Car car2 = new Car("Car-2", model, 123, new Date(3));
        Car car3 = new Car("Car-3", model, 123, new Date());
        car2.setImage(1);
        AutoDB.getInst().add(car1);
        AutoDB.getInst().add(car2);
        AutoDB.getInst().add(car3);

        assertThat(AutoDB.getInst().findById(Car.class, car2.getId()),
                is(AutoDB.getInst().sortByPhoto().get(0)));
    }

    @Test
    public void sortByBrand() {
        Brand brand1 = new Brand("Brand-1");
        Brand brand2 = new Brand("Brand-2");
        Brand brand3 = new Brand("Brand-3");

        Car car1 = new Car("Car-1", new Model("1", brand1), 123, new Date(1));
        Car car2 = new Car("Car-2", new Model("2", brand2), 123, new Date(3));
        Car car3 = new Car("Car-3", new Model("3", brand3), 123, new Date());

        AutoDB.getInst().add(car1);
        AutoDB.getInst().add(car2);
        AutoDB.getInst().add(car3);

        assertThat(AutoDB.getInst().findById(Car.class, car3.getId()),
                is(AutoDB.getInst().sortByBrand(car3.getModel().getBrand().getId()).get(0)));

    }
}