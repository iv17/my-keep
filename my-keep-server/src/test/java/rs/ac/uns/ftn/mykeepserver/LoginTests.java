package rs.ac.uns.ftn.mykeepserver;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import rs.ac.uns.ftn.mykeepserver.util.Utility;
import rs.ac.uns.ftn.mykeepserver.web.dto.LoginRequestDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:application.properties")
@Transactional
public class LoginTests {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;
	
	private LoginRequestDTO loginRequestDTO;

	@Before
	public void setUp() throws Exception {
		
		this.mockMvc = MockMvcBuilders.
    			webAppContextSetup(this.context)
    			.apply(springSecurity())
    			.build();
		
		this.loginRequestDTO = new LoginRequestDTO();
	}
	
	@Test
	public void unsuccessfulLoginWrongEmail() throws IOException, Exception {
		
		this.loginRequestDTO.setEmail("email@gmail.com");
		this.loginRequestDTO.setPassword("Bar5slova");
		
		this.mockMvc.perform(post("/api/users/login").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(Utility.json(this.loginRequestDTO)))
				.andExpect(status().isNotFound())
				.andExpect(content().contentType(MediaType.TEXT_PLAIN));		
	}
	
	@Test
	public void unsuccessfulLoginWrongPassword() throws IOException, Exception {
		
		this.loginRequestDTO.setEmail("ivana@gmail.com");
		this.loginRequestDTO.setPassword("wrongpassword");
		
		this.mockMvc.perform(post("/api/users/login").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(Utility.json(this.loginRequestDTO)))
				.andExpect(status().isNotFound())
				.andExpect(content().contentType(MediaType.TEXT_PLAIN))
				.andExpect(content().string("Wrong email or password!"));		
	}
	
	@Test
	public void successfulLogin() throws IOException, Exception {
		
		this.loginRequestDTO.setEmail("ivana@gmail.com");
		this.loginRequestDTO.setPassword("ivana");
		
		this.mockMvc.perform(post("/api/users/login").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(Utility.json(this.loginRequestDTO)))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.token").exists());
	}

}
