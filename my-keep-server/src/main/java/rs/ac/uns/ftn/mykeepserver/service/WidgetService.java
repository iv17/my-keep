package rs.ac.uns.ftn.mykeepserver.service;

import java.util.List;

import rs.ac.uns.ftn.mykeepserver.model.Widget;

public interface WidgetService {

	public Widget findById(int id);
	public Widget save(Widget widget);
	public void remove(Widget widget);
	public List<Widget> findBySearch(String search);
	
}
