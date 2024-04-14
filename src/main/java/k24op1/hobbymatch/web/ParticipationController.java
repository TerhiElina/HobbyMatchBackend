package k24op1.hobbymatch.web;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import k24op1.hobbymatch.domain.Happening;
import k24op1.hobbymatch.domain.HappeningRepository;
import k24op1.hobbymatch.domain.User;
import k24op1.hobbymatch.domain.UserRepository;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class ParticipationController {
    @Autowired
    private HappeningRepository hRepository;

    @Autowired
    private UserRepository uRepository;
    
    //Lisätään käyttäjä tapahtumaan, kun painaa nappia
    //Principal objekti on springbootin tarjoama objekti,
    //joka palauttaa kirjautuneen käyttäjän tiedot
    @PostMapping("/happenings/{happeningId}/join")
    @PreAuthorize("hasAuthority('ADMIN')")
        public String joinHappening(@PathVariable("happeningId") Long happeningId, Principal principal,  RedirectAttributes redirectAttributes) {
        Optional<Happening> optionalHappening = hRepository.findById(happeningId);
        User user = uRepository.findByUsername(principal.getName());

        if (optionalHappening.isPresent() && user != null) {
            Happening happening = optionalHappening.get();
            

            // Lisää käyttäjä tapahtuman osallistujaksi
            happening.getParticipants().add(user);
            hRepository.save(happening);
            
             // Lisätään viesti onnistuneesta ilmoittautumisesta
            redirectAttributes.addFlashAttribute("successMessage", "Ilmoittautuminen onnistui!");
            return "redirect:/happeninglist";
        } else {
            return "redirect:/happeninglist";
        }
    }
    


}
