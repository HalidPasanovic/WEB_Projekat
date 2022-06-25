package Service;

import java.util.List;
import Model.Comment;
import Repository.CommentRepository;
import Repository.Interfaces.ICommentRepository;
import Service.Interfaces.ICommentService;

public class CommentService implements ICommentService {

    private ICommentRepository repository;

    public CommentService(String fileName) {
        repository = new CommentRepository(fileName);
    }

    @Override
    public void Create(Comment element) throws Exception {
        repository.Create(element);
    }

    @Override
    public Comment Read(int id) throws Exception {
        return repository.Read(id);
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
        return repository.GetAll();
    }
    
}
