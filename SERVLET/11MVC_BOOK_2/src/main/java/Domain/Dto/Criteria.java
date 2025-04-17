// 검색 조건 전달
// 클라이언트(사용자)가 도서 목록 페이지에서 검색 기능을 사용할 때, 어떤 기준으로(타입: type) 어떤 검색어(키워드: keyword)로 검색할 것인지에 대한 정보를 담아서 서버로 전달하는 역할
// BookListController는 요청 파라미터에서 type과 keyword 값을 읽어와 Criteria 객체에 저장하고, 이 객체를 서비스 계층(BookServiceImpl)으로 전달하여 검색 기능을 수행하도록 합니다.

// 페이징 정보 전달
// 도서 목록을 여러 페이지로 나누어 표시할 때, 현재 사용자가 보고 있는 페이지 번호(pageno)와 한 페이지에 보여줄 항목 수(amount)에 대한 정보를 담아서 서버로 전달하는 역할을 합니다.
// BookListController는 요청 파라미터에서 pageno와 amount 값을 읽어와 Criteria 객체에 저장하고, 이 객체를 서비스 계층(BookServiceImpl)으로 전달하여 해당 페이지의 도서 목록을 조회하도록 합니다.


package Domain.Dto;

public class Criteria {
	private int pageno; 	//현재 페이지
	private int amount;		//페이지 당 보여줄 게시물 건수
	private String type;	//타입(도서명 , 도서코드 , 출판사)
	private String keyword;	//키워드
	
	public Criteria() {
		//처음페이지로 들어왔을떄 기본값
		this.pageno = 1;
		this.amount = 10;
	}
	
	public Criteria(String pageno, int amount, String type, String keyword) {
		super();
		this.pageno = Integer.parseInt(pageno);
		this.amount = amount;
		this.type = type;
		this.keyword = keyword;
	}
	
	
	public Criteria(String pageno, int amount) {
		this.pageno = Integer.parseInt(pageno) ;
		this.amount = amount ;
	}

	public int getPageno() {
		return pageno;
	}
	public void setPageno(int pageno) {
		this.pageno = pageno;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	@Override
	public String toString() {
		return "Criteria [pageno=" + pageno + ", amount=" + amount + ", type=" + type + ", keyword=" + keyword + "]";
	}

	//toString
	//getter and setter
	//생성자(디폴트,모든인자)
	
	
}
