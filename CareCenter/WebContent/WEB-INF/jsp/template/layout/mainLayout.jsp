<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
	<tiles:insertAttribute name="header"/>
	<body class="<tiles:insertAttribute name="bodyClass"/>">
        <tiles:insertAttribute name="navBar"/>
		<div class="contentWrapper">
			<tiles:insertAttribute name="body"/>
			<tiles:insertAttribute name="rightmenu"/>
		</div>
		<tiles:insertAttribute name="footer"/>
	</body>
</html>