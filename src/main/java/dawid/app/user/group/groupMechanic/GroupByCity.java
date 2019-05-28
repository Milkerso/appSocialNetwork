package dawid.app.user.group.groupMechanic;

import dawid.app.user.User;
import dawid.app.user.group.Group;

public class GroupByCity implements GroupMechanic{
    @Override
    public int groupBy(User user) {
       if(user.getGroups().isEmpty())
       {

       }
       return 0;
    }
}
