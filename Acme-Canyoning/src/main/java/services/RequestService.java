package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RequestRepository;
import domain.Request;

@Service 
@Transactional 
public class RequestService {
	
	// Managed repository -------------------
		@Autowired
		private RequestRepository requestRepository;

		// Supporting Services ------------------
		@Autowired
		private OrganiserService organiserService;
		
		@Autowired
		private ActivityService activityService;



		// COnstructors -------------------------
		public RequestService() {
			super();
		}
		
		
		public Request findOne(int requestId) {
			Assert.isTrue(requestId != 0);

			Request result;

			result = requestRepository.findOne(requestId);
			Assert.notNull(result);

			return result;
		}



//		public Collection<Request> requestAcceptedByActivity(int activityId) {
//			Collection<Request> result;
//			
//			
//			
//			result = requestRepository.requestAcceptedByActivity(activityId);
//			
//			return result;
//		}

		// Simple CRUD methods--------------------

}
