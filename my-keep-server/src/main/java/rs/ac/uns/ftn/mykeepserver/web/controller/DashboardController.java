package rs.ac.uns.ftn.mykeepserver.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.mykeepserver.converter.DashboardConverter;
import rs.ac.uns.ftn.mykeepserver.model.Dashboard;
import rs.ac.uns.ftn.mykeepserver.service.DashboardService;
import rs.ac.uns.ftn.mykeepserver.service.validation.DashboardValidationService;
import rs.ac.uns.ftn.mykeepserver.service.validation.UserValidationService;
import rs.ac.uns.ftn.mykeepserver.web.dto.DashboardDTO;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/dashboards")
public class DashboardController {

	@Autowired
	private DashboardService dashboardService;

	@Autowired
	private UserValidationService userValidationService;

	@Autowired
	private DashboardValidationService dashboardValidationService;

	@Autowired
	private DashboardConverter dashboardConverter;


	@RequestMapping(
			value = "/{id}",
			method = RequestMethod.GET
			)
	public ResponseEntity<DashboardDTO> getById(@PathVariable int id, Authentication authentication) {

		userValidationService.validateIfUserExist(authentication);
		dashboardValidationService.validateIfDashboardExist(id);

		Dashboard dashboard = dashboardService.findById(id);

		DashboardDTO response = dashboardConverter.convert(dashboard);

		return new ResponseEntity<DashboardDTO>(response, HttpStatus.OK);
	}

	@RequestMapping(
			value = "/{id}",
			method = RequestMethod.PUT
			)
	public ResponseEntity<DashboardDTO> update(@PathVariable int id, @RequestBody DashboardDTO request, Authentication authentication) {

		userValidationService.validateIfUserExist(authentication);
		dashboardValidationService.validateIfDashboardExist(id);

		Dashboard dashboard = dashboardConverter.convert(request);

		Dashboard updatedDashboard = dashboardService.update(id, dashboard);

		DashboardDTO response = dashboardConverter.convert(updatedDashboard);

		return new ResponseEntity<DashboardDTO>(response, HttpStatus.CREATED);
	}
	
	@RequestMapping(
			value = "/{id}/change",
			method = RequestMethod.PUT
			)
	public ResponseEntity<DashboardDTO> change(@PathVariable int id, @RequestBody DashboardDTO request, Authentication authentication) {

		userValidationService.validateIfUserExist(authentication);
		dashboardValidationService.validateIfDashboardExist(id);

		Dashboard dashboard = dashboardConverter.convert(request);

		Dashboard updatedDashboard = dashboardService.changeDashboard(id, dashboard);

		DashboardDTO response = dashboardConverter.convert(updatedDashboard);

		return new ResponseEntity<DashboardDTO>(response, HttpStatus.CREATED);
	}


	@RequestMapping(
			value = "/{id}",
			method = RequestMethod.DELETE
			)
	public ResponseEntity<DashboardDTO> delete(@PathVariable int id, Authentication authentication) {

		userValidationService.validateIfUserExist(authentication);
		dashboardValidationService.validateIfDashboardExist(id);

		Dashboard dashboard = dashboardService.clearDashboard(id);

		DashboardDTO response = dashboardConverter.convert(dashboard);
		
		return new ResponseEntity<DashboardDTO>(response, HttpStatus.OK);
	}

	@RequestMapping(
			value = "/{id}/search",
			method = RequestMethod.GET
			)
	public ResponseEntity<DashboardDTO> search(@PathVariable int id, @RequestParam String search, Authentication authentication) {

		userValidationService.validateIfUserExist(authentication);
		dashboardValidationService.validateIfDashboardExist(id);

		Dashboard dashboard = dashboardService.search(id, search);

		DashboardDTO response = dashboardConverter.convert(dashboard);

		return new ResponseEntity<DashboardDTO>(response, HttpStatus.OK);
	}


}
