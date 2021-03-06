package rs.ac.uns.ftn.mykeepserver.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 816250384561263812L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false , unique = true)
	private int id;
	
	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "password")
	private String password;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "notes_id")
	private Dashboard notesDashboard;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "archive_id")
	private Dashboard archiveDashboard;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "trash_id")
	private Dashboard trashDashboard;
	
	
	public User() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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

	public Dashboard getNotesDashboard() {
		return notesDashboard;
	}

	public void setNotesDashboard(Dashboard notesDashboard) {
		this.notesDashboard = notesDashboard;
	}

	public Dashboard getArchiveDashboard() {
		return archiveDashboard;
	}

	public void setArchiveDashboard(Dashboard archiveDashboard) {
		this.archiveDashboard = archiveDashboard;
	}

	public Dashboard getTrashDashboard() {
		return trashDashboard;
	}

	public void setTrashDashboard(Dashboard trashDashboard) {
		this.trashDashboard = trashDashboard;
	}
	
}
