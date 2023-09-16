package GameRecommendationSystem.Application;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	@GetMapping("/hi")
	public String HelloWorld(){
	return "Hi everyone, this is Group-Name (Best group name ever). ";
	}

	@GetMapping("/purpose")
	public String HelloWorld(){
		String purpose = "The purpose of this project is to create a game recommendation system.";
	return purpose;
	}
}
