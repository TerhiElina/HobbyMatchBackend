package k24op1.hobbymatch.web;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import k24op1.hobbymatch.domain.Group;
import k24op1.hobbymatch.domain.GroupRepository;
import k24op1.hobbymatch.domain.HappeningRepository;
import k24op1.hobbymatch.domain.Happening;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMethod;







@Controller

public class HappeningController {
    @ Autowired
    private GroupRepository grepository;

    @Autowired
    private HappeningRepository hrepository;
    
    @RequestMapping("/happeninglist")
    public String happeningList (Model model) {
    model.addAttribute("happenings", hrepository.findAll());
    return "happeninglist";
}

@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
@PreAuthorize("hasAuthority('ADMIN')")
public String deleteHappening(@PathVariable("id") Long happeningId, Model model){
    hrepository.deleteById(happeningId);
    return "redirect:../happeninglist";
} 
@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
@PreAuthorize("hasAuthority('ADMIN')")
public String editHappening(@PathVariable("id") Long happeningId, Model model) {
    Happening happening = hrepository.findById(happeningId).orElse(null);
    Iterable<Group> iterableGroups = grepository.findAll();
    
    // findAll luo itarable tyypin, joka täytyy muuttaa lista tyypiksi
    List<Group> groups = new ArrayList<>();
    iterableGroups.forEach(groups::add);
    
    if (happening == null) {
        return "redirect:/happeninglist";
    }
    model.addAttribute("happening", happening);
    model.addAttribute("groups", groups); // Tässä lisätään groups modeliin
    return "edithappening";
}

@RequestMapping(value = "/add")
@PreAuthorize("hasAuthority('ADMIN')")
    public String addHappening(Model model){
        model.addAttribute("happening", new Happening());
        model.addAttribute("group", grepository.findAll());
        return "addhappening";
    }

@RequestMapping(value="/save", method=RequestMethod.POST)
public String save(Happening happening) {

    hrepository.save(happening);
    return "redirect:happeninglist";
}




}



