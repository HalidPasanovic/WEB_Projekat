package services;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.tomcat.websocket.server.UriTemplate;

import Model.Users.Administrator;
import Model.Users.Customer;
import Model.Users.Manager;
import Model.Users.Trainer;
import Repository.Users.AdministratorRepository;
import Repository.Users.CustomerRepository;
import Repository.Users.ManagerRepository;
import Repository.Users.TrainerRepository;
import beans.User;
import dao.UserDAO;

@Path("/login")
public class LoginService {
	
	@Context
	ServletContext ctx;
	
	public LoginService() {
	}
	
	@PostConstruct
	public void init() {
    	
		if (ctx.getAttribute("managerRepository") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("managerRepository", new ManagerRepository(contextPath + "/managers.csv"));
		}
		if (ctx.getAttribute("trainerRepository") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("trainerRepository", new TrainerRepository(contextPath + "/trainers.csv"));
		}
		if (ctx.getAttribute("administratorRepository") == null) {
			String contextPath = ctx.getRealPath("");
			System.out.println(contextPath);
			ctx.setAttribute("administratorRepository", new AdministratorRepository(contextPath + "/admins.csv"));
		}
		if (ctx.getAttribute("customersRepository") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("customersRepository", new CustomerRepository(contextPath + "/customers.csv"));
		}
	}
	
	@POST
	@Path("/{username}&{password}")
	@Produces(MediaType.APPLICATION_JSON)
    public int Read(@PathParam("username") String username,@PathParam("password") String password) throws Exception {
		ManagerRepository repo = (ManagerRepository) ctx.getAttribute("managerRepository");
		List<Manager> managers = repo.GetAll();
		for(Manager manager : managers)
		{
			if((manager.getUsername().equals(username)) && (manager.getPassword().equals(password)))
			{
				return 1;
			}
		}
		CustomerRepository repoc = (CustomerRepository) ctx.getAttribute("customersRepository");
		List<Customer> customers = repoc.GetAll();
		for(Customer customer : customers)
		{
			if((customer.getUsername().equals(username)) && (customer.getPassword().equals(password)))
			{
				return 1;
			}
		}
		AdministratorRepository repoa = (AdministratorRepository) ctx.getAttribute("administratorRepository");
		List<Administrator> admins = repoa.GetAll();
		for(Administrator admin : admins)
		{
			if((admin.getUsername().equals(username)) && (admin.getPassword().equals(password)))
			{
				return 1;
			}
		}
		TrainerRepository repot = (TrainerRepository) ctx.getAttribute("trainerRepository");
		List<Trainer> trainers = repot.GetAll();
		for(Trainer t : trainers)
		{
			if((t.getUsername().equals(username)) && (t.getPassword().equals(password)))
			{
				return 1;
			}
		}
		return -1;
    }
}
