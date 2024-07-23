package mk.finki.ukim.mk.lab3.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "userInfo")
public class User {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Convert(converter = UserFullNameConverter.class)
    private UserFullname userFullname;

    private String password;

    @DateTimeFormat(pattern="dd-MM-yyyy")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<ShoppingCart> carts;

    public User(String username, String name, String surname, String password, LocalDate dateOfBirth) {
        this.username = username;
        userFullname=new UserFullname(name,surname);
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }

    public User() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return userFullname.getName();
    }

    public String getSurname() {
        return userFullname.getSurname();
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
}
