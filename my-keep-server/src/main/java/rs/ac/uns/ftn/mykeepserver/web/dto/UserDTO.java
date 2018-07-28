package rs.ac.uns.ftn.mykeepserver.web.dto;

public class UserDTO {

	private String firstName;
	private String lastName;
	private String email;
	private DashboardDTO notesDashboard;
	private DashboardDTO archiveDashboard;
	private DashboardDTO trashDashboard;
	private int notesId;
	private int archiveId;
	private int trashId;


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

	public DashboardDTO getTrashDashboard() {
		return trashDashboard;
	}

	public void setTrashDashboard(DashboardDTO trashDashboard) {
		this.trashDashboard = trashDashboard;
	}

	public int getNotesId() {
		return notesId;
	}

	public void setNotesId(int notesId) {
		this.notesId = notesId;
	}

	public int getArchiveId() {
		return archiveId;
	}

	public void setArchiveId(int archiveId) {
		this.archiveId = archiveId;
	}

	public int getTrashId() {
		return trashId;
	}

	public void setTrashId(int trashId) {
		this.trashId = trashId;
	}

}
