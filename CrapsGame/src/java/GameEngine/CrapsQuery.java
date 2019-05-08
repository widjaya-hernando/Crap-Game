/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameEngine;

import java.sql.*;
import java.util.*;

public class CrapsQuery
        
{
    //change the URL
   private static final String URL = "jdbc:derby://localhost:1527/CRAPS";
   private static final String USERNAME = "app";
   private static final String PASSWORD = "app";

   private Connection connection = null; // manages connection
   private PreparedStatement getUserID = null;
   private PreparedStatement getSessionID = null;
   private PreparedStatement updateUser = null;
   private PreparedStatement updateCraps = null;
   private PreparedStatement insertNewUser = null; 
   private PreparedStatement insertNewCraps = null; 
   private PreparedStatement insertNewRoll = null;
   
   private String userID;
   private String sessionID;
    
   // constructor
   public CrapsQuery()
   {
      try 
      {
         connection = 
            DriverManager.getConnection( URL, USERNAME, PASSWORD );
         
         // Get the last User_ID from the UserTable       

        getUserID = connection.prepareStatement (
            "SELECT * FROM UserTable ORDER BY User_ID DESC");
         
         // Get the last Craps_SessionID from the CrapsTable
         getSessionID = connection.prepareStatement (
            "SELECT * FROM CrapsTable ORDER BY Craps_SessionID DESC");
         
         // Update the win and loss of a user
         updateUser = connection.prepareStatement(
            "UPDATE UserTable SET User_Win = ?, User_Lose = ? " + 
            "WHERE User_ID = ?");
         
         // Update the win and loss of a user
         updateCraps = connection.prepareStatement(
            "UPDATE CrapsTable SET Craps_Outcome = ?, Roll_Total = ?, Craps_Points = ? " + 
            "WHERE Craps_SessionID = ?");       
         
         // create insert that adds a new entry into the database
         insertNewUser = connection.prepareStatement( 
            "INSERT INTO UserTable " + 
            "( User_FName, User_LName ) " + 
            "VALUES ( ?, ? )" );
         
         // create insert that adds a new entry into the database
         insertNewCraps = connection.prepareStatement( 
            "INSERT INTO CrapsTable " + 
            "( User_ID ) " + 
            "VALUES ( ? )" );
         
         // create insert that adds a new entry into the database
         insertNewRoll = connection.prepareStatement( 
            "INSERT INTO RollTable " + 
            "( Craps_SessionID, Roll_Die1Val, Roll_Die2Val ) " + 
            "VALUES ( ?, ?, ? )" );

      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
         System.exit( 1 );
      } // end catch
   } // end PersonQueries constructor
   
   //update user's win and lose
   public int updateUser(String win, String lose) {
      int result = 0;
      
      // set parameters, then execute insertNewUser
      try 
      {
         updateUser.setString( 1, win );
         updateUser.setString( 2, lose );
         updateUser.setString( 3, userID );

         // insert the new entry; returns # of rows updated
         result = updateUser.executeUpdate(); 
      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
         close();
      } // end catch
      
      return result; 
   }
   
   //update CrapsTable
   public int updateCraps(String crapsOutcome, String rollTotal, String crapsPoint) {
      int result = 0;
      
      // set parameters, then execute insertNewUser
      try 
      {
         updateCraps.setString( 1, crapsOutcome );
         updateCraps.setString( 2, rollTotal );
         updateCraps.setString( 3, crapsPoint );
         updateCraps.setString( 4, sessionID );

         // insert the new entry; returns # of rows updated
         result = updateCraps.executeUpdate(); 
      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
         close();
      } // end catch
      
      return result;
   }
   
   // add a new entry of User
   public int addUser( 
      String fname, String lname)
   {
      int result = 0;
      ResultSet resultSet;
      // set parameters, then execute insertNewUser
      try 
      {
         insertNewUser.setString( 1, fname );
         insertNewUser.setString( 2, lname );

         // insert the new entry; returns # of rows updated
         result = insertNewUser.executeUpdate(); 

         //Update userID
          if(result == 1){
          resultSet = getUserID.executeQuery();
          resultSet.next();
          userID = Integer.toString(resultSet.getInt( "User_ID" ));
          }
      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
         close();
      } // end catch      
      return result;
   } // end method addUser
   
      // add a new entry of Craps game
   public int addCraps()
   {
      int result = 0;
      ResultSet resultset;
      
      // set parameters, then execute insertNewCraps
      try 
      {
         insertNewCraps.setString( 1, userID );

         // insert the new entry; returns # of rows updated
         result = insertNewCraps.executeUpdate(); 
         
         //Update userID
          if(result == 1){
          resultset = getSessionID.executeQuery();
          resultset.next();
          sessionID = Integer.toString(resultset.getInt( "Craps_SessionID" ));
          }
      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
         close();
      } // end catch
      
      return result;
   } // end method addCraps
   
         // add a new entry for roll
   public int addRoll( 
      String rollDie1Val, String rollDie2Val )
   {
      int result = 0;
      
      // set parameters, then execute insertNewRoll
      try 
      {
         insertNewRoll.setString( 1, sessionID );
         insertNewRoll.setString( 2, rollDie1Val );
         insertNewRoll.setString( 3, rollDie2Val );

         // insert the new entry; returns # of rows updated
         result = insertNewRoll.executeUpdate(); 
      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
         close();
      } // end catch
      
      return result;
   } // end method addCraps
   
   // close the database connection
   public void close()
   {
      try 
      {
         connection.close();
      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
      } // end catch
   } // end method close
} // end class CrapsQuery
