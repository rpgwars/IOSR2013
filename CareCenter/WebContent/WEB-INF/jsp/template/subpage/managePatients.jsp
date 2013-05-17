<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>


<div class="mainContent">
	
	<div style="width: 600; overflow: hidden;">
		<div style="float: left; width: 500px">
			<h2>Registered patients:</h2>
			<br>
			<c:choose>
				<c:when test="${not empty patientList}">
				<table>
				<c:forEach items="${patientList}" var="patient" varStatus="status">
					
					<c:if test="${status.index % 2 == 0}">
						<tr>
			 				<td>${patient.name}</td>
			 				<td>${patient.surname}</td>
			 				<td>${patinet.email }</td>
			 				<td>${patient.street }</td>
			 				<td>${patient.city }</td>
			 				<td> <a href="${patient.id}/assignCarePlan.html">assign care plan</a></td>
			 				<td> <a href="${patient.id}/showReports.html">show reports</a></td>
			 			</tr>
		 			</c:if>
				</c:forEach>
				</table>
				</c:when>
				<c:otherwise>
					<h1>-----------</h1>
				</c:otherwise>
			</c:choose>
		</div>
		<div style="width: 600px; overflow: hidden;">
			<h2>Registered patients:</h2>
			<br>
			<c:choose>
				<c:when test="${not empty patientList}">
				<table>
				<c:forEach items="${patientList}" var="patient" varStatus="status">
					<c:if test="${status.index % 2 == 1}">
						<tr>
			 				<td>${status.index}</td>
			 				<td>${patient.name}</td>
			 				<td>${patient.surname}</td>
			 				<td>${patinet.email }</td>
			 				<td>${patient.street }</td>
			 				<td>${patient.city }</td>
			 				<td> <a href="${patient.id}/assignCarePlan.html">assign care plan</a></td>
			 				<td> <a href="${patient.id}/showReports.html">show reports</a></td>
			 			</tr>
		 			</c:if>
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
