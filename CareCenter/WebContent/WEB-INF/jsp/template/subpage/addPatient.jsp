<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>



<div class="mainContent">

	
	<div>
		<form:form method="post" commandName="patient">
			<div>
				<ul>
					<li><form:label path="name">name</form:label></li>
					<li><form:input path="name"></form:input></li>
					<li><form:errors path="name"></form:errors></li>
				</ul>			
			</div>
			<div>
				<ul>
					<li><form:label path="surname">surname</form:label></li>
					<li><form:input path="surname"></form:input></li>
					<li><form:errors path="surname"></form:errors></li>
				</ul>
			</div>
			<div>
				<ul>
					<li><form:label path="email">email</form:label></li>
					<li><form:input path="email"></form:input></li>
					<li><form:errors path="email"></form:errors></li>
				</ul>
			</div>
			<div>
				<ul>
					<li><form:label path="password">password</form:label></li>
					<li><form:password path="password"></form:password></li>
					<li><form:errors path="password"></form:errors></li>
				</ul>
			</div>
			<div>
				<ul>
					<li><form:label path="repeatedPassword">repeated password</form:label></li>
					<li><form:password path="repeatedPassword"></form:password></li>
					<li><form:errors path="repeatedPassword"></form:errors></li>
				</ul>
			</div>
			<div>
				<ul>
					<li><form:label path="birthDate">birth date (dd/MM/yyyy)</form:label></li>
					<li><form:input path="birthDate"></form:input></li>
					<li><form:errors path="birthDate"></form:errors></li>
				</ul>
			</div>
			<div>
				<ul>
					<li><form:label path="city">city</form:label></li>
					<li><form:input path="city"></form:input></li>
					<li><form:errors path="city"></form:errors></li>
  				</ul>
			</div>
			<div>
				<ul>
					<li><form:label path="street">street</form:label></li>
					<li><form:input path="street"></form:input></li>
					<li><form:errors path="street"></form:errors></li>
  				</ul>
			</div>
			<div>
				<ul>
					<li><form:label path="postalCode">postal code</form:label></li>
					<li><form:input path="postalCode"></form:input></li>
					<li><form:errors path="postalCode"></form:errors></li>
  				</ul>
			</div>
			<div>
				<ul>
					<li><form:label path="groupId">Group ID:</form:label></li>
					<li><form:input path="groupId"></form:input></li>
					<li><form:errors path="groupId"></form:errors></li>
				</ul>
			</div>
			<hr>
			<input type="submit" value="Register"/>
		</form:form>
	</div>
	<hr>
	<div style="width: 500; overflow: hidden;">
		<div style="float: left; width: 500px">
			<h2>Registered patients:</h2>
			<br>
			<c:choose>
				<c:when test="${not empty patientList}">
				<table>
				<c:forEach items="${patientList}" var="patient" varStatus="status">
					<tr>
					
		 				<td>${status.index}</td>
		 				<td>${patient.name}</td>
		 				<td>${patient.surname}</td>
		 				<td>${patinet.email }</td>
		 				<td>${patient.street }</td>
		 				<td>${patient.city }</td>
		 				<td> <a href="${patient.id}/assignGroup.html">assign group</a></td>
	
		 			</tr>
				</c:forEach>
				</table>
				</c:when>
				<c:otherwise>
					<h1>-----------</h1>
				</c:otherwise>
			</c:choose>
		</div>
		<div style="width: 500px; overflow: hidden;">
			<h2>Registered groups:</h2>
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
</div>
