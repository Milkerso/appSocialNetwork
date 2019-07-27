package dawid.app.post.comment;


public interface CommentService {
    void findById(int id);
    void save(Comment comment);
}
