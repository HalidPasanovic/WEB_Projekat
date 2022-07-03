package Service.Interfaces;

import Model.Comment;
import Model.Users.Customer;

public interface ICommentService extends ICrud<Comment> {

    public boolean CheckIfCanLeaveComment(Customer customer, int idOfFacility);
    
}
