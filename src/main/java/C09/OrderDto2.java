package C09;

import java.time.LocalDate;

public class OrderDto2 {
	private String addr;
	private LocalDate order_Date;
	private Integer sum;
	private double average;

	public OrderDto2() {
	}

	public OrderDto2(String addr, LocalDate order_Date, Integer sum, double average) {
		super();
		this.addr = addr;
		this.order_Date = order_Date;
		this.sum = sum;
		this.average = average;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public LocalDate getOrder_Date() {
		return order_Date;
	}

	public void setOrder_Date(LocalDate order_Date) {
		this.order_Date = order_Date;
	}

	public Integer getSum() {
		return sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	@Override
	public String toString() {
		return "OrderDto2 [addr=" + addr + ", order_Date=" + order_Date + ", sum=" + sum + ", average=" + average + "]";
	}

}
