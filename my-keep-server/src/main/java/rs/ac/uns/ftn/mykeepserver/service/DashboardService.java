package rs.ac.uns.ftn.mykeepserver.service;

import rs.ac.uns.ftn.mykeepserver.model.Dashboard;
import rs.ac.uns.ftn.mykeepserver.model.DashboardStatus;

public interface DashboardService {

	public Dashboard findById(int id);
	public Dashboard findByDashboardStatus(DashboardStatus dashboardStatus);
	public Dashboard save(Dashboard dashboard);
	public Dashboard update(int id, Dashboard dashboard);
	public Dashboard changeDashboard(int id, Dashboard request);
	public Dashboard clearDashboard(int id);
	public Dashboard search(int id, String search);

}
