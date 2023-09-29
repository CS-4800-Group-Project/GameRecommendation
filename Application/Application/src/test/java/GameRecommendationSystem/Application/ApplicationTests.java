package GameRecommendationSystem.Application;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
@ContextConfiguration
@SpringBootTest(classes = {Application.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application.properties")
class ApplicationTests {
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTest;

	@Test
	void onWebLoad() {
		String response = restTest.getForObject("http://localhost:" + port + "/", String.class);
		assertThat(response).contains("VIDEO GAME");
	}

}
