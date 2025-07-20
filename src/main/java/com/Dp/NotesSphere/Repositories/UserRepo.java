package com.Dp.NotesSphere.Repositories;

import com.Dp.NotesSphere.Modals.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User,String> {
    User findByUsername(String username);

    void deleteByUsername(String username);
}
