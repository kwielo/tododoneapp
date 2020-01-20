package co.wielo.tododone.controller.note;

import co.wielo.tododone.model.Note;
import co.wielo.tododone.model.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EditController
{
    @Autowired
    private NoteRepository noteRepository;

    @GetMapping("/note/edit/{noteId}")
    public String edit(@PathVariable long noteId, Model model)
    {
        Note note = noteRepository.getOne(noteId);
        model.addAttribute("note", note);
        model.addAttribute("priorities", Note.priorities);

        return "edit";
    }

    @PostMapping("/note/edit/{noteId}")
    public String edit(
        @PathVariable long noteId,
        @RequestParam String description,
        @RequestParam int priority
    ) {
        Note note = noteRepository.getOne(noteId);
        note.setDescription(description);
        note.setPriority(priority);
        noteRepository.save(note);

        return "redirect:/";
    }
}
