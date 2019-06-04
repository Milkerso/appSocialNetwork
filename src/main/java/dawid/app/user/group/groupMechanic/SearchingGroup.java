package dawid.app.user.group.groupMechanic;

import dawid.app.user.User;
import dawid.app.user.group.GroupService;

public class SearchingGroup {
    private GroupService groupService;
    public boolean checkGroup(User user)
    {
        if(user.getGroups().isEmpty())
        {
         //   AllGroup group=groupService.searchGroupByAllArgument(user.getPhysicalActivity(),user.getFreeTime(),user.getEmail());
        }
        return true;

    }


}
