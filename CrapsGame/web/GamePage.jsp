<%-- 
    Document   : rollDice
    Created on : Apr 24, 2018, 8:57:41 AM
    Author     : zpy5045
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Craps</title>
    </head>
    <body>
        
        <h1>Roll the Dice</h1>
        
        <form action = "" method = "POST">
        
            <input id="gameTrigger" type="hidden" name="action" value=""> 
            <label>Die1:</label>
            <input type="text" value="${ game.die1 }" readonly="true" id="die1"/><br><br>
            <label>Die2:</label>
            <input type="text" value="${ game.die2 }" readonly="true" id="die2"/><br><br>
            <label>Sum:</label>
            <input type="text" value="${ game.sum }" readonly="true" id="sum"/><br><br>
            <label>Point:</label>
            <input type="text" value="${ game.point }" readonly="true" id="point"/><br><br>
            
            <p>You <span id="winloss">${ gamestatus }</span>!</p>
            
            <button onclick="roll()" type="submit">Roll</button>&nbsp;&nbsp;
            <button onclick="exit()" type="submit">Exit</button>
        
        </form>
        
            
        <script type="text/javascript">
            function roll() {
                document.getElementById("gameTrigger").value = "roll";
            }
            
            function exit() {
                document.getElementById("gameTrigger").value = "exit";
            }
        </script>
            
    </body>
</html>
