package mk.finki.ukim.mk.lab3.repository.jpa;

import mk.finki.ukim.mk.lab3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsernameAndPassword(String username, String password);
    List<User> findAllByUserFullnameContaining(String s);
    List<User> findAllByUsername(String s);

}
