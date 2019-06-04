package dawid.app.post.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("commentRepository")
public interface CommentRepository extends JpaRepository<Comment,Integer> {

    void findById(int id);
}
