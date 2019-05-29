package dawid.app.user.photo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("photoRepository")
public interface PhotoRepository  extends JpaRepository<Photo, Integer> {

    @Query(value = "Select * from photo where photo.user_id=:userId AND photo.profile_photo=1", nativeQuery = true)
    Photo findByUserId(int userId);

    @Modifying
    @Query("UPDATE Photo p SET p.name = :newName, p.description = :newDescription, p.data = :newData WHERE p.id= :id")
    void updateUserProfilePhoto(@Param("newName") String newName, @Param("newDescription") String newDescription,
                           @Param("newData") byte[] newData, @Param("id") Integer id);

    @Modifying
    @Query(value = "DELETE FROM photo WHERE photoId = :id", nativeQuery = true)
    void deletePhoto(@Param("id") int id);

    @Modifying
    @Query(value = "Update Photo p Set p.profilePhoto=0 where p.userId = :uncheckProfilPhotoId AND p.profilePhoto=1")
    void uncheckProfilPhoto(@Param("uncheckProfilPhotoId") int uncheckProfilPhotoId);

    @Modifying
    @Query(value = "Update Photo p Set p.profilePhoto=1 where p.userId = :checkProfilPhotoId")
    void checkProfilPhoto(@Param("checkProfilPhotoId") int checkProfilPhotoId);

}
