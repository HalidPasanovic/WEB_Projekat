package Repository;

import java.util.ArrayList;
import java.util.List;
import Model.Comment;
import Model.Facilities.SportFacility;
import Model.Users.Customer;
import Repository.Interfaces.ICommentRepository;

public class CommentRepository extends Repository<Comment> implements ICommentRepository {

    private static CommentRepository instance;

    public static CommentRepository getInstance(String contextPath) {
        if (instance == null) {
            instance = new CommentRepository(contextPath);
        }
        return instance;
    }

    public CommentRepository(String contextPath) {
        super();
        this.fileName = contextPath + "/data/comments.csv";
        InstantiateRepository();
    }

    @Override
    public List<Comment> GetAll() {
        List<Comment> result = new ArrayList<Comment>();
    	List<List<String>> objects = serializer.FromCSV(fileName);
        for (List<String> object : objects) {
            Comment element = new Comment();
            element.FromCSV(object);
            if(!element.isDeleted()){
                result.add(element);
            }
        }
        return result;
    }

    @Override
    public List<Comment> GetAllWithLogicalyDeleted() {
        List<Comment> result = new ArrayList<Comment>();
    	List<List<String>> objects = serializer.FromCSV(fileName);
        for (List<String> object : objects) {
            Comment element = new Comment();
            element.FromCSV(object);
            result.add(element);
        }
        return result;
    }

    @Override
    public void CheckIfElementEligableForDeletion(Comment element) throws Exception {}

    @Override
    protected void DeleteDependanciesInOtherRepositories(Comment element) throws Exception {}

    @Override
    protected void DeleteDependanciesInOtherRepositoriesPhysically(Comment element) throws Exception {}

    @Override
    public void RemoveCommentsForDeletedFacility(SportFacility deletedFacility) throws Exception {
        List<Comment> comments = GetAll();
        List<Comment> deletedComments = new ArrayList<>();
        for (Comment comment : comments) {
            if(comment.getFacility().getId() == deletedFacility.getId()){
                deletedComments.add(comment);
            }
        }
        comments.removeAll(deletedComments);
        serializer.ToCSV(fileName, comments);
    }

    @Override
    public void RemoveCommentsForDeletedCustomer(Customer deletedCustomer) throws Exception {
        List<Comment> comments = GetAll();
        List<Comment> deletedComments = new ArrayList<>();
        for (Comment comment : comments) {
            if(comment.getCustomer().getId() == deletedCustomer.getId()){
                deletedComments.add(comment);
            }
        }
        comments.removeAll(deletedComments);
        serializer.ToCSV(fileName, comments);
    }

}
