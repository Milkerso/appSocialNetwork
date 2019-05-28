package dawid.app.user.group;

import org.springframework.beans.factory.annotation.Autowired;

public class GroupServiceImpl implements GroupService{
    @Autowired
    private GroupRepository groupRepository;
    @Override
    public void saveGroup(Group group) {
        groupRepository.save(group);
    }

    @Override
    public Group findByUserID(int userId) {
        return null;
    }

    @Override
    public Group findGroupByUserId(int userId) {
        return null;
    }

    @Override
    public Group searchGroupByAllArgument(String physical_activities, String free_time, String city) {
      return groupRepository.searchGroupByAllArgument(physical_activities,free_time,city);
    }


}
