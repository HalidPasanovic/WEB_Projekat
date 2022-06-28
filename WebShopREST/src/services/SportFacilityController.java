package services;

import java.util.ArrayList;
import java.util.List;
import Model.Adress;
import Model.Location;
import Model.Facilities.FacilityType;
import Model.Facilities.RecreationType;
import Model.Facilities.SportFacility;
import services.Interfaces.ICrud;
import Service.Facilities.SportFacilityService;

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

@Path("/facility")
public class SportFacilityController implements ICrud<SportFacility> {

    @Context
	ServletContext ctx;

    public SportFacilityController() {}

    @PostConstruct
	public void init() {
		if (ctx.getAttribute("SportFacilityService") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("SportFacilityService", new SportFacilityService(contextPath));
			System.out.println(contextPath);
		}
	}

    @POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    public void Create(SportFacility element) throws Exception {
        try {
            SportFacilityService service = (SportFacilityService) ctx.getAttribute("SportFacilityService");
            service.Create(element);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    @GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public SportFacility Read(@PathParam("id") int id) throws Exception {
        try {
            SportFacilityService service = (SportFacilityService) ctx.getAttribute("SportFacilityService");
            return service.Read(id);
        } catch (Exception e) {
        	return null;
        }
    }

    @PUT
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    public void Update(SportFacility element) throws Exception {
        try {
            SportFacilityService service = (SportFacilityService) ctx.getAttribute("SportFacilityService");
            service.Update(element);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    @DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public void Delete(@PathParam("id") int id) throws Exception {
        try {
            SportFacilityService service = (SportFacilityService) ctx.getAttribute("SportFacilityService");
            service.Delete(id);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    @GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    public List<SportFacility> GetAll() {
        try {
            SportFacilityService service = (SportFacilityService) ctx.getAttribute("SportFacilityService");
            return service.GetAll();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void DeletePhysically(@PathParam("id") int id) throws Exception {
        try {
            SportFacilityService service = (SportFacilityService) ctx.getAttribute("SportFacilityService");
            service.DeletePhysically(id);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    @Override
    public List<SportFacility> GetAllWithLogicalyDeleted() throws Exception {
        SportFacilityService service = (SportFacilityService) ctx.getAttribute("SportFacilityService");
        return service.GetAllWithLogicalyDeleted();
    }
    
}
