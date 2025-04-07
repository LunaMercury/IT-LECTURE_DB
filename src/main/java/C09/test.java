package C09;

import java.util.List;

public class test {

	public static void main(String[] args) {
		List<OrderDto2> list = null;
		try {
			list = DBUtils.getInstance().selectAll_2();
			System.out.println(list);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("데이터를 불러오는 중 오류가 발생했습니다.");
			return;
		}

	}
}
