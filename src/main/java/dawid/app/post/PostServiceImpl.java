package dawid.app.post;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("postService")
@Transactional
public class PostServiceImpl implements PostService {
    @Override
    public void findById(int id) {

    }
}
