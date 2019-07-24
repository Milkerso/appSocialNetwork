package dawid.app.user;


import dawid.app.user.group.AllGroup;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;


@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Getter
    @Setter
    private Set<Role> roles;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "group_user", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<AllGroup> groups;
    @Transient
    @Getter
    @Setter
    private int nrRoli;
    @Transient
    @Getter
    @Setter
    private String name;
    @Transient
    @Getter
    @Setter
    private String sex;
    @Transient
    @Getter
    @Setter
    private String lastName;
    @Transient
    @Getter
    @Setter
    private String city;
    @Transient
    @Getter
    @Setter
    private String newPassword;
    @Column(name = "activation_code")
    @Getter
    @Setter
    private String activationCode;




    public Set<AllGroup> getGroups() {
        return groups;
    }

    public void setGroups(Set<AllGroup> groups) {
        this.groups = groups;
    }


}
