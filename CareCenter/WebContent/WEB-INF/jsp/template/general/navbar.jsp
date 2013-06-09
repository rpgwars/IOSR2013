<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
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
    String addActivity = protocol + name + "doctor/addActivity.html";
    String addCarePlan = protocol + name + "doctor/addCarePlan.html";
    String addPatient = protocol + name + "doctor/addPatient.html";
    String managePatients = protocol + name + "doctor/managePatients.html";
%>

<header>
    <a href="/" class="mainLogo">
        <img src="/themes/img/logo.png" />
        <h4> Panel Administracyjny
            <span>CareCenter</span>
        </h4>
    </a>
    <div class="logout">
        <%String logoutLink = protocol + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/" +"logout.html";%>
        <a href="<%=logoutLink %>">Logout <span class="fui-checkround-24"></span></a>
    </div>
    <div class="navbar navbar-inverse">
        <div class="navbar-inner">
            <div class="container">
                <div class="nav-collapse collapse">
                    <ul class="nav">
                        <security:authorize access="hasRole('ROLE_ADMIN')">
                        <li>
                            <a href="<%=home%>">
                                Home
                                <span class="navbar-unread">1</span>
                            </a>
                        </li>
                        <li>
                            <a href="<%=addDoctor%>">
                                Doctors
                            </a>
                        </li>
                        <li>
                            <a href="<%=addGroup%>">
                                Groups
                            </a>
                        </li>
                        </security:authorize>
                        <security:authorize access="hasRole('ROLE_DOCTOR')">
                        	<li>
                                <a href="<%=addPatient%>">Patients</a>
                            </li>
                            <li>
                                <a href="<%=addCategory%>">Categories</a>
                            </li>
                            <li>
                                <a href="<%=addActivity%>">Activities</a>
                            </li>
                            <li>
                                <a href="<%=addCarePlan%>">CarePlans</a>
                            </li>
                            <li>
                                <a href="<%=managePatients%>">Manage patients</a>
                            </li>
                        </security:authorize>
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </div>
    </div>
</header>
