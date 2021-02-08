package Objects;

public abstract class HotelRoom{
	protected String description;
	protected Guest occupants;
	public abstract int getPrice();
	public String toString() {
		return description;
	}
	public abstract String getType();
}
