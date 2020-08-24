package data;

import model.Brand;
import model.Driver;

import java.util.List;

public interface Store {
    int add(Object t);

    <T> List<T> getAll(Class<T> c);

    <T> T findById(Class<T> c, int id);

    Driver findDriverByLogin(String login);

    void update(Object e);

}
