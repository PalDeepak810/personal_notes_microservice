package com.Dp.NotesSphere.Services;

import com.Dp.NotesSphere.Modals.User;
import com.Dp.NotesSphere.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService{

    @Autowired
    private UserRepo userRepo;

    private static final PasswordEncoder passwordencoder = new BCryptPasswordEncoder();



    public User saveUser(User user) {
        if (!user.getPassword().startsWith("$2a$") && !user.getPassword().startsWith("$2b$")) {
            user.setPassword(passwordencoder.encode(user.getPassword()));
        }
        return userRepo.save(user);
    }

    public List<User> getAll(){
        return userRepo.findAll();
    }

    public User getByname(String name){
        return userRepo.findByUsername(name);
    }

    public void delete(String username){
        userRepo.deleteByUsername(username);
    }
}