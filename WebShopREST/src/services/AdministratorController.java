package services;

import java.util.List;
import Model.Users.Administrator;
import Service.Users.AdministratorService;
import services.Interfaces.ICrud;
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

@Path("/admins")
public class AdministratorController implements ICrud<Administrator> {
    
	@Context
	ServletContext ctx;
	
	public AdministratorController() {}
	
    @PostConstruct
	public void init() {
		if (ctx.getAttribute("administratorService") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("administratorService", new AdministratorService(contextPath));
			System.out.println(contextPath);
		}
	}

    
    @POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    public void Create(Administrator element) throws Exception {
    	AdministratorService repo = (AdministratorService) ctx.getAttribute("administratorService");
        repo.Create(element);
    }

    @GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public Administrator Read(@PathParam("id") int id) throws Exception {
    	AdministratorService repo = (AdministratorService) ctx.getAttribute("administratorService");
        return repo.Read(id);
    }

    @PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public void Update(Administrator element) throws Exception {
    	AdministratorService repo = (AdministratorService) ctx.getAttribute("administratorService");
    	repo.Update(element);
    }

    @DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public void Delete(@PathParam("id") int id) throws Exception {
    	AdministratorService repo = (AdministratorService) ctx.getAttribute("administratorService");
    	repo.Delete(id);
    }

    @GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    public List<Administrator> GetAll() {
    	AdministratorService repo = (AdministratorService) ctx.getAttribute("administratorService");
    	return repo.GetAll();
    }

	@DELETE
	@Path("/physically/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
	public void DeletePhysically(int id) throws Exception {
		AdministratorService repo = (AdministratorService) ctx.getAttribute("administratorService");
    	repo.DeletePhysically(id);
	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
	public List<Administrator> GetAllWithLogicalyDeleted() throws Exception{
		AdministratorService repo = (AdministratorService) ctx.getAttribute("administratorService");
		return repo.GetAllWithLogicalyDeleted();
	}

    
}
