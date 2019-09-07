package dawid.app.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Table(name = "photo")
public class Photo {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "photo_id")
    @Getter
    @Setter
    private int id;

    @Column(name ="name")
    @Getter
    @Setter
    private  String name;

    @Column(name="description")
    @Getter
    @Setter
    private  String description;

    @Column(name="profile_photo")
    @Getter
    @Setter
    private int profilePhoto;

    @Column(name="user_id")
    @Getter
    @Setter
    private int userId;

    @Column(name = "data")
    @Getter
    @Setter
    private byte[] data;

    @Transient
    @Getter
    @Setter
    private MultipartFile multipartFile;


}
