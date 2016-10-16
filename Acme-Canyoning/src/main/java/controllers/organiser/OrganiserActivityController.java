package controllers.organiser;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActivityService;
import services.CanyonService;
import services.OrganiserService;
import services.RequestService;
import controllers.AbstractController;
import domain.Activity;
import domain.Canyon;
import domain.Organiser;

@Controller
@RequestMapping("/activity/organiser")
public class OrganiserActivityController extends AbstractController {

	// Supporting services ----------------------------------------------------
	@Autowired
	private OrganiserService organiserService;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private CanyonService canyonService;

	// Constructors -----------------------------------------------------------
	public OrganiserActivityController() {
		super();
	}

	// List -------------------------------------------------------------------
	@RequestMapping("/mylist")
	public ModelAndView list() {
		ModelAndView result;
		Collection<Activity> activities;

		activities = activityService.findActivitiesByOrganiser();

		result = new ModelAndView("activity/list");
		result.addObject("requestUri", "/activity/mylist.do");
		result.addObject("activities", activities);

		return result;
	}

	// Create---------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Activity activity;
		Collection<Canyon> canyons;

		activity = activityService.create();
		canyons = canyonService.findAll();

		result = new ModelAndView("activity/create");
		result.addObject("activity", activity);
		result.addObject("canyons", canyons);

		return result;
	}

	// Edit
	// -------------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int activityId) {
		ModelAndView result;
		Activity activity;
		Collection<Canyon> canyons;

		activity = activityService.findOne(activityId);
		canyons = canyonService.findAll();

		result = new ModelAndView("activity/edit");
		result.addObject("activity", activity);
		result.addObject("canyons", canyons);

		return result;
	}

	// Save -------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid Activity activity, BindingResult binding) {
		ModelAndView result;
		Collection<Canyon> canyons;
		canyons = canyonService.findAll();
		if (binding.hasErrors()) {
			System.out.print(binding.getFieldError());
			System.out.print(binding.getAllErrors());
			canyons = canyonService.findAll();

			result = new ModelAndView("activity/edit");
			result.addObject("activity", activity);
			result.addObject("canyons", canyons);
		} else {
			try {
				activityService.save(activity);
				result = new ModelAndView("redirect:/activity/list.do");
			} catch (Throwable oops) {
				canyons = canyonService.findAll();

				result = new ModelAndView("activity/edit");
				result.addObject("activity", activity);
				result.addObject("canyons", canyons);
				result.addObject("message2", "activity.commit.error");
			}
		}
		return result;
	}

	// reinstantiate
	// Activity---------------------------------------------------------------------
	@RequestMapping(value = "/reinstantiate", method = RequestMethod.POST, params = "reinstantiate")
	public ModelAndView reinstantiate(@Valid Activity activity, BindingResult binding) {
		ModelAndView result;
		Organiser organiser;

		if (binding.hasErrors()) {
			System.out.print(binding.getFieldError());
			System.out.print(binding.getAllErrors());

			result = new ModelAndView("activity/reinstantiate");
			result.addObject("activity", activity);

		} else {
			try {
				organiser = organiserService.findByPrincipal();
				activityService.reinstantiateMomentActivity(activity,organiser);
				result = new ModelAndView("redirect:/activity/list.do");
			} catch (Throwable oops) {

				result = new ModelAndView("activity/reinstantiate");
				result.addObject("activity", activity);
				result.addObject("message2", "activity.commit.error");
			}
		}
		return result;
	}


}
