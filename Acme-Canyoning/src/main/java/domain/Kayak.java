package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
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

}
