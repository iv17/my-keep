package rs.ac.uns.ftn.mykeepserver.web.dto;

import java.util.List;

public class DashboardDTO {
	
	private List<WidgetDTO> widgets;	

	public List<WidgetDTO> getWidgets() {
		return widgets;
	}

	public void setWidgets(List<WidgetDTO> widgets) {
		this.widgets = widgets;
	}

}
