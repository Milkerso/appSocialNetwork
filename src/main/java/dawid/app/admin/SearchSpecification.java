package dawid.app.admin;

import dawid.app.user.User;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


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
