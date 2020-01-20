package co.wielo.tododone.controller.note;

import co.wielo.tododone.model.Note;
import co.wielo.tododone.model.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DoneController
{
    @Autowired
    private NoteRepository noteRepository;

    @GetMapping("/note/done/{noteId}")
    public String done(@PathVariable long noteId)
    {
        Note note = noteRepository.getOne(noteId);
        note.setDone(true);

        noteRepository.save(note);

        return "redirect:/";
    }

    @GetMapping("/note/undone/{noteId}")
    public String undone(@PathVariable long noteId)
    {
        Note note = noteRepository.getOne(noteId);
        note.setDone(false);

        noteRepository.save(note);

        return "redirect:/";
    }
}
