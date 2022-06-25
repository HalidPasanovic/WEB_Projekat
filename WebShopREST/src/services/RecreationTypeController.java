package services;

import java.util.List;

import Model.Facilities.RecreationType;
import Repository.Interfaces.ICrud;
import Service.Facilities.RecreationTypeService;

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

@Path("/recreationtypes")
public class RecreationTypeController implements ICrud<RecreationType> {
    
    //private IAdministratorRepository repository = new AdministratorRepository("");
    
	@Context
	ServletContext ctx;
	
	public RecreationTypeController() {}
	
    @PostConstruct
	public void init() {
		if (ctx.getAttribute("RecreationTypeService") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("RecreationTypeService", new RecreationTypeService(contextPath));
			System.out.println(contextPath);
		}
	}

    
    @POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    public void Create(RecreationType element) throws Exception {
    	RecreationTypeService repo = (RecreationTypeService) ctx.getAttribute("RecreationTypeService");
        repo.Create(element);
    }

    @GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public RecreationType Read(@PathParam("id") int id) throws Exception {
    	RecreationTypeService repo = (RecreationTypeService) ctx.getAttribute("RecreationTypeService");
        return repo.Read(id);
    }

    @PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public void Update(RecreationType element) throws Exception {
    	RecreationTypeService repo = (RecreationTypeService) ctx.getAttribute("RecreationTypeService");
    	repo.Update(element);
    }

    @DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public void Delete(@PathParam("id") int id) throws Exception {
    	RecreationTypeService repo = (RecreationTypeService) ctx.getAttribute("RecreationTypeService");
    	repo.Delete(id);
    }

    @GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    public List<RecreationType> GetAll() {
    	RecreationTypeService repo = (RecreationTypeService) ctx.getAttribute("RecreationTypeService");
    	return repo.GetAll();
    	//System.out.println("Uspesno");
    	//return null;
    }

    
}
