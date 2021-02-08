package Objects;

import java.util.List;

public class TransactionsDataBase {
	public static void main(String [] args) {
		DataBaseManager db = DataBaseManager.getInstance();
		String s = java.time.LocalDate.now().toString();
		db.createTables();
		db.insertGuest("adam","(123) 345-5689","adam1@gmail.com","123412341234","1/21","123");
		db.insertGuest("adam","123-456","adam2@gmail.com","123422343234","1/21","123");
		db.insertGuest("benjamin","123-456","adam3@gmail.com","423412341234","1/21","123");
		db.insertTransaction("adam1@gmail.com","12.62",s,100,1);
		db.insertTransaction("adam1@gmail.com","22.62",s,200,1);
		db.insertTransaction("adam2@gmail.com","32.62",s,300,1);
		db.insertTransaction("adam2@gmail.com","42.62",s,400,1);
		db.insertTransaction("adam3@gmail.com","52.62",s,500,1);
		db.insertTransaction("adam3@gmail.com","62.62",s,600,1);
		db.insertTransaction("adam1@gmail.com","12.62",s,100,0);
		
//		List<String[]> results = db.findGuestByName("adam");
//		for (String [] a : results) {
//			System.out.println(a[0] + ", " + a[1] + ", " + a[2]);
//		}
//		List <String []> trans = db.queryAllTransactionsByEmail("adam1@gmail.com");
//		for (String [] arr : trans) {
//			System.out.println( arr[0] + " paid " + arr[1] + " on " + arr[2] + ". orderID: " + arr[3]);
//		System.out.println();
//		}
		
	}
}
