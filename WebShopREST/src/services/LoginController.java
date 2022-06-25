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
import Service.Users.AdministratorService;
import Service.Users.CustomerService;
import Service.Users.ManagerService;
import Service.Users.TrainerService;

@Path("/login")
public class LoginController {
	
	@Context
	ServletContext ctx;
	
	public LoginController() {
	}
	
	@PostConstruct
	public void init() {
    	String contextPath = ctx.getRealPath("");
		System.out.println(contextPath);
		if (ctx.getAttribute("managerService") == null) {
			ctx.setAttribute("managerService", new ManagerService(contextPath));
		}
		if (ctx.getAttribute("trainerService") == null) {
			ctx.setAttribute("trainerService", new TrainerService(contextPath));
		}
		if (ctx.getAttribute("administratorService") == null) {
			ctx.setAttribute("administratorService", new AdministratorService(contextPath));
		}
		if (ctx.getAttribute("customersService") == null) {
			ctx.setAttribute("customersService", new CustomerService(contextPath));
		}
	}
	
	@POST
	@Path("/{username}&{password}")
	@Produces(MediaType.APPLICATION_JSON)
    public int Read(@PathParam("username") String username,@PathParam("password") String password) throws Exception {
		ManagerService repo = (ManagerService) ctx.getAttribute("managerService");
		List<Manager> managers = repo.GetAll();
		for(Manager manager : managers)
		{
			if((manager.getUsername().equals(username)) && (manager.getPassword().equals(password)))
			{
				return 1;
			}
		}
		CustomerService repoc = (CustomerService) ctx.getAttribute("customersService");
		List<Customer> customers = repoc.GetAll();
		for(Customer customer : customers)
		{
			if((customer.getUsername().equals(username)) && (customer.getPassword().equals(password)))
			{
				return 1;
			}
		}
		AdministratorService repoa = (AdministratorService) ctx.getAttribute("administratorService");
		List<Administrator> admins = repoa.GetAll();
		for(Administrator admin : admins)
		{
			if((admin.getUsername().equals(username)) && (admin.getPassword().equals(password)))
			{
				return 1;
			}
		}
		TrainerService repot = (TrainerService) ctx.getAttribute("trainerService");
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
