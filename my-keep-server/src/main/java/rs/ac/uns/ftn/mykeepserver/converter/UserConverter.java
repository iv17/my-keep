package rs.ac.uns.ftn.mykeepserver.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import rs.ac.uns.ftn.mykeepserver.model.Dashboard;
import rs.ac.uns.ftn.mykeepserver.model.DashboardType;
import rs.ac.uns.ftn.mykeepserver.model.User;
import rs.ac.uns.ftn.mykeepserver.web.dto.LoginRequestDTO;
import rs.ac.uns.ftn.mykeepserver.web.dto.LoginResponseDTO;
import rs.ac.uns.ftn.mykeepserver.web.dto.RegisterRequestDTO;
import rs.ac.uns.ftn.mykeepserver.web.dto.UpdateUserRequestDTO;
import rs.ac.uns.ftn.mykeepserver.web.dto.UserDTO;

@Component
public class UserConverter {

	@Autowired
	private DashboardConverter dashboardConverter;
	
	
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	//==================== LOGIN ====================
	public User convert(LoginRequestDTO dto) {
		User user = new User();
		user.setEmail(dto.getEmail());
		user.setPassword(encoder.encode(dto.getPassword()));
		
		return user;
	}
	
	public LoginResponseDTO convert(User user, String token) {
		UserDTO userDTO = new UserDTO();
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setEmail(user.getEmail());
		userDTO.setNotesDashboard(dashboardConverter.convert(user.getNotesDashboard()));
		userDTO.setArchiveDashboard(dashboardConverter.convert(user.getArchiveDashboard()));
		userDTO.setTrashBashboard(dashboardConverter.convert(user.getTrashDashboard()));
		
		LoginResponseDTO dto = new LoginResponseDTO();
		dto.setUser(userDTO);
		dto.setToken(token);
		
		return dto;
	}
	
	//==================== REGISTER ====================
	public User convert(RegisterRequestDTO dto) {
		User user = new User();
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setEmail(dto.getEmail());
		user.setPassword(encoder.encode(dto.getPassword()));
		
		Dashboard notesDasboard = new Dashboard();
		notesDasboard.setDashboardType(DashboardType.NOTES);
		Dashboard archiveDashboard = new Dashboard();
		archiveDashboard.setDashboardType(DashboardType.ARCHIVE);
		Dashboard trashDashboard = new Dashboard();
		trashDashboard.setDashboardType(DashboardType.TRASH);
		
		user.setNotesDashboard(notesDasboard);
		user.setArchiveDashboard(archiveDashboard);
		user.setTrashDashboard(trashDashboard);
		
		return user;
	}
	
	public UserDTO convert(User user) {
		UserDTO dto = new UserDTO();
		dto.setId(user.getId());
		dto.setFirstName(user.getFirstName());
		dto.setLastName(user.getLastName());
		dto.setEmail(user.getEmail());
			
		dto.setNotesDashboard(dashboardConverter.convert(user.getNotesDashboard()));
		dto.setArchiveDashboard(dashboardConverter.convert(user.getArchiveDashboard()));
		dto.setTrashBashboard(dashboardConverter.convert(user.getTrashDashboard()));
		
		return dto;
	}
	//==================== UPDATE ====================
	public User convert(UpdateUserRequestDTO dto) {
		User user = new User();
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		
		return user;
	}
}
