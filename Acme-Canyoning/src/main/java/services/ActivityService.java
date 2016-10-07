package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ActivityRepository;
import security.Authority;
import domain.Activity;
import domain.Actor;
import domain.Canyon;
import domain.Comment;
import domain.Organiser;

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

		result = new Activity();

		organiser = organiserService.findByPrincipal();
		result.setOrganiser(organiser);

		comments = new ArrayList<Comment>();
		result.setComments(comments);

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
		Collection<Activity> result1;
		result1 = activityRepository.findActivityByCanyonKeyword(text);
		

		for (Activity t : result1) {
			if (!result.contains(t)) {
				result.add(t);
			}
		}
		return result;
	}

}
