package dawid.app.user.photo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLOutput;

@Service("photoService")
@Transactional
public class PhotoServiceImpl implements PhotoService{

    @Autowired
    private PhotoRepository photoRepository;
    @Override
    public void savePhoto(Photo photo) {
            photoRepository.save(photo);

    }

    @Override
    public Photo findByUserIDProfilPhoto(int userId) {
        return photoRepository.findByUserIdProfilPhoto(userId);
    }

    @Override
    public void updateUserProfilePhoto(String newName, String newDescription, byte[] newData, int id) {
        System.out.println("update photo");
        photoRepository.updateUserProfilePhoto(newName,newDescription,newData,id);
    }

    @Override
    public void deletePhoto(int id) {
    photoRepository.deletePhoto(id);
    }

    @Override
    public void checkProfilPhoto(int checkProfilPhotoId) {
        photoRepository.checkProfilPhoto(checkProfilPhotoId);

    }

    @Override
    public void uncheckProfilPhoto(int uncheckProfilPhotoId) {
        photoRepository.uncheckProfilPhoto(uncheckProfilPhotoId);
    }


}
