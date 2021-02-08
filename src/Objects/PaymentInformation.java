package Objects;

public class PaymentInformation {
	private String cardNo;
	private String expDate;
	private String cvv;
	public PaymentInformation(String cn, String xpd, String cv) {
		setCardNo(cn);
		setExpDate(xpd);
		setCvv(cv);
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
}
