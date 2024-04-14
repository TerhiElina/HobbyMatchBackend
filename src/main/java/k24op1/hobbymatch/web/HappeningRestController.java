package k24op1.hobbymatch.web;

import org.springframework.web.bind.annotation.RestController;

import k24op1.hobbymatch.domain.Happening;
import k24op1.hobbymatch.domain.HappeningRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
public class HappeningRestController {
@Autowired
private HappeningRepository hRepository;

//HTapahtumien näyttäminen
@GetMapping("/happenings")
// List tyyppiä käytetään, kun halutaan palauttaa kaikki
public @ResponseBody List <Happening> getHappeningRest() {
    return (List <Happening>) hRepository.findAll();
}
//Yksittäisen tapahtuman näyttäminen
@GetMapping("/happenings/{id}")
//Optionalia käytetään, koska haetaan yhksittäistä tapahtumaa,
//findById:n avulla, joka saattaa palauttaa nullin
public @ResponseBody Optional <Happening> getHappeningById(@PathVariable("id") Long happeningId){
    return hRepository.findById(happeningId);
}

//Uuden tapahtuman luonti

@PostMapping("/happenings")
public @ResponseBody Happening addHappening(@RequestBody Happening newHappening){
    return hRepository.save(newHappening);
}
//Päivittää olemassa olevan tapahtuman id:n perusteella
@PutMapping("/happenings/{id}")
    public @ResponseBody Optional<Happening> updateHappening(@PathVariable("id") Long happeningId, @RequestBody Happening updatedHappening) {
        Optional<Happening> existingHappening = hRepository.findById(happeningId);
        //Jos happening löytyy, tallennetaan se
        //Muuten palautetaan tyhjä optional objekti
        if (existingHappening.isPresent()) {
            Happening savedHappening = hRepository.save(updatedHappening);
            return Optional.of(savedHappening);
        } else {
            return Optional.empty();
        }
    }
    @DeleteMapping("/happenings/{id}")
    public @ResponseBody Optional<Happening> deleteHappening(@PathVariable("id") Long happeningId) {
        Optional<Happening> existingHappening = hRepository.findById(happeningId);
        
        // Tarkistetaan, onko tapahtuma olemassa annetulla ID:llä
        if (existingHappening.isPresent()) {
            // Jos tapahtuma löytyy, poistetaan se
            hRepository.deleteById(happeningId);
            return existingHappening;
        } else {
            // Jos tapahtumaa ei löydy, palautetaan tyhjä optional objekti
            return Optional.empty();
        }
    }
    

}






    


