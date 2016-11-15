package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import services.CanyonService;
import services.StoryService;
import domain.Administrator;
import domain.Canyon;
import domain.Story;

@Controller
@RequestMapping("/story")
public class StoryController extends AbstractController {

	// Supporting services ----------------------------------------------------
	@Autowired
	private StoryService storyService;
	@Autowired
	private CanyonService canyonService;
	@Autowired
	private AdministratorService administratorService;

	// Constructors -----------------------------------------------------------
	public StoryController() {
		super();
	}

	// Display --------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(int storyId) {
		ModelAndView result;
		Administrator administrator;
		Story story;
		Boolean myStory;
		Boolean myStoryAdministrator;
		Boolean logeado;

		story = storyService.findOne(storyId);
		myStoryAdministrator = false;
		myStory = false;
		logeado = false;

		try {

			administrator = administratorService.findByPrincipal();
			if (story.getAdministrator().getId() == administrator.getId()) {
				myStoryAdministrator = true;
			}

		} catch (Throwable oops) {
			myStory = false;
			logeado = false;
			myStoryAdministrator = false;
		}

		;

		story = storyService.findOne(storyId);

		result = new ModelAndView("story/display");
		result.addObject("story", story);
		result.addObject("myStory", myStory);
		result.addObject("myStoryAdministrator", myStoryAdministrator);
		result.addObject("logeado", logeado);
		return result;
	}

	// ListByCanyon
	// ---------------------------------------------------------------------------
	@RequestMapping(value = "/listByCanyon")
	public ModelAndView listByCanyon(@RequestParam int canyonId) {

		ModelAndView result;
		Collection<Story> stories;
		Canyon canyon;
		Administrator administrator;
		Boolean mycanyon;
		stories = storyService.storiesByCanyon(canyonId);
		result = new ModelAndView("story/list");
		result.addObject("stories", stories);
		result.addObject("canyonId", canyonId);
		mycanyon = false;
		try {
			administrator = administratorService.findByPrincipal();
			canyon = canyonService.findOne(canyonId);

			mycanyon = administrator.equals(canyon.getAdministrator());

		} catch (Throwable oops) {

		}
		result.addObject("mycanyon", mycanyon);

		return result;
	}

}
