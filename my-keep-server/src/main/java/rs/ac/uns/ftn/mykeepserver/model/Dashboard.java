package rs.ac.uns.ftn.mykeepserver.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "dashboards")
public class Dashboard implements Serializable {

	private static final long serialVersionUID = -9056970010173391664L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false , unique = true)
	private int id;

	@Column(name = "type")
	private DashboardType dashboardType;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "dashboard")
	private Set<Widget> widgets;

	public Dashboard() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DashboardType getDashboardType() {
		return dashboardType;
	}

	public void setDashboardType(DashboardType dashboardType) {
		this.dashboardType = dashboardType;
	}

	public Set<Widget> getWidgets() {
		return widgets;
	}

	public void setWidgets(Set<Widget> widgets) {
		this.widgets = widgets;
	}

}
