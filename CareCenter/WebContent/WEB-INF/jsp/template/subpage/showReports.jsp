<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div style="width: 1000;">
	<h2>Care plan reports:</h2>
	<br>
	<c:choose>
		<c:when test="${not empty planList}">
		
		<c:forEach items="${planList}" var="carePlan">
			<div>${carePlan.carePlanName}</div>
			<c:forEach items="${carePlan.patientCarePlanList}" var="patientCarePlan">
				<div>Plan start date: ${patientCarePlan.startDate}</div>
				<div>Plan end date: ${patientCarePlan.endDate}</div>
				<div>remarks: ${patientCarePlan.remarks}</div>
				<c:forEach items="${patientCarePlan.reportList}" var="report"  varStatus="status">
					<table>
						<tr>
							<c:if test="${report.done}">
			 					<td>+</td>
			 				</c:if>
			 				<c:if test="${!report.done}">
			 					<td>-</td>
			 				</c:if>
			 				<td>${report.remarks}</td>
			 				<td>${report.date}</td>
			 				<td>${report.activity.activityName}</td>
			 			</tr>
			 		</table>
		 		</c:forEach>
	 		</c:forEach>
	 		<hr/>
		</c:forEach>
		
		</c:when>
		<c:otherwise>
			<h1>-----------</h1>
		</c:otherwise>
	</c:choose>
</div>