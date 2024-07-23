package mk.finki.ukim.mk.lab3.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

   /* public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateCreated;*/

    @OneToMany(mappedBy = "shoppingCart",  cascade = CascadeType.ALL)
    private List<Order> orders;

    public ShoppingCart(User user) {
        this.user = user;
        orders = new LinkedList<>();
    }

    public ShoppingCart() {

    }

    public void setUser(User user) {
        this.user = user;
    }

/*
    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
*/

    public void add(Order s)
    {
        orders.add(s);
    }
}
