package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Banner;
import domain.Customer;
import domain.Organiser;
import services.BannerService;
import services.CampaignService;
import services.DailyPlanService;
import services.ManagerService;
import services.TripService;
import services.UserService;

@Controller
@RequestMapping("/dashboard/administrator")
public class DashboardAdministratorController extends AbstractController {

	// Services -----------------------
	@Autowired
	private UserService userService;
	@Autowired
	private ManagerService managerService;
	@Autowired
	private TripService tripService;
	@Autowired
	private DailyPlanService dailyplanService;
	@Autowired
	private CampaignService campaignService;
	@Autowired
	private BannerService bannerService;

	// Constructor --------------------
	public DashboardAdministratorController() {
		super();
	}

	// Listing ------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		// The total number of users who have registered to the system
		int totalNumberUsers = userService.totalNumberofUsersRegistered();

		// The total number of trips that have been registered
		int totalTripsRegistered = tripService.totalNumberOfTripsRegistered();

		// The average number of trips per user
		Double averageTripsPerUser = tripService.averageNumberOfTripsByUsers();
		// standard deviation of trips per user
		Double desviationTripsUser = tripService.standardDeviationOfTripsByUsers();

		// The average number of daily plans per trip
		Double averageDailyplansPerTrip = dailyplanService.averageNumberOfDailyPlansByTrip();
		// standard deviation of daily plans per trip
		Double desviationDailyplansTrip = dailyplanService.standardDeviationOfDailyPlansByTrip();

		// The users who have registered at least 80% the maximum number of
		// trips that a user has registered
		Collection<Organiser> users80percent = userService.usersWhoRegisteredAtLeast80Maximum();

		// The users who have been inactive for more than one year.
		Collection<Organiser> usersInactive = userService.inactivesOneYear();

		// The managers who have registered more campaigns.
		Collection<Customer> managersMoreCampaigns = managerService.managersMoreCampaigns();

		// The minimum, the maximum and the average number of campaigns per
		// manager
		int minimum = managerService.minimumNumberOfCampaignsPerManager();
		int maximum = managerService.maximumNumberOfCampaignsPerManager();
		double average = managerService.averageNumberOfCampaignsPerManager();

		// The average amount of money that has been charged per campaign.
		Double averageAmount = campaignService.averageCharge();

		// The average number of days that the campaigns last and the
		// corresponding standard deviation.
		Double averageCampaignsLast = campaignService.averageNumberDaysCampaignsLast();
		Double standardDesviationCampaignsLast = campaignService.standardDeviationOfNumberDaysCampaignsLast();

		// The banners in the active campaigns that have been displayed at least
		// 10% more than the average.
		Collection<Banner> banners10MoreAverage = bannerService.banners10Average();

		// The banners in the active campaigns that have been displayed at least
		// 10% less than the average.
		Collection<Banner> banners10LessAverage = bannerService.banners10LessAverage();

		result = new ModelAndView("administrator/dashboard");
		result.addObject("totalNumberUsers", totalNumberUsers);
		result.addObject("totalTripsRegistered", totalTripsRegistered);
		result.addObject("averageTripsPerUser", averageTripsPerUser);
		result.addObject("desviationTripsUser", desviationTripsUser);
		result.addObject("averageDailyplansPerTrip", averageDailyplansPerTrip);
		result.addObject("desviationDailyplansTrip", desviationDailyplansTrip);
		result.addObject("users80percent", users80percent);
		result.addObject("usersInactive", usersInactive);

		result.addObject("minimum", minimum);
		result.addObject("maximum", maximum);
		result.addObject("average", average);
		result.addObject("averageAmount", averageAmount);
		result.addObject("averageCampaignsLast", averageCampaignsLast);
		result.addObject("standardDesviationCampaignsLast", standardDesviationCampaignsLast);
		result.addObject("managersMoreCampaigns", managersMoreCampaigns);

		result.addObject("banners10MoreAverage", banners10MoreAverage);
		result.addObject("banners10LessAverage", banners10LessAverage);

		return result;
	}

}
