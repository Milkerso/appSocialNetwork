package dawid.app.user.userProfile;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("physicalActivityRepository")
public interface PhysicalActivityRepository extends JpaRepository<PhysicalActivity, Integer> {

    PhysicalActivity findById(int id);
}
