package Service.Users;

import java.util.List;
import Model.Users.Administrator;
import Repository.Users.AdministratorRepository;
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

@Path("/admins")
public class AdministratorService implements IAdministratorService {
    
    //private IAdministratorRepository repository = new AdministratorRepository("");
    
	@Context
	ServletContext ctx;
	
	public AdministratorService() {}
	
    @PostConstruct
	public void init() {
		if (ctx.getAttribute("administratorRepository") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("administratorRepository", new AdministratorRepository(contextPath + "/products.csv"));
			System.out.println(contextPath);
		}
	}

    
    
    public void Create(Administrator element) throws Exception {
    	AdministratorRepository repo = (AdministratorRepository) ctx.getAttribute("administratorRepository");
        repo.Create(element);
    }

    @GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public Administrator Read(@PathParam("id") int id) throws Exception {
    	AdministratorRepository repo = (AdministratorRepository) ctx.getAttribute("administratorRepository");
        return repo.Read(id);
    }

    @PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public void Update(Administrator element) throws Exception {
    	AdministratorRepository repo = (AdministratorRepository) ctx.getAttribute("administratorRepository");
    	repo.Update(element);
    }

    @DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public void Delete(@PathParam("id") int id) throws Exception {
    	AdministratorRepository repo = (AdministratorRepository) ctx.getAttribute("administratorRepository");
    	repo.Delete(id);
    }

    @GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    public List<Administrator> GetAll() {
    	//AdministratorRepository repo = (AdministratorRepository) ctx.getAttribute("administratorRepository");
        //return repo.GetAll();
    	System.out.println("Uspesno");
    	return null;
    }

    
}
