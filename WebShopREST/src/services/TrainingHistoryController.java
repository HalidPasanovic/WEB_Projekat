package services;

import java.util.ArrayList;
import java.util.List;
import Model.Memberships.Membership;
import Model.Trainings.TrainingHistory;
import Service.Interfaces.Memberships.IMembershipService;
import Service.Interfaces.Trainings.ITrainingHistoryService;
import Service.Memberships.MembershipService;
import Service.Trainings.TrainingHistoryService;
import Service.Users.CustomerService;
import services.Interfaces.ICrud;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/trainingHistory")
public class TrainingHistoryController implements ICrud<TrainingHistory> {

    @Context
	ServletContext ctx;

    public TrainingHistoryController() {}

    @PostConstruct
	public void init() {
		if (ctx.getAttribute("TrainingHistoryService") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("TrainingHistoryService", new TrainingHistoryService(contextPath));
		}
		if (ctx.getAttribute("MembershipService") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("MembershipService", new MembershipService(contextPath));
		}
	}

    @POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public void Create(TrainingHistory element) throws Exception {
        ITrainingHistoryService service = (ITrainingHistoryService) ctx.getAttribute("TrainingHistoryService");
        service.Create(element);
    }
    
    @POST
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public void CreateForUser(@PathParam("id") int id, TrainingHistory element) throws Exception {
        ITrainingHistoryService service = (ITrainingHistoryService) ctx.getAttribute("TrainingHistoryService");
        IMembershipService membershipService = (IMembershipService) ctx.getAttribute("MembershipService");
        boolean found = false;
        for (Membership it: membershipService.GetAll()) {
            if(it.getBuyer().getId() == id){
                found = true;
                if(!it.isStatus()){
                    throw new Exception("Membership isn't active");
                }
                if(it.getUsedVisits() >= it.getType().getVisitationCount()){
                    throw new Exception("All visitations are used up already");
                }
            }
        }
        if(!found){
            throw new Exception("User doesn't have a membership");
        }
        service.Create(element);
    }

    @GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public TrainingHistory Read(@PathParam("id") int id) throws Exception {
        ITrainingHistoryService service = (ITrainingHistoryService) ctx.getAttribute("TrainingHistoryService");
        return service.Read(id);
    }

    @PUT
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public void Update(TrainingHistory element) throws Exception {
        ITrainingHistoryService service = (ITrainingHistoryService) ctx.getAttribute("TrainingHistoryService");
        service.Update(element);
    }

    @DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public void Delete(@PathParam("id") int id) throws Exception {
        ITrainingHistoryService service = (ITrainingHistoryService) ctx.getAttribute("TrainingHistoryService");
        CustomerService customerService = new CustomerService(ctx.getRealPath(""));
        customerService.CheckIfAnotherVisitExistsAndUpdateMembership(id, service.Read(id));
        service.Delete(id);
    }

    @DELETE
	@Path("/physically/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public void DeletePhysically(@PathParam("id") int id) throws Exception {
        ITrainingHistoryService service = (ITrainingHistoryService) ctx.getAttribute("TrainingHistoryService");
        service.DeletePhysically(id);
    }

    @GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<TrainingHistory> GetAll() throws Exception {
        ITrainingHistoryService service = (ITrainingHistoryService) ctx.getAttribute("TrainingHistoryService");
        return service.GetAll();
    }

    @GET
	@Path("/specific/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public List<TrainingHistory> GetAllForUser(@PathParam("id") int id) throws Exception {
        ITrainingHistoryService service = (ITrainingHistoryService) ctx.getAttribute("TrainingHistoryService");
        List<TrainingHistory> histories = service.GetAll();
        List<TrainingHistory> result = new ArrayList<>();
        for (TrainingHistory trainingHistory : histories) {
            if(trainingHistory.getCustomer().getId() == id){
                result.add(trainingHistory);
            }
        }
        return result;
    }

    @GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<TrainingHistory> GetAllWithLogicalyDeleted() throws Exception {
        ITrainingHistoryService service = (ITrainingHistoryService) ctx.getAttribute("TrainingHistoryService");
        return service.GetAllWithLogicalyDeleted();
    }
    
}
