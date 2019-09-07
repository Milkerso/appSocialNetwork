package dawid.app.service;

import dawid.app.model.AllGroup;
import dawid.app.model.Post;
import dawid.app.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
