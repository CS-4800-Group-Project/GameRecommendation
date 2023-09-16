package GameRecommendationSystem.Application;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	@GetMapping("/hi")
	public String HelloWorld(){
	return "Hi everyone, this is Group-Name (Best group name ever). ";
	}
}
