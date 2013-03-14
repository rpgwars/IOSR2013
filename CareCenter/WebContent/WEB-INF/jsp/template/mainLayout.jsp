<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>
    	<title>
    		<tiles:insertAttribute name="title"/>
    	</title>	
    	<link rel="stylesheet" href="<spring:theme code="css"/>" type="text/css"/>
    	
    </head>
    
    <body>
    	
    	<tiles:insertAttribute name="header"/>
    	<tiles:insertAttribute name="body"/>
    	<tiles:insertAttribute name="menu"/>

    </body>

</html>