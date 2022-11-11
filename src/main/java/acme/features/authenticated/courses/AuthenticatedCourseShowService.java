package acme.features.authenticated.courses;
/*
 * AuthenticatedConsumerCreateService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.courses.Course;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedCourseShowService implements AbstractShowService<Authenticated, Course> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedCourseRepository repository;

	@Override
	public boolean authorise(final Request<Course> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Course> request, final Course entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "ticker", "caption", "abstractCourse", "link");
		int id;
		id = request.getModel().getInteger("id");
		final Collection<Object[]> costs;
		costs = this.repository.costByCurrency(id);
		String res = "";
		int i = 0;
		for(final Object[] obj: costs) {
			
			final Double aux = Double.parseDouble(obj[0].toString());
			final NumberFormat formatter = new DecimalFormat("#0.00");
			final String formattedNumber = formatter.format(aux);
			
			if(i == 0) {
				
				res =res.concat(formattedNumber).concat(obj[1].toString());
			}else {
				res = res.concat("+").concat(formattedNumber).concat(obj[1].toString());
			}
			i++;
			
		}
		
		
		
		model.setAttribute("costs", res);
	}
	
	@Override
	public Course findOne(final Request<Course> request) {
		assert request != null;

		Course result;
		
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findCouseById(id);

		return result;
	}

	// Internal state ---------------------------------------------------------

}