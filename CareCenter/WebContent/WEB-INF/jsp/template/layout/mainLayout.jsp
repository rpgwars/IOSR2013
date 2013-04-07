<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
	<tiles:insertAttribute name="header"/>

	<body>
		<div style="float: right;">
			<%String logoutLink = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/" +"logout.html";%>
			<a href="<%=logoutLink %>">logout</a>
		</div>
		<div class="contentWrapper">
			<tiles:insertAttribute name="body"/>
			<tiles:insertAttribute name="rightmenu"/>
		</div>
	
		<tiles:insertAttribute name="menu"/>
		<tiles:insertAttribute name="footer"/>
	</body>
</html>