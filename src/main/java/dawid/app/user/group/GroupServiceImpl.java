package dawid.app.user.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("groupService")
@Transactional
public class GroupServiceImpl implements GroupService{
    @Autowired
    private GroupRepository groupRepository;
    @Override
    public void saveGroup(AllGroup group) {
        groupRepository.save(group);
    }

    @Override
    public AllGroup findByUserID(int userId) {
        return null;
    }

    @Override
    public AllGroup findGroupByUserId(int userId) {
        return null;
    }

    @Override
    public AllGroup searchGroupByAllArgument(int physical_activities, int free_time, String city) {
      return groupRepository.searchGroupByAllArgument(physical_activities,free_time,city);
    }

    @Override
    public void saveUserProfileGroups(AllGroup allGroup) {

        groupRepository.save(allGroup);
    }


}
