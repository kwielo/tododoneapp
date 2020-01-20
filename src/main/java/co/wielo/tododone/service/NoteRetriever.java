package co.wielo.tododone.service;

import co.wielo.tododone.model.Note;
import co.wielo.tododone.model.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class NoteRetriever
{
    @Autowired
    NoteRepository noteRepository;

    public Page<Note> retrievePaginated(String searchQuery, String filter, PageRequest pageRequest)
    {
        Page<Note> notes;
        String description = "%" + searchQuery + "%";
        if (filter.equals("deleted")) {
            notes = noteRepository.findByDescriptionLikeAndDeletedTrue(description, pageRequest);
        } else if (filter.equals("done")) {
            notes = noteRepository.findByDescriptionLikeAndDeletedFalseAndDoneTrue(description, pageRequest);
        } else if (filter.equals("undone")) {
            notes = noteRepository.findByDescriptionLikeAndDeletedFalseAndDoneFalse(description, pageRequest);
        } else {
            notes = noteRepository.findByDescriptionLikeAndDeletedFalse(description, pageRequest);
        }

        return notes;
    }
}
