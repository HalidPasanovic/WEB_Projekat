package services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import Model.Users.Manager;
import Model.Users.Trainer;
import Repository.Interfaces.Users.ITrainerRepository;
import Repository.Users.ManagerRepository;
import Repository.Users.TrainerRepository;
import Service.Interfaces.Users.ITrainerService;

@Path("/trainers")
public class TrainerService implements ITrainerService {

	@Context
	ServletContext ctx;
	
	public TrainerService() {}
	
    @PostConstruct
	public void init() {
		if (ctx.getAttribute("trainerRepository") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("trainerRepository", new TrainerRepository(contextPath + "/trainers.csv"));
			System.out.println(contextPath);
		}
	}
    
    public void Create(Trainer element) throws Exception {
    	TrainerRepository repo = (TrainerRepository) ctx.getAttribute("trainerRepository");
        repo.Create(element);
    }

    @GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public Trainer Read(@PathParam("id") int id) throws Exception {
    	TrainerRepository repo = (TrainerRepository) ctx.getAttribute("trainerRepository");
        return repo.Read(id);
    }

    @PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public void Update(Trainer element) throws Exception {
    	TrainerRepository repo = (TrainerRepository) ctx.getAttribute("trainerRepository");
    	repo.Update(element);
    }

    @DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public void Delete(@PathParam("id") int id) throws Exception {
    	TrainerRepository repo = (TrainerRepository) ctx.getAttribute("trainerRepository");
    	repo.Delete(id);
    }

    @GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    public List<Trainer> GetAll() {
    	TrainerRepository repo = (TrainerRepository) ctx.getAttribute("trainerRepository");
    	return repo.GetAll();
    }

    
}
