import project.Linear;
import project.binary;
import project.Hash;
import project.Index;
import project.Merged;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

class Choice extends JFrame implements ActionListener{

JButton b1 = new JButton("Linear Search");
JButton b2 = new JButton("Binary Search");
JButton b3 = new JButton("Index Search");
JButton b4 = new JButton("Hash Search");
JButton b5 = new JButton("Merged List");

Choice(){
    setLayout(null);
    setSize(500, 500);
    setVisible(true);
    setTitle("Searching Techniques");

    b1.setBounds(30, 30, 250, 50);
    b1.setFont(new Font("Arial", Font.PLAIN, 24));
    b2.setBounds(30, 100, 250, 50);
b2.setFont(new Font("Arial", Font.PLAIN, 24));
    b3.setBounds(30, 170, 250, 50);
b3.setFont(new Font("Arial", Font.PLAIN, 24));
    b4.setBounds(30, 240, 250, 50);
b4.setFont(new Font("Arial", Font.PLAIN, 24));
    b5.setBounds(30, 310, 250, 50);
b5.setFont(new Font("Arial", Font.PLAIN, 24)); 

    add(b1);
    add(b2);
    add(b3);
    add(b4);
    add(b5);
    b1.addActionListener(this);
    b2.addActionListener(this);
    b3.addActionListener(this);
    b4.addActionListener(this);
    b5.addActionListener(this);
}

public void actionPerformed(ActionEvent e){
    if(e.getSource()==b1){
       new Linear();                                                 // imports the linear file for performing linear search
    }
    if(e.getSource()==b2){
        new binary();                                                      // imports the binary file for performing binary search
    }
    if(e.getSource()==b3){
        new Index();                               // imports the index file for performing index search
    }
    if(e.getSource()==b4){
       new Hash();                              // imports the hash file for performing hash search
    }
    if(e.getSource()==b5){
         new Merged();                                              // imports the merged file for merging the two singly linked list
    }
}
}

public class MainGUI{                                                                                  //main class of the entire topic 
    public static void main(String args[]){
        Choice ch = new Choice();                                                        // constructor for the class Choice
    }
}