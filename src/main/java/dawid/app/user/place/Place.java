package dawid.app.user.place;

import dawid.app.user.group.AllGroup;
import dawid.app.user.photo.Photo;
import dawid.app.user.userProfile.FreeTime;
import dawid.app.user.userProfile.PhysicalActivity;
import dawid.app.user.userProfile.UserProfile;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "place")
public class Place {

    @Id
    @Getter
    @Setter
    @Column(name = "place_id")
    private int id;

    @Column(name = "email")
    @Getter
    @Setter
    @NotNull
    private String email;


    @Column(name = "address")
    @Getter
    @Setter
    @NotNull
    private String address;

    @Column(name = "number")
    @Getter
    @Setter
    @NotNull
    private int number;




    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "free_time_place", joinColumns = @JoinColumn(name = "place_id"), inverseJoinColumns = @JoinColumn(name = "free_time_id"))
    @Getter
    @Setter
    private List<FreeTime> freeTimes;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "physical_activity_place", joinColumns = @JoinColumn(name = "place_id"), inverseJoinColumns = @JoinColumn(name = "physical_activity_id"))
    @Getter
    @Setter
    private List<PhysicalActivity> physicalActivities;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id")
    @Getter
    @Setter
    private Photo photo;

    @Column(name = "title")
    @Getter
    @Setter
    private String title;

    @Transient
    @Getter
    @Setter
    private String encodedFile;

    @Column(name = "description")
    @Getter
    @Setter
    private String decription;

}
