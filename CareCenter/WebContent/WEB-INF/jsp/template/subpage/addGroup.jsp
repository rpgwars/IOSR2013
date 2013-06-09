<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>


<div class="mainContent">
    <div class="addInstanceForm">
        <h2 class="actionHeader">Create new Group</h2>
		<form:form method="post" commandName="group">
			<div>
				<ul>
					<li><form:input path="groupName" placeholder="Name"></form:input></li>
					<li><form:errors path="groupName"></form:errors></li>
				</ul>			
			</div>
			<hr>
			<input class="btn btn-large btn-block btn-primary" type="submit" value="AddGroup"/>
		</form:form>
    </div>
    <div class="contentWrap">
        <div class="instanceTableContainer">
		<h2>Groups:</h2>
		<br>
		<c:choose>
			<c:when test="${not empty groupList}">
			<table class="table table-striped">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Group name</th>
                </tr>
                </thead>
                <tbody>
			<c:forEach items="${groupList}" var="group" varStatus="status">
				<tr>
				
	 				<td>${group.id}</td>
	 				<td>${group.groupName}</td>
	 			</tr>
			</c:forEach>
                </tbody>
			</table>
			</c:when>
			<c:otherwise>
				<h1>-----------</h1>
			</c:otherwise>
		</c:choose>
	</div>
</div>
