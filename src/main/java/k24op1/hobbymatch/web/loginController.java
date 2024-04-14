package k24op1.hobbymatch.web;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class loginController {
 @GetMapping("/login")
 public String login() {
return "login";
 } 
 }
