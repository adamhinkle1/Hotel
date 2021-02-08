package Objects;

public class Guest {
	private String name;
	private String phone;
	private String email;
	private PaymentInformation payment;
	public Guest(String n, String p, String em, PaymentInformation pay) {
		setName(n);
		setPhone(p);
		setEmail(em);
		setPayment(pay);
	}
	public Guest(String n, String phone, String em,String card, String xp, String cvv) {
		setName(n);
		setPhone(phone);
		setEmail(em);
		setPayment(new PaymentInformation(card, xp, cvv));
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public PaymentInformation getPayment() {
		return payment;
	}
	public void setPayment(PaymentInformation payment) {
		this.payment = payment;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String toString() {
		return name + ", " + phone + "@ " + email;
	}
	
	
}
