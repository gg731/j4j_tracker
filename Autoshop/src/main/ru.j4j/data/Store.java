package data;

import model.Brand;
import model.Driver;
import model.Engine;

import java.util.List;

public interface Store {
     <T> T add(T t);

    List<Brand> getAllBrands();

    Driver findDriverById(int id);

    Engine findEngineById(int id);
}
