package mk.finki.ukim.mk.lab3.service.impl;

import mk.finki.ukim.mk.lab3.model.User;
import mk.finki.ukim.mk.lab3.repository.jpa.UserRepository;
import mk.finki.ukim.mk.lab3.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {
    private  final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User s) {
       return userRepository.save(s);
    }

    @Override
    public User findByName(String s) {
        return userRepository.findAllByUsername(s).stream().findFirst().orElseThrow();
    }

    @Override
    public boolean findName(String s) {
        return (long) userRepository.findAllByUsername(s).size() >0;
    }

}
