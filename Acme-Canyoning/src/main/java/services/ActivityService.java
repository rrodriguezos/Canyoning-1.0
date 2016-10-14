package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ActivityRepository;
import security.Authority;
import domain.Activity;
import domain.Actor;
import domain.Comment;
import domain.Customer;
import domain.Organiser;
import domain.Request;
import domain.Request.RequestState;

@Service
@Transactional
public class ActivityService {

	// Managed repository -------------------
	@Autowired
	private ActivityRepository activityRepository;

	// Supporting Services ------------------
	@Autowired
	private OrganiserService organiserService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private RequestService requestService;

	@Autowired
	private ActorService actorService;

	// COnstructors -------------------------
	public ActivityService() {
		super();
	}

	// Simple CRUD methods--------------------

	public Activity create() {
		Activity result;
		Organiser organiser;
		Collection<Comment> comments;
		Collection<Request> requests;

		result = new Activity();

		organiser = organiserService.findByPrincipal();
		result.setOrganiser(organiser);

		comments = new ArrayList<Comment>();
		result.setComments(comments);
	
		
		requests = new ArrayList<Request>();
		result.setRequests(requests);

		return result;
	}

	public Collection<Activity> findAll() {
		Collection<Activity> result;

		result = activityRepository.findAll();

		return result;
	}

	public Activity findOne(int activityId) {
		Activity result;

		result = activityRepository.findOne(activityId);

		return result;
	}

	public void save(Activity activity) {
		Assert.notNull(activity);

		activityRepository.saveAndFlush(activity);
	}

	public void delete(Activity activity) {
		activityRepository.delete(activity);
	}

	// Other Methods---------------------------------------------------------

	private void checkPrincipalOrganiser() {
		Actor actor;
		Authority authority;

		actor = actorService.findByPrincipal();
		Assert.isTrue(actor != null);

		authority = new Authority();
		authority.setAuthority("ORGANISER");

		Assert.isTrue(actor.getUserAccount().getAuthorities()
				.contains(authority));
	}

	public Collection<Activity> activitiesByCanyon(int canyonId) {
		Collection<Activity> result;

		result = activityRepository.activityByCanyon(canyonId);
		return result;
	}

	public Collection<Activity> findActivityByKeyword(String text) {
		Collection<Activity> result;

		result = activityRepository.findActivityByKeyword(text);

		return result;

	}

	public Collection<Activity> allActivitiesByCustomerRequest(int customerId) {
		Collection<Activity> result = new LinkedList<Activity>();
		Assert.isTrue(customerService.findByPrincipal().getId() == customerId);
		Collection<Request> allRequestByCustomer = new LinkedList<Request>();

		Collection<Request> requestPending = requestService
				.requestPendingByCustomer();

		Collection<Request> requestAccepted = requestService
				.requestAcceptedByCustomer();

		Collection<Request> requestReject = requestService
				.requestRejectByCustomer();

		allRequestByCustomer.addAll(requestPending);
		allRequestByCustomer.addAll(requestAccepted);
		allRequestByCustomer.addAll(requestReject);

		for (Request reqs : allRequestByCustomer) {

			result.add(reqs.getActivity());

		}
		return result;
	}

	public Collection<Activity> activitiesAcceptedByRequestCustomer(
			int customerId) {
		Collection<Activity> result = new LinkedList<Activity>();
		Assert.isTrue(customerService.findByPrincipal().getId() == customerId);
		Collection<Request> allRequestByCustomer = new LinkedList<Request>();

		Collection<Request> requestAccepted = requestService
				.requestAcceptedByCustomer();

		allRequestByCustomer.addAll(requestAccepted);

		for (Request reqs : allRequestByCustomer) {

			result.add(reqs.getActivity());

		}
		return result;
	}

	public Collection<Activity> activitiesRejectByRequestCustomer(int customerId) {
		Collection<Activity> result = new LinkedList<Activity>();
		Assert.isTrue(customerService.findByPrincipal().getId() == customerId);
		Collection<Request> allRequestByCustomer = new LinkedList<Request>();

		Collection<Request> requestReject = requestService
				.requestRejectByCustomer();

		allRequestByCustomer.addAll(requestReject);

		for (Request reqs : allRequestByCustomer) {

			result.add(reqs.getActivity());

		}
		return result;
	}

	public Collection<Activity> activitiesPendingByRequestCustomer(
			int customerId) {
		Collection<Activity> result = new LinkedList<Activity>();
		Assert.isTrue(customerService.findByPrincipal().getId() == customerId);
		Collection<Request> allRequestByCustomer = new LinkedList<Request>();

		Collection<Request> requestPending = requestService
				.requestPendingByCustomer();

		allRequestByCustomer.addAll(requestPending);

		for (Request reqs : allRequestByCustomer) {

			result.add(reqs.getActivity());

		}
		return result;
	}

	public void acceptRequestByActivity(Activity activity, Request request) {

		Assert.notNull(organiserService.findByPrincipal());
		Assert.isTrue(request.getRequestState() == RequestState.PENDING);
		restaAsiento(activity);
		request.setMomentAccepted(new Date(System.currentTimeMillis() - 1000));
		request.setRequestState(RequestState.ACCEPTED);
		activityRepository.saveAndFlush(activity);

	}

	public void rejectRequestByActivity(Activity activity, Request request) {

		Assert.notNull(organiserService.findByPrincipal());
		Assert.isTrue(request.getRequestState() == RequestState.PENDING);
		request.setRequestState(RequestState.REJECTED);
		activityRepository.saveAndFlush(activity);

	}

	public void restaAsiento(Activity activity) {

		Assert.isTrue(activity.getSeatsAvailable() > 0);
		activity.setSeatsAvailable(activity.getSeatsAvailable() - 1);
		activityRepository.saveAndFlush(activity);

	}

	public Activity activityByRequest(int requestId) {
		Activity result;
		Request request = requestService.findOne(requestId);

		result = request.getActivity();

		return result;
	}

	public Collection<Activity> findActivitiesByOrganiser() {
			Collection<Activity> result;
			Organiser organiser;
			organiser = organiserService.findByPrincipal();

			result = activityRepository.requestByOrganiser(organiser.getId());
			return result;
		}
	}


