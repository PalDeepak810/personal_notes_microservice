package com.Dp.NotesSphere.Controllers;

import com.Dp.NotesSphere.Modals.User;
import com.Dp.NotesSphere.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> Create(@RequestBody User user) {
        try {
            userService.saveUser(user);
            return new ResponseEntity<User>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<User> GetAll() {
        return userService.getAll();
    }

    @GetMapping("username/{username}")
    public ResponseEntity<User> GetByName(@PathVariable String username) {

            Optional<User> ab = Optional.ofNullable(userService.getByname(username));
            if (ab.isPresent()) {
                return new ResponseEntity<>(ab.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("username/{username}")
    public ResponseEntity<User> change(@RequestBody User newuser,@PathVariable String username){
        User old = userService.getByname(username);

        if(old!=null){
            old.setUsername(
                    newuser.getUsername()!=null &&!newuser.getUsername().isEmpty()?newuser.getUsername():old.getUsername()
            );

            old.setPassword(
                    newuser.getPassword()!=null&&!newuser.getPassword().isEmpty()?newuser.getPassword():old.getPassword()
            );

            User New  = userService.saveUser(old);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("username/{username}")
    public ResponseEntity<User> delete(@PathVariable String username){
        User user = userService.getByname(username);

        if(user!=null){
            userService.delete(username);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}