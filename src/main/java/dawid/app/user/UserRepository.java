package dawid.app.user;


import dawid.app.user.group.AllGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    @Modifying
    @Query("UPDATE User u SET u.password = :newPassword WHERE u.email= :email")
    void updateUserPassword(@Param("newPassword") String password, @Param("email") String email);

    @Modifying
    @Query("UPDATE User u SET u.active = :activeParam WHERE u.activationCode = :activationCode")
    void updateActivation(@Param("activeParam") int activeParam, @Param("activationCode") String activationCode);

    @Modifying
    @Query("UPDATE User u SET u.email = :newEmail WHERE u.id= :id")
    void updateUserProfile(@Param("newEmail") String newEmail, @Param("id") Integer id);

    List<User> findAllByGroups(AllGroup allGroup);


}
