<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>


<div class="mainContent">

	
	<div>
		<form:form method="post" commandName="group">
			<div>
				<ul>
					<li><form:label path="groupName">Name: </form:label></li>
					<li><form:input path="groupName"></form:input></li>
					<li><form:errors path="groupName"></form:errors></li>
				</ul>			
			</div>
			<hr>
			<input type="submit" value="AddGroup"/>
		</form:form>
	</div>
	<hr>
	<div>
		<h2>Groups:</h2>
		<br>
		<c:choose>
			<c:when test="${not empty groupList}">
			<table>
			<c:forEach items="${groupList}" var="group" varStatus="status">
				<tr>
				
	 				<td>${group.id}</td>
	 				<td>${group.groupName}</td>
	 			</tr>
			</c:forEach>
			</table>
			</c:when>
			<c:otherwise>
				<h1>-----------</h1>
			</c:otherwise>
		</c:choose>
	</div>
</div>
