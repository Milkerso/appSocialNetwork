package dawid.app.user.place;


import dawid.app.user.photo.Photo;
import org.hibernate.boot.jaxb.hbm.spi.JaxbHbmParentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("placeRepository")
public interface PlaceRepository extends JpaRepository<Photo, Integer> {

}
