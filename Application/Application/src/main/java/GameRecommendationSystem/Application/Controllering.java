package GameRecommendationSystem.Application;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
public class Controllering {
	private String jsonString = "{\"name\": \"Jose\", \"age\": 20}";
	ObjectMapper objectMapper = new ObjectMapper();

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
	
	@GetMapping("/JSON")
    public String json(Model model) {
		String name = null;
		String age = null;
		try {
			JsonNode jsonNode = objectMapper.readTree(jsonString);
			name = jsonNode.get("name").asText();
			age = jsonNode.get("age").asText();

		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		model.addAttribute("name", name);
		model.addAttribute("age", age);
        return "name";
    }

}
