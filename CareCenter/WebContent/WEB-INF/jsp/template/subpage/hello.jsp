
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div class="tile mainContent">

    <div style="width: 1000">
		
		<h2>Alarms:</h2>
		<br>
		<c:choose>
			<c:when test="${not empty alarmList}">
			<table>
			<c:forEach items="${alarmList}" var="alarm" varStatus="status">
				<tr>
	 				<td>${status.index}</td>
	 				<td>${alarm.date}</td>
	 				<td>${alarm.patient.name}</td>
	 				<td>${alarm.patient.surname}</td>
	 				<td>${alarm.location}</td>
	 				<td>${alarm.description}</td>
	 				<td><a href="doctor/removeAlarm/${alarm.id}.html">remove</a></td>
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

<div id="schedule" class="schedule">
    <div id="week" class="week">
        <div id="monday" class="day">

        </div>
        <div id="tuesday" class="day">

        </div>
        <div id="wednesday" class="day">

        </div>
        <div id="thursday" class="day">

        </div>
        <div id="friday" class="day">

        </div>
        <div id="saturday" class="day">

        </div>
        <div id="sunday" class="day">

        </div>
    </div>
</div>