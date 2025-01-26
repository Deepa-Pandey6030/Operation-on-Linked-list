package project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

class nod {
	class Node {                                                                              // class to create a node
		int data;                                                                 // data part of doubly linked list
		Node next;                                                            // pointer to connect wih the next node
		Node pre;                                                               // pointer to connect with the previous node
		public Node(int data) {
			this.data = data;                               // method to assign data part to the node
			this.next = null;                                 // Initialize the next pointer to null, indicating the end of the list
			this.pre=null;                                     // Initialize the previous pointer to null, indicating the head of the list
		}
	}
	Node head = null;                                                                     // Initially head is declared as null
	Node p = null;                                                                            // pointer to connect the two node

	public void add(int data) {                                                       // Method which adds the node to the doubly linked list
		Node newnode = new Node(data);                  // Creating a new node
		if(head==null) {
			head = newnode;                              // Creating a first node
			p = head;
		}
		else {
			newnode.pre=p;                                                                      // stores the address of previous node
			p.next = newnode;                                                                  // stores the address of new node  in the previous node
			p = p.next;                                                                                // connects the two nodes
		}

	}

	public void display() {                                                                                 // Method to display the elements of the list
		Node ptr = head;                                                                     // pointer which traverses the list
		if(head == null) {                                                                      // when the list is empty!!!
			System.out.println("List is empty!");
		}
		else {
			while(ptr!=null) {                                                                                          // traverses till last node
				System.out.print(ptr.data+" ");                                            // prints the data part of the that node
				ptr =ptr.next;                                                                             // jumps to the next node
			}
		}
	}

	public void sort() {                                                                                                               // Method to sort the enetered list
		Node p=head,q;                                                                                              // pointer which traverses the list
		while(p!=null) {                                                                                                // first while where the pointer point to every node for sorting
			q=p.next;                                                                                      // starts sorting the list after node pointed by p
			while(q!=null) {
				if(p.data>q.data) {                                                  // Bubble sort
					int temp=p.data;
					p.data=q.data;
					q.data=temp;
				}
				q=q.next;
			}
			p=p.next;
		}
	}

	public boolean binarysearch(int k)                                                              // method for performing binary search
	{
		if(head==null) {                                                                            // when list is empty!!!!
			System.out.println("Sorry, the list is empty.");
			return false;
		}
		Node start=head;                                                                        // In the first iteration we start from the first node
		Node last=lastnode();                                                                 // calls the method to find the last node
		while(last!=start) {                                                                       // keeps on iterating tll start and last ponter points to the same node 
			Node mid=middle(start,last);                               // calls the method to find the middle node 
			if(mid.data==k) {
				return true;                                             // if founds then returnd true
			}
			else if(mid.data<k) {                                                 // if the key value is greater than the mid node we eliminate the first half of the list
				start=mid.next;
			}
			else {                                                                             // if the key value is lesser than the mid node we eliminate the second half of the list
				last=mid;                
			}
		}
		return start!=null  && start.data==k;                                         // when the first node is the key
	}
	private Node lastnode() {                                                                                 // method to find the last node 
		Node p=head;
		if(p==null)
			return null;
		while(p.next!=null) {                                                                      // iterates till we find the last node 
			p=p.next;                                                                      
		}
		return p;                                                                                            // returns poiinter pointing to the last node 
	}
	private Node middle(Node start,Node last) {                                             // method to find the middle of the list 
		if(start==null) {
			return null;
		}
		Node slow;
		Node fast;
		slow=fast=start;
		while(fast!=last && fast.next!=last) {                                        // by using slow and fast pointer at the end iteration slow points to the middle node
			slow=slow.next;                                                         // points every next node 
			fast=fast.next.next;                                                   // points every third node 
		}
		return slow;
	}
}


public class binary extends JFrame implements ActionListener {                                         // class which extends the action Listener 
	nod ob = new nod();                                                                                         // instance of the class which consructs the doubly linked list 
	JLabel l1 = new JLabel("Enter data:");                                                          // label which asks for the data of the node in GUI
	JTextField t1 = new JTextField();                                                                    // textfield for the same 
	JButton b1 = new JButton("Add node");                                                       // A button after pressing the node gets added
	JLabel l2 = new JLabel("List: ");
	JLabel l3 = new JLabel(" Search: ");
	JTextField t2 = new JTextField();                                                                     // accepts the node to search 
	JButton b3 = new JButton("Binary Search");                                              // A button after pressing it the searching starts
	JButton b4 = new JButton("Back");                                                               // button to exit the current binary search page 

	public binary() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setSize(700, 700);
		setVisible(true);
		setTitle("Binary search");
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
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1) {                                                                                    // if add button is pressed  
			try {                                                                                                    // Excepton Handling 
				int value = Integer.parseInt(t1.getText());     									// stores the value after taking it from the text field 
				ob.add(value);                                                         // calls the function which add the nodes 
				updateListDisplay();                                             // Simultaneously displays the nodes 
			}
			catch(NumberFormatException ex) {		
				JOptionPane.showMessageDialog(this, "Please enter a valid Integer");    // Data Validation 
			}
		}
		if(e.getSource()==b3) {                                                                                // if search button is pressed                                   
			try {                                                                                                 // Exception Handling 
				int item=Integer.parseInt(t2.getText());                                  // Stores the key to search
				ob.sort();
				if(ob.binarysearch(item)) {                                 // call the mehod which searches the enetered node
					JOptionPane.showMessageDialog(this, "Found in the given list!!!");         // pops the message if found
				}
				else {
					JOptionPane.showMessageDialog(this, "Not found in the given list!!!");   // pops the message is not found
				}
				updateListDisplay();
			}
			catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "Please enter a valid Integer");                         //Data Validation for the searching element input
			}
		}
		if (e.getSource() == b4) {
            this.dispose(); // Closes the current window
        }

		t1.setText("");
	}

	private void updateListDisplay() {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();                         // Capture the output of the display() method
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(outputStream));
		ob.display();                                                                                                 // Capture the output of the display() method
		System.out.flush();                                                                                   // Restore original System.out
		System.setOut(originalOut);
		l2.setText("List: " + outputStream.toString().trim());                    // Set the text of the label to the captured output
	}
}