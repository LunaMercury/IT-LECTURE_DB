package C09;

public class OrderDto {

	private String category;
	private Integer sum;

	public OrderDto() {
	}

	public OrderDto(String category, Integer sum) {
		super();
		this.category = category;
		this.sum = sum;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getSum() {
		return sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}

	@Override
	public String toString() {
		return "OrderDto [category=" + category + ", sum=" + sum + "]";
	}
	
}