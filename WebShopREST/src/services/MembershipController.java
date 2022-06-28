package services;

import java.util.List;
import Model.Memberships.Membership;
import Service.Interfaces.Memberships.IMembershipService;
import Service.Memberships.MembershipService;
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

@Path("/membership")
public class MembershipController implements ICrud<Membership>{

    @Context
	ServletContext ctx;

    public MembershipController() {}

    @PostConstruct
	public void init() {
		if (ctx.getAttribute("MembershipService") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("MembershipService", new MembershipService(contextPath));
		}
	}

    @POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public void Create(Membership element) throws Exception {
        IMembershipService service = (IMembershipService) ctx.getAttribute("MembershipService");
        service.Create(element);
    }

    @GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public Membership Read(@PathParam("id") int id) throws Exception {
        IMembershipService service = (IMembershipService) ctx.getAttribute("MembershipService");
        return service.Read(id);
    }

    @PUT
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public void Update(Membership element) throws Exception {
        IMembershipService service = (IMembershipService) ctx.getAttribute("MembershipService");
        service.Update(element);
    }

    @DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public void Delete(@PathParam("id") int id) throws Exception {
        IMembershipService service = (IMembershipService) ctx.getAttribute("MembershipService");
        service.Delete(id);
    }

    @DELETE
	@Path("/physically/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public void DeletePhysically(@PathParam("id") int id) throws Exception {
        IMembershipService service = (IMembershipService) ctx.getAttribute("MembershipService");
        service.DeletePhysically(id);
    }

    @GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<Membership> GetAll() throws Exception {
        IMembershipService service = (IMembershipService) ctx.getAttribute("MembershipService");
        return service.GetAll();
    }

    @GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<Membership> GetAllWithLogicalyDeleted() throws Exception {
        IMembershipService service = (IMembershipService) ctx.getAttribute("MembershipService");
        return service.GetAllWithLogicalyDeleted();
    }
    
}
