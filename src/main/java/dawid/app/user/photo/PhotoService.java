package dawid.app.user.photo;

public interface PhotoService {

     void savePhoto(Photo photo);

     Photo findByUserID(int userId);
     void updateUserProfilePhoto(String name, String description, byte[] data,int id);
     void deletePhoto(int id);
     void checkProfilPhoto(int checkProfilPhotoId);
     void uncheckProfilPhoto(int uncheckProfilPhotoId);

}
