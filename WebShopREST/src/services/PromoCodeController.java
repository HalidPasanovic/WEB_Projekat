package services;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

import Model.PromoCode;
import Service.PromoCodeService;
import Service.Interfaces.IPromoCodeService;
import services.Interfaces.ICrud;

@Path("/promocodes")
public class PromoCodeController implements ICrud<PromoCode> {

    @Context
    ServletContext ctx;

    public PromoCodeController() {}

    @PostConstruct
    public void init() {
        if (ctx.getAttribute("PromoCodeService") == null) {
            String contextPath = ctx.getRealPath("");
            ctx.setAttribute("PromoCodeService", new PromoCodeService(contextPath));
            System.out.println(contextPath);
        }
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public void Create(PromoCode element) throws Exception {
        if (element.getDiscountPercentage() > 99 || element.getDiscountPercentage() < 1) {
            throw new Exception("Form not inputed correctly");
        }
        IPromoCodeService service = (IPromoCodeService) ctx.getAttribute("PromoCodeService");
        service.Create(element);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public PromoCode Read(@PathParam("id") int id) throws Exception {
        IPromoCodeService service = (IPromoCodeService) ctx.getAttribute("PromoCodeService");
        return service.Read(id);
    }

    @PUT
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public void Update(PromoCode element) throws Exception {
        IPromoCodeService service = (IPromoCodeService) ctx.getAttribute("PromoCodeService");
        service.Update(element);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public void Delete(@PathParam("id") int id) throws Exception {
        IPromoCodeService service = (IPromoCodeService) ctx.getAttribute("PromoCodeService");
        service.Delete(id);
    }

    @DELETE
    @Path("/physically/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public void DeletePhysically(@PathParam("id") int id) throws Exception {
        IPromoCodeService service = (IPromoCodeService) ctx.getAttribute("PromoCodeService");
        service.DeletePhysically(id);
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<PromoCode> GetAll() throws Exception {
        IPromoCodeService service = (IPromoCodeService) ctx.getAttribute("PromoCodeService");
        return service.GetAll();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<PromoCode> GetAllWithLogicalyDeleted() throws Exception {
        IPromoCodeService service = (IPromoCodeService) ctx.getAttribute("PromoCodeService");
        return service.GetAllWithLogicalyDeleted();
    }

    @GET
    @Path("/promo/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public PromoCode GetCodeIfExists(@PathParam("code") String code) throws Exception {
        IPromoCodeService service = (IPromoCodeService) ctx.getAttribute("PromoCodeService");
        List<PromoCode> codes = service.GetAll();
        for (PromoCode promoCode : codes) {
            if (promoCode.getCode().equals(code)) {
                LocalDate promoCodeDate = LocalDate.parse(promoCode.getHowLongItWorksDate());
                if (promoCodeDate.isAfter(LocalDate.now())) {
                    if (promoCode.getAmmountOfUsage() > 0) {
                        return promoCode;
                    }
                    throw new Exception("Code used up");
                }
                throw new Exception("Code expired");
            }
        }
        throw new Exception("Code doesnt exist");
    }

}
