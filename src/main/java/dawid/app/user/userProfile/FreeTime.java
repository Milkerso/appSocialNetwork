package dawid.app.user.userProfile;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "role")
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
}
