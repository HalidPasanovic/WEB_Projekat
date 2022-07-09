package services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import Model.PromoCode;
import Model.Memberships.Membership;
import Model.Memberships.MembershipType;
import Model.Memberships.MembershipTypeEnum;
import Model.Users.Customer;
import Service.PromoCodeService;
import Service.Interfaces.IPromoCodeService;
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
public class MembershipController{

    @Context
	ServletContext ctx;

    public MembershipController() {}

    @PostConstruct
	public void init() {
		if (ctx.getAttribute("MembershipService") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("MembershipService", new MembershipService(contextPath));
            ctx.setAttribute("PromoCodeService", new PromoCodeService(contextPath));
		}
	}

    @POST
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public void Create(Membership element, @PathParam("id") int id) throws Exception {
        IMembershipService service = (IMembershipService) ctx.getAttribute("MembershipService");
        IPromoCodeService promoCodeService = (IPromoCodeService) ctx.getAttribute("PromoCodeService");
        try {
            PromoCode code = promoCodeService.Read(id);
            LocalDate promoCodeDate = LocalDate.parse(code.getHowLongItWorksDate());
            if(promoCodeDate.isAfter(LocalDate.now())){
                if(code.getAmmountOfUsage() > 0){
                    code.setAmmountOfUsage(code.getAmmountOfUsage() - 1);
                    promoCodeService.Update(code);
                }
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        
       
        LocalDate date = LocalDate.now();
        if(element.getType().gettype().equals(MembershipTypeEnum.Yearly)){
            date = date.plusYears(1);
        } else if(element.getType().gettype().equals(MembershipTypeEnum.Monthly)){
            date = date.plusMonths(1);
        } else if(element.getType().gettype().equals(MembershipTypeEnum.Weeklong)){
            date = date.plusWeeks(1);
        }
        element.setValidUntil(String.valueOf(date));
        element.setPaymentDate(String.valueOf(LocalDate.now()));
        service.Create(element);
    }

    @GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public Membership Read(@PathParam("id") int id) throws Exception {
        IMembershipService service = (IMembershipService) ctx.getAttribute("MembershipService");
        return service.Read(id);
    }

    @PUT
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    public void Update(Membership element) throws Exception {
        IMembershipService service = (IMembershipService) ctx.getAttribute("MembershipService");
        service.Update(element);
    }

    @DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public void Delete(@PathParam("id") int id) throws Exception {
        IMembershipService service = (IMembershipService) ctx.getAttribute("MembershipService");
        service.Delete(id);
    }

    @DELETE
	@Path("/physically/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public void DeletePhysically(@PathParam("id") int id) throws Exception {
        IMembershipService service = (IMembershipService) ctx.getAttribute("MembershipService");
        service.DeletePhysically(id);
    }

    @GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    public List<Membership> GetAll() throws Exception {
        IMembershipService service = (IMembershipService) ctx.getAttribute("MembershipService");
        return service.GetAll();
    }

    @GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
    public List<Membership> GetAllWithLogicalyDeleted() throws Exception {
        IMembershipService service = (IMembershipService) ctx.getAttribute("MembershipService");
        return service.GetAllWithLogicalyDeleted();
    }
    
}
