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
import Model.Users.User;
import Service.Users.AdministratorService;
import Service.Users.CustomerService;
import Service.Users.ManagerService;
import Service.Users.TrainerService;
import Service.Users.UserService;

@Path("/login")
public class LoginController {
	
	@Context
	ServletContext ctx;
	
	public LoginController() {
	}
	
	@PostConstruct
	public void init() {
    	String contextPath = ctx.getRealPath("");
		if (ctx.getAttribute("UserService") == null) {
			ctx.setAttribute("UserService", new UserService(contextPath));
		}
	}
	
	@POST
	@Path("/{username}&{password}")
	@Produces(MediaType.APPLICATION_JSON)
    public User Read(@PathParam("username") String username,@PathParam("password") String password) throws Exception {
		UserService userService = (UserService) ctx.getAttribute("UserService");
		try {
			User temp = userService.Login(username, password);
			return temp;
		} catch (Exception e) {
			return null;
		}
    }
}
