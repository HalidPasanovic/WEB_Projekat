package services;

import java.time.LocalTime;
import java.util.List;
import Model.Facilities.SportFacility;
import Model.Trainings.Training;
import Model.Trainings.TrainingType;
import Model.Users.Trainer;
import Service.Interfaces.Trainings.ITrainingService;
import Service.Trainings.TrainingService;
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

@Path("/training")
public class TrainingController implements ICrud<Training> {

    @Context
	ServletContext ctx;

    public TrainingController() {}

    @PostConstruct
	public void init() {
		if (ctx.getAttribute("TrainingService") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("TrainingService", new TrainingService(contextPath));
		}
	}

    @POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public void Create(Training element) throws Exception {
        ITrainingService service = (ITrainingService) ctx.getAttribute("TrainingService");
        service.Create(element);
    }

    @GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public Training Read(@PathParam("id") int id) throws Exception {
        ITrainingService service = (ITrainingService) ctx.getAttribute("TrainingService");
        return service.Read(id);
    }

    @PUT
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public void Update(Training element) throws Exception {
        ITrainingService service = (ITrainingService) ctx.getAttribute("TrainingService");
        service.Update(element);
    }

    @DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public void Delete(@PathParam("id") int id) throws Exception {
        ITrainingService service = (ITrainingService) ctx.getAttribute("TrainingService");
        service.Delete(id);
    }

    @DELETE
	@Path("/physically/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public void DeletePhysically(@PathParam("id") int id) throws Exception {
        ITrainingService service = (ITrainingService) ctx.getAttribute("TrainingService");
        service.DeletePhysically(id);
    }

    @GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<Training> GetAll() throws Exception {
        ITrainingService service = (ITrainingService) ctx.getAttribute("TrainingService");
        return service.GetAll();
    }

    @GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<Training> GetAllWithLogicalyDeleted() throws Exception {
        ITrainingService service = (ITrainingService) ctx.getAttribute("TrainingService");
        return service.GetAllWithLogicalyDeleted();
    }
    
}
