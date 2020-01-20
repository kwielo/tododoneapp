package co.wielo.tododone.controller;

import co.wielo.tododone.model.Note;
import co.wielo.tododone.service.NoteRetriever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class IndexController
{
    @Autowired
    private NoteRetriever noteRetriever;

    @GetMapping("/")
    public String index(
        @RequestParam(name = "search", defaultValue = "") String searchQuery,
        @RequestParam(name = "show", defaultValue = "") String filter,
        @RequestParam(defaultValue = "1") int page,
        Model model,
        Principal principal
    ) {
        page = page - 1;
        PageRequest pageRequest = PageRequest
            .of(page, 8, Sort.by("done").and(Sort.by("updatedAt").descending()));
        Page<Note> notes = noteRetriever.retrievePaginated(searchQuery, filter, pageRequest);

        model.addAttribute("searchQuery", searchQuery);
        model.addAttribute("showFilter", filter);
        model.addAttribute("notes", notes);
        model.addAttribute("page", page);
        model.addAttribute("user", principal);

        return "index";
    }
}
