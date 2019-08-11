package dawid.app.repository;

import dawid.app.controller.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("placeRepository")
public interface PlaceRepository extends JpaRepository<Place,Integer> {

}
