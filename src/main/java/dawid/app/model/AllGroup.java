package dawid.app.model;

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
    private int groupId;

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @JoinColumn(name = "common_physical_activities")
    @ManyToOne
    @Getter
    @Setter
    private PhysicalActivity CommonPhysicalActivities;

    @Column(name = "common_city")
    @Getter
    @Setter
    private String commonCity;

    @JoinColumn(name = "common_free_time")
    @Getter
    @Setter
    @ManyToOne
    private FreeTime commonFreeTime;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "group_user", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    @Getter @Setter
    private Set<UserProfile> userProfiles;
}