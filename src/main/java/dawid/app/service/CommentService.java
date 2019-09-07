package dawid.app.service;


import dawid.app.model.Comment;

public interface CommentService {
    void findById(int id);
    void save(Comment comment);
}
