package dawid.app.user.group;

import java.util.List;

public interface GroupService {

    void saveGroup(AllGroup group);

    List<AllGroup> findByUserID(int userId);
    AllGroup searchGroupByAllArgument(int physical_activities, int free_time, String city);
     void saveUserProfileGroups(AllGroup allGroup);
}
