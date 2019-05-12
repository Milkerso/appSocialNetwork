package dawid.app.user.photo;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Table(name = "photo")
public class Photo {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "photo_id")
    private int id;

    @Column(name ="name")
    private  String name;

    @Column(name="description")
    private  String description;

    @Column(name="profile_photo")
    private int profilePhoto;

    @Column(name="user_id")
    private int userId;

    @Column(name = "data")
    private byte[] data;

    @Transient
    private MultipartFile multipartFile;

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(int profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }



    public int getUserId() {
        return userId;
    }

    public byte[] getData() {
        return data;
    }

    public int getId() {
        return id;
    }
}
