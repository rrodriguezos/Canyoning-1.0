package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = { @Index(columnList="moment"),  @Index(columnList="numberSeats"), @Index(columnList="title"), @Index(columnList="description")})
public class Activity extends Commentable {

	// Constructor ----------------------------------------------
	public Activity() {
		super();
	}

	// Attributes -------------------------------------------------
	private String title;
	private String description;
	private Integer numberSeats;
	private Date moment;

	@NotBlank
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@NotBlank
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotNull
	public Integer getNumberSeats() {
		return numberSeats;
	}

	public void setNumberSeats(Integer numberSeats) {
		this.numberSeats = numberSeats;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	@Future
	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	// Relationships ---------------------------------------------------
	private Organiser organiser;
	private Canyon canyon;

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Canyon getCanyon() {
		return canyon;
	}

	public void setCanyon(Canyon canyon) {
		this.canyon = canyon;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Organiser getOrganiser() {
		return organiser;
	}

	public void setOrganiser(Organiser organiser) {
		this.organiser = organiser;
	}

}
