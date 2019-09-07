package dawid.app.service;

import dawid.app.model.AllGroup;

import java.util.List;

public interface GroupService {

    void saveGroup(AllGroup group);

    List<AllGroup> findByUserID(int userId);
    AllGroup findByGroupId(int groupId);
    AllGroup searchGroupByAllArgument(int physical_activities, int free_time, String city);
     void saveUserProfileGroups(AllGroup allGroup);
}
