package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Kayak extends PieceEquipment {

	// Constructors...................
	public Kayak() {
		super();
	}

	// Attributes ----------------------------------------------------------
	private int numberSeats;
	private double length;

	@Min(0)
	public int getNumberSeats() {
		return numberSeats;
	}

	public void setNumberSeats(int numberSeats) {
		this.numberSeats = numberSeats;
	}

	@NotNull
	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	// Relationships --------------------------------------------------------

	private Collection<Activity> activities;

	@Valid
	@NotNull
	@ManyToMany
	public Collection<Activity> getActivities() {
		return activities;
	}

	public void setActivities(Collection<Activity> activities) {
		this.activities = activities;
	}
	
	private Organiser organiser;

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
