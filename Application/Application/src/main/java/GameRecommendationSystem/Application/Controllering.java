package GameRecommendationSystem.Application;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
@Controller
public class Controllering {
	private String jsonString = "{\"name\": \"Jose\", \"age\": 20}";
	ObjectMapper objectMapper = new ObjectMapper();

	// http://localhost:8080/
    @GetMapping("/")
    public String welcome() {
        return "homepage";
    }

	@GetMapping("/results")
	public String results(String userInput, Model model){
		model.addAttribute("userInput", userInput);
		return "results";
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
	@GetMapping("/Jsoup")
    public String Jsoup() {
		Document doc;
        try {
            doc = Jsoup.connect("http://localhost:8081").userAgent("JSoupExample") .get();
			String text = doc.text();
            System.out.println("Text : " + text);
    	} catch (IOException e) {
            e.printStackTrace();
		}
		return "JSoupExample";
	}
}