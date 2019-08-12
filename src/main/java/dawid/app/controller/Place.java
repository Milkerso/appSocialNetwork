package dawid.app.controller;

import dawid.app.user.group.AllGroup;
import dawid.app.user.photo.Photo;
import dawid.app.user.userProfile.FreeTime;
import dawid.app.user.userProfile.PhysicalActivity;
import dawid.app.user.userProfile.UserProfile;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "place")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    @Column(name = "place_id")
    private int id;

    @Column(name = "email")
    @Getter
    @Setter
    private String email;

    @Column(name = "link")
    @Getter
    @Setter
    private String link;


    @Column(name = "address")
    @Getter
    @Setter
    private String address;

    @Column(name = "number")
    @Getter
    @Setter
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "place_photo", joinColumns = @JoinColumn(name = "place_id"), inverseJoinColumns = @JoinColumn(name = "photo_id"))
    @Getter
    @Setter
    private List<Photo> photos;

    @Column(name = "title")
    @Getter
    @Setter
    private String title;

    @Transient
    @Getter
    @Setter
    private MultipartFile photo;
    @Transient
    @Getter
    @Setter
    private String photoEncoded;

    @Column(name = "description")
    @Getter
    @Setter
    private String description;
    @Transient
    @Setter
    @Getter
    private ArrayList<Integer> freeTime;

    @Transient
    @Getter
    @Setter
    private ArrayList<Integer> physicalActivity;

}
