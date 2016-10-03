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
import domain.Comment;
import domain.Organiser;

@Service 
@Transactional 
public class ActivityService { 

	//Managed repository -------------------
	@Autowired
	private ActivityRepository activityRepository;


	//Supporting Services ------------------
	@Autowired
	private OrganiserService organiserService;
	
	@Autowired
	private ActorService actorService;

	//COnstructors -------------------------
	public ActivityService(){
		super();
	}


	//Simple CRUD methods--------------------

	public Activity create(){
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

	public Collection<Activity> findAll(){
		Collection<Activity> result;

		result = activityRepository.findAll();

		return result;
	}

	public Activity findOne(int activityId){
		Activity result;

		result = activityRepository.findOne(activityId);

		return result;
	}

	public void save(Activity activity){
		Assert.notNull(activity);

		activityRepository.saveAndFlush(activity);
	}

	public void delete(Activity activity){
		activityRepository.delete(activity);
	}


	//Other Methods---------------------------------------------------------
	public Collection<Activity> findAllAppropriated(){
		Collection<Activity> result;

		result = activityRepository.findAllAppropriated();

		return result;
	}
	
	
	private void checkPrincipalOrganiser(){
		Actor actor;
		Authority authority;
	
		actor = actorService.findByPrincipal();
		Assert.isTrue(actor != null);

		
		authority = new Authority();
		authority.setAuthority("ORGANISER");
		
		Assert.isTrue(actor.getUserAccount().getAuthorities().contains(authority));
	}


	public Collection<Activity> activitiesByActivityType(int activitytypeId) {
		return activityRepository.activitiesByActivityType(activitytypeId);
	}


	public Activity activityBySlot(int slotId) {
		return activityRepository.activityBySlot(slotId);
	}

} 
