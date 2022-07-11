package services;

import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import Model.Users.Gender;
import Model.Users.Trainer;
import Model.Users.UserRole;
import services.Interfaces.ICrud;
import Service.Users.TrainerService;

@Path("/trainers")
public class TrainerController implements ICrud<Trainer> {

	@Context
	ServletContext ctx;
	
	public TrainerController() {}
	
    @PostConstruct
	public void init() {
		if (ctx.getAttribute("trainerService") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("trainerService", new TrainerService(contextPath));
			System.out.println(contextPath);
		}
	}
    
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    public void Create(Trainer element) throws Exception {
		TrainerService repo = (TrainerService) ctx.getAttribute("trainerService");
        repo.Create(element);
    }

    @GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public Trainer Read(@PathParam("id") int id) throws Exception {
    	TrainerService repo = (TrainerService) ctx.getAttribute("trainerService");
        return repo.Read(id);
    }

    @PUT
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    public void Update(Trainer element) throws Exception {
    	TrainerService repo = (TrainerService) ctx.getAttribute("trainerService");
    	repo.Update(element);
    }

    @DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public void Delete(@PathParam("id") int id) throws Exception {
    	TrainerService repo = (TrainerService) ctx.getAttribute("trainerService");
    	repo.Delete(id);
    }

    @GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    public List<Trainer> GetAll() {
    	TrainerService repo = (TrainerService) ctx.getAttribute("trainerService");
    	return repo.GetAll();
    }
    
    @DELETE
	@Path("/physically/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public void DeletePhysically(int id) throws Exception {
		TrainerService repo = (TrainerService) ctx.getAttribute("trainerService");
    	repo.DeletePhysically(id);
	}

	@Override
	public List<Trainer> GetAllWithLogicalyDeleted() throws Exception {
		TrainerService repo = (TrainerService) ctx.getAttribute("trainerService");
		return repo.GetAll();
	}

    
}
