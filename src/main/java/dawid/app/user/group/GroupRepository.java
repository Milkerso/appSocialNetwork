package dawid.app.user.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository("groupRepository")
public interface GroupRepository extends JpaRepository<Group, Integer> {

    @Query(value = "Select * from group", nativeQuery = true)
    Set<Group> allGroup();

    @Query(value = "Select * from group where group.common_physical_activities=:physical_activities AND group.common_free_time=:free_time AND group.common_city=:city", nativeQuery = true)
    Group searchGroupByAllArgument(@Param("physical_activities") String physical_activities,
                                         @Param("free_time") String free_time,@Param("city") String city);

    @Query("UPDATE Photo p SET p.name = :newName, p.description = :newDescription, p.data = :newData WHERE p.id= :id")
    void updateUserProfilPhoto(@Param("newName") String newName, @Param("newDescription") String newDescription,
                               @Param("newData") byte[] newData, @Param("id") Integer id);
    @Query(value = "Select * from group where group_id=", nativeQuery = true)
    Group findByUserId(int userId);

}
