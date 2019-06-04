package dawid.app.user.group;

import dawid.app.user.userProfile.UserProfile;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "all_group")
public class AllGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    @Column(name = "group_id")
    private int groupID;

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @Column(name = "common_physical_activities")
    @Getter
    @Setter
    private int CommonPhysicalActivities;

    @Column(name = "common_city")
    @Getter
    @Setter
    private String commonCity;

    @Column(name = "common_free_time")
    @Getter
    @Setter
    private int commonFreeTime;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "group_user", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    @Getter @Setter
    private Set<UserProfile> userProfiles;
}