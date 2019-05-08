/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import GameEngine.User;
import GameEngine.CrapsSimple;
import GameEngine.CrapsQuery;

/**
 *
 * @author Alex Conway
 */
public class control extends HttpServlet {

    User user = new User();
    CrapsSimple game = new CrapsSimple();
    CrapsQuery db = new CrapsQuery();
    
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String url = null;
        
        if (action.equals("start")) {
                user.setFirst(request.getParameter("FirstName"));
                user.setLast(request.getParameter("LastName"));
                
                db.addUser(user.getFirst(), user.getLast());
                db.addCraps();

                url = "/GamePage.jsp";
                getServletContext().getRequestDispatcher(url).forward(request, response);
        }
        else if (action.equals("roll")){
                url = "/GamePage.jsp";
                request.setAttribute("game", game);
                
                switch(game.getWinState()){
                    case 1:
                        db.updateCraps("win", Integer.toString(game.getRolls()), Integer.toString(game.getPoint()));
                        user.setWins(game.getWins());
                        break;
                    case 0:
                        db.updateCraps("lose", Integer.toString(game.getRolls()), Integer.toString(game.getPoint()));
                        user.setLosses(game.getLosses());
                        break;
                    default:
                        break;
                    }
                
                game.rollDice();
                db.addRoll(Integer.toString(game.getDie1()), Integer.toString(game.getDie2()));

                getServletContext().getRequestDispatcher(url).forward(request, response);
        }
        else {
                url = "/result.jsp";
                request.setAttribute("game", game);
                
                db.updateUser(Integer.toString(user.getWins()), Integer.toString(user.getLosses()));
                db.close();
                getServletContext().getRequestDispatcher(url).forward(request, response);
        }    
    }
}


    //@Override
    //public String getServletInfo() {
        //return "Short description";
    //}// </editor-fold>

//}
