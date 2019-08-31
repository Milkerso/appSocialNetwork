package dawid.app.post;

import dawid.app.user.group.AllGroup;

import java.util.List;

public interface PostService {

    Post findById(int id);

    List<Post> findByGroupId(AllGroup allGroup);

    public void  save(Post post);
}
