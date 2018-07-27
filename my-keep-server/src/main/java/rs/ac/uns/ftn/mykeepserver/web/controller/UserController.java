package rs.ac.uns.ftn.mykeepserver.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.mykeepserver.converter.DashboardConverter;
import rs.ac.uns.ftn.mykeepserver.converter.UserConverter;
import rs.ac.uns.ftn.mykeepserver.exceptions.BadRequestException;
import rs.ac.uns.ftn.mykeepserver.model.User;
import rs.ac.uns.ftn.mykeepserver.security.TokenUtils;
import rs.ac.uns.ftn.mykeepserver.service.DashboardService;
import rs.ac.uns.ftn.mykeepserver.service.UserService;
import rs.ac.uns.ftn.mykeepserver.service.validation.UserValidationService;
import rs.ac.uns.ftn.mykeepserver.web.dto.ChangePasswordRequestDTO;
import rs.ac.uns.ftn.mykeepserver.web.dto.LoginRequestDTO;
import rs.ac.uns.ftn.mykeepserver.web.dto.LoginResponseDTO;
import rs.ac.uns.ftn.mykeepserver.web.dto.RegisterRequestDTO;
import rs.ac.uns.ftn.mykeepserver.web.dto.UpdateUserRequestDTO;
import rs.ac.uns.ftn.mykeepserver.web.dto.UserDTO;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	DashboardService dashboardService;

	@Autowired
	UserValidationService userValidationService;

	@Autowired
	UserConverter userConverter;

	@Autowired
	DashboardConverter dashboardConverter;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private TokenUtils tokenUtils;
	@Value("${token.header}")
	private String tokenHeader;


	@RequestMapping(
			value = "/register",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<UserDTO> registration(@RequestBody RegisterRequestDTO request) {

		userValidationService.validateIfEmailIsUnique(request.getEmail());

		User user = userConverter.convert(request);

		userService.save(user);

		UserDTO response =  userConverter.convert(user);

		return new ResponseEntity<UserDTO>(response, HttpStatus.CREATED);

	}

	@RequestMapping(
			value = "/login", 
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
		Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		SecurityContextHolder.getContext().setAuthentication(authentication);


		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
		String token = this.tokenUtils.generateToken(userDetails);
		User user = userService.findByEmail(request.getEmail());
		
		LoginResponseDTO response = userConverter.convert(user, token);
	
		return new ResponseEntity<LoginResponseDTO>(response, HttpStatus.OK);

	}

	@RequestMapping(value = "/logout",
			method = RequestMethod.GET)
	public ResponseEntity<Void> logoutUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)){
			SecurityContextHolder.clearContext();
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			throw new BadRequestException("User is not authenticated!");
		}

	}

	@RequestMapping(
			value = "/me",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<UserDTO> getMe(Authentication authentication) {

		userValidationService.validateIfUserExist(authentication);

		User user = userService.findByEmail(authentication.getName());

		UserDTO response =  userConverter.convert(user);

		return new ResponseEntity<UserDTO>(response, HttpStatus.OK);
	}


	@RequestMapping(
			value = "/{id}",
			method = RequestMethod.PATCH,
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<UserDTO> changePassword(@PathVariable int id, @RequestBody ChangePasswordRequestDTO request, Authentication authentication) {

		userValidationService.validateIfUserExist(authentication);		
		userValidationService.validateIfPasswordMatch(request.getNewPassword(), request.getRepeatedNewPassword());

		User user = userService.changePassword(id, request.getNewPassword());
		UserDTO response =  userConverter.convert(user);

		return new ResponseEntity<UserDTO>(response, HttpStatus.CREATED);
	} 


	@RequestMapping(
			value = "/{id}",
			method = RequestMethod.PUT
			)
	public ResponseEntity<UserDTO> update(@PathVariable int id, @RequestBody UpdateUserRequestDTO request, Authentication authentication) {

		userValidationService.validateIfUserExist(authentication);

		User user =  userConverter.convert(request);

		User updatedUser = userService.update(id, user);

		UserDTO response =  userConverter.convert(updatedUser);

		return new ResponseEntity<UserDTO>(response, HttpStatus.CREATED);
	} 

}
