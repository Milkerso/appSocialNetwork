package dawid.app.user.group;

import dawid.app.user.photo.Photo;

public interface GroupService {

    void saveGroup(Group group);

    Group findByUserID(int userId);
    Group findGroupByUserId(int userId);
    Group searchGroupByAllArgument( String physical_activities, String free_time, String city);
}
