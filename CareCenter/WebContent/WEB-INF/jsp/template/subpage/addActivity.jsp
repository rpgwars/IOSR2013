<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div class="mainContent">
	<div>
		<form:form method="post" commandName="activity">
			<div>
				<ul>
					<li><form:label path="activityName">Name: </form:label></li>
					<li><form:input path="activityName"></form:input></li>
					<li><form:errors path="activityName"></form:errors></li>
				</ul>			
			</div>
			<div>
				<ul>
					<li><form:label path="description">Description: </form:label></li>
					<li><form:textarea rows="5" cols="20" path="description"></form:textarea></li>
					<li><form:errors path="description"></form:errors></li>
				</ul>			
			</div>
			<div>
				<form:select path="categoryId">
    				<form:options items="${categoryList}" itemValue="id" itemLabel="categoryDescription" />
				</form:select>
			</div>
			<hr>
			<input type="submit" value="Add activity"/>
		</form:form>
	</div>
	<hr>
	<div>
		<h2>Activities:</h2>
		<br>
		<c:choose>
			<c:when test="${not empty activityList}">
			<table>
			<c:forEach items="${activityList}" var="activity">
				<tr>
	 				<td>${activity.activityName}</td>
	 				<td>
	 					<textarea rows="5" cols="20" readonly="readonly">
	 						 ${activity.description}
	 					</textarea>
	 				</td>
	 				<td>${activity.activityCategory.categoryName}</td>
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
