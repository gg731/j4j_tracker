package data;

import model.Car;
import model.Driver;

import java.util.List;

public interface Store {
    int add(Object t);

    <T> List<T> getAll(Class<T> c);

    <T> T findById(Class<T> c, int id);

    Driver findDriverByLogin(String login);

    void update(Object e);

    List<Car> sortByDate();

    List<Car> sortByPhoto();

    List<Car> sortByBrand(int id);

}
