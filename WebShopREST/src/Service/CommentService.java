package Service;

import java.util.HashMap;
import java.util.List;
import Model.Comment;
import Model.Facilities.SportFacility;
import Model.Users.Customer;
import Repository.CommentRepository;
import Repository.Interfaces.ICommentRepository;
import Service.Facilities.SportFacilityService;
import Service.Interfaces.ICommentService;
import Service.Interfaces.Facilities.ISportFacilityService;
import Service.Interfaces.Users.ICustomerService;
import Service.Users.CustomerService;
import Utilities.DataStructureConverter;

public class CommentService implements ICommentService {

    private ICommentRepository repository;
    private ICustomerService customerService;
    private ISportFacilityService sportFacilityService;

    public CommentService(String contextPath) {
        repository = CommentRepository.getInstance(contextPath);
        customerService = new CustomerService(contextPath);
        sportFacilityService = new SportFacilityService(contextPath);
    }

    @Override
    public void Create(Comment element) throws Exception {
        repository.Create(element);
    }

    @Override
    public Comment Read(int id) throws Exception {
        return FindByID(GetAll(), id);
    }

    @Override
    public void Update(Comment element) throws Exception {
        repository.Update(element);
    }

    @Override
    public void Delete(int id) throws Exception {
        repository.Delete(id);
    }

    @Override
    public List<Comment> GetAll() {
        List<Comment> comments = repository.GetAll();
        comments = SetCustomerForComments(comments);
        return SetFacilityForComments(comments);
    }

    private List<Comment> SetCustomerForComments(List<Comment> comments){
        DataStructureConverter<Customer> converter = new DataStructureConverter<Customer>();
        HashMap<Integer, Customer> customers = converter.ConvertListToMap(customerService.GetAll());
        for (Comment comment : comments) {
            if(customers.containsKey(comment.getCustomer().getId())){
                comment.setCustomer(customers.get(comment.getCustomer().getId()));
            }
        }
        return comments;
    }

    private List<Comment> SetFacilityForComments(List<Comment> comments){
        DataStructureConverter<SportFacility> converter = new DataStructureConverter<SportFacility>();
        HashMap<Integer, SportFacility> facilities = converter.ConvertListToMap(sportFacilityService.GetAll());
        for (Comment comment : comments) {
            if(facilities.containsKey(comment.getFacility().getId())){
                comment.setFacility(facilities.get(comment.getFacility().getId()));
            }
        }
        return comments;
    }

    private Comment FindByID(List<Comment> comments, int id) throws Exception{
        for (Comment comment : comments) {
            if(comment.getId() == id){
                return comment;
            }
        }
        throw new Exception("Element not found");
    }
    
}
