package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActivityService;
import services.CanyonService;
import services.CordService;
import services.CustomerService;
import services.KayakService;
import services.PieceEquipmentService;
import services.StoryService;
import services.WetsuitService;
import controllers.AbstractController;
import domain.Activity;
import domain.Canyon;

@Controller
@RequestMapping("/dashboard/administrator")
public class DashboardAdministratorController extends AbstractController {

	// Services -----------------------
	@Autowired
	private CustomerService customerService;

	@Autowired
	private ActivityService activityService;

	@Autowired
	private KayakService kayakService;

	@Autowired
	private CordService cordService;

	@Autowired
	private WetsuitService wetsuitService;

	@Autowired
	private PieceEquipmentService pieceEquipmentService;

	@Autowired
	private CanyonService canyonService;

	@Autowired
	private StoryService storyService;

	// Constructor --------------------
	public DashboardAdministratorController() {
		super();
	}

	// Listing ------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		// --------C-------------
		// The average number of activities per organiser.
		Double averageActivitiesPerOrganiser = activityService
				.averageNumberOfActivitiesByOrganisers();
		// The average number of customers in the waiting lists.
		Double averageCustomersInWaitingList = customerService
				.averageCustomersInWaitingList();
		// The average number of seats offered in the activities that are going
		// to be organised in the forthcoming three months.
		Double averageSeatsOrganisedThreeMonths = activityService
				.averageSeatsOrganisedThreeMonths();
		// 10% more than the average.
		Collection<Activity> activity10MoreAverage = activityService
				.findWithMoreTenPercentOfSeatsAvg();
		// 10% less than the average.
		Collection<Activity> activity10LessAverage = activityService
				.findWithLessTenPercentOfSeatsAvg();
		// The average of the time that a customer remains in a waiting list.
		Double averageTimeRemainWaitingList = customerService
				.averageTimeRemainWaitingList();
		// The standard deviation of the time that a customer remains in a
		// waiting list.
		Double stdTimeRemainWaitingList = customerService
				.stdTimeRemainWaitingList();

		// --------B-------------
		// The total number of kayaks, cords, and wetsuits that the organisers
		// have registered.
		Integer totalNumberKayaks = kayakService.numberTotalKayaks();
		Integer totalNumberCords = cordService.numberTotalCords();
		Integer totalNumberWetsuits = wetsuitService.numberTotalWetsuits();

		// The average number of pieces of equipment required per activity.
		Double averagePiecesPerActivity = pieceEquipmentService
				.averagePiecesPerActivity();

		// The average number of kayaks per activity.
		Double averageKayaksByActivity = kayakService.averageKayaksByActivity();

		// The average number of cords per activity.
		Double averageCordsByActivity = cordService.averageCordsByActivity();

		// The average number of wetsuits per activity.
		Double averageWetsuitsByActivity = wetsuitService
				.averageWetsuitsByActivity();

		// --------A-------------

		// The average number of stories per canyon.
		Double avgStoriesPerCanyon = storyService.avgStoriesPerCanyon();

		// The min number of stories per canyon.
		Double minStoriesPerCanyon = storyService.minStoriesPerCanyon();

		// The max number of stories per canyon.
		Double maxStoriesPerCanyon = storyService.maxStoriesPerCanyon();

		// A listing of canyons sorted according to the number of stories that
		// have been written about them

		Collection<Canyon> canyonsSortedStories = canyonService
				.canyonsSortedStories();

		result = new ModelAndView("administrator/dashboard");
		result.addObject("averageActivitiesPerOrganiser",
				averageActivitiesPerOrganiser);
		result.addObject("averageCustomersInWaitingList",
				averageCustomersInWaitingList);
		result.addObject("averageSeatsOrganisedThreeMonths",
				averageSeatsOrganisedThreeMonths);

		result.addObject("activity10MoreAverage", activity10MoreAverage);
		result.addObject("activity10LessAverage", activity10LessAverage);
		result.addObject("averageTimeRemainWaitingList",
				averageTimeRemainWaitingList);
		result.addObject("stdTimeRemainWaitingList", stdTimeRemainWaitingList);

		result.addObject("totalNumberKayaks", totalNumberKayaks);
		result.addObject("totalNumberCords", totalNumberCords);
		result.addObject("totalNumberWetsuits", totalNumberWetsuits);
		result.addObject("averagePiecesPerActivity", averagePiecesPerActivity);
		result.addObject("averageKayaksByActivity", averageKayaksByActivity);
		result.addObject("averageCordsByActivity", averageCordsByActivity);
		result.addObject("averageWetsuitsByActivity", averageWetsuitsByActivity);

		result.addObject("avgStoriesPerCanyon", avgStoriesPerCanyon);
		result.addObject("minStoriesPerCanyon", minStoriesPerCanyon);
		result.addObject("maxStoriesPerCanyon", maxStoriesPerCanyon);
		result.addObject("canyonsSortedStories", canyonsSortedStories);
		return result;
	}
}
