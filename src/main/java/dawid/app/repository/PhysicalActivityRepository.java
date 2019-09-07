package dawid.app.repository;


import dawid.app.model.PhysicalActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("physicalActivityRepository")
public interface PhysicalActivityRepository extends JpaRepository<PhysicalActivity, Integer> {

    PhysicalActivity findById(int id);
}
