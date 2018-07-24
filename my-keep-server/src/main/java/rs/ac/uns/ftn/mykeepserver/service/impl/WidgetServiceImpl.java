package rs.ac.uns.ftn.mykeepserver.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.mykeepserver.model.Widget;
import rs.ac.uns.ftn.mykeepserver.repository.WidgetRepository;
import rs.ac.uns.ftn.mykeepserver.service.WidgetService;

@Service
public class WidgetServiceImpl implements WidgetService {

	@Autowired
	private WidgetRepository widgetRepository;
	
	
	@Override
	public Widget findById(int id) {
		return widgetRepository.findById(id);
	}

	@Override
	public Widget save(Widget widget) {
		return widgetRepository.save(widget);
	}

	@Override
	public void remove(Widget widget) {
		widgetRepository.delete(widget);
		
	}

	@Override
	public List<Widget> findBySearch(String search) {
		return widgetRepository.findBySearch(search);
	}

}
