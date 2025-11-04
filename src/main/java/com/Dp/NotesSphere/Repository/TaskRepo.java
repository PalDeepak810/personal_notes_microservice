package com.Dp.NotesSphere.Repository;

import com.Dp.NotesSphere.Modals.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends MongoRepository<Task, String> {
    List<Task> findByUserId(String userId);
}
