package services;

import java.util.ArrayList;
import java.util.List;
import Model.Adress;
import Model.Comment;
import Model.CommentStatus;
import Model.Location;
import Model.Facilities.FacilityType;
import Model.Facilities.RecreationType;
import Model.Facilities.SportFacility;
import Model.Trainings.Training;
import services.Interfaces.ICrud;
import Service.Facilities.RecreationTypeService;
import Service.Facilities.SportFacilityService;
import Service.CommentService;
import Service.Interfaces.ICommentService;

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
import DTO.SportFacilityDTO;

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
		}
        if (ctx.getAttribute("CommentService") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("CommentService", new CommentService(contextPath));
		}
        if (ctx.getAttribute("RecreationTypeService") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("RecreationTypeService", new RecreationTypeService(contextPath));
		}
	}

    @POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void Create(SportFacility element) throws Exception {
        try {
            SportFacilityService service = (SportFacilityService) ctx.getAttribute("SportFacilityService");
            service.Create(element);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
    
    @POST
	@Path("/createandreturn")
	@Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public int Create2(SportFacility element) throws Exception {
        try {
            SportFacilityService service = (SportFacilityService) ctx.getAttribute("SportFacilityService");
            return service.CreateAndReturn(element);
        } catch (Exception e) {
            //TODO: handle exception
        }
		return 0;
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

    @GET
	@Path("/dto/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public SportFacilityDTO ReadDTO(@PathParam("id") int id) throws Exception {
        try {
            SportFacilityService service = (SportFacilityService) ctx.getAttribute("SportFacilityService");
            SportFacilityDTO temp = new SportFacilityDTO(service.Read(id));
            temp.rating = GetRating(temp.id);
            return temp;
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
    
    @PUT
	@Path("/create/{prvi}&{drugi}")
	@Produces(MediaType.APPLICATION_JSON)
    public void Update2(@PathParam("prvi") int id,@PathParam("drugi") int idd) throws Exception {
        try {
            SportFacilityService service = (SportFacilityService) ctx.getAttribute("SportFacilityService");
            RecreationTypeService ss = (RecreationTypeService) ctx.getAttribute("RecreationTypeService");
            RecreationType t = ss.Read(idd);
            SportFacility sf = service.Read(id);
            sf.AddRecreationTypes(t);
            service.Update(sf);
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

    @GET
	@Path("/dto")
	@Produces(MediaType.APPLICATION_JSON)
    public List<SportFacilityDTO> GetAllDTO() {
        try {
            SportFacilityService service = (SportFacilityService) ctx.getAttribute("SportFacilityService");
            List<SportFacilityDTO> dtos = new ArrayList<>();
            for (SportFacility it : service.GetAll()) {
                SportFacilityDTO temp = new SportFacilityDTO(it);
                temp.rating = GetRating(temp.id);
                dtos.add(temp);
            }
            return dtos;
        } catch (Exception e) {
            return null;
        }
    }

    private float GetRating(int id) throws Exception {
        ICommentService service = (ICommentService) ctx.getAttribute("CommentService");
        float result = 0;
        int count = 0;
        for (Comment it : service.GetAll()) {
            if(it.getStatus() == CommentStatus.Accepted){
                if(it.getFacility().getId() == id){
                    count++;
                    result += it.getRating();
                }
            }
        }
        if(count == 0){
            return 0;
        }
        return result/count;
    }

    @GET
	@Path("/trainings/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public List<Training> GetAllTrainingsForFacility(@PathParam("id") int id) throws Exception {
        try {
            SportFacilityService service = (SportFacilityService) ctx.getAttribute("SportFacilityService");
            return service.GetAllTrainingsForFacility(id);
        } catch (Exception e) {
            return null;
        }
    }

    @GET
	@Path("/comments/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public List<Comment> GetAllCommentsForFacility(@PathParam("id") int id) throws Exception {
        try {
            SportFacilityService service = (SportFacilityService) ctx.getAttribute("SportFacilityService");
            return service.GetAllCommentsForFacility(id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @DELETE
	@Path("/physically/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public void DeletePhysically(@PathParam("id") int id) throws Exception {
        try {
            SportFacilityService service = (SportFacilityService) ctx.getAttribute("SportFacilityService");
            service.DeletePhysically(id);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    @Override
    @GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
    public List<SportFacility> GetAllWithLogicalyDeleted() throws Exception {
        SportFacilityService service = (SportFacilityService) ctx.getAttribute("SportFacilityService");
        return service.GetAllWithLogicalyDeleted();
    }
    
}
