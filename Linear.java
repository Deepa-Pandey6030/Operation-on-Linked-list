package project;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.io.*;

class Lin{                                                                                                   
    class Node{                                                                                          // class to create a node 
        int data;                                                                                            // data part of doubly linked list
        Node next;                                                                                       // reference to connect wih the next node
        Node pre;                                                                                         // reference to connect with the previous node 
        public Node(int data){
          this.data = data;                                                                           // function to assign data part to the node 
          this.next = null;                                                                             // Initialize the next pointer to null, indicating the end of the list
          this.pre=null;                                                                                  // Initialize the previous pointer to null, indicating the head of the list
        }
    }

    Node head = null;                                                                                // Initially head is declared as null      
    Node p = null;                                                                                       // pointer to connect the two node 

    public void add(int data){                                                                   // method which adds the node to the doubly linked list
            Node newnode = new Node(data);                                          // Creating a new node
            if(head==null){                                                                                
                head = newnode;                                                                       // Creating a first node
                p = head;
            }
            else{ 
                newnode.pre=p;                                                                        // stores the address of previous node 
                p.next = newnode;                                                                    // stores the address of newnode in the previous node
                p = p.next;                                                                                   // connects the two nodes
            }   
    }

    public void display(){                                                                             // method to display the elements of the list                                                           
        Node ptr = head;                                                                                // pointer which traverses the list 
        if(head == null){                                                                                  // when the list is empty!!!
            System.out.println("List is empty!");
        }
        else{
            while(ptr!=null){                                                                               // traverses till last node 
                System.out.print(ptr.data+" ");                                                 // prints the data part of the that node
                ptr =ptr.next;                                                                                 // jumps to the next node
            }
        }
    }

    public int linearsearch(int item){                                                             // Function for the linear search in the doubly linked list 
        Node temp = head;                                                                                // pointer which traverses the list 
        int i,pos=1;                                                                                                // initialization of variable for the position record 
        while(temp!=null){
        if(temp.data==item){                                                                              // condition to check that the current node is the key or not 
        return pos;                                                                                                  // returns the position of the key node 
    }
pos++;                                                                                                                  // increments the position value 
temp=temp.next;                                                                                              // jumps to the next node in every iteration  
   }
   return 0;                                                                                                             // returns 0 if the key isnot present in the list
}
}

public class Linear extends JFrame implements ActionListener{                         // linear class extends the action Listener 
    Lin no = new Lin();                                                                                           // instance of the class which consructs the doubly linked list 
    JLabel l1 = new JLabel("Enter data:");                                                          // label which asks for the data of the node in GUI
    JTextField t1 = new JTextField();                                                                    // textfield for the same 
    JButton b1 = new JButton("Add node");                                                     // A button after pressing the node gets added
    JLabel l2 = new JLabel("List: ");                                                                      
    JLabel l3 = new JLabel("Search: ");                                                              
    JTextField t2 = new JTextField();                                                                   // accepts the node to search 
    JButton b3 = new JButton("Search");                                                          // A button after pressing it the searching starts
    JButton b4 = new JButton("Back");                                                              // button to exit the current linear search page 

    public Linear(){                                                                                                   
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(700, 700);
        setVisible(true);
        setTitle("Linear Search");

      		 l1.setBounds(30, 20, 230, 40);
 		l1.setFont(new Font("Arial", Font.PLAIN, 24));
		t1.setBounds(180, 20, 100, 40);
    		t1.setFont(new Font("Arial", Font.PLAIN, 18));
		b1.setBounds(300, 20, 230, 40);
		b1.setFont(new Font("Arial", Font.PLAIN, 24));
		l2.setBounds(30, 80, 800, 40);
 		l2.setFont(new Font("Arial", Font.PLAIN, 24));
		l3.setBounds(30, 140, 100, 40);
		l3.setFont(new Font("Arial", Font.PLAIN, 24));
		t2.setBounds(200, 140, 80, 40);
		t2.setFont(new Font("Arial", Font.PLAIN, 18));
		b3.setBounds(300, 140, 250, 40);
		b3.setFont(new Font("Arial", Font.PLAIN, 24));
		b4.setBounds(40, 600, 250, 40);
		b4.setFont(new Font("Arial", Font.PLAIN, 24));


        add(l1);
        add(t1);
        add(b1);
        add(l2);
        add(l3);
        add(t2);
        add(b3);
        add(b4);
        b1.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        
    }
public void actionPerformed(ActionEvent e){                                                           
    if(e.getSource()==b1){                                                                                                 // if add button is pressed  
    try{                                                                                                                                       // Excepton Handling 
    int value = Integer.parseInt(t1.getText());                                                           // stores the value after taking it from the text field 
    no.add(value);                                                                                                                // calls the method which add the nodes 
    updateListDisplay();
    t1.setText("");                                                                                                     // Simultaneously displays the nodes 
    }
    catch(NumberFormatException ex){
    JOptionPane.showMessageDialog(this, "Please enter a valid Integer");                                         // Data Validation 
    }
    }
        if(e.getSource()==b3){                                                                                              // if search button is pressed                                    
try{                                                                                                                                            // Exception Handling 
int item=Integer.parseInt(t2.getText());                                                                    // Stores the key to search                                                  
int pos=no.linearsearch(item);                                                                                      // call the function which the searches the enetered node
if(pos!=0){                                                                   
JOptionPane.showMessageDialog(this, "Found in the given list at position "+pos+".");                       // pops the message if found
}
else{
JOptionPane.showMessageDialog(this, "Not found in the given list!!!");                              // pops the message is not found
}
updateListDisplay();
}
catch(NumberFormatException ex){
JOptionPane.showMessageDialog(this, "Please enter a valid Integer");                                  //Data Validation for the searching element input
}
}
if (e.getSource() == b4) {                                              
    this.dispose();                                                                                                     // Closes the current window                                                                           
        updateListDisplay();
        t1.setText("");
    }
}
    private void updateListDisplay(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();                              // Capture the output of the display() method
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        no.display();                                                                                                       // Capture the output of the display() method
        System.out.flush();                                                                                                                    
        System.setOut(originalOut);                                                               // Restore original System.out
        l2.setText("List: " + outputStream.toString().trim());                                                           // Set the text of the label to the captured output
    }
}