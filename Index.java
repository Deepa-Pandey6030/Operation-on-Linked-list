package project;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.io.*;

class Ind {
	class node {                                                                               // class to create a node                                           
		int data;                                                                 // data part of doubly linked list
		node next;                                                             // pointer to connect wih the next node
		node pre;                                                               // pointer to connect wih the previous node
		public node(int data) {
			this.data = data;                               // function to assign data part to the node 
			this.next = null;                                 // function to assign next part to the node
			this.pre=null;                                     // function to assign previous part to the node
		}
	}
	class index {                                                                               // class to create the index list (singly)
		node ref;                                                                 // reference to a node that this index points to 
		index next;                                                              // Pointer to the next index in the list
		public index(node ref) {                                      // constructor which initializes the pointer to point to the node in the in the index list
			this.ref = ref;                                     // Set the node reference                    
			this.next = null;                                // Initialize the next pointer to null, indicating the end of the list
		}
	}
	index indexhead=null;                                                          // head of the index list initialized as null
	node p=null;
	int size=-1;                                                                               // initialization of size variable

	public node add(int data,node head) {                             // method which adds node to the list
		node newnode = new node(data);                 //assigns the data part to the new node 
		if(head==null) {
			head = newnode;                            // creating the first node 
			p=head;
		}
		else {
			newnode.pre=p;                              // stores the address of previous node 
			p.next = newnode;                          // stores the address of newnode in the previous node
			p = p.next;
		}
		size++;                                                                   // in every call the size variable increments by one  
		if ((size%3==0)) {                                                  // dividing the original list into small groups contaning three nodes 
			index newindex = new index(newnode);                               // stores the head of every such small groups 
			if (indexhead == null) {
				indexhead = newindex;                                          // head of the original list becomes the head of index list 
			} else {
				index current = indexhead;
				while (current.next != null) {                                   // in every call pointers traverse till last node 
					current = current.next;
				}
				current.next = newindex;                                       // joins the new index node at the end 
			}
		}
		return head;                                                                                                      // returns the head of the original list 
	}

	public void display(node head) {                            // method to display the elements of the list 
		node ptr = head;                                    // pointer which traverses the list 
		if(head == null) {                                     // when the list is empty!!!
			System.out.println("List is empty!");
		}
		else {
			while(ptr!=null) {                // traverses till last node 
				System.out.print(ptr.data+" ");             // prints the data part of the that node
				ptr =ptr.next;                                            // jumps to the next node
			}
		}
	}
	public int indexsearch(int key) {                                                          // method for index searching 
		index p = indexhead;                                                                   // pointer pointing to the head of the index list 
		int pos=0;                                                                              // initialiizing pos variable 
		while (p != null) {
			node refnode = p.ref;			// Get the node referenced by the current index
			
			if(refnode == null)
			{
				p = p.next;
				continue;
			}
			
			if (refnode.data == key) {                                      // if the data in the referenced node matches with the key 
				pos++;
				return pos;
			}
			else if  ((refnode.data < key) && (refnode.next)!=null && (refnode.next.data>key)) 
			{                 // if the key is present between the current node and the next node
				int i=0;                                         // counter for the number of nodes checked 
				node current = refnode;           // starts checking the from the current referenced node in the original llist 
				while (i!=3 && current!=null) {                                // only checks in that small divided group
					pos++;                                     // increment position 
					if (current.data == key) 
					{          // performs linear search 
						i++;
						return pos;                        // returns position of the element if found
					}
					current = current.next;               // if doesn't matches move to the next node 
				}
			}
                                                              // If the key is not between current and next, search all subsequent nodes of the index list 
			else 
			{
				node current =refnode;                 // starts searching from the current node 
                // Continue searching until the end of the list          															
				while(current!=null) 
				{						
					pos++;
					if(current.data==key) 
					{     													
						return pos;																// if key found returns position
					}
					current=current.next;           // move to the next node
				}
			}
			p = p.next;                                       // move to next node in the ndex list 
		}
		return 0;                                                       // when key is not found in the list returns 0
	}
}


public class Index extends JFrame implements ActionListener 
{                                    																	// index class extends the action Listener 
	Ind.node head1=null;                                                                                // initializing the head of the list 
	Ind ob = new Ind();                                                                                     // instance of the class which consructs the doubly linked list 
	JLabel l1 = new JLabel("Enter data :");                                // label which asks for the data of the node in GUI
	JTextField t1 = new JTextField();                                                              // textfield for the same
	JButton b1 = new JButton("Add node");                                                // A button after pressing the node gets added
	JLabel l2=new JLabel("List: ");
	JLabel l3=new JLabel(" Search");
	JTextField t2=new JTextField();                                                               // accepts the node to search 
	JButton b2 = new JButton("Index Search");                                        // button after pressing it the searching starts
	JButton b3 = new JButton("Back");                                                        // button to exit the current index search page 

	public Index() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setSize(700, 700);
		setVisible(true);
		setTitle("Linked List using GUI");
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
		b2.setBounds(300, 140, 250, 40);
		b2.setFont(new Font("Arial", Font.PLAIN, 24));
		b3.setBounds(40, 600, 250, 40);
		b3.setFont(new Font("Arial", Font.PLAIN, 24));
		add(l1);
		add(t1);
		add(b1);
		add(l2);
		add(l3);
		add(t2);
		add(b2);
		add(b3);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);

	}
	public void actionPerformed(ActionEvent e) 
	{ 
		if(e.getSource()==b1) 
		{
			try 
			{                                                                                                 // Excepton Handling
				int value = Integer.parseInt(t1.getText());         // stores the value after taking it from the text field
				head1=ob.add(value,head1);                                // calls the function which add the nodes // calls the function which add the nodes
				updateListDisplay();                                               // Simultaneously displays the nodes 
			}
			catch(NumberFormatException ex) 
			{
				JOptionPane.showMessageDialog(this, "Please enter a valid Integer");              // Data Validation 
			}
		}
		if(e.getSource()==b2) 
		{
			try 
			{                                                // Exception Handling 
				int val=Integer.parseInt(t2.getText());                      // Stores the key to search 
				int pos=ob.indexsearch(val);
				if(pos!=0) {                                                // call the function which the searches the enetered node
					JOptionPane.showMessageDialog(this, "Found in the given list at position "+pos+" .");        // pops the message if found
				}
				else {
					JOptionPane.showMessageDialog(this, "Not found in the given list!!!");    // pops the message is not found
				}
				updateListDisplay();
			}
			catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "Please enter a valid Integer");             //Data Validation for the searching element input                  
			}
		}
		if (e.getSource() == b3) 
		{
			this.dispose();                                                             // Closes the current window
		}
		updateListDisplay();
		t1.setText("");
		t2.setText("");
	}
	private void updateListDisplay() 
	{
		ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();               
		PrintStream originalOut = System.out;                                                                                   
		System.setOut(new PrintStream(outputStream1));
		ob.display(head1);                                                                                                                         // Capture the output of the display() method
		System.out.flush();
		System.setOut(originalOut);                                          // Restore original System.out                            
		l2.setText("List : " + outputStream1.toString().trim());                         // Set the text of the label to the captured output
	}
}