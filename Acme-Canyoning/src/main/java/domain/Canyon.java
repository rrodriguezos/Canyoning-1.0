package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = { @Index(columnList = "difficultyLevel"),
		@Index(columnList = "route"), @Index(columnList = "title"),
		@Index(columnList = "description") })
public class Canyon extends Commentable {

	// Constructor ----------------------------------------------
	public Canyon() {
		super();
	}

	// Attributes -------------------------------------------------
	private String title;
	private String description;
	private Collection<String> pictures;
	private Integer difficultyLevel;
	private String route;
	private Double gpsLatitude;
	private Double gpsLongitude;

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ElementCollection
	public Collection<String> getPictures() {
		return pictures;
	}

	public void setPictures(Collection<String> pictures) {
		this.pictures = pictures;
	}

	@Range(min = 0, max = 10)
	@NotNull
	public Integer getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(Integer difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	@NotNull
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public Double getGpsLatitude() {
		return gpsLatitude;
	}

	public void setGpsLatitude(Double gpsLatitude) {
		this.gpsLatitude = gpsLatitude;
	}

	@NotNull
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public Double getGpsLongitude() {
		return gpsLongitude;
	}

	public void setGpsLongitude(Double gpsLongitude) {
		this.gpsLongitude = gpsLongitude;
	}

	// Relationships ---------------------------------------------------
	private Administrator administrator;
	private Collection<Activity> activities;

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "canyon")
	public Collection<Activity> getActivities() {
		return activities;
	}

	public void setActivities(Collection<Activity> activities) {
		this.activities = activities;
	}

}
