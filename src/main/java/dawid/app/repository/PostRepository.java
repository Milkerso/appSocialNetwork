package dawid.app.repository;


import dawid.app.model.AllGroup;
import dawid.app.model.Photo;
import dawid.app.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("postRepository")
public interface PostRepository extends JpaRepository<Post, Integer> {

    Post findById(int id);
    List<Post> findByGroupId(AllGroup allGroup);

    interface PhotoService {

         void savePhoto(Photo photo);

         Photo findByUserIDProfilPhoto(int userId);

         void updateUserProfilePhoto(String name, String description, byte[] data, int id);
         void deletePhoto(int id);
         void checkProfilPhoto(int checkProfilPhotoId);
         void uncheckProfilPhoto(int uncheckProfilPhotoId);

    }
}
