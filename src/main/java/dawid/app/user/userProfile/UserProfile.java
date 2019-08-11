package dawid.app.user.userProfile;

import dawid.app.user.group.AllGroup;
import dawid.app.user.photo.Photo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_profile_id")
    @Getter
    @Setter
    private int id;

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @Column(name = "last_name")
    @Getter
    @Setter
    private String lastName;

    @Column(name = "hobby")
    @Getter
    @Setter
    private String hobby;

    @Column(name = "city")
    @Getter
    @Setter
    private String city;

    @Column(name = "sex")
    @Getter
    @Setter
    private String sex;

    @Column(name = "who_search")
    @Getter
    @Setter
    private String whoSearch;

    @Column(name = "language")
    @Getter
    @Setter
    private String language;

    @Column(name = "description")
    @Getter
    @Setter
    private String description;

    @Column(name = "birth_date")
    @Getter
    @Setter
    private Date birthDate;

    @Column(name = "characters")
    @Getter
    @Setter
    private String character;

    @Column(name = "number")
    @Getter
    @Setter
    private int number;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "free_time_user", joinColumns = @JoinColumn(name = "user_profile_id"), inverseJoinColumns = @JoinColumn(name = "free_time_id"))
    @Getter
    @Setter
    private List<FreeTime> freeTimes;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "physical_activity_user", joinColumns = @JoinColumn(name = "user_profile_id"), inverseJoinColumns = @JoinColumn(name = "physical_activity_id"))
    @Getter
    @Setter
    private List<PhysicalActivity> physicalActivities;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "photo_user", joinColumns = @JoinColumn(name = "user_profile_id"), inverseJoinColumns = @JoinColumn(name = "photo_id"))
    @Getter
    @Setter
    private List<Photo> photos;



    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "group_user", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    @Getter
    @Setter
    private Set<AllGroup> allGroups;

    @Transient
    @Setter
    @Getter
    private ArrayList<Integer> freeTime;

    @Transient
    @Getter
    @Setter
    private ArrayList<Integer> physicalActivity;

    @Transient
    @Getter
    @Setter
    private String email;

    @Transient
    @Getter
    @Setter
    private String photoEncoded;


}
