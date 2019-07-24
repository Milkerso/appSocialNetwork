package dawid.app.post;

import dawid.app.user.group.AllGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.acl.Group;
import java.util.List;

@Service("postService")
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;
    @Override
    public Post findById(int id) {
       return postRepository.findById(id);
    }

    @Override
    public List<Post> findByGroupId(AllGroup allGroup) {
        return postRepository.findByGroupId(allGroup);
    }

    @Override
    public void save(Post post) {
        postRepository.save(post);
    }


}
