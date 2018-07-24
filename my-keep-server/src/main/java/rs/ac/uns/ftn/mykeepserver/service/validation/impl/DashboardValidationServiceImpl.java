package rs.ac.uns.ftn.mykeepserver.service.validation.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rs.ac.uns.ftn.mykeepserver.exceptions.BadRequestException;
import rs.ac.uns.ftn.mykeepserver.service.DashboardService;
import rs.ac.uns.ftn.mykeepserver.service.validation.DashboardValidationService;

@Component
public class DashboardValidationServiceImpl implements DashboardValidationService {

	@Autowired
	private DashboardService dashboardService;

	String exception = "Dashboard doesn't exist!";

	@Override
	public void validateIfDashboardExist(int id) {
		if(dashboardService.findById(id) == null) {
			throw new BadRequestException(exception);
		}
	}

}
