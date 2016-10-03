package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Organiser extends Actor {

	// Constructors...................
	public Organiser() {
		super();
	}

	// Attributes ----------------------------------------------------------

	private String about;

	@NotBlank
	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	// Relationships --------------------------------------------------------
	private Collection<Activity> activities;
	private OrganiserComment organiserComment;

	@Valid
	@NotNull
	@OneToMany(mappedBy = "organiser")
	public Collection<Activity> getActivities() {
		return activities;
	}

	public void setActivities(Collection<Activity> activities) {
		this.activities = activities;
	}

	@Valid
	@OneToOne(optional = true, cascade = CascadeType.ALL)
	public OrganiserComment getOrganiserComment() {
		return organiserComment;
	}

	public void setOrganiserComment(OrganiserComment organiserComment) {
		this.organiserComment = organiserComment;
	}

}