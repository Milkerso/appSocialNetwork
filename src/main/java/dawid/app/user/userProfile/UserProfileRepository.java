package dawid.app.user.userProfile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.sql.Date;

@Repository("userProfileRepository")
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {

    UserProfile findUserProfileById(int userProfileId);
    @Modifying
    @Query("UPDATE UserProfile u SET u.language = :newlanguage, u.number = :newnumber, u.character = :newcharacter, u.birthDate = :newbirthDate WHERE u.id= :id")
    void updateRegisterStepTwo(@Param("newlanguage") String newlanguage, @Param("newnumber") int newnumber,
                               @Param("newcharacter") String newcharacter, @Param("newbirthDate") Date newbirthDate, @Param("id") Integer id);

    @Modifying
    @Query("UPDATE UserProfile u SET u.whoSearch = :newWhoSearch, u.description = :newDescription WHERE u.id= :id")
    void updateRegisterStepThree(String newWhoSearch, String newDescription, int id);

}
