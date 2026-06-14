package com.bharath.PersonalNotes.note;


import com.bharath.PersonalNotes.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepo extends JpaRepository<Note, Integer> {

    List<Note> findByUser(Users user);
    Optional<Note> findByIdAndUser(int id, Users user);

}
