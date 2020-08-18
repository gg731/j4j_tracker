package data;

import model.Brand;
import model.Model;

import java.util.List;

public interface Store {
    <T> T add(T t);

    List<Brand> getAllBrands();

//    Brand addBrand(Brand brand);
}
