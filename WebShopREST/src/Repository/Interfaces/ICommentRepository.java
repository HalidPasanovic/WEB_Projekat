package Repository.Interfaces;

import Model.Comment;
import Model.Facilities.SportFacility;
import Model.Users.Customer;

public interface ICommentRepository extends ICrud<Comment> {

    public void RemoveCommentsForDeletedFacility(SportFacility deletedFacility) throws Exception;

    public void RemoveCommentsForDeletedCustomer(Customer deletedCustomer) throws Exception;
    
}
