package dawid.app.user.group;

public interface GroupService {

    void saveGroup(AllGroup group);

    AllGroup findByUserID(int userId);
    AllGroup findGroupByUserId(int userId);
    AllGroup searchGroupByAllArgument(int physical_activities, int free_time, String city);
     void saveUserProfileGroups(AllGroup allGroup);
}
