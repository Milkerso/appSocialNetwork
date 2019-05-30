package dawid.app.user.userProfile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("freeTimeRepository")
public interface FreeTimeRepository extends JpaRepository<FreeTime, Integer> {

    FreeTime findById(int id);
}
