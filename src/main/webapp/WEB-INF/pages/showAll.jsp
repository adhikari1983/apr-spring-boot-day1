<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>congratulation page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<marquee> Congratulations for getting all the records!!!</marquee>
<div class="container">
       
  <table class="table table-hover">
    <thead>
      <tr bgcolor="yellow">
        <th>Employee Id</th>
        <th>Employee Name</th>
        <th>Salary</th>
        <th>Email Address</th>
        <th>Password</th>
        <th colspan=2>Action(delete/edit)</th>
        
      </tr>
    </thead>
    <tbody>
        <c:forEach var="oneObject"    items="${employeeDtoList}">
        		<tr bgcolor="pink">
        			<td>${oneObject.employeeId}</td>
        			<td>${oneObject.employeeName}</td>
        			<td>${oneObject.salary}</td>
        			<td>${oneObject.emailId}</td>
        			<td>${oneObject.password}</td>
        			<td>
        			   <a href="deleteEmployee?employeeId=${oneObject.employeeId}">
        			    <button type="button" class="btn btn-warning">
        			    	<img src="images/delete.jpg" style="height:40px;width:40px">
        			    </button>
        			   </a> 
        			</td>
       				<td>
       				   <a href="updateEmployee?employeeId=${oneObject.employeeId}"> 
       				    <button type="button" class="btn btn-danger">
       				    	<img src="images/edit.jpg" style="height:40px;width:40px">
       				    </button>
       				    </a>
       				</td>
      			</tr>
             
        </c:forEach>
    
          
    </tbody>
  </table>
  
</div>

</body>
</html>