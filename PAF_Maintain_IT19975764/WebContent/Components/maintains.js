$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validateMaintainForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
var type = ($("#hideMaintainIDSave").val() == "") ? "POST" : "PUT";
$.ajax(
{
url : "MaintainAPI",
type : type,
data : $("#formMaintain").serialize(),
dataType : "text",
complete : function(response, status)
{
onMaintainSaveComplete(response.responseText, status);
}
});
});
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
 $("#hideMaintainIDSave").val($(this).closest("tr").find('td:eq(0)').text());
 $("#ID").val($(this).closest("tr").find('td:eq(0)').text());
 $("#description").val($(this).closest("tr").find('td:eq(1)').text());
 $("#affected_Area").val($(this).closest("tr").find('td:eq(2)').text());
 $("#affected_time").val($(this).closest("tr").find('td:eq(3)').text());
 $("#predicted_time").val($(this).closest("tr").find('td:eq(4)').text());
});
// CLIENT-MODEL================================================================
function validateMaintainForm()
{

if ($("#description").val().trim() == "")
 {
 return "Insert Description.";
 }



if ($("#affected_Area").val().trim() == "")
 {
 return "Insert Affected Area ";
 } 


if ($("#affected_time").val().trim() == "")
 {
 return "Insert Affected Time.";
 } 
if ($("#predicted_time").val().trim() == "")
 {
 return "Insert Predicted Time.";
 }
return true;
}

function onMaintainSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divMaintainGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while saving..");
 $("#alertError").show();
 } 
14
 $("#hideMaintainIDSave").val("");
 $("#formMaintain")[0].reset();
}
$(document).on("click", ".btnRemove", function(event)
		{
		 $.ajax(
		 {
		 url : "MaintainAPI",
		 type : "DELETE",
		 data : "ID=" + $(this).attr("data-ID"),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onMaintainDeleteComplete(response.responseText, status);
		 }
		 });
		});

function onMaintainDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divMaintainGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}