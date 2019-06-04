package dawid.app.user.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository("groupRepository")
public interface GroupRepository extends JpaRepository<AllGroup, Integer> {

    @Query(value = "Select * from group", nativeQuery = true)
    Set<AllGroup> allGroup();

    @Query(value = "Select * from all_group where all_group.common_physical_activities=:physical_activities AND all_group.common_free_time=:free_time AND all_group.common_city LIKE :city", nativeQuery = true)
    AllGroup searchGroupByAllArgument(@Param("physical_activities") int physical_activities,
                                      @Param("free_time") int free_time, @Param("city") String city);


    @Query(value = "Select * from group where group_id=", nativeQuery = true)
    AllGroup findByUserId(int userId);

}
