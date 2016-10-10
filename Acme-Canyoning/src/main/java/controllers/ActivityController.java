package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActivityService;
import services.AdministratorService;
import services.CanyonService;
import services.CommentService;
import domain.Activity;
import domain.Administrator;
import domain.Canyon;
import domain.Comment;

@Controller
@RequestMapping("/activity")
public class ActivityController extends AbstractController {

	// Supporting services ----------------------------------------------------
	@Autowired
	private ActivityService activityService;
	@Autowired
	private CanyonService canyonService;
	@Autowired
	private AdministratorService administratorService;
	@Autowired
	private CommentService commentService;

	// Constructors -----------------------------------------------------------
	public ActivityController() {
		super();
	}

	// List
	// ---------------------------------------------------------------------------
	@RequestMapping(value = "/list")
	public ModelAndView list() {

		ModelAndView result;
		Collection<Activity> activities;

		activities = activityService.findAll();

		result = new ModelAndView("activity/list");
		result.addObject("activities", activities);
		result.addObject("requestUri", "/activity/list.do");

		return result;
	}

	// Display --------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(int activityId) {
		ModelAndView result;
		Activity activity;
		Collection<Comment> comments;

		comments = commentService.findCommentsByCommentableId(activityId);

		activity = activityService.findOne(activityId);
		comments = commentService.findCommentsByCommentableId(activityId);

		result = new ModelAndView("activity/display");
		result.addObject("activity", activity);

		result.addObject("comments", comments);
		return result;
	}
	// List
		// ---------------------------------------------------------------------------
		@RequestMapping(value = "/listByCanyon")
		public ModelAndView listByCanyon(@RequestParam int canyonId) {

			ModelAndView result;
			Collection<Activity> activities;
			Canyon canyon;
			Administrator administrator;
			Boolean mycanyon;
			activities = activityService.activitiesByCanyon(canyonId);
			result = new ModelAndView("activity/list");
			result.addObject("activities", activities);
			result.addObject("canyonId", canyonId);
			mycanyon = false;
			try {
				administrator = administratorService.findByPrincipal();
				canyon = canyonService.findOne(canyonId);

				mycanyon = administrator.equals(canyon.getAdministrator());

			} catch (Throwable oops) {

			}
			result.addObject("myactivity", mycanyon);
	

			return result;
		}
	
	

}
