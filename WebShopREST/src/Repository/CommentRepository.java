package Repository;

import java.util.ArrayList;
import java.util.List;
import Model.Comment;
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
            result.add(element);
        }
        return result;
    }
    
}
