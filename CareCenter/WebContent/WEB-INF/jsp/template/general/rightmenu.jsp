<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>		
		
		<% String name= request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
		String protocol; 
		if(request.isSecure())
			protocol = "https://";
		else
			protocol = "http://";
		
		String home = protocol + name + "hello.html";
		String addDoctor = protocol + name + "administration/addDoctor.html";
		String addGroup = protocol + name + "administration/addGroup.html";
		String addCategory = protocol + name + "doctor/addCategory.html";
		%>
		
		<div class="rightmenu">
		
		
			<ul>
			<security:authorize access="hasRole('ROLE_ADMIN')">
				<li>
					<a href="<%=home%>">Home</a>
				</li>
				<li>
					<a href="<%=addDoctor%>">Doctors</a>
				</li>
				<li>
					<a href="<%=addGroup%>">Groups</a>
				</li>
			</security:authorize>
			
			<security:authorize access="hasRole('ROLE_DOCTOR')">
				<li>
					<a href="<%=addCategory%>">Categories</a>
				</li>
			</security:authorize>
	
			</ul>
		</div>