<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>



<div class="mainContent">

	<div>
		<form:form method="post" commandName="patientCarePlan">
				<div>
					<ul>
						<li><form:label path="remarks">Remarks specific to this patient</form:label></li>
						<li><form:textarea rows="5" cols="20" path="remarks"></form:textarea></li>
						<li><form:errors path="remarks"></form:errors></li>
					</ul>
				</div>
				<div>
					<ul>
						<li><form:label path="startDate">Care plan start date (dd/MM/yyyy)</form:label></li>
						<li><form:input path="startDate"></form:input></li>
						<li><form:errors path="startDate"></form:errors></li>
					</ul>
				</div>
				<div>
					<ul>
						<li><form:label path="endDate">Care plan end date (dd/MM/yyyy)</form:label></li>
						<li><form:input path="endDate"></form:input></li>
						<li><form:errors path="endDate"></form:errors></li>
					</ul>
				</div>
				<div>
					<select name="carePlanId">
						<c:forEach items="${possibleCarePlanList}" var="possibleCarePlan">
							<option>
								${possibleCarePlan.id} 
							</option>
						</c:forEach>
					</select>
					
				</div>
				<hr>
				<input type="submit" value="Assign care plan"/>
		</form:form>
	</div>
	<div style="width: 500; overflow: hidden;">
		<div style="float: left; width: 500px">
			<h2>Unassigned care plans:</h2>
			<br>
			<c:choose>
				<c:when test="${not empty possibleCarePlanList}">
				<table>
					<c:forEach items="${possibleCarePlanList}" var="carePlan" varStatus="status">
						<tr>
						
			 				<td>${carePlan.carePlanName}</td>
			 				<td>${carePlan.id}</td>
			 				<td>
			 					<div>
			 						<c:forEach items="${carePlan.activityCarePlanList}" var="activityCarePlan">
			 							<div>
			 								<span>${activityCarePlan.activity.activityName}</span>
			 								<span>Day: ${activityCarePlan.dayOfWeek}</span>
			 								<span>Hour: ${activityCarePlan.hourOfDay }</span>
			 							</div>	
			 						</c:forEach>
			 					</div>
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
		<div style="width: 500px; overflow: hidden;">
			<h2>Assigned care plans:</h2>
			<br>
			<c:choose>
				<c:when test="${not empty assignedCarePlanList}">
				<table>
					<c:forEach items="${assignedCarePlanList}" var="carePlan" varStatus="status">
						<tr>
						
			 				<td>${carePlan.carePlanName}</td>
			 				<td>${carePlan.id}</td>
			 				<td>
			 					<div>
			 						<c:forEach items="${carePlan.activityCarePlanList}" var="activityCarePlan">
			 							<span>${activityCarePlan.activity.activityName}</span>
			 							<span>Day: ${activityCarePlan.dayOfWeek}</span>
			 							<span>Hour: ${activityCarePlan.hourOfDay }</span>	
			 						</c:forEach>
			 					</div>
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
</div>