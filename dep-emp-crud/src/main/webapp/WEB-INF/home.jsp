<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
h1 {
	color: purple;
}

header {
	background-color: white;
	width: 100%;
	height: 50px;
	text-align: center;
}

#sidebar-left {
	float: left;
	width: 7%;
	background-color: gray;
}

#main {
	float: left;
	width: 85%;
	background-color: white;
}

#footer {
	clear: both;
	height: 50px;
	width: 100%;
	text-align: center;
	background-color: #ffa483;
}

#sidebar-left {
	min-height: 500px
}

table.d, td {
	table-layout: fixed;
	width: 10%;
	height: 5px;
	padding: 10px;
}

input[type=text] {
	border: 1px solid red;
	background-color: light-grey;
}
</style>


<title><spring:message code="label.getall"></spring:message></title>
</head>
<body>


	<form:errors path="department.*"></form:errors>
	<div id="header">
		<h2>
			<a href="/DeptList?siteLanguage=en">English</a>|<a
				href="/DeptList?siteLanguage=ja">Japenese</a>
		</h2>
	</div>

	<div id="sidebar-left">
		<a href="showdepartments">[+]</a>departments
		<c:if test="${name eq 'names'}">
			<c:forEach var="dept" items="${DeptListemp}">
				<c:if test="${not empty dept}">


					<input type="hidden" name="deptId" value="${dept.deptId}">


					<br>
                             &nbsp <a href="DeptList"><b>=></b></a>&nbsp <button
						type="button" class="btn btn-success" style="width: 100px;">
						<a href="emplist?deptId=${dept.deptId}"><font
							color="black">${dept.deptName} </font></a>
					</button>
					<br>


				</c:if>
			</c:forEach>
		</c:if>
	</div>


	<div id=main>
		<div align="center">
			<c:if test="${homepage ne 'emppage'}">
				<c:if test="${Register eq 'newform'}">
					<form action="CreateDepartment" method="post">
				</c:if>
				<c:if test="${Register ne 'newform'}">
					<form action="UpdateDepartment" method="post">
				</c:if>


				<table class="t" border="1 px solid black" align="center">
					<thead>
						<tr>
							<th colspan="3" style="text-align: center"><h1>
									<spring:message code="label.details"></spring:message>
								</h1></th>
							<th colspan="2" style="text-align: center"><a
								href="NewDepartment"><h2>
										<spring:message code="label.insertnew"></spring:message>
									</h2></a></th>
						</tr>
						<tr>
							<th style="text-align: center"><spring:message
									code="label.DeptID"></spring:message></th>
							<th style="text-align: center"><spring:message
									code="label.DeptName"></spring:message></th>

							<th style="text-align: center"><spring:message
									code="label.Update"></spring:message></th>
							<th style="text-align: center"><spring:message
									code="label.Delete"></spring:message></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${DeptList}" var="dept">


							<c:if test="${departmentId eq dept.deptId}">
								<tr>
									<td><input type="text" name="deptId"
										value="${dept.deptId}" readonly="readonly" /></td>
									<td><input type="text" name="deptName"
										value="${dept.deptName}" /></td>

									<td><input type="submit"
										value="<spring:message code="label.Update"></spring:message>" /></td>
								</tr>
							</c:if>
							<c:if test="${departmentId ne dept.deptId}">
								<tr>
									<td><c:out value="${dept.deptId}" /></td>
									<td><c:out value="${dept.deptName}" /></td>

									<td><a href="GetDepartment?deptId=${dept.deptId}"><spring:message
												code="label.Update"></spring:message></a></td>
									<td><a href="DeleteDepartment?deptId=${dept.deptId}"><h3>
												<spring:message code="label.Delete"></spring:message>
											</h3></a></td>
								</tr>
							</c:if>
						</c:forEach>
						<c:if test="${createdept eq 'newdept'}">
							<tr>
								<td><input type="text" name="deptId" /></td>
								<td><input type="text" name="deptName" /></td>

								<td colspan="2"><input type="submit"
									value="<spring:message code="label.S"></spring:message>" /></td>
							</tr>
						</c:if>
					</tbody>
				</table>
				</form>
				<div id="pagination">
					<c:url value="/DeptList" var="prev">
						<c:param name="page" value="${page-1}" />
					</c:url>
					<c:if test="${page > 1}">
						<a href="<c:out value="${prev}" />" class="pn prev">Prev</a>
					</c:if>
					<c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
						<c:choose>
							<c:when test="${page == i.index}">
								<span>${i.index}</span>
							</c:when>
							<c:otherwise>
								<c:url value="/DeptList" var="url">
									<c:param name="page" value="${i.index}" />
								</c:url>
								<a href='<c:out value="${url}" />'>${i.index}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:url value="/DeptList" var="next">
						<c:param name="page" value="${page + 1}" />
					</c:url>
					<c:if test="${page + 1 <= maxPages}">
						<a href='<c:out value="${next}" />' class="pn next">Next</a>
					</c:if>
				</div>
			</c:if>

		</div>
		<c:if test="${homepage eq 'emppage'}">
			<c:if test="${Register eq 'NewForm'}">
				<form action="saveEmployee" method="post">
			</c:if>
			<c:if test="${Register ne 'NewForm'}">
				<form action="updateEmployee" method="post">
			</c:if>


			<table border="2" style="width: 100%"background-color:#eee;>
				<thead>
					<tr>
						<th colspan="4" style="text-align: center"><h1>
								<spring:message code="label.Empdetails"></spring:message>
							</h1></th>
						<th colspan="2" style="text-align: center"><a
							href="newEmployee">
								<h2>
									<spring:message code="label.insertnewEmp"></spring:message>
								</h2>
						</a></th>
					</tr>
					<tr>
						<th style="text-align: center"><spring:message
								code="label.EmpID"></spring:message></th>
						<th style="text-align: center"><spring:message
								code="label.EDID"></spring:message></th>
						<th style="text-align: center"><spring:message
								code="label.EmpName"></spring:message></th>
						<th style="text-align: center"><spring:message
								code="label.EmpAge"></spring:message></th>
						<th style="text-align: center"><spring:message
								code="label.Update"></spring:message></th>
						<th style="text-align: center"><spring:message
								code="label.Delete"></spring:message></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${EmpList}" var="emp">
						<c:if test="${employeeid eq emp.empId}">
							<c:if test="${Did eq emp.eDid}">
								<tr>
									<td><input type="text" name="empId" value="${emp.empId}"
										readonly="readonly" /></td>
									<td><input type="text" name="eDid" value="${emp.eDid}"
										readonly="readonly" /></td>
									<td><input type="text" name="empName"
										value="${emp.empName}" /></td>
									<td><input type="text" name="empAge" value="${emp.empAge}" /></td>
									<td><input type="submit"
										value="<spring:message code="label.Update"></spring:message>" /></td>
								</tr>
							</c:if>
						</c:if>
						<c:if test="${employeeid ne emp.empId}">
							<tr>

								<td>${emp.empId}</td>
								<td>${emp.eDid}</td>
								<td>${emp.empName}</td>
								<td>${emp.empAge}</td>
								<td><a href="getEmployee?id=${emp.empId}&did=${emp.eDid}"><spring:message
											code="label.Update"></spring:message></a></td>
								<td><a
									href="deleteEmployee?id=${emp.empId}&did=${emp.eDid}"> <spring:message
											code="label.Delete"></spring:message></a></td>
							</tr>
						</c:if>
					</c:forEach>
					<c:if test="${insertEmployee eq 'newemployee'}">
						<tr>
							<td><input type="text" name="empId" /></td>
							<td><input type="text" name="eDid" /></td>
							<td><input type="text" name="empName" /></td>
							<td><input type="text" name="empAge" /></td>
							<td colspan="2"><input type="submit"
								value="<spring:message code="label.S"></spring:message>" /></td>
						</tr>
					</c:if>
				</tbody>
			</table>
			</form>
			<div id="pagination">
				<c:url value="/emplist" var="prev">
					<c:param name="deptId" value="${DeptId}" />

					<c:param name="page" value="${page-1}" />
				</c:url>
				<c:if test="${page > 1}">
					<a href="<c:out value="${prev}" />" class="pn prev">Prev</a>
				</c:if>
				<c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
					<c:choose>
						<c:when test="${page == i.index}">
							<span>${i.index}</span>
						</c:when>
						<c:otherwise>
							<c:url value="/emplist" var="url">
								<c:param name="deptId" value="${DeptId}" />
								<c:param name="page" value="${i.index}" />
							</c:url>
							<a href='<c:out value="${url}" />'>${i.index}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:url value="/emplist" var="next">
					<c:param name="deptId" value="${DeptId}" />
					<c:param name="page" value="${page + 1}" />
				</c:url>
				<c:if test="${page + 1 <= maxPages}">
					<a href='<c:out value="${next}" />' class="pn next">Next</a>
				</c:if>
			</div>
		</c:if>
	</div>
</body>
</html>