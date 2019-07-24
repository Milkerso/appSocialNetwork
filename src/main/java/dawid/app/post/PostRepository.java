package dawid.app.post;


import dawid.app.user.group.AllGroup;
import javafx.geometry.Pos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.security.acl.Group;
import java.util.List;

@Repository("postRepository")
public interface PostRepository extends JpaRepository<Post, Integer> {

    Post findById(int id);
    List<Post> findByGroupId(AllGroup allGroup);
}
