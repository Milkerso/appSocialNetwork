package dawid.app.user.userProfile;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "free_time")
public class FreeTime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    @Column(name = "free_time_id")
    private int id;

    @Column(name = "free_time_name")
    @Getter @Setter
    @NotNull
    private String name;

    @ManyToMany(mappedBy = "freeTimes")
    @Getter @Setter
    private Set<UserProfile> userProfiles;


}
