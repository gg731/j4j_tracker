package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "model")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String model;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    public Model() {
    }

    public Model(String model, Brand brand) {
        this.model = model;
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Model model1 = (Model) o;
        return id == model1.id &&
                Objects.equals(model, model1.model) &&
                Objects.equals(brand, model1.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, brand);
    }
}
