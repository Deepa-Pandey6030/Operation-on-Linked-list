package project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Has {
    // Node class to represent each element in the hash table
    class Node {
        int data; 														// Data stored in the node
        Node next; 														// Pointer to the next node 
        Node pre; 														// Pointer to the previous node 

        public Node(int data) 
		{
            this.data = data; // Assign the data value to the node
            this.next = null; // Initialize the next pointer to null
            this.pre = null; // Initialize the previous pointer to null
        }
    }

    Node[] table = new Node[10]; // Array representing the hash table with 10 slots

    // Method to add a new element to the hash table
    public void add(int data) {
        int index = data % 10;              // Hashing function to find index
        int begindex = index; // Store the starting index for checking full table

        // Find the next available slot in the hash table
        while (table[index] != null) {
            index = (index + 1) % 10; // Linear probing for the next index
            if (index == begindex) { // If we loop back to the start
                JOptionPane.showMessageDialog(null, "Hash table is full");
                return; // Exit if the table is full
            }
        }

        // Create a new node and insert it into the hash table
        Node newnode = new Node(data);
        table[index] = newnode; // Insert node at the found index
    }

    // Method to delete an element from the hash table
    public void delete(int data) {
        int index = data % 10; // Hashing function to find index
        int begindex = index; // Store the starting index

        // Search for the node to delete
        while (table[index] == null || table[index].data != data) {
            index = (index + 1) % 10; // Linear probing for the next index
            if (index == begindex) {
                JOptionPane.showMessageDialog(null, "Element not found");
                return; // Exit if the element is not found
            }
        }

        // Remove the node from the hash table
        table[index] = null; // Set the found index to null to delete the node
    }

    // Method to display the contents of the hash table
    public String display() {
        StringBuilder listDisplay = new StringBuilder("Hash table: ");
        for (int i = 0; i < 10; i++) {
            if (table[i] != null) // If there is a node at this index
                listDisplay.append(table[i].data).append(" "); // Append its data to the display string
        }
        return listDisplay.toString().trim(); // Return the formatted string
    }

    // Method for linear search in the hash table
    public int hashsearch(int item) {
        int index = item % 10; // Hashing function to find index
        int begindex = index; // Store the starting index

        // Search for the item in the hash table
        while (table[index] == null || table[index].data != item) {
            index = (index + 1) % 10; // Linear probing for the next index
            if (index == begindex || table[index] == null) // If we loop back to the start or find null
                return -1; // Item not found
        }
        return index; // Item found
    }
}

public class Hash extends JFrame implements ActionListener {
    Has no = new Has(); // Instance of the Has class for managing the hash table
    JLabel l1 = new JLabel("Enter data:"); // Label for data entry
    JTextField t1 = new JTextField(); // Text field for entering data
    JButton b1 = new JButton("Add element"); // Button to add element to hash table
    JLabel l2 = new JLabel("List: "); // Label to display the current list
    JLabel l3 = new JLabel("Search: "); // Label for search operation
    JTextField t2 = new JTextField(); // Text field for searching
    JButton b3 = new JButton("Search"); // Button to initiate search
    JButton b4 = new JButton("Back"); // Button to exit the current window

    public Hash() {
        // Set up the JFrame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(700, 700);
        setVisible(true);
        setTitle("Hashing function");

        // Set bounds for the components
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

        // Add components to the JFrame
        add(l1);
        add(t1);
        add(b1);
        add(l2);
        add(l3);
        add(t2);
        add(b3);
        add(b4);

        // Register action listeners for buttons
        b1.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) 
	{
        // Action for adding an element
        if (e.getSource() == b1) 
		{
            try {
                int value = Integer.parseInt(t1.getText()); // Parse the input value
                no.add(value); // Add the value to the hash table
                updateListDisplay(); // Update the displayed list
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid Integer"); // Error message for invalid input
            }
        }

        // Action for searching an element
        if (e.getSource() == b3) {
            try 
			{
                int item = Integer.parseInt(t2.getText()); // Parse the search value
                int index = (no.hashsearch(item)); 
				if(index != -1)
				{
                    JOptionPane.showMessageDialog(this, "Found in the given list at index "+index); // Message if found
                } 
				
				else 
				{
                    JOptionPane.showMessageDialog(this, "Not found in the given list!!!"); // Message if not found
                }
                updateListDisplay(); // Update the displayed list
            } 
			
			catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid Integer"); // Error message for invalid input
            }
        }

        // Action for closing the window
        if (e.getSource() == b4) {
            this.dispose(); // Closes the current window
        }

        t1.setText(""); // Clear the input field
    }

    // Method to update the display of the hash table
    private void updateListDisplay() {
        l2.setText(no.display()); // Update the label to show current hash table contents
    }
}