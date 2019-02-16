package org.delivery.qualifier;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class QualifierApplicationTests {
	@LocalServerPort
	int localPort;

	@Autowired
	TestRestTemplate restTemplate;

    @Value("${nouns}")
    List<String> nouns;

    @Value("${adjectives}")
    List<String> adjectives;

	@Test
	public void testRootIndexContent() {
		String content = restTemplate.getForObject("http://localhost:" + localPort + "/", String.class);
		assertTrue(content.contains("Spring boot"));
	}

	@Test
	public void testQualify(){
		String name = "roberto";
		String content = restTemplate.getForObject("http://localhost:" + localPort + "/qualify/" + name, String.class);
		assertTrue(content.startsWith(name));
	}

	@Test
	public void testQualifyNouns(){
		String name = "roberto";
		String content = restTemplate.getForObject("http://localhost:" + localPort + "/qualify/" + name, String.class);
		assertTrue(nouns.stream().anyMatch(noun -> content.contains(noun)));
	}

	@Test
	public void testQualifyAdjectives(){
		String name = "roberto";
		String content = restTemplate.getForObject("http://localhost:" + localPort + "/qualify/" + name, String.class);
		assertTrue(adjectives.stream().anyMatch(adjective -> content.contains(adjective)));
	}
}

