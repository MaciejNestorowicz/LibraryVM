package pl.vm.library.to;

import java.io.Serializable;
import java.util.Date;

/**
 * Transport Object of the Reservation class.
 */
public class ReservationTo implements Serializable {

	private static final long serialVersionUID = -60690548233543094L;

	private Long id;

	private UserTo userTo;

	private BookTo bookTo;

	private Date fromDate;

	private Date toDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserTo getUserTo() {
		return userTo;
	}

	public void setUserTo(UserTo userTo) {
		this.userTo = userTo;
	}

	public BookTo getBookTo() {
		return bookTo;
	}

	public void setBookTo(BookTo bookTo) {
		this.bookTo = bookTo;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
}