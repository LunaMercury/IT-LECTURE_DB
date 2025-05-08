<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <title>MeetBlog | SIGNUP</title>
    <link rel="stylesheet" href="../../static/css/user_css/signup.css">
    <script src="../../static/js/signup.js"></script>
</head>

<body>
    <section class="login-section">
        <img src="../../static/image/로고.png" alt="logo">
        <h2>회원가입 페이지입니다.</h2>
        <form id="login-form" action="${pageContext.request.contextPath}/join" method="post" onsubmit="return false" >
            <label>
                <input type="text" id="name" name="name" placeholder="이름">
            </label>
            <label>
                <input id="nickname" type="text" name="nickname" placeholder="사용자 닉네임">
            </label>
            <label>
                <input type="password" id="pwd" name="password" placeholder="비밀번호">
            </label>
            <label>
                <input id="email-input" type="email" name="email" placeholder="이메일">
            </label>
            <div class="email-auth-pack">
                <label>
                    <input id="email-auth-input" type="text" name="email-auth" placeholder="인증번호 입력">
                </label>
                <label>
                    <input id="email-auth-btn" type="button" value="인증번호전송">
                </label>
            </div>
            <button class="submit">회원가입</button>
        </form>

    </section>

</body>

</html>