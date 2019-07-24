package dawid.app.user.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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
    public List<AllGroup> findByUserID(int userId) {
       return groupRepository.findByUserId(userId);
    }

    @Override
    public AllGroup findByGroupId(int groupId) {
        return groupRepository.findByGroupId(groupId);
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
