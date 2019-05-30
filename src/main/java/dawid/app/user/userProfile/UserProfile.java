package dawid.app.user.userProfile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import lombok.*;

@Entity
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @Column(name = "user_profile_id")
    @Getter @Setter
    private int id;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "free_time_user", joinColumns = @JoinColumn(name = "user_profile_id"), inverseJoinColumns = @JoinColumn(name = "free_time_id"))
    @Getter @Setter
    private Set<FreeTime> freeTimes;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "physical_activity_user", joinColumns = @JoinColumn(name = "user_profile_id"), inverseJoinColumns = @JoinColumn(name = "physical_activity_id"))
    @Getter @Setter
    private Set<PhysicalActivity> physicalActivities;

    @Transient
    @Setter @Getter
    private ArrayList<Integer> freeTime;

    @Transient
    @Getter @Setter
    private ArrayList<Integer> physicalActivity;
}
