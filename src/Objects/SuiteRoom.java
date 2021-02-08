package Objects;

public class SuiteRoom extends HotelRoom {
	public static final int price = 100;
	protected String type;
	public SuiteRoom() {
		description = "Suite";
		type = "suite";
	}

	public int getPrice() {
		return price;
	}
	public String toString() {
		return description;
	}
	public String getType() { return type;}
}

