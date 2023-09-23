package GameRecommendationSystem.Application;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controllering {
	@GetMapping("/hi")
	public String helloWorld(){
		return "Hi everyone, this is Group-Name (Best group name ever). ";
	}

	
	//http://localhost:8081/
    @GetMapping("/")
    public String welcome() {
        return "homepage";
    }

	@GetMapping("/results")
	public String results(String userInput, Model model){
		model.addAttribute("userInput", userInput);
		return "results";
	}
}

