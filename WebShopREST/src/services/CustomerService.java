package services;

import java.util.ArrayList;
import java.util.List;

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

import Model.Users.Administrator;
import Model.Users.Customer;
import Repository.Interfaces.Users.ICustomerRepository;
import Repository.Users.AdministratorRepository;
import Repository.Users.CustomerRepository;
import Service.Interfaces.Users.ICustomerService;

@Path("/customers")
public class CustomerService implements ICustomerService {
	
	@Context
	ServletContext ctx;
	
	public CustomerService() {}
	
	@PostConstruct
	public void init() {
		if (ctx.getAttribute("customerRepository") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("customerRepository", new CustomerRepository(contextPath + "/customers.csv"));
			System.out.println(contextPath);
		}
	}
	
	
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void Create(Customer element) throws Exception {
		CustomerRepository repo = (CustomerRepository) ctx.getAttribute("customerRepository");
        repo.Create(element);
    }
	
    @GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public Customer Read(@PathParam("id") int id) throws Exception {
    	CustomerRepository repo = (CustomerRepository) ctx.getAttribute("customerRepository");
        return repo.Read(id);
    }

    @PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public void Update(Customer element) throws Exception {
    	CustomerRepository repo = (CustomerRepository) ctx.getAttribute("customerRepository");
    	repo.Update(element);
    }

    @DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public void Delete(@PathParam("id") int id) throws Exception {
    	CustomerRepository repo = (CustomerRepository) ctx.getAttribute("customerRepository");
    	repo.Delete(id);
    }

    @GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    public List<Customer> GetAll() {
    	CustomerRepository repo = (CustomerRepository) ctx.getAttribute("customerRepository");
    	return repo.GetAll();
    	
    }
 
}
