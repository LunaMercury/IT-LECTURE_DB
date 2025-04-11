package type;

public enum Role {
	// 아래처럼 enum으로 만들면 숫자로 나타낼 수 있다. 순서대로 1, 2, 3....이다.
	// 권한 등을 설정할 때 편리하다.
	ROLE_ANNONYMOUS,	// 0
	ROLE_USER,			// 1
	ROLE_MANAGER,		// 2
	ROLE_ADMIN			// 3
}
