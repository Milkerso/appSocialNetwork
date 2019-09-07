package dawid.app.service.groupMechanic;

import dawid.app.model.User;

public class GroupByCity implements GroupMechanic{
    @Override
    public int groupBy(User user) {
       if(user.getGroups().isEmpty())
       {

       }
       return 0;
    }
}
