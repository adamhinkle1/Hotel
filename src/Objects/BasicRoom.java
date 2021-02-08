package Objects;

public class BasicRoom extends HotelRoom{
	public static final int price = 75;
	protected String type;
	public BasicRoom() {
		description = "Basic Room";
		type = "basic";
	}
	public int getPrice() {return price;}
	public String toString() {return description;}
	public String getType() { return type;}
}
