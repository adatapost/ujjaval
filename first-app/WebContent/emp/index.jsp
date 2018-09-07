<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
  <meta charset="ISO-8859-1">
  <title>Manage Employee</title>
</head>
<body>
<div class="container">
   <div class="data-entry">
     <fieldset>
      <legend>Employee</legend>
      <form method="post" action="emp">
        <p>
        Id : <input type="number" name="id" value="${emp.id}" /> 
        <button name="cmd" value="ById">Search</button>
        </p>
        <p>Name : <input type="text" name="empName" value="${emp.empName}" /> </p>
        <p>Salary : <input type="number" name="empSalary" value="${emp.empSalary}" /> </p>
        <p>JoinDate : <input type="date" name="joinDate" value="${emp.joinDate}" /> </p>
        <p>
          ${ message }
        </p>
        <p>
        <c:if test="${ empty emp.id }">
          <button name="cmd" value="Add">Add</button>
        </c:if>
        
        <c:if test="${ not empty emp.id }">
          <button name="cmd" value="Update">Update</button>
          <button name="cmd" value="Delete">Delete</button>
        </c:if>
        
          <button name="cmd" value="Cancel">Cancel</button>
        </p>
      </form>
     </fieldset>
   </div>
   
   <div class="data-list">
     <table>
       <c:forEach var="e" items="${emps }">
         <tr>
          <td>${ e.id }</td>
          <td>${e.empName }</td>
          <td>${e.empSalary}</td>
          <td>${e.joinDate }</td>
          <td>
           <form method="post" action="emp">
             <input type="hidden" name="id" value="${e.id }" />
             <button name="cmd" value="ById">Edit</button>
             <button name="cmd" value="Delete">Delete</button>
           </form>
          </td>
         </tr>
       </c:forEach>
     </table>
   </div>
</div> 
</body>
</html>