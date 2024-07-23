package mk.finki.ukim.mk.lab3.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orderInfo")
public class Order {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Balloon balloon;

    private String size;

    private String username;

    @ManyToOne
    private ShoppingCart shoppingCart;
    public LocalDateTime getDateCreated() {
       return dateCreated;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateCreated;

    public Order(Balloon balloon, String size, String username, ShoppingCart cart) {
        this.balloon=balloon;
        this.size=size;
        this.shoppingCart=cart;
        this.username=username;
    }

    public Order() {

    }

    public void setOrderId(Long orderId) {
    }


    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Long getOrderId() { return id;
    }

    public String getBalloonColor() {
        return balloon.getName();
    }
    public String getBalloonSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }



    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
}
