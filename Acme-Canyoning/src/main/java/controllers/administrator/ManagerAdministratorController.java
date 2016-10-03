/* UserController.java
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import controllers.AbstractController;
import domain.Customer;
import forms.OrganiserForm;
import services.ManagerService;

@Controller
@RequestMapping("/managger/administrator")
public class ManagerAdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private ManagerService managerService;

	// Constructors -----------------------------------------------------------

	public ManagerAdministratorController() {
		super();
	}

	//Create---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		OrganiserForm managerForm;

		managerForm = new OrganiserForm();

		result = new ModelAndView("managger/create");
		result.addObject("managerForm", managerForm);
		return result;
	}

	// Save ----------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid OrganiserForm managerForm,
			BindingResult binding) {
		ModelAndView result;
		Customer manager;
		Boolean verificarPass;

		verificarPass = managerForm.getPassword().equals(
				managerForm.getConfirmPassword());

		if (binding.hasErrors() || !verificarPass) {
			result = createEditModelAndView(managerForm);
			if (!verificarPass) {
				result.addObject("message2", "manager.commit.password");
			}
		} else {
			try {
				manager = managerService.reconstruct(managerForm);
				managerService.save(manager);
				result = new ModelAndView("redirect:/");
			} catch (Throwable oops) {
				result = createEditModelAndView(managerForm);
				if (oops instanceof DataIntegrityViolationException) {
					result.addObject("message2",
							"manager.commit.duplicatedUsername");
				} else {
					result.addObject("message2", "manager.commit.error");
				}
			}
		}

		return result;
	}

	// List ------------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Customer> managers;

		managers = managerService.findAll();

		result = new ModelAndView("managger/list");
		result.addObject("managers", managers);
		result.addObject("requestUri", "managger/administrator/list.do");

		return result;
	}

	// Display -----------------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(int managerId) {
		ModelAndView result;
		Customer manager;

		manager = managerService.findOne(managerId);

		result = new ModelAndView("managger/display");
		result.addObject("manager", manager);

		return result;
	}

	// Ancillary methods--------------------------------------------------------

	protected ModelAndView createEditModelAndView(OrganiserForm managerForm) {
		ModelAndView result;

		result = createEditModelAndView(managerForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(OrganiserForm managerForm,
			String message) {
		ModelAndView result;

		result = new ModelAndView("managger/create");

		result.addObject("manager", managerForm);
		result.addObject("message2", message);
		return result;
	}
}