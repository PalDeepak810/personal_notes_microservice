package com.Dp.NotesSphere.Repository;

import com.Dp.NotesSphere.Modals.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepo extends MongoRepository<Task,String> {
}
