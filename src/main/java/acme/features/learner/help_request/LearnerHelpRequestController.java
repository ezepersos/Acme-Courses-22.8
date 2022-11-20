package acme.features.learner.help_request;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.help_requests.HelpRequest;
import acme.framework.controllers.AbstractController;
import acme.roles.Learner;

@Controller
public class LearnerHelpRequestController extends AbstractController<Learner, HelpRequest>{

	// Internal state
	
	@Autowired
	protected LearnerHelpRequestListService listService;
	
	@Autowired
	protected LearnerHelpRequestShowService showService;
	
	@Autowired
	protected LearnerHelpRequestCreateService	createHelpRequestService;

	@Autowired
	protected LearnerHelpRequestDeleteService	deleteService;

	@Autowired
	protected LearnerHelpRequestUpdateService	updateService;

	@Autowired
	protected LearnerHelpRequestPublishService publishService;
	
	// Constructors
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("create", this.createHelpRequestService);
		super.addCommand("delete", this.deleteService);
		super.addCommand("update", this.updateService);
		super.addCommand("publish", "update", this.publishService);
	}
}
