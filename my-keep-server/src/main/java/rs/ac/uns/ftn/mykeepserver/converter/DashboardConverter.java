package rs.ac.uns.ftn.mykeepserver.converter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rs.ac.uns.ftn.mykeepserver.model.Dashboard;
import rs.ac.uns.ftn.mykeepserver.model.Widget;
import rs.ac.uns.ftn.mykeepserver.web.dto.DashboardDTO;
import rs.ac.uns.ftn.mykeepserver.web.dto.WidgetDTO;

@Component
public class DashboardConverter {
	
	@Autowired
	private WidgetConverter widgetConverter;
	
	//==================== UPDATE ====================
	public Dashboard convert(DashboardDTO dto) {
		Dashboard dashboard = new Dashboard();
		if(dto.getWidgets() != null) {
			Set<Widget> widgets = new HashSet<>();
			for (WidgetDTO widgetDTO : dto.getWidgets()) {
				Widget widget = widgetConverter.convert(widgetDTO);
				widgets.add(widget);
			}
			dashboard.setWidgets(widgets);
		}
		
		return dashboard;
	}
	
	public DashboardDTO convert(Dashboard dashboard) {
		DashboardDTO dto = new DashboardDTO();
		if(dashboard.getWidgets() != null) {
			List<WidgetDTO> widgets = new ArrayList<>();
			for (Widget widget : dashboard.getWidgets()) {
				widgets.add(widgetConverter.convert(widget));
			}
			dto.setWidgets(widgets);
		}
		
		return dto;
	}
	
}
