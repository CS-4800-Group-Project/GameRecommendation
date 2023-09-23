package GameRecommendationSystem.Application;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	@GetMapping("/hi")
	public String helloWorld(){
		return "Hi everyone, this is Group-Name (Best group name ever). ";
	}

	@GetMapping("/homepage")
	public String purpose(){
		return "homepage.html";
	}

	@GetMapping("/")
	public String aboutMe(){
		return "Hi I am the controller weeeeeee";
	}

}
