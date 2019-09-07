package dawid.app.service;

import dawid.app.model.AllGroup;
import dawid.app.model.Post;

import java.util.List;

public interface PostService {

    Post findById(int id);

    List<Post> findByGroupId(AllGroup allGroup);

    public void  save(Post post);
}
