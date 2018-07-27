package rs.ac.uns.ftn.mykeepserver.service.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.mykeepserver.model.Dashboard;
import rs.ac.uns.ftn.mykeepserver.model.Widget;
import rs.ac.uns.ftn.mykeepserver.repository.DashboardRepository;
import rs.ac.uns.ftn.mykeepserver.repository.WidgetRepository;
import rs.ac.uns.ftn.mykeepserver.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	DashboardRepository dashboardRepository;

	@Autowired
	WidgetRepository widgetRepository;

	@Override
	public Dashboard findById(int id) {
		return dashboardRepository.findById(id);
	}

	@Override
	public Dashboard update(int id, Dashboard request) {

		Dashboard dashboard = findById(id);

		if(!dashboard.getWidgets().equals(request.getWidgets())) {
			if(dashboard.getWidgets().isEmpty()) {
				for (Widget widget : request.getWidgets()) {
					widget.setDashboard(dashboard);
					dashboard.getWidgets().add(widget);
				}
				dashboardRepository.save(dashboard);
				return dashboard;

			} else {
				for (Widget widget : request.getWidgets()) {
					if(widget.getId() == -1) {
						widget.setDashboard(dashboard);
						dashboard.getWidgets().add(widget);

						dashboardRepository.save(dashboard);
					} 
				}

				for (Widget widget : request.getWidgets()) {
					for (Widget w : dashboard.getWidgets()) {
						if(w.getId() == widget.getId() && !w.equals(widget)) {
							w.setTitle(widget.getTitle());
							w.setContent(widget.getContent());
							w.setDate(widget.getDate());
							w.setDragAndDrop(widget.isDragAndDrop());
							w.setResizable(widget.isResizable());
							w.setX(widget.getX());
							w.setY(widget.getY());
							w.setW(widget.getW());
							w.setH(widget.getH());
							w.setxSm(widget.getxSm());
							w.setySm(widget.getySm());
							w.setwSm(widget.getwSm());
							w.sethSm(widget.gethSm());
							w.setxMd(widget.getxMd());
							w.setyMd(widget.getyMd());
							w.setwMd(widget.getwMd());
							w.sethMd(widget.gethMd());
							w.setxLg(widget.getxLg());
							w.setyLg(widget.getyLg());
							w.setwLg(widget.getwLg());
							w.sethLg(widget.gethLg());
							w.setxXl(widget.getxXl());
							w.setyXl(widget.getyXl());
							w.setwXl(widget.getwXl());
							w.sethXl(widget.gethXl());
						} 
					} 
					dashboardRepository.save(dashboard);

				}
				return dashboard;

			}
		}
		return dashboard;
	}

	@Override
	public Dashboard changeDashboard(int id, Dashboard request) {
		Dashboard dashboard = findById(id);

		if(dashboard.getWidgets().isEmpty()) {
			for (Widget widget : request.getWidgets()) {
				widget.setDashboard(dashboard);
				dashboard.getWidgets().add(widget);
			}
			dashboardRepository.save(dashboard);
			return dashboard;

		} else {
			//substract(a,b) --> a - b
			//U request-u su svi podaci, i oni u bazi i oni koje treba sacuvati
			@SuppressWarnings("rawtypes")
			Collection result = CollectionUtils.subtract(request.getWidgets(), dashboard.getWidgets());
			for (Object o : result) {
				Widget widget = (Widget) o;
				dashboard.getWidgets().add(widget);
				dashboardRepository.save(dashboard);
			}
		}

		return dashboard;
	}

	@Override
	public Dashboard clearDashboard(int id) {
		Dashboard dashboard = findById(id);
		dashboard.getWidgets().clear();
		dashboardRepository.save(dashboard);

		return dashboard;
	}

	@Override
	public Dashboard search(int id, String search) {
		Dashboard dashboard = findById(id);

		List<Widget> widgets = widgetRepository.findBySearch(search);
		Set<Widget> set = new HashSet<>();
		if(widgets.isEmpty()) {
			set = dashboard.getWidgets();
		} else {
			for (Widget widget : widgets) {
				set.add(widget);
			}
		}

		dashboard.setWidgets(set);
		return dashboard;
	}

}
