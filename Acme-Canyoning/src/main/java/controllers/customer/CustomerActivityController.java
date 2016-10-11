package controllers.customer;

import org.springframework.beans.factory.annotation.Autowired;

import services.ActivityService;
import services.AdministratorService;
import services.CanyonService;
import services.CommentService;
import services.CustomerService;
import controllers.AbstractController;

public class CustomerActivityController extends AbstractController {

	// Supporting services ----------------------------------------------------
	@Autowired
	private CustomerService customerSService;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private CanyonService canyonService;
	@Autowired
	private AdministratorService administratorService;
	@Autowired
	private CommentService commentService;

	// Constructors -----------------------------------------------------------
	public CustomerActivityController() {
		super();
	}

}
