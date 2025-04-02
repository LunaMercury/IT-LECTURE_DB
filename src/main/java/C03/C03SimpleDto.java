package C03;

public class C03SimpleDto {
	private String name;
	private Integer age;
	private String addr;

	
	public C03SimpleDto() {
	}


	public C03SimpleDto(String name, Integer age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getAge() {
		return age;
	}


	public void setAge(Integer age) {
		this.age = age;
	}


	public String getAddr() {
		return addr;
	}


	public void setAddr(String addr) {
		this.addr = addr;
	}


	@Override
	public String toString() {
		return "C03SimpleDto [name=" + name + ", age=" + age + ", addr=" + addr + "]";
	}
	
}
