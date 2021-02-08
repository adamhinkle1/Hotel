package Objects;
public abstract class Amenity extends HotelRoom{
	protected String type;
	public abstract int getPrice();
	public String getType() {
		return type;
	}
}