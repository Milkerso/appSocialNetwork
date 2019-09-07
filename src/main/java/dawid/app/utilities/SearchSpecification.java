package dawid.app.utilities;

import dawid.app.model.User;
import org.springframework.data.jpa.domain.Specification;


public class SearchSpecification {

    public static Specification<User> hasName(String name)
    {
        if(name.equals("0"))
        {
            System.out.println("hhaha");
            return (user, cq,cb)->cb.isTrue((cb.literal(true)));
        }
        System.out.println("lol?");
        return (user,cq,cb)->cb.like(user.get("userProfile").get("name"),name);
    }

    public static Specification<User> hasLastName(String lastName)
    {
        if(lastName.equals("0"))
        {
            return (user, cq,cb)->cb.isTrue((cb.literal(true)));
        }
        return (user,cq,cb)->cb.like(user.get("userProfile").get("lastName"),lastName);
    }

    public static Specification<User> hasEmail(String email)
    {
        if(email.equals("0"))
        {
            return (user, cq,cb)->cb.isTrue((cb.literal(true)));
        }
        return (user,cq,cb)->cb.like(user.get("email"),email);
    }
}
