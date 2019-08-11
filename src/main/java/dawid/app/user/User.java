package dawid.app.user;


import dawid.app.user.group.AllGroup;
import dawid.app.user.userProfile.UserProfile;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;


@Entity
@Table(name = "user")
public class User {

    @Id
    @Getter
    @Setter
    @Column(name = "user_id")
    private int id;

    @Column(name = "email")
    @Getter
    @Setter
    @NotNull
    private String email;


    @Column(name = "password")
    @Getter
    @Setter
    @NotNull
    private String password;

    @Column(name = "active")
    @Getter
    @Setter
    @NotNull
    private int active;


    @ManyToMany(cascade = CascadeType.ALL)
    @Getter
    @Setter
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "group_user", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<AllGroup> groups;

    @Column(name = "activation_code")
    @Getter
    @Setter
    private String activationCode;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_profile")
    @Getter
    @Setter
    private UserProfile userProfile;

    public Set<AllGroup> getGroups() {
        return groups;
    }

    public void setGroups(Set<AllGroup> groups) {
        this.groups = groups;
    }

    @Transient
    @Getter
    @Setter
    private String newPassword;
    @Transient
    @Getter
    @Setter
    private int nrRoli;
}
