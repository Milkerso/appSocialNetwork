package dawid.app.post.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("commentService")
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentRepository commentRepository;

    @Override
    public void findById(int id) {
        commentRepository.findById(id);
    }

    @Override
    public void save(Comment comment)
    {
        commentRepository.save(comment);
    }
}
