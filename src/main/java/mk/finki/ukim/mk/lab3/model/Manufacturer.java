package mk.finki.ukim.mk.lab3.model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Manufacturer {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String country;
    private String address;

    @OneToMany(mappedBy = "manufacturer", fetch = FetchType.EAGER)
    private List<Balloon> balloons;

    public Manufacturer(Long id, String name, String country, String address) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.address = address;
        balloons = new LinkedList<>();
    }

    public Manufacturer() {

    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;

    }

    public Long getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getAddress() {
        return address;
    }
}
