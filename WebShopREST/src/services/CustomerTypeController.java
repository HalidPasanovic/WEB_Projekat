package services;

import java.util.List;
import Model.Users.CustomerType;
import Service.Interfaces.Users.ICustomerTypeService;
import Service.Users.CustomerTypeService;
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

@Path("/customerType")
public class CustomerTypeController implements ICrud<CustomerType> {

    @Context
	ServletContext ctx;
	
	public CustomerController() {}
	
	@PostConstruct
	public void init() {
		if (ctx.getAttribute("CustomerTypeService") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("CustomerTypeService", new CustomerTypeService(contextPath));
		}
	}
    
    @POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public void Create(CustomerType element) throws Exception {
        ICustomerTypeService service = (ICustomerTypeService) ctx.getAttribute("CustomerTypeService");
        service.Create(element);
    }

    @GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public CustomerType Read(@PathParam("id") int id) throws Exception {
        ICustomerTypeService service = (ICustomerTypeService) ctx.getAttribute("CustomerTypeService");
        return service.Read(id);
    }

    @PUT
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public void Update(CustomerType element) throws Exception {
        ICustomerTypeService service = (ICustomerTypeService) ctx.getAttribute("CustomerTypeService");
        
    }

    @DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public void Delete(@PathParam("id") int id) throws Exception {
        ICustomerTypeService service = (ICustomerTypeService) ctx.getAttribute("CustomerTypeService");
        service.Delete(id);
    }

    @DELETE
	@Path("/physically/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public void DeletePhysically(@PathParam("id") int id) throws Exception {
        ICustomerTypeService service = (ICustomerTypeService) ctx.getAttribute("CustomerTypeService");
        service.DeletePhysically(id);
    }

    @GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<CustomerType> GetAll() throws Exception {
        ICustomerTypeService service = (ICustomerTypeService) ctx.getAttribute("CustomerTypeService");
        return service.GetAll();
    }

    @GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<CustomerType> GetAllWithLogicalyDeleted() throws Exception {
        ICustomerTypeService service = (ICustomerTypeService) ctx.getAttribute("CustomerTypeService");
        return service.GetAllWithLogicalyDeleted();
    }
    
}
