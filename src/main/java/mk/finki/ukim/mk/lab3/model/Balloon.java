package mk.finki.ukim.mk.lab3.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Balloon implements Comparable<Balloon>{
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    private Manufacturer manufacturer;

    @OneToMany(mappedBy = "balloon", fetch = FetchType.EAGER)
    private List<Order> order;

    //TODO check do i really have to keep a list of orders here
    public Balloon(String name, String description, Manufacturer manufacturer) {
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
    }

    public Balloon() {

    }

    @Override
    public String toString() {
        return String.format("Name: %s Description: %s", name, description);
    }

    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    @Override
    public int compareTo(Balloon o) {
        return this.getName().compareTo(o.getName());
    }
}
