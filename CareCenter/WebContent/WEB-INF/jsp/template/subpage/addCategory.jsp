<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div class="mainContent">
	<div>
		<form:form method="post" commandName="activityCategory">
			<div>
				<ul>
					<li><form:label path="categoryName">Name: </form:label></li>
					<li><form:input path="categoryName"></form:input></li>
					<li><form:errors path="categoryName"></form:errors></li>
				</ul>			
			</div>
			<div>
				<ul>
					<li><form:label path="categoryDescription">Description: </form:label></li>
					<li><form:textarea rows="5" cols="20" path="categoryDescription"></form:textarea></li>
					<li><form:errors path="categoryDescription"></form:errors></li>
				</ul>			
			</div>
			<hr>
			<input type="submit" value="Add category"/>
		</form:form>
	</div>
	<hr>
	<div>
		<h2>Categories:</h2>
		<br>
		<c:choose>
			<c:when test="${not empty categoryList}">
			<table>
			<c:forEach items="${categoryList}" var="category">
				<tr>
	 				<td>${category.categoryName}</td>
	 				<td>
	 					<textarea rows="5" cols="20" readonly="readonly">
	 						 ${category.categoryDescription}
	 					</textarea>
	 				</td>
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
