package dawid.app.user;


import java.sql.Date;
import lombok.*;
import java.util.Set;

import dawid.app.user.group.Group;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;



@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter @Setter
	@Column(name = "user_id")
	private int id;

	@Column(name = "email")
	@Getter @Setter
	@NotNull
	private String email;



	@Column(name = "password")
	@Getter @Setter
	@NotNull
	private String password;

	@Column(name = "active")
	@Getter @Setter
	@NotNull
	private int active;


	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	@Getter @Setter
	private Set<Role> roles;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "group_user", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
	private Set<Group> groups;

	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	@Transient
	@Getter @Setter
	private int nrRoli;

	@Transient
	@Getter @Setter
	private String name;

	@Transient
	@Getter @Setter
	private String sex;

	@Transient
	@Getter @Setter
	private String lastName;

	@Transient
	@Getter @Setter
	private String city;

	@Transient
	@Getter @Setter
	private String newPassword;


	@Column(name = "activation_code")
	@Getter @Setter
	private String activationCode;





}
