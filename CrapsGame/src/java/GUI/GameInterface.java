package GUI;

import GameEngine.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.*;
/**
 *
 * @author Paul
 */
public class GameInterface extends JFrame {
    
    JTabbedPane tabbedPane = new JTabbedPane();
    
    private JLabel label1, label2, label3, label4, title;
    private JTextField tf1, tf2, tf3, tf4;
    private JButton start;
    private JPanel p1, p2, p3, p4, p5, p6;
    private JPanel upperPanel, lowerPanel, panel1;
    CardLayout cl = new CardLayout();
    
    CrapsSimple cs = new CrapsSimple();
    User user = new User();
    CrapsQuery cq = new CrapsQuery();
    
    private JLabel heading = new JLabel("Game Records");
    private JLabel l1, l2, l3, firstname, lastname;
    private JTextField total, wins, loses, fName, lName;
    private JPanel p21, p22, p23, p24, panel2, pHead;
    private JButton refresh, logout;
    
    static String name;
    static String[] splited;
    
    public GameInterface() {
        super("Game Interface");
        
        name = JOptionPane.showInputDialog("What's your first and last name? (Put a space between them)");
        splited = name.split("\\s+");
        cq.addUser(splited[0], splited[1]);
        cq.addCraps();
        
        //title, also used to tell user game status, like "Keep rolling", "You won"
        title = new JLabel("Roll a Dice to Start");
        title.setFont(new Font("Aria",Font.BOLD, 20));
        p5 = new JPanel(new FlowLayout());
        p5.add(title);
        
        //Create fout text field and the correspond four labels
        label1 = new JLabel("Dice 1");
        label2 = new JLabel("Dice 2");
        label3 = new JLabel("Sum   ");
        label4 = new JLabel("Points");
        tf1 = new JTextField(3);
        tf1.setEditable(false);
        tf2 = new JTextField(3); 
        tf2.setEditable(false);
        tf3 = new JTextField(3);
        tf3.setEditable(false);
        tf4 = new JTextField(3);
        tf4.setEditable(false);
        
        //Create the button used to play game
        start = new JButton("Roll");
        //Button handler
        GameInterface.ButtonHandler bh = new GameInterface.ButtonHandler();
        start.addActionListener(bh);
        
        //Panels that organize text fields and labels
        p1 = new JPanel(new FlowLayout());
        p1.add(label1);
        p1.add(tf1);
        p2 = new JPanel(new FlowLayout());
        p2.add(label2);
        p2.add(tf2);
        p3 = new JPanel(new FlowLayout());
        p3.add(label3);
        p3.add(tf3);
        p4 = new JPanel(new FlowLayout());
        p4.add(label4);
        p4.add(tf4);
        p6 = new JPanel(new FlowLayout());
        p6.add(start);
        
        panel1 = new JPanel(new GridLayout(6,1));
        panel1.add(p5);
        panel1.add(p1);//Four pairs of text field and label
        panel1.add(p2);//Game play button
        panel1.add(p3);
        panel1.add(p4);
        panel1.add(p6);
        tabbedPane.addTab( "Game", null, panel1, "Game Page" );
        
        //
        //TAB 2
        //
        
        heading.setFont(new Font("Aria",Font.BOLD, 20));
        pHead = new JPanel(new FlowLayout());
        pHead.add(heading);
        
        //Labels and TextFields of game information
        total = new JTextField(3);
        total.setEditable(false);
        wins = new JTextField(3);
        wins.setEditable(false);
        loses = new JTextField(3);
        loses.setEditable(false);
        l1 = new JLabel("Total game played:");
        l2 = new JLabel("Wins:");
        l3 = new JLabel("Loses:");
        p21 = new JPanel(new FlowLayout());
        p21.add(l1);
        p21.add(total);
        p21.add(l2);
        p21.add(wins);
        p21.add(l3);
        p21.add(loses);
        
        //labels and text fields of player's info
        firstname = new JLabel("First name:");
        lastname = new JLabel("Last name:");
        fName = new JTextField(5);
        fName.setEditable(false);
        lName = new JTextField(5);
        lName.setEditable(false);
        p23 = new JPanel(new FlowLayout());
        p23.add(firstname);
        p23.add(fName);
        p23.add(lastname);
        p23.add(lName);
        
        //Two buttons
        refresh = new JButton("Refresh");
        logout = new JButton("Exit");
        p24 = new JPanel(new FlowLayout());
        p24.add(refresh);
        p24.add(logout);
        refresh.addActionListener(bh);
        logout.addActionListener(bh);
        
        //Add together
        panel2 = new JPanel(new GridLayout(4,1));
        panel2.add(pHead);
        panel2.add(p23);
        panel2.add(p21);
        panel2.add(p24);
        tabbedPane.addTab( "Status", null, panel2, "Status Page" );
        
        
        
        add(tabbedPane);
        
    }
    
    private class ButtonHandler implements ActionListener
    {
        // handle button event
        public void actionPerformed( ActionEvent event )
        {
            //Refresh game status button
            if (event.getSource()== refresh) {
                
                user.setFirst(splited[0]);
                user.setLast(splited[1]);
                user.setWins(cs.getWins());
                user.setLosses(cs.getLosses());
                
                fName.setText(user.getFirst());
                lName.setText(user.getLast());
                total.setText(Integer.toString(user.getWins()+user.getLosses()));
                wins.setText(Integer.toString(user.getWins()));
                loses.setText(Integer.toString(user.getLosses()));
                
                
            } else if (event.getSource() == logout) {
                cq.updateUser(Integer.toString(user.getWins()), Integer.toString(user.getLosses()));
                
                System.exit(0);
            } else if (event.getSource() == start) {
                
                switch(cs.getWinState()){
                    case 0: 
                    case 1: cq.addCraps();
                            break;
                    default: break;                    
                }
                cs.rollDice();
                tf1.setText(Integer.toString(cs.getDie1()));
                tf2.setText(Integer.toString(cs.getDie2()));
                tf3.setText(Integer.toString(cs.getSum()));
                tf4.setText(Integer.toString(cs.getPoint()));
                title.setText(cs.getGameStatus());
                cq.addRoll(Integer.toString(cs.getDie1()),Integer.toString(cs.getDie2()));
                 switch(cs.getWinState()){
                    case 1:
                        cq.updateCraps("win", Integer.toString(cs.getRolls()), Integer.toString(cs.getPoint()));
                        user.setWins(cs.getWins());
                        break;
                    case 0:
                        cq.updateCraps("lose", Integer.toString(cs.getRolls()), Integer.toString(cs.getPoint()));
                        user.setLosses(cs.getLosses());
                        break;
                    default:
                        break;
                    }
                
                
            }
        }
    }
    
    
}
