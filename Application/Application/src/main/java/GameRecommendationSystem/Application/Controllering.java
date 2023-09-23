package GameRecommendationSystem.Application;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controllering {
	@GetMapping("/hi")
	public String helloWorld(){
		return "Hi everyone, this is Group-Name (Best group name ever). ";
	}

	@GetMapping("/Self")
	public String aboutMe(){
		return "Hi I am the controller weeeeeee";
	}
	
    @GetMapping("/")
    public String welcome() {
        return "homepage";
    }

}

