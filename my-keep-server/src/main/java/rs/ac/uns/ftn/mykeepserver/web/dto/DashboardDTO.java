package rs.ac.uns.ftn.mykeepserver.web.dto;

import java.util.List;

import rs.ac.uns.ftn.mykeepserver.model.DashboardType;

public class DashboardDTO {
	
	private int id;
	private DashboardType type;
	private List<WidgetDTO> widgets;	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DashboardType getType() {
		return type;
	}

	public void setType(DashboardType type) {
		this.type = type;
	}

	public List<WidgetDTO> getWidgets() {
		return widgets;
	}

	public void setWidgets(List<WidgetDTO> widgets) {
		this.widgets = widgets;
	}

}
