<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div class="mainContent">
    <div class="addInstanceForm">
        <h2 class="actionHeader">Add new category</h2>
		<form:form method="post" commandName="activityCategory">
			<div>
				<ul>
					<li><form:input path="categoryName" placeholder="Name"></form:input></li>
					<li><form:errors path="categoryName"></form:errors></li>
				</ul>			
			</div>
			<div>
				<ul>
					<li><form:textarea rows="5" cols="20" path="categoryDescription" placeholder="Description"></form:textarea></li>
					<li><form:errors path="categoryDescription"></form:errors></li>
				</ul>			
			</div>
			<hr>
			<input type="submit" class="btn btn-large btn-block btn-primary" value="Add category"/>
		</form:form>
	</div>
    <div class="contentWrap">
        <div class="instanceTableContainer">
		<h2>Categories:</h2>
		<br>
		<c:choose>
			<c:when test="${not empty categoryList}">
			<table class="table table-striped">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                </tr>
                </thead>
                <tbody>
			<c:forEach items="${categoryList}" var="category">
				<tr>
	 				<td>${category.categoryName}</td>
	 				<td>${category.categoryDescription}</td>
	 			</tr>
			</c:forEach>
                </tbody>
			</table>
			</c:when>
			<c:otherwise>
				<h1>No category in a system</h1>
			</c:otherwise>
		</c:choose>
	    </div>
    </div>
</div>
