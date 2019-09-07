package dawid.app.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;


@Entity
@Getter
@Setter
@Table(name = "user")
public class User {

    @Id
    @Column(name = "user_id")
    private int id;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "password")
    @NotNull
    private String password;

    @Column(name = "active")
    @NotNull
    private int active;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "group_user", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<AllGroup> groups;

    @Column(name = "activation_code")
    private String activationCode;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_profile")
    private UserProfile userProfile;

    public Set<AllGroup> getGroups() {
        return groups;
    }

    public void setGroups(Set<AllGroup> groups) {
        this.groups = groups;
    }

    @Transient
    private String newPassword;
    @Transient
    private int nrRoli;
}
