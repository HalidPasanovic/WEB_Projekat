package services;

import java.util.List;
import Model.Trainings.TrainingType;
import Service.Interfaces.Trainings.ITrainingTypeService;
import Service.Trainings.TrainingTypeService;
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

@Path("/trainingType")
public class TrainingTypeController implements ICrud<TrainingType>{

    @Context
	ServletContext ctx;

    public TrainingTypeController() {}

    @PostConstruct
	public void init() {
		if (ctx.getAttribute("TrainingTypeService") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("TrainingTypeService", new TrainingTypeService(contextPath));
		}
	}

    @POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public void Create(TrainingType element) throws Exception {
        ITrainingTypeService service = (ITrainingTypeService) ctx.getAttribute("TrainingTypeService");
        service.Create(element);
    }

    @GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public TrainingType Read(@PathParam("id") int id) throws Exception {
        ITrainingTypeService service = (ITrainingTypeService) ctx.getAttribute("TrainingTypeService");
        return service.Read(id);
    }

    @PUT
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public void Update(TrainingType element) throws Exception {
        ITrainingTypeService service = (ITrainingTypeService) ctx.getAttribute("TrainingTypeService");
        service.Update(element);
    }

    @DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public void Delete(@PathParam("id") int id) throws Exception {
        ITrainingTypeService service = (ITrainingTypeService) ctx.getAttribute("TrainingTypeService");
        service.Delete(id);
    }

    @DELETE
	@Path("/physically/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public void DeletePhysically(@PathParam("id") int id) throws Exception {
        ITrainingTypeService service = (ITrainingTypeService) ctx.getAttribute("TrainingTypeService");
        service.DeletePhysically(id);
    }

    @GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<TrainingType> GetAll() throws Exception {
        ITrainingTypeService service = (ITrainingTypeService) ctx.getAttribute("TrainingTypeService");
        return service.GetAll();
    }

    @GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<TrainingType> GetAllWithLogicalyDeleted() throws Exception {
        ITrainingTypeService service = (ITrainingTypeService) ctx.getAttribute("TrainingTypeService");
        return service.GetAllWithLogicalyDeleted();
    }
    
}
