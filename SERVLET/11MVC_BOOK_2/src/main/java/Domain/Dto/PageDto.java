package Domain.Dto;

public class PageDto {
	private static final long serialVersionUID = 5L;

	private long totalCount;
	// 페이지정보(전체페이지,현재페이지)
	private int totalPage; // 총게시물건수 / amount
	private Criteria criteria; // 현재페이지,한페이지당 읽을 게시물의 건수가 저장되어있음

	// 블럭정보
	private int pagePerBlock; // 블럭에 표시할 페이지개수(15건 지정)
	private int totalBlock; // totalpage / pagePerBlock
	private int nowBlock; // 현재페이지번호 /pagePerBlock

	// 표시할 페이지(블럭에표시할 시작페이지-마지막페이지)
	private int startPage;
	private int endPage;

	// NextPrev 버튼 표시여부
	private boolean prev, next;

	// 생성자
	public PageDto() {
	}

	// 아래 메서드로 인해 criteria 재설정, endPage 및 startPage 값 설정.
	public PageDto(long totalcount, Criteria criteria) {
		this.totalCount = totalcount;
		this.criteria = criteria;

		// 전체 페이지 계산
		// 즉, 게시물 총 갯수/페이지 당 보여줄 갯수 = 페이지 갯수(블럭)
		totalPage = (int) Math.ceil((1.0 * totalcount) / criteria.getAmount());

		// 블럭계산
		pagePerBlock = 10;
		totalBlock = (int) Math.ceil((1.0 * totalPage) / pagePerBlock);
		nowBlock = (int) Math.ceil((1.0 * criteria.getPageno()) / pagePerBlock);

		// Next,Prev 버튼 활성화 유무
		// 나중에 book/list.jsp에서 true,false 값으로 판단하기 위해
		prev = nowBlock > 1;
		next = nowBlock < totalBlock;

		// 블럭에 표시할 페이지 번호 계산
		this.endPage = (nowBlock * pagePerBlock < totalPage) ? nowBlock * pagePerBlock : totalPage;
		this.startPage = nowBlock * pagePerBlock - pagePerBlock + 1;

	}

	public PageDto(long totalCount, int totalPage, Criteria criteria, int pagePerBlock, int totalBlock, int nowBlock,
			int startPage, int endPage, boolean prev, boolean next) {
		super();
		this.totalCount = totalCount;
		this.totalPage = totalPage;
		this.criteria = criteria;
		this.pagePerBlock = pagePerBlock;
		this.totalBlock = totalBlock;
		this.nowBlock = nowBlock;
		this.startPage = startPage;
		this.endPage = endPage;
		this.prev = prev;
		this.next = next;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public Criteria getCriteria() {
		return criteria;
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}

	public int getPagePerBlock() {
		return pagePerBlock;
	}

	public void setPagePerBlock(int pagePerBlock) {
		this.pagePerBlock = pagePerBlock;
	}

	public int getTotalBlock() {
		return totalBlock;
	}

	public void setTotalBlock(int totalBlock) {
		this.totalBlock = totalBlock;
	}

	public int getNowBlock() {
		return nowBlock;
	}

	public void setNowBlock(int nowBlock) {
		this.nowBlock = nowBlock;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "PageDto [totalCount=" + totalCount + ", totalPage=" + totalPage + ", criteria=" + criteria
				+ ", pagePerBlock=" + pagePerBlock + ", totalBlock=" + totalBlock + ", nowBlock=" + nowBlock
				+ ", startPage=" + startPage + ", endPage=" + endPage + ", prev=" + prev + ", next=" + next + "]";
	}
	

}
