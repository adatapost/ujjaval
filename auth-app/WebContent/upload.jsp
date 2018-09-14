<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="title" value="Home" />
<%@ include file="templates/my-header.jsp" %>

<form method="post"
      action="upload-db"
      enctype="multipart/form-data">

 <p>Name : <input type="text" name="name"> </p>
 <p>Select a file <input type="file" name="file"> </p>
 <p><button>Submit</button>
</form> 
<p>${message}</p>

<%@ include file="templates/my-footer.jsp" %>