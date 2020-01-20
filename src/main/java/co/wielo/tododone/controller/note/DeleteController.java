package co.wielo.tododone.controller.note;

import co.wielo.tododone.model.Note;
import co.wielo.tododone.model.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DeleteController
{
    @Autowired
    private NoteRepository noteRepository;

    @GetMapping("/note/delete/{noteId}")
    public String delete(@PathVariable long noteId)
    {
        Note note = noteRepository.getOne(noteId);
        note.setDeleted(true);

        noteRepository.save(note);

        return "redirect:/";
    }

}
