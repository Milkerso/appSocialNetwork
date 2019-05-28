package dawid.app.user.userProfile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import lombok.*;

@Entity
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @Column(name = "user_profile_id")
    @Getter @Setter
    private int userProfileID;

    @Column(name = "name")
    @Getter @Setter
    private String name;

    @Column(name = "last_name")
    @Getter @Setter
    private String lastName;

    @Column(name = "hobby")
    @Getter @Setter
    private String hobby;

    @Column(name = "city")
    @Getter @Setter
    private String city;

    @Column(name = "sex")
    @Getter @Setter
    private String sex;

    @Column(name="who_search")
    @Getter @Setter
    private String whoSearch;

    @Column(name = "language")
    @Getter @Setter
    private String language;

    @Column(name = "description")
    @Getter @Setter
    private String description;

    @Column(name = "birth_date")
    @Getter @Setter
    private Date birthDate;

    @Column(name = "characters")
    @Getter @Setter
    private String character;

    @Column(name = "number")
    @Getter @Setter
    private int number;
}
