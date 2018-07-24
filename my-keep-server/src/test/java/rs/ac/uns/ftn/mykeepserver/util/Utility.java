package rs.ac.uns.ftn.mykeepserver.util;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import rs.ac.uns.ftn.mykeepserver.web.dto.LoginRequestDTO;
import rs.ac.uns.ftn.mykeepserver.web.dto.LoginResponseDTO;

public class Utility {

	public static String json(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        
        return mapper.writeValueAsString(object);
	}
	
	public static String login(MockMvc mvc, String email, String password) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		
		LoginRequestDTO dto = new LoginRequestDTO();
		dto.setEmail(email);
		dto.setPassword(password);
		
		ResultActions retVal = mvc.perform(post("/api/users/login").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(Utility.json(dto)));
		
		LoginResponseDTO loginResponse = mapper.readValue(retVal.andReturn().getResponse().getContentAsString(), LoginResponseDTO.class);
		
		return loginResponse.getToken();
	}

}
