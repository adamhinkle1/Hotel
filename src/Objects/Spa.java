package Objects;

public class Spa extends Amenity{
	protected HotelRoom room;
	public static final int price = 5;
	public Spa() {}
	public Spa(HotelRoom room) {
		this.room = room;
		type = room.getType();
	}
	public int getPrice() {
		return room.getPrice() + price;
	}
	public String toString() {
		return room.toString() + " with a spa";
	}
	public String getType() { return type;}
}
