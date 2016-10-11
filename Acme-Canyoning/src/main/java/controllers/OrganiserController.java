package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.OrganiserService;
import domain.Organiser;

@Controller
@RequestMapping("/organiser")
public class OrganiserController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private OrganiserService organiserService;

	// Constructors -----------------------------------------------------------

	public OrganiserController() {
		super();
	}

	// List -----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Organiser> organisers;

		organisers = organiserService.findAll();

		result = new ModelAndView("organiser/list");
		result.addObject("organisers", organisers);
		result.addObject("requestURI", "organiser/list.do");

		return result;
	}

}
