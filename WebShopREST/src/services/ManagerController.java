package services;

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
import Service.Interfaces.ICrud;
import Service.Users.ManagerService;

@Path("/managers")
public class ManagerController implements ICrud<Manager> {

	@Context
	ServletContext ctx;
	
	public ManagerController() {
	}
	
    @PostConstruct
	public void init() {
    	
		if (ctx.getAttribute("managerService") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("managerService", new ManagerService(contextPath));
			System.out.println(contextPath);
		}
	}
    
    public void Create(Manager element) throws Exception {
    	ManagerService repo = (ManagerService) ctx.getAttribute("managerService");
        repo.Create(element);
    }

    @GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public Manager Read(@PathParam("id") int id) throws Exception {
    	ManagerService repo = (ManagerService) ctx.getAttribute("managerService");
        return repo.Read(id);
    }

    @PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public void Update(Manager element) throws Exception {
    	ManagerService repo = (ManagerService) ctx.getAttribute("managerService");
    	repo.Update(element);
    }

    @DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public void Delete(@PathParam("id") int id) throws Exception {
    	ManagerService repo = (ManagerService) ctx.getAttribute("managerService");
    	repo.Delete(id);
    }

    @GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    public List<Manager> GetAll() {
    	ManagerService repo = (ManagerService) ctx.getAttribute("managerService");
    	return repo.GetAll();
    }

}
