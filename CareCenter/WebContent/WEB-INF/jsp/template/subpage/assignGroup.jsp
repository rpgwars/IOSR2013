<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>



<div class="mainContent">
	<table>
		<c:forEach items="${possibleGroups}" var="possibleGroup" varStatus="status">
			<tr>
			
 				<td>${status.index}</td>
 				<td>group nr. ${possibleGroup}</td>
 				<td> <a href="assignGroup/${possibleGroup}.html">assign group</a></td>
			</tr>
		</c:forEach>
	</table>
</div>
