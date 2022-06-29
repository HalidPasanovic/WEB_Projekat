package services;

import java.util.List;
import Model.Memberships.MembershipType;
import Service.Interfaces.Memberships.IMembershipTypeService;
import Service.Memberships.MembershipTypeService;
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

@Path("/membershipType")
public class MembershipTypeController implements ICrud<MembershipType>{

    @Context
	ServletContext ctx;

    public MembershipTypeController() {}

    @PostConstruct
	public void init() {
		if (ctx.getAttribute("MembershipTypeService") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("MembershipTypeService", new MembershipTypeService(contextPath));
		}
	}

    @POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public void Create(MembershipType element) throws Exception {
        IMembershipTypeService service = (IMembershipTypeService) ctx.getAttribute("MembershipTypeService");
        service.Create(element);
    }

    @GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public MembershipType Read(@PathParam("id") int id) throws Exception {
        IMembershipTypeService service = (IMembershipTypeService) ctx.getAttribute("MembershipTypeService");
        return service.Read(id);
    }

    @PUT
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public void Update(MembershipType element) throws Exception {
        IMembershipTypeService service = (IMembershipTypeService) ctx.getAttribute("MembershipTypeService");
        service.Update(element);
    }

    @DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public void Delete(@PathParam("id") int id) throws Exception {
        IMembershipTypeService service = (IMembershipTypeService) ctx.getAttribute("MembershipTypeService");
        service.Delete(id);
    }

    @DELETE
	@Path("/physically/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public void DeletePhysically(@PathParam("id") int id) throws Exception {
        IMembershipTypeService service = (IMembershipTypeService) ctx.getAttribute("MembershipTypeService");
        service.DeletePhysically(id);   
    }

    @GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<MembershipType> GetAll() throws Exception {
        IMembershipTypeService service = (IMembershipTypeService) ctx.getAttribute("MembershipTypeService");
        return service.GetAll();
    }

    @GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<MembershipType> GetAllWithLogicalyDeleted() throws Exception {
        IMembershipTypeService service = (IMembershipTypeService) ctx.getAttribute("MembershipTypeService");
        return service.GetAllWithLogicalyDeleted();
    }
    
}
