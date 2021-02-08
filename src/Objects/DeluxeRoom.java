package Objects;
public class DeluxeRoom extends HotelRoom{
	public static final int price = 150;
	protected String type;
	public DeluxeRoom() {
		description = "Deluxe Room";
		type = "deluxe";
	}
	public int getPrice() {return price;}
	public String toString() {return description;}
	public String getType() { return type;}
}

