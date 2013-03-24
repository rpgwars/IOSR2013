
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<body class="loginRegisterPage">

	<form action="j_spring_security_check" method="post" >
		<fieldset>
			<legend>Login</legend>
		
			<p>
				<label for="j_username">login</label>:
				<input id="j_username" name="j_username" size="20" maxlength="50" type="text"/>
			</p>
		
			<p>
				<label for="j_password">Password</label>:
				<input id="j_password" name="j_password" size="20" maxlength="50" type="password"/>
			</p>
		
			<p>
				<input type="submit" value="Login"/>
			</p>
		</fieldset>
	</form>

</body>