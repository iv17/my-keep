package rs.ac.uns.ftn.mykeepserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rs.ac.uns.ftn.mykeepserver.model.Widget;

public interface WidgetRepository extends JpaRepository<Widget, Integer> {

	Widget findById(int id);

	@Query("SELECT DISTINCT widget FROM Widget widget "
			+ "WHERE ( "
			+ "widget.title LIKE CONCAT ('%', :search, '%') OR "
			+ "widget.content LIKE CONCAT ('%', :search, '%') OR "
			+ "widget.date LIKE CONCAT ('%', :search, '%')"
			+ ")")
	List<Widget> findBySearch(@Param("search") String search);

}
