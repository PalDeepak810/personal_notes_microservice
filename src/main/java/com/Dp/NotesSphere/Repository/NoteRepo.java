package com.Dp.NotesSphere.Repository;

import com.Dp.NotesSphere.Modals.Note;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface NoteRepo extends MongoRepository<Note, String> {
    List<Note> findByUsername(String username);
    Optional<Note> findByIdAndUsername(String id, String username);
    void deleteByIdAndUsername(String id, String username);
}
