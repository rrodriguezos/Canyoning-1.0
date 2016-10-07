package controllers;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActivityService;
import services.CanyonService;
import services.OrganiserService;
import domain.Activity;
import domain.Canyon;
import domain.Organiser;

@Controller
@RequestMapping("/activity")
public class ActivityController extends AbstractController {

	// Supporting services ----------------------------------------------------
	@Autowired
	private ActivityService activityService;
	@Autowired
	private CanyonService canyonService;
	@Autowired
	private OrganiserService organiserService;


	// Constructors -----------------------------------------------------------
	public ActivityController() {
		super();
	}

	// List
	// ---------------------------------------------------------------------------
	@RequestMapping(value = "/list")
	public ModelAndView list(@RequestParam int canyonId) {

		ModelAndView result;
		Collection<Activity> activities;
		Canyon canyon;
		Organiser organiser;
		Boolean myActivity;
		activities = activityService.activitiesByCanyon(canyonId);
		result = new ModelAndView("activity/list");
		result.addObject("activities", activities);
		result.addObject("canyonId", canyonId);
		myActivity = false;
		try {
			organiser = organiserService.findByPrincipal();
			canyon = canyonService.findOne(canyonId);

			myActivity = organiser.equals(canyon.getAdministrator());

		} catch (Throwable oops) {

		}
		result.addObject("myactivity", myActivity);

		return result;
	}


	// Display --------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(int activityId) {
		ModelAndView result;
		Activity activity;

		activity = activityService.findOne(activityId);

		result = new ModelAndView("activity/display");
		result.addObject("activity", activity);

		return result;
	}

}
