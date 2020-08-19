package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "driver")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "history", joinColumns = {
            @JoinColumn(name = "id_car")},
            inverseJoinColumns = {
                    @JoinColumn(name = "id_driver")})
    private List<Car> car = new ArrayList<>();

    public Driver() {
    }

    public Driver(String name) {
        this.name = name;
    }

    public void addCar(Car car) {
        this.car.add(car);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
