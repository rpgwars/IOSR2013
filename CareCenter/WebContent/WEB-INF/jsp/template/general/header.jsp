<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width">

    <title>

    </title>
	<%
	 String pathToCss=request.getServerName();
	%>
    <link rel="stylesheet" href="<spring:theme code="normalize-css"/>" type="text/css">
    <link rel="stylesheet" href="<spring:theme code="main-css"/>" type="text/css"/>
</head>