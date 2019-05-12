package dawid.app.user;


import java.sql.Date;
import java.util.Set;
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
	@Column(name = "user_id")
	private int id;

	@Column(name = "email")
	@NotNull
	private String email;


	@Column(name = "password")
	@NotNull
	private String password;

	@Column(name = "name")
	@NotNull
	private String name;

	@Column(name = "last_name")
	@NotNull
	private String lastName;

	@Column(name = "active")
	@NotNull
	private int active;

	// additional description

	@Column(name = "hobby")
	private String hobby;

	@Column(name = "city")
	private String city;

	@Column(name = "sex")
	private String sex;

	@Column(name="who_search")
	private String whoSearch;


	@Column(name = "language")
	private String language;

	@Column(name = "free_time")
	private String freeTime;

	@Column(name = "physical_activity")
	private String physicalActivity;

	@Column(name = "description")
	private String description;

	@Column(name = "birth_date")
	private Date birthDate;

	@Column(name = "characters")
	private String character;

	@Column(name = "number")
	private int number;

	@Transient
	private MultipartFile photo;

	@Transient
	private String image;

	@Transient //Annotation so it does not persist in the database
	public String getImage() {
	    //Convert the data type byte to String, store it in the variable and return it
		String encodedImage = Base64.encode(this.data);
		return encodedImage;
	}

    @Lob
    @Column(name = "data")
    private byte[] data;

	@Column(name = "file_name")
    private String fileName;

	@Column(name = "file_type")
    private String fileType;


	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	@Transient
	private int nrRoli;

	@Transient
	private String newPassword;


	@Column(name = "activation_code")
	private String activationCode;


	//gettery i settery
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public int getNrRoli() {
		return nrRoli;
	}
	public void setNrRoli(int nrRoli) {
		this.nrRoli = nrRoli;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getActivationCode() {
		return activationCode;
	}
	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getcity() {
		return city;
	}
	public void setcity(String city) {
		this.city = city;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getFreeTime() {
		return freeTime;
	}
	public void setFreeTime(String freeTime) {
		this.freeTime = freeTime;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getCharacter() {
		return character;
	}
	public void setCharacter(String character) {
		this.character = character;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getWhoSearch() {
		return whoSearch;
	}
	public void setWhoSearch(String whoSearch) {
		this.whoSearch = whoSearch;
	}
	public String getPhysicalActivity() {
		return physicalActivity;
	}
	public void setPhysicalActivity(String physicalActivity) {
		this.physicalActivity = physicalActivity;
	}
	public MultipartFile  getPhoto() {
		return photo;
	}
	public void setPhoto(MultipartFile  photo) {
		this.photo = photo;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public void setImage(String image) {
		this.image = image;
	}



}
