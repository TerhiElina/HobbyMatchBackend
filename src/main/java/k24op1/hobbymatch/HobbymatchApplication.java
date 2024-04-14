package k24op1.hobbymatch;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import k24op1.hobbymatch.domain.Group;
import k24op1.hobbymatch.domain.GroupRepository;
import k24op1.hobbymatch.domain.Happening;
import k24op1.hobbymatch.domain.HappeningRepository;
import k24op1.hobbymatch.domain.UserRepository;
//Importoidaan päivämäärän ja ajan käsittelyyn tarvittavat importit
import k24op1.hobbymatch.domain.User;

import java.text.SimpleDateFormat;
import java.time.LocalTime;

@SpringBootApplication
public class HobbymatchApplication {
	//Loggerin avulla voidaan logata terminaaliin tallennetut tiedot
	private static final Logger log = LoggerFactory.getLogger(HobbymatchApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(HobbymatchApplication.class, args);
	}
	@Bean
	public CommandLineRunner testHappening(GroupRepository groupRepository,
	HappeningRepository happeningRepository, UserRepository userRepository){
		return (args) -> {
			Group group1 = new Group("Mikin purjehdusseura", "Rentoa purjehdusta");
			Group group2 = new Group("Akun paketointikerho", "Paketoinnin alkeet");
			Group group3 = new Group("Roopen säästövinkit", "Säästövinkkejä säästämisestä kiinnostuneille");

			groupRepository.save(group1);
			groupRepository.save(group2);
			groupRepository.save(group3);

			//Logitetaan data, jotta se saadaan näkyviin terminaalissa
			System.out.println("Tallennetut ryhmät:");
			System.out.println(group1);
			System.out.println(group2);
			System.out.println(group3);

			//Luodaan testikäyttäjät
			User user1 = new User("Mikki", "$2a$10$BF9OEjYvXMfvLScYOPP9eulOpmtb7nvzlVp35Am3uQRa7tDbG6l9m","mikki.hiiri@disneymail.com","ADMIN");
			User user2 = new User("Aku", "$2a$10$UJ0pXR1EHOcuarx8k/qX3.7vx6APXr0otreKInPdqIvK1.I81z66q","aku.ankka@disneymail.com","ADMIN");
			
			userRepository.save(user1);
			userRepository.save(user2);
			
			// Käsitellään päivämäärä ja aika oikeaan muotoon
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

            Happening happening1 = new Happening("Purjehdus", dateFormat.parse("15.04.2024"), LocalTime.of(12, 0),
                    "Mikkihiirenranta 1", "00100", "Hiirilä", group1);
            Happening happening2 = new Happening("Paketointipaja", dateFormat.parse("18.04.2024"), LocalTime.of(15, 0),
                    "Lahjatie 1", "00200", "Ankkala", group2);
            Happening happening3 = new Happening("Säästäjien sijoitusvinkit", dateFormat.parse("20.04.2024"),
                    LocalTime.of(16, 0), "Säästöporsaankatu 1", "00200", "Ankkala", group3);

			happeningRepository.save(happening1);
			happeningRepository.save(happening2);
			happeningRepository.save(happening3);

			System.out.println("Tallennetut testitapahtumat:");
            System.out.println(happening1);
            System.out.println(happening2);
            System.out.println(happening3);
		};
	}
	
}
