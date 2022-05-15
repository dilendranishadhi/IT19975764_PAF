package com;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class maintains {
    //A common method to connect to the DB
	    private Connection connect() 
	        { 
                Connection con = null; 
                try
	        { 
	            Class.forName("com.mysql.jdbc.Driver"); 
	    
	    //Provide the correct details: DBServer/DBName, username, password 
	    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/maintain2", "root", "root"); 
    } 
    catch (Exception e) 
	        {e.printStackTrace();} 
    return con; 
    } 
    
    public String insertMaintain(String ID, String description, String affected_Area,String affected_time,String predicted_time ) 
    { 
        String output = ""; 
            try
            { 
                Connection con = connect(); 
                if (con == null) 
                    {	
                        return "Error while connecting to the database for inserting.";
                    } 
                
                // create a prepared statement
                String query = "insert into maintain2(`ID`,`description`,`affected_Area`,`affected_time`,`predicted_time`)"
                + " values (?, ?, ?, ?, ?)"; 
                PreparedStatement preparedStmt = con.prepareStatement(query); 
                // binding values
                preparedStmt.setInt(1, 0); 
                preparedStmt.setString(2, description); 
                preparedStmt.setString(3, affected_Area);
                preparedStmt.setString(4, affected_time);
                preparedStmt.setString(5, predicted_time); 
 
                
              

                // execute the statement
                preparedStmt.execute(); 
                con.close(); 
                //output = "New bill Create successfully"; 
                
                String newMaintain = readMaintain();
                output = "{\"status\":\"success\", \"data\": \"" + 
                		newMaintain + "\"}"; 
                
            } 
            catch (Exception e) 
            { 
//            output = "Error while creating the bill"; 
            output = "{\"status\":\"error\", \"data\": \"Error while inserting the maintain.\"}"; 
            System.err.println(e.getMessage()); 
            } 
        return output; 
    } 
    
    public String readMaintain() 
    { 
    String output = ""; 
    try
    { 
        Connection con = connect(); 
        if (con == null) 
        {
        	return "Error while connecting to the database for reading."; } 
        // Prepare the html table to be displayed
        output = "<table border='1'><tr>"
        		+ "<th> ID</th>"
        		+ "<th>description</th>" +
                "<th>affected_Area</th>" + 
                "<th>affected_time</th>"+
                "<th>predicted_time</th> </tr>"; 
        
        /**Use join query connect tree table */
        String query = "SELECT * FROM maintain2" ; 
        Statement stmt = con.createStatement(); 
        ResultSet rs = stmt.executeQuery(query); 
        // iterate through the rows in the result set
        while (rs.next()) 
        { 
            String ID = rs.getString("ID"); 
            String description = rs.getString("description"); 
            String affected_Area = rs.getString("affected_Area"); 
            String affected_time  = rs.getString("affected_time");
            String predicted_time = rs.getString("predicted_time"); 
           
            // Add into the html table
            output += "<tr><td>" + ID + "</td>"; 
            output += "<td>" + description + "</td>"; 
            output += "<td>" + affected_Area + "</td>";
            output += "<td>" + affected_time + "</td>"; 
            output += "<td>" + predicted_time + "</td>"; 

 
            		 
            // buttons
        			output += "<td><input name='btnUpdate' "
        					+ "type='button' value='Update' "
        					+ " class='btnUpdate btn btn-secondary'></td>"
        					+ "<td><input name='btnRemove' "
        					+ "type='button' value='Remove' "
        					+ "class='btnRemove btn btn-danger' "
        					+ "data-id='"
        			 + ID + "'>" + "</td></tr>";
        } 
        con.close(); 
        // Complete the html table
        output += "</table>"; 
    } 
	catch (Exception e) 
	{ 
		output = "Error while reading the maintain."; 
		System.err.println(e.getMessage()); 
	} 
	return output; 
	} 
    
   
  
    
    public String updateMaintain(String ID, String description, String affected_Area,String affected_time, String predicted_time)  
    
    { 
        String output = ""; 
    try
    { 
        Connection con = connect(); 
        if (con == null) 
    { 
	return "Error while connecting to the database for updating."; } 
    // create a prepared statement
    String query = "UPDATE maintain2 SET description=?,affected_Area=?,affected_time=?,predicted_time=? WHERE ID=?"; 
    PreparedStatement preparedStmt = con.prepareStatement(query); 
        // binding values
    
    
    preparedStmt.setString(1, description); 
    preparedStmt.setString(2, affected_Area);
    preparedStmt.setString(3, affected_time);
    preparedStmt.setString(4, predicted_time); 
    preparedStmt.setInt(5, Integer.parseInt(ID));
    
       

    
    // execute the statement
    preparedStmt.execute(); 
    con.close(); 
    String newMaintain = readMaintain();
	output = "{\"status\":\"success\", \"data\": \"" + newMaintain + "\"}";
}
catch (Exception e)
{
	output = "{\"status\":\"error\", \"data\": \"Error while deleting the maintain details. \"}";
	System.err.println(e.getMessage());
}

return output;
} 

    
	public String deleteMaintain(String ID)
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{
	return "Error while connecting to the database for deleting.";
	}
	// create a prepared statement
	String query = "delete from maintain2 where ID=?";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
	preparedStmt.setInt(1, Integer.parseInt(ID));
	// execute the statement
	preparedStmt.execute();
	con.close();
	String newMaintain = readMaintain();
	output = "{\"status\":\"success\", \"data\": \"" + newMaintain + "\"}";
	}
	catch (Exception e)
	{
	 output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";
	 System.err.println(e.getMessage());
	}
	return output;
	}

}

