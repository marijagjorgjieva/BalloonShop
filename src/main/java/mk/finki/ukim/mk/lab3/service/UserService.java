package mk.finki.ukim.mk.lab3.service;

import mk.finki.ukim.mk.lab3.model.User;

public interface UserService {
    User addUser(User s);
    User findByName(String s);
    boolean findName(String s);

}
