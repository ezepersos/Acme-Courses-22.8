package acme.features.authenticated.teacher;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.framework.controllers.AbstractController;
import acme.framework.roles.Authenticated;
import acme.roles.Teacher;

@Controller
public class AuthenticatedTeacherController extends AbstractController<Authenticated, Teacher> {
		
	// Internal state ---------------------------------------------------------

		@Autowired
		protected AuthenticatedTeacherCreateService	createService;

		// Constructors -----------------------------------------------------------


		@PostConstruct
		protected void initialise() {
			super.addCommand("create", this.createService);
		}

	}