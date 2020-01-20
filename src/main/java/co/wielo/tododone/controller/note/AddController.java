package co.wielo.tododone.controller.note;

import co.wielo.tododone.model.Note;
import co.wielo.tododone.model.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class AddController
{
    @Autowired
    private NoteRepository noteRepository;

    @GetMapping("/note/add")
    public String add(Model model)
    {
        model.addAttribute("priorities", Note.priorities);

        return "add";
    }

    @PostMapping("/note/add")
    public String add(
        @RequestParam String description,
        @RequestParam int priority,
        Principal principal
    ) {
        Note note = new Note();
        note.setDescription(description);
        note.setPriority(priority);
        note.setAuthor(principal.getName());
        noteRepository.save(note);

        return "redirect:/";
    }
}
