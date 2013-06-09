<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>


<div class="mainContent">
	<div class="addInstanceForm">
        <h2 class="actionHeader">Create new Doctor</h2>
		<form:form method="post" commandName="doctor">
			<div>
				<ul>
					<li><form:input path="name" placeholder="Name"></form:input></li>
					<li><form:errors path="name"></form:errors></li>
				</ul>			
			</div>
			<div>
				<ul>
					<li><form:input path="surname" placeholder="Surname"></form:input></li>
					<li><form:errors path="surname"></form:errors></li>
				</ul>
			</div>
			<div>
				<ul>
					<li><form:input path="email" placeholder="Email"></form:input></li>
					<li><form:errors path="email"></form:errors></li>
				</ul>
			</div>
			<div>
				<ul>
					<li><form:password path="password"  placeholder="Password"></form:password></li>
					<li><form:errors path="password"></form:errors></li>
				</ul>
			</div>
			<div>
				<ul>
					<li><form:password path="repeatedPassword"  placeholder="Repeat password"></form:password></li>
					<li><form:errors path="repeatedPassword"></form:errors></li>
				</ul>
			</div>
			<div>
				<ul>
					<li><form:input path="birthDate" placeholder="Birth date (dd/MM/yyyy)"></form:input></li>
					<li><form:errors path="birthDate"></form:errors></li>
				</ul>
			</div>
			<div>
				<ul>
					<li>
						<form:select path="degree">
		     				<form:options items="${degreeList}"></form:options>                                                                                                       
		  				</form:select>
		  			</li>
  				</ul>
			</div>
			<div>
				<ul>
					<li><form:input path="groupId" placeholder="Group ID"></form:input></li>
					<li><form:errors path="groupId"></form:errors></li>
				</ul>
			</div>
			<hr>
			<input type="submit" class="btn btn-large btn-block btn-primary" value="Register"/>
		</form:form>
	</div>
	<div class="contentWrap">
		<div class="instanceTableContainer">
			<h2>Registered doctors:</h2>
			<br>
			<c:choose>
				<c:when test="${not empty doctorList}">
				<table class="table table-striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Doctor</th>
                            <th>Email</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${doctorList}" var="doctor" varStatus="status">
                        <tr>

                            <td>${status.index}</td>
                            <td>${doctor.name}  ${doctor.surname}</td>
                            <td>${doctor.email }</td>
                            <td> <a href="${doctor.id}/assignGroup.html">assign group</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
				</table>
				</c:when>
				<c:otherwise>
					<h1>There is no doctor registered in our database.</h1>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="instanceTableContainer">
			<h2>Registered groups:</h2>
			<br>
			<c:choose>
				<c:when test="${not empty groupList}">
				<table class="table table-striped">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Group Name</th>
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
					<h1>There is no group registered in our database.</h1>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>
