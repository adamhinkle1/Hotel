package Objects;

public class DownComforter extends Amenity {
	protected HotelRoom room;
	public static final int price = 20;
	public DownComforter() {}
	public DownComforter(HotelRoom room) {
		this.room = room;
		type = room.getType();
	}
	public int getPrice() {
		return room.getPrice() + price;
	}
	public String toString() {
		return room.toString() + " with mini bar";
	}
	public String getType() { return type;}


}

