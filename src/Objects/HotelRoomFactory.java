package Objects;

import java.util.ArrayList;

public class HotelRoomFactory {
	public static HotelRoom createHotelRoom(String type) {
    	HotelRoom room;
		if (type.equalsIgnoreCase("basic")) {
    		room = new BasicRoom();
    	}
    	else if (type.equalsIgnoreCase("deluxe")) {
    		room = new DeluxeRoom();
    	}
    	else {
    		room = new SuiteRoom();
    	}
		return room;
	}
	public static HotelRoom addAmenities(HotelRoom room, ArrayList<String> amenities) {
    	for (int i = 0; i < amenities.size(); i++) {
    		if (amenities.get(i).equalsIgnoreCase("Spa")) {
    			room = new Spa(room);
    		}
    		else if (amenities.get(i).equalsIgnoreCase("Mini Bar")) {
    			room = new MiniBar(room);
    		}
    		else if (amenities.get(i).equalsIgnoreCase("Gym Access")) {
    			room = new GymAccess(room);
    		}
    		else if (amenities.get(i).equalsIgnoreCase("Down Comforter")) {
    			room = new DownComforter(room);
    		}
    		else {
    			 System.out.println("we dont have that at Adam's Hotel");
    		}
    	}
    	return room;
	}
}