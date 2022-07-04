package Service.Interfaces;

import java.util.List;
import Model.Comment;
import Model.Users.Customer;

public interface ICommentService extends ICrud<Comment> {

    public boolean CheckIfCanLeaveComment(Customer customer, int idOfFacility);

    public List<Comment> GetAllApprovedComments();

    public List<Comment> GetAllApprovedCommentsForFacility(int idOfFacility);
    
}
