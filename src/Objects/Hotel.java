package Objects;

import java.util.List;

public class Hotel {
	private static int transactionNumber = 0;
	private static Hotel hotel = null;
	private static DataBaseManager DB;
	
	private Hotel() {}
	
	public static Hotel getInstance() {
		if (hotel == null) {
			hotel = new Hotel();
			DB = DataBaseManager.getInstance();
		}
		return hotel;
	}
	public List<String []> findGuestByName(String name) {
		return DB.findGuestByName(name);
	}
	public String [] findGuestByEmail(String email) {
		return DB.findGuestByEmail(email);
	}
	public void checkIn(HotelRoom room, Guest guest,String total) {
		DB.insertGuest(guest.getName(),guest.getPhone(),guest.getEmail(),guest.getPayment().getCardNo(),
				guest.getPayment().getExpDate(),guest.getPayment().getCvv());
		DB.insertTransaction(guest.getEmail(), total, java.time.LocalDate.now().toString(), transactionNumber++,1);
	}
	public List<String[]> findAllTransactionsByEmail(String email){
		return DB.queryAllTransactionsByEmail(email);
	}
	public List<String[]> findOpenTransactionsByEmail(String email){
		return DB.queryOpenTransactionsByEmail(email);
	}
	public void checkOut(String transNumber) {
		String [] s= DB.getTransaction(transNumber);
		DB.insertTransaction(s[0],s[1],s[2],Integer.parseInt(s[3]),0);
	}
}
