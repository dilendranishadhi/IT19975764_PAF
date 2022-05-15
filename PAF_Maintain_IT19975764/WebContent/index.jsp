<%@ page import = "com.maintains" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="Views/bootstrap.min.css">

<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/maintains.js"></script>


<title>Project Details</title>
</head>
<body>



<h1>Maintain Management</h1>
<form id="formMaintain" name="formMaintain">

 <input id="ID" name="ID" type="hidden"
 class="form-control form-control-sm"  readonly>
 <br> 
 Description:
 <input id="description" name="description" type="text"
 class="form-control form-control-sm">
 <br> 
 Affected Area:
 <input id="affected_Area" name="affected_Area" type="text"
 class="form-control form-control-sm">
 <br>
 Affected time:
 <input id="affected_time" name="affected_time" type="text"
 class="form-control form-control-sm">
 <br>
Predicted time:
 <input id="predicted_time" name="predicted_time" type="text"
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
 <input type="hidden" id="hideMaintainIDSave"
 name="hideMaintainIDSave" value="">
</form>

<div id="alertSuccess" class="alert alert-success"></div>
    <div id="alertError" class="alert alert-danger"></div>

<br>

<br><br>
	<div id="divMaintainGrid">
	<%
	maintains maintainObj = new maintains();
		out.print(maintainObj.readMaintain());
	%>
	
	 
	</div>

</body>
</html>