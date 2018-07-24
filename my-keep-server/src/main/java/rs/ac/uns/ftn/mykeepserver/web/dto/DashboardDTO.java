package rs.ac.uns.ftn.mykeepserver.web.dto;

import java.util.List;

import rs.ac.uns.ftn.mykeepserver.model.DashboardStatus;

public class DashboardDTO {
	
	private int id;
	private DashboardStatus status;
	private List<WidgetDTO> widgets;	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DashboardStatus getStatus() {
		return status;
	}

	public void setStatus(DashboardStatus status) {
		this.status = status;
	}

	public List<WidgetDTO> getWidgets() {
		return widgets;
	}

	public void setWidgets(List<WidgetDTO> widgets) {
		this.widgets = widgets;
	}

}
