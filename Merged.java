package project;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.io.*;

class Mer{
class node{                                   //class to create a node 
int data;                                     // data part of doubly linked list
node next;                                  // reference to connect with the next node 
public node(int data){
this.data = data;                          // function to assign data part to the node 

this.next = null;                       // Initialize the next pointer to null, indicating the end of the list
}
}
public node add(int data,node head){      // method which adds the node to the doubly linked list
node newnode = new node(data);        //Creating a new node 
if(head==null){
head = newnode;                        //creating a first node 
}
else{
node p=head;
           // traverses the list to reach the last node to add
while(p.next!=null){
 p = p.next;                               
}
p.next = newnode;         //stores the address of newnode in the last 
}
return head;           //returns the head of list
}

 public void display(node head){       //method to display the lists
node ptr = head;
if(head == null){
System.out.println("List is empty!");        // when is the list is empty
}
else{
while(ptr!=null){                   // traverses till last node 
System.out.print(ptr.data+" ");        
ptr =ptr.next;
}
}
}
public node merge(node head1, node head2) {
node head3 = null;
node p = null;
// return null when both lists are empty
if (head1 == null) return head2;
if (head2 == null) return head1;
while (head1 != null && head2 != null) {
node newnode;                // declaring new node for the merged list
 if (head1.data <= head2.data) {             
newnode = new node(head1.data);    					//adds the head1 data to the merged list as it is greater
head1 = head1.next;                //moves to next node 
} else {
 newnode = new node(head2.data);                   //adds the head1 data to the merged list as it is greater
head2 = head2.next;                  //moves to next node
}
 if (head3 == null) {
head3 = newnode; 						// creating first node of the merged list
 p = head3;
} else {
p.next = newnode;                      //keeps on adding the remaining nodes to the merged list
p = p.next;
}
}
while (head1 != null) {     
p.next = new node(head1.data);           // loop until the end of the first linked list(head1)
p = p.next;
head1 = head1.next;
}
while (head2 != null) {
p.next = new node(head2.data);          //loop until the end of the first linked list(head1)
p = p.next;
head2 = head2.next;
}
return head3;                         //returns the head of the merged list
}
public void sort(node head)					// method to sort the lists in every call
{
node ptr=head,q;                         // initializing the p to head of the list 
while(ptr.next!=null)
{
q=ptr.next;
// performs the bubble sort
while(q!=null)
{
if(ptr.data>q.data){
int temp=ptr.data;
ptr.data=q.data;
q.data=temp;
}
q=q.next;
}
ptr=ptr.next;
}
}
}


public class Merged extends JFrame implements ActionListener{			//merged class extends the action listener 
Mer.node head1=null;            // initializing the head of the first list 
Mer.node head2=null;       // initializing the head of the second list 
Mer.node head3=null;       // initializing the head of the merged list 
Mer ob = new Mer();
JLabel l1 = new JLabel("Enter data :");
JTextField t1 = new JTextField();
JButton b1 = new JButton("Add node");
JLabel l2 = new JLabel("List 1: ");
JLabel l3= new JLabel("Enter data :");
JTextField t2 = new JTextField();
JButton b2 = new JButton("Add node");
JLabel l4 = new JLabel("List 2: ");
JButton b3 = new JButton("Press to merge");
JLabel l5 = new JLabel("Merged List: ");
JButton b4 = new JButton("Back");

public Merged(){
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setLayout(null);
setSize(700, 700);
setVisible(true);
setTitle("Merging of list");
l1.setBounds(30, 20, 800, 40);
 l1.setFont(new Font("Arial", Font.PLAIN, 24));
t1.setBounds(180, 20, 100, 40);
t1.setFont(new Font("Arial", Font.PLAIN, 18));
b1.setBounds(300, 20, 230, 40);
b1.setFont(new Font("Arial", Font.PLAIN, 24));
l2.setBounds(30, 80, 800, 40);
l2.setFont(new Font("Arial", Font.PLAIN, 24));
l3.setBounds(30, 140, 800, 40);
l3.setFont(new Font("Arial", Font.PLAIN, 24));
t2.setBounds(180, 140, 100, 40);
t2.setFont(new Font("Arial", Font.PLAIN, 18));
b2.setBounds(300, 140, 230, 40);
b2.setFont(new Font("Arial", Font.PLAIN, 24));
l4.setBounds(30, 200, 800,40);
l4.setFont(new Font("Arial", Font.PLAIN, 24));
b3.setBounds(150,260,300,40);
b3.setFont(new Font("Arial", Font.PLAIN, 24));
l5.setBounds(30,320,800,40);
l5.setFont(new Font("Arial", Font.PLAIN, 24));
b4.setBounds(40, 600, 250, 40);
b4.setFont(new Font("Arial", Font.PLAIN, 24));

add(l1);
add(t1);
add(b1);
add(l2);
add(l3);
add(t2);
add(b2);
add(l4);
add(b3);
add(l5);
add(b4);
b1.addActionListener(this);
b2.addActionListener(this);
b3.addActionListener(this);
b4.addActionListener(this);
}
public void actionPerformed(ActionEvent e){
if(e.getSource()==b1){
try{
int value = Integer.parseInt(t1.getText());     
head1=ob.add(value,head1);
ob.sort(head1);
updateListDisplay();
}
catch(NumberFormatException ex){
JOptionPane.showMessageDialog(this, "Please enter a valid Integer");
}
}
if(e.getSource()==b2){
try{
int value = Integer.parseInt(t2.getText());
head2=ob.add(value,head2);
ob.sort(head2);
updateListDisplay();
}
catch(NumberFormatException ex){
JOptionPane.showMessageDialog(this, "Please enter a valid Integer");
}
}
if(e.getSource()==b3){
head3=ob.merge(head1,head2);
updateListDisplay();
}
if (e.getSource() == b4) {
    this.dispose(); // Closes the current window
    }
t1.setText("");
t2.setText("");
}
private void updateListDisplay(){
ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
PrintStream originalOut = System.out;
System.setOut(new PrintStream(outputStream1));
ob.display(head1);
System.out.flush();
System.setOut(originalOut);
l2.setText("List 1: " + outputStream1.toString().trim());

ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream();
System.setOut(new PrintStream(outputStream2));
ob.display(head2);
System.out.flush();
System.setOut(originalOut);
l4.setText("List 2: " + outputStream2.toString().trim());
if (head3 != null) {
ByteArrayOutputStream outputStream3 = new ByteArrayOutputStream();
System.setOut(new PrintStream(outputStream3));
ob.display(head3);
System.out.flush();
System.setOut(originalOut);
l5.setText("Merged List: " + outputStream3.toString().trim());
} 
else {
l5.setText("Merged List: (empty)");
}
}
}