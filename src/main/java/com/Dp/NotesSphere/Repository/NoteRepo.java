package com.Dp.NotesSphere.Repository;

import com.Dp.NotesSphere.Modals.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoteRepo extends MongoRepository<Note,String> {
}
