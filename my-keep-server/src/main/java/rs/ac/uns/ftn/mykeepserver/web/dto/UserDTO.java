package rs.ac.uns.ftn.mykeepserver.web.dto;

public class UserDTO {

	private String firstName;
	private String lastName;
	private String email;
	private DashboardDTO notesDashboard;
	private DashboardDTO archiveDashboard;
	private DashboardDTO trashBashboard;

	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public DashboardDTO getNotesDashboard() {
		return notesDashboard;
	}

	public void setNotesDashboard(DashboardDTO notesDashboard) {
		this.notesDashboard = notesDashboard;
	}

	public DashboardDTO getArchiveDashboard() {
		return archiveDashboard;
	}

	public void setArchiveDashboard(DashboardDTO archiveDashboard) {
		this.archiveDashboard = archiveDashboard;
	}

	public DashboardDTO getTrashBashboard() {
		return trashBashboard;
	}

	public void setTrashBashboard(DashboardDTO trashBashboard) {
		this.trashBashboard = trashBashboard;
	}
	
}
