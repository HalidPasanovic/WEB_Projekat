package services;

import java.util.List;
import Model.Trainings.TrainingHistory;
import Service.Interfaces.Trainings.ITrainingHistoryService;
import Service.Trainings.TrainingHistoryService;
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
	}

    @POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public void Create(TrainingHistory element) throws Exception {
        ITrainingHistoryService service = (ITrainingHistoryService) ctx.getAttribute("TrainingHistoryService");
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
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<TrainingHistory> GetAllWithLogicalyDeleted() throws Exception {
        ITrainingHistoryService service = (ITrainingHistoryService) ctx.getAttribute("TrainingHistoryService");
        return service.GetAllWithLogicalyDeleted();
    }
    
}
