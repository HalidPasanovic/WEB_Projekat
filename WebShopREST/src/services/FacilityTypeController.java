package services;

import java.util.List;

import Model.Facilities.FacilityType;
import Model.Facilities.RecreationType;
import Model.Facilities.SportFacility;
import Model.Users.Administrator;
import Repository.Interfaces.ICrud;
import Repository.Users.AdministratorRepository;
import Service.Facilities.FacilityTypeService;
import Service.Facilities.RecreationTypeService;
import Service.Interfaces.Users.IAdministratorService;

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

@Path("/facilitytypes")
public class FacilityTypeController implements ICrud<FacilityType> {
        
	@Context
	ServletContext ctx;
	
	public FacilityTypeController() {}
	
    @PostConstruct
	public void init() {
		if (ctx.getAttribute("FacilityTypeService") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("FacilityTypeService", new FacilityTypeService(contextPath, "/data/facilityTypes.csv"));
			System.out.println(contextPath);
		}
	}

    
    @POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    public void Create(FacilityType element) throws Exception {
    	FacilityTypeService repo = (FacilityTypeService) ctx.getAttribute("FacilityTypeService");
        repo.Create(element);
    }

    @GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public FacilityType Read(@PathParam("id") int id) throws Exception {
    	FacilityTypeService repo = (FacilityTypeService) ctx.getAttribute("FacilityTypeService");
        return repo.Read(id);
    }

    @PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public void Update(FacilityType element) throws Exception {
    	FacilityTypeService repo = (FacilityTypeService) ctx.getAttribute("FacilityTypeService");
    	repo.Update(element);
    }

    @DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public void Delete(@PathParam("id") int id) throws Exception {
    	FacilityTypeService repo = (FacilityTypeService) ctx.getAttribute("FacilityTypeService");
    	repo.Delete(id);
    }

    @GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    public List<FacilityType> GetAll() {
    	FacilityTypeService repo = (FacilityTypeService) ctx.getAttribute("FacilityTypeService");
    	return repo.GetAll();
    }

    
}
