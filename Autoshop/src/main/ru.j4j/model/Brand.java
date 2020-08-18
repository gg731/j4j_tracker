package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "brand")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String brand;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Model> model = new ArrayList<>();

    public Brand(String brand) {
        this.brand = brand;
    }

    public Brand() {
    }

    public void addModel(Model model) {
        this.model.add(model);
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<Model> getModel() {
        return model;
    }

    public void setModel(List<Model> model) {
        this.model = model;
    }
}
