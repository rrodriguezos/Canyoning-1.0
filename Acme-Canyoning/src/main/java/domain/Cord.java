package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Cord extends PieceEquipment {

	// Constructors...................
	public Cord() {
		super();
	}

	// Attributes ----------------------------------------------------------

	private double length;
	private double maximumWeight;

	@NotNull
	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	@NotNull
	public double getMaximumWeight() {
		return maximumWeight;
	}

	public void setMaximumWeight(double maximumWeight) {
		this.maximumWeight = maximumWeight;
	}

}
