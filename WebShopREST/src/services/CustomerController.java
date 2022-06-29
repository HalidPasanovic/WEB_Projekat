package services;

import java.util.ArrayList;
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
import Model.Memberships.Membership;
import Model.Users.Customer;
import Model.Users.CustomerType;
import Model.Users.Gender;
import Model.Users.UserRole;
import services.Interfaces.ICrud;
import Service.Users.CustomerService;

@Path("/customers")
public class CustomerController implements ICrud<Customer> {
	
	@Context
	ServletContext ctx;
	
	public CustomerController() {}
	
	@PostConstruct
	public void init() {
		if (ctx.getAttribute("customerService") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("customerService", new CustomerService(contextPath));
			System.out.println(contextPath);
		}
	}
	
	
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void Create(Customer element) throws Exception {
		CustomerService repo = (CustomerService) ctx.getAttribute("customerService");
        repo.Create(element);
    }
	
    @GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public Customer Read(@PathParam("id") int id) throws Exception {
    	CustomerService repo = (CustomerService) ctx.getAttribute("customerService");
        return repo.Read(id);
    }

    @PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public void Update(Customer element) throws Exception {
    	CustomerService repo = (CustomerService) ctx.getAttribute("customerService");
    	repo.Update(element);
    }

    @DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public void Delete(@PathParam("id") int id) throws Exception {
    	CustomerService repo = (CustomerService) ctx.getAttribute("customerService");
    	repo.Delete(id);
    }

    @GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    public List<Customer> GetAll() {
    	CustomerService repo = (CustomerService) ctx.getAttribute("customerService");
    	return repo.GetAll();
    	
    }

	@DELETE
	@Path("/physically/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
	public void DeletePhysically(int id) throws Exception {
		CustomerService repo = (CustomerService) ctx.getAttribute("customerService");
    	repo.DeletePhysically(id);
	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
	public List<Customer> GetAllWithLogicalyDeleted() throws Exception {
		CustomerService repo = (CustomerService) ctx.getAttribute("customerService");
		return repo.GetAllWithLogicalyDeleted();
	}
 
}
