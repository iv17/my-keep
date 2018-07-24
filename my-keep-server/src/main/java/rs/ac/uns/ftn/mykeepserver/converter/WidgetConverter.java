package rs.ac.uns.ftn.mykeepserver.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rs.ac.uns.ftn.mykeepserver.model.Widget;
import rs.ac.uns.ftn.mykeepserver.service.DashboardService;
import rs.ac.uns.ftn.mykeepserver.web.dto.WidgetDTO;

@Component
public class WidgetConverter {

	@Autowired
	DashboardService dashboardService;
	
	public WidgetDTO convert(Widget a) {
		WidgetDTO b = new WidgetDTO();
		b.setId(a.getId());
		b.setTitle(a.getTitle());
		b.setContent(a.getContent());
		b.setDate(a.getDate());
		b.setDragAndDrop(a.isDragAndDrop());
		b.setResizable(a.isResizable());
		b.setX(a.getX());
		b.setY(a.getY());
		b.setW(a.getW());
		b.setH(a.getH());
		b.setxSm(a.getxSm());
		b.setySm(a.getySm());
		b.setwSm(a.getwSm());
		b.sethSm(a.gethSm());
		b.setxMd(a.getxMd());
		b.setyMd(a.getyMd());
		b.setwMd(a.getwMd());
		b.sethMd(a.gethMd());
		b.setxLg(a.getxLg());
		b.setyLg(a.getyLg());
		b.setwLg(a.getwLg());
		b.sethLg(a.gethLg());
		b.setxXl(a.getxXl());
		b.setyXl(a.getyXl());
		b.setwXl(a.getwXl());
		b.sethXl(a.gethXl());
		b.setDashboardId(a.getDashboard().getId());
		
		return b;
	}
	
	public Widget convert(WidgetDTO a) {
		Widget b = new Widget();
		b.setId(a.getId());
		b.setTitle(a.getTitle());
		b.setContent(a.getContent());
		b.setDate(a.getDate());
		b.setDragAndDrop(a.isDragAndDrop());
		b.setResizable(a.isResizable());
		b.setX(a.getX());
		b.setY(a.getY());
		b.setW(a.getW());
		b.setH(a.getH());
		b.setxSm(a.getxSm());
		b.setySm(a.getySm());
		b.setwSm(a.getwSm());
		b.sethSm(a.gethSm());
		b.setxMd(a.getxMd());
		b.setyMd(a.getyMd());
		b.setwMd(a.getwMd());
		b.sethMd(a.gethMd());
		b.setxLg(a.getxLg());
		b.setyLg(a.getyLg());
		b.setwLg(a.getwLg());
		b.sethLg(a.gethLg());
		b.setxXl(a.getxXl());
		b.setyXl(a.getyXl());
		b.setwXl(a.getwXl());
		b.sethXl(a.gethXl());
		b.setDashboard(dashboardService.findById(a.getDashboardId()));
		
		return b;
	}
}
