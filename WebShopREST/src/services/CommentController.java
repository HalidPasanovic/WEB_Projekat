package services;

import java.util.List;
import Model.Comment;
import Model.Users.Customer;
import Service.CommentService;
import Service.Interfaces.ICommentService;
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

@Path("/comment")
public class CommentController implements ICrud<Comment>{

    @Context
	ServletContext ctx;

    public CommentController() {}

    @PostConstruct
	public void init() {
		if (ctx.getAttribute("CommentService") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("CommentService", new CommentService(contextPath));
		}
	}

    @POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public void Create(Comment element) throws Exception {
        ICommentService service = (ICommentService) ctx.getAttribute("CommentService");
        if(element.getRating() >= 1 && element.getRating() <= 5){
            service.Create(element);
            return;
        }
        throw new Exception("Rating must be in range of 1-5");
    }

    @GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public Comment Read(@PathParam("id") int id) throws Exception {
        ICommentService service = (ICommentService) ctx.getAttribute("CommentService");
        return service.Read(id);
    }

    @PUT
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public void Update(Comment element) throws Exception {
        ICommentService service = (ICommentService) ctx.getAttribute("CommentService");
        service.Update(element);
    }

    @DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public void Delete(@PathParam("id") int id) throws Exception {
        ICommentService service = (ICommentService) ctx.getAttribute("CommentService");
        service.Delete(id);
    }

    @DELETE
	@Path("/physically/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public void DeletePhysically(@PathParam("id") int id) throws Exception {
        ICommentService service = (ICommentService) ctx.getAttribute("CommentService");
        service.DeletePhysically(id);
    }

    @GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<Comment> GetAll() throws Exception {
        ICommentService service = (ICommentService) ctx.getAttribute("CommentService");
        return service.GetAll();
    }

    @GET
	@Path("/rating/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public float GetRating(@PathParam("id") int id) throws Exception {
        ICommentService service = (ICommentService) ctx.getAttribute("CommentService");
        float result = 0;
        int count = 0;
        for (Comment it : service.GetAll()) {
            if(it.getFacility().getId() == id){
                count++;
                result += it.getRating();
            }
        }
        if(count == 0){
            return 0;
        }
        return result/count;
    }

    @POST
	@Path("/canComment/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public boolean GetIfCanComment(@PathParam("id") int id, Customer customer) throws Exception {
        ICommentService service = (ICommentService) ctx.getAttribute("CommentService");
        return service.CheckIfCanLeaveComment(customer, id);
    }

    @GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<Comment> GetAllWithLogicalyDeleted() throws Exception {
        ICommentService service = (ICommentService) ctx.getAttribute("CommentService");
        return service.GetAllWithLogicalyDeleted();
    }
    
    @PUT
	@Path("/reject/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public Comment Reject(@PathParam("id") int id) throws Exception {
        CommentService service = (CommentService) ctx.getAttribute("CommentService");
        service.Reject(id);
        return service.Read(id);
    }
    
    @PUT
	@Path("/accept/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public Comment Accept(@PathParam("id") int id) throws Exception {
        CommentService service = (CommentService) ctx.getAttribute("CommentService");
        service.Accept(id);
        return service.Read(id);
    }

    @GET
	@Path("/accepted/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public List<Comment> GetAllApprovedCommentsForFacility(@PathParam("id") int id) throws Exception {
        ICommentService service = (ICommentService) ctx.getAttribute("CommentService");
        return service.GetAllApprovedCommentsForFacility(id);
    }

    @POST
	@Path("/accepted/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public List<Comment> GetAllApprovedCommentsForFacilityWithUserComment(@PathParam("id") int id, Customer customer) throws Exception {
        ICommentService service = (ICommentService) ctx.getAttribute("CommentService");
        return service.GetAllApprovedCommentsForFacilityWithUserComments(id, customer.getId());
    }
    
}
