package services;

import java.io.File;
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

import Model.Users.Administrator;
import Model.Users.Manager;
import Repository.Interfaces.Users.IManagerRepository;
import Repository.Users.AdministratorRepository;
import Repository.Users.ManagerRepository;
import Service.Interfaces.Users.IManagerService;

@Path("/managers")
public class ManagerService implements IManagerService {

	@Context
	ServletContext ctx;
	
	public ManagerService() {
	}
	
    @PostConstruct
	public void init() {
    	
		if (ctx.getAttribute("managerRepository") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("managerRepository", new ManagerRepository(contextPath + "/managers.csv"));
			System.out.println(contextPath);
		}
	}
    
    public void Create(Manager element) throws Exception {
    	ManagerRepository repo = (ManagerRepository) ctx.getAttribute("managerRepository");
        repo.Create(element);
    }

    @GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public Manager Read(@PathParam("id") int id) throws Exception {
    	ManagerRepository repo = (ManagerRepository) ctx.getAttribute("managerRepository");
        return repo.Read(id);
    }

    @PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public void Update(Manager element) throws Exception {
    	ManagerRepository repo = (ManagerRepository) ctx.getAttribute("managerRepository");
    	repo.Update(element);
    }

    @DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public void Delete(@PathParam("id") int id) throws Exception {
    	ManagerRepository repo = (ManagerRepository) ctx.getAttribute("managerRepository");
    	repo.Delete(id);
    }

    @GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    public List<Manager> GetAll() {
    	ManagerRepository repo = (ManagerRepository) ctx.getAttribute("managerRepository");
    	return repo.GetAll();
    }

}
