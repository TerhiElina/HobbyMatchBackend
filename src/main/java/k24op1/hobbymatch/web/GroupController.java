package k24op1.hobbymatch.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import k24op1.hobbymatch.domain.Group;
import k24op1.hobbymatch.domain.GroupRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class GroupController {

    @Autowired
    private GroupRepository grepository;

    @RequestMapping (value={"/grouplist"})
    public String GroupList(Model model){
        model.addAttribute("groups", grepository.findAll());
        return "grouplist";
    }
    @GetMapping("/addgroup")
    public String addGroup (Model model) {
        model.addAttribute("group", new Group());
        return "addgroup";
    }
    @RequestMapping(value= "/savegroup", method = RequestMethod.POST)
    public String save(@Valid Group group, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "addgroup";
        }
        grepository.save(group);
        return "redirect:grouplist";
    }

}
