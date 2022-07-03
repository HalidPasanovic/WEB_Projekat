package services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import Model.Facilities.SportFacility;
import Model.Users.Gender;
import Model.Users.Manager;
import Model.Users.UserRole;
import services.Interfaces.ICrud;
import Service.Facilities.SportFacilityService;
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
		
		if (ctx.getAttribute("facilityService") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("facilityService", new SportFacilityService(contextPath));
			System.out.println(contextPath);
		}
	}
    
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public void Create(Manager element) throws Exception {
    	ManagerService repo = (ManagerService) ctx.getAttribute("managerService");
        repo.Create(element);
    }
	
	@POST
	@Path("/createandreturn")
	@Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public int Create2(Manager element) throws Exception {
        try {
        	ManagerService service = (ManagerService) ctx.getAttribute("managerService");
            return service.CreateAndReturn(element);
        } catch (Exception e) {
            //TODO: handle exception
        }
		return 0;
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
    
    @GET
	@Path("/empty")
	@Produces(MediaType.APPLICATION_JSON)
    public List<Manager> GetAllEmpty() {
    	ManagerService repo = (ManagerService) ctx.getAttribute("managerService");
    	return repo.GetAllEmpty();
    }

	@DELETE
	@Path("/physically/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
	public void DeletePhysically(@PathParam("id") int id) throws Exception {
		ManagerService repo = (ManagerService) ctx.getAttribute("managerService");
    	repo.DeletePhysically(id);
	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
	public List<Manager> GetAllWithLogicalyDeleted() throws Exception {
		ManagerService repo = (ManagerService) ctx.getAttribute("managerService");
		return repo.GetAllWithLogicalyDeleted();
	}
	
	@POST
	@Path("/updatefacility/{id}&{second}")
	@Produces(MediaType.APPLICATION_JSON)
    public void UpdateFacility(@PathParam("id") int id, @PathParam("second") int fid) throws Exception {
    	ManagerService repo = (ManagerService) ctx.getAttribute("managerService");
        Manager m = repo.Read(id);
        
        SportFacilityService repo2 = (SportFacilityService) ctx.getAttribute("facilityService");
        SportFacility s = repo2.Read(fid);
        
        m.addFacility(s);
        repo.Update(m, m.getUsername());
        
    }

}
