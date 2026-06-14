package com.bharath.PersonalNotes.note;

import com.bharath.PersonalNotes.user.UserRepo;
import com.bharath.PersonalNotes.user.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteService {

    @Autowired
    private NoteRepo noteRepo;
    @Autowired
    private UserRepo userRepo;


    public Note createNoteService(Note note, String username) {

        Users user = userRepo.findByUsername(username);

        note.setUser(user);
        note.setCreatedAt(LocalDateTime.now());
        note.setUpdatedAt(LocalDateTime.now());

        return noteRepo.save(note);

    }

    public List<Note> getNoteService(String username) {

        Users user = userRepo.findByUsername(username);

        return noteRepo.findByUser(user);
    }

    public Note getNoteByIdService(int id, String username) {
        Users user = userRepo.findByUsername(username);

        Optional<Note> note = noteRepo.findByIdAndUser(id,user);

        return note.orElseThrow(() -> new RuntimeException("Note not found"));

    }

    public Note updateNoteByIdService(int id, Note updatedNote, String username) {
        Users user = userRepo.findByUsername(username);

        Note existingNote = noteRepo.findByIdAndUser(id, user)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        existingNote.setTitle(updatedNote.getTitle());
        existingNote.setContent(updatedNote.getContent());
        existingNote.setUpdatedAt(LocalDateTime.now());

        return noteRepo.save(existingNote);

    }

    public void deleteNoteByIdService(int id, String username) {
        Users user = userRepo.findByUsername(username);

        Note note = noteRepo.findByIdAndUser(id, user)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        noteRepo.delete(note);
    }

}
