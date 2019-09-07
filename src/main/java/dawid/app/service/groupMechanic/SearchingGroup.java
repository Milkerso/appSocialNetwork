package dawid.app.service.groupMechanic;

import dawid.app.model.User;
import dawid.app.service.GroupService;

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
