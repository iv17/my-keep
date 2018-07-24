package rs.ac.uns.ftn.mykeepserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.mykeepserver.model.Dashboard;
import rs.ac.uns.ftn.mykeepserver.model.DashboardStatus;

public interface DashboardRepository extends JpaRepository<Dashboard, Integer> {

	Dashboard findById(int id);
	
	Dashboard findByDashboardStatus(DashboardStatus dashboardStatus);
	
}
