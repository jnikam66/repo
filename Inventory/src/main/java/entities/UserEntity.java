package entities;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="User")
@SuppressWarnings("unused")
public class UserEntity implements Serializable{
	@Id
	@Column(name="USERNAME")
	private String username;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="ACCESSLEVEL")
	private String accessLevel;
	
	@Column(name="COMPANY")
	private String company;
	
	@Column(name="COUNTRY")
	private String country;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="CONTACTNO")
	private String contactno;
	
	@Column(name="HASHKEY")
	private String hashKey;
	
	@Column(name="ISACTIVE")
	private Boolean isActive;
	
	@Column(name="PROFILEPICTURE")
	private Blob profilePicture;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

		public String getContactno() {
		return contactno;
	}

	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

	public String getHashKey() {
		return hashKey;
	}

	public void setHashKey(String hashKey) {
		this.hashKey = hashKey;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Blob getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(Blob profilePicture) {
		this.profilePicture = profilePicture;
	}
}
