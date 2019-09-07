package dawid.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "physical_activity")
public class PhysicalActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    @Column(name = "physical_activity_id")
    private int id;

    @Column(name = "physical_activity_name")
    @Getter @Setter
    @NotNull
    private String name;


    @ManyToMany(mappedBy = "physicalActivities")
    @Getter @Setter
    private Set<UserProfile> userProfiles;


}
