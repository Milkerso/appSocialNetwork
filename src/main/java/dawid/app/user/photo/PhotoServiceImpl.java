package dawid.app.user.photo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
    public Photo findByUserID(int userId) {
        return photoRepository.findByUserId(userId);
    }

    @Override
    public void updateUserProfilPhoto(String newName, String newDescription, byte[] newData, int id) {
        photoRepository.updateUserProfilPhoto(newName,newDescription,newData,id);
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
