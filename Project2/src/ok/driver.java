//https://www.youtube.com/watch?v=1NlxKCu1720&feature=youtu.be

package ok;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.*;

class driver extends JFrame {
	private JButton[][] button;
	private int row;
	private int column;
	private Grid play;
	private int count;
	private int numOfBombs;
	
	
	//constructor
	public driver(int r, int c) {
		this.row = r; //for the amount of row
		this.column = c; // for the amount of column
		play = new Grid(r,c); //setting up the grid array
		this.numOfBombs = play.getNumBombs(); // to get the amount of bombs
		this.count = (row * column) - numOfBombs; //to set up the win condition clearing the game without hitting the bombs
		
		
		//setting the Jframe with a gridlayout
		JFrame frame = new JFrame("MineSweeper");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		JPanel bombgrid = new JPanel();
		bombgrid.setLayout(new GridLayout(row, column)); //setting the layout to the amount of row*column
		
		//create the bombs for the grid
		buttonCreator(play.getNumRows(), play.getNumColumns(), bombgrid, frame);
		
		 mainPanel.add(bombgrid);  
	     frame.add(mainPanel);
	     frame.setSize(500,500);
	     frame.setVisible(true);
		
		
		
	}
	
	public  void buttonCreator (int row, int column, JPanel bombgrid, JFrame frame) {
		 
		button = new JButton[play.getNumRows()][play.getNumColumns()];  //setting the length of the buttons array
		//adding all the buttons
		for (int i = 0; i < row; i++) {
         for (int j = 0; j < column; j++) {
            	button[i][j] = new JButton();
            	
            	int k = i;//for the actionlistener
            	int v = j;//for the actionlistener
            	
            	//when button is clicked
            	button[k][v].addActionListener(new ActionListener() {
            		@Override
            		public void actionPerformed(ActionEvent event) {
            		
            		if (play.isBombAtLocation(k, v) == true) //if the placement for button has a bomb	
            		{
                	button[k][v].setText("B"); //set the button as B
                	  //to reveal the whole grid since you lost
                	for (int i = 0; i < row; i++) 
                		{
                				for (int j = 0; j < column; j++) 
                				{
                					if(play.isBombAtLocation(i, j) == true) 
                					{
                						button[i][j].setText("B");  //reveal all the bombs
                					}
                        	
                					if(play.isBombAtLocation(i, j) != true) 
                					{
                						button[i][j].setText(String.valueOf(play.getCountAtLocation(i, j))); //use to reveal the count for the nonbombs and set the text for the button
                        	
                					}
                				}
                		}
                	
                	//ask to reset the game when you lost or close the game
                	int reset =JOptionPane.showConfirmDialog(frame,"You lost, do you want to play again?");  
                	if (reset == JOptionPane.YES_OPTION)
                    	{	
                			new driver(row, column);
                    	}
                	if (reset == JOptionPane.NO_OPTION)
                	{	
                		System.exit(0);
                	}
                

            		}
            		
            		//if the button doesn't have a bomb
            		//the reason for the "isBlank() is so we won't update if the button already has a text
            		else {
            			if (button[k][v].getText().isBlank()) {
            				button[k][v].setText( String.valueOf(play.getCountAtLocation(k, v))); //reveal the count if the placement isn't true
                			count--;
            			}
            			
            			
            			//when count is 0, reveals the surrounding zeros around the current button clicked.
            			if(play.getCountAtLocation(k, v) == 0) {
            				try {
            					if (play.getCountAtLocation(k+1, v)== 0 && button[k+1][v].getText().isBlank()) 
            					{
            						button[k+1][v].setText( String.valueOf(play.getCountAtLocation(k+1, v)));
            						count--;
            					}}
            					catch(ArrayIndexOutOfBoundsException e) {}
            				try {
            					if (play.getCountAtLocation(k, v+1)== 0 && button[k][v+1].getText().isBlank()) 
            					{
            						button[k][v+1].setText( String.valueOf(play.getCountAtLocation(k, v+1)));
            						count--;
            					}}
            					catch(ArrayIndexOutOfBoundsException e) { }
            				try {
            					if (play.getCountAtLocation(k+1, v+1)== 0 && button[k+1][v+1].getText().isBlank()) 
            					{
            						button[k+1][v+1].setText( String.valueOf(play.getCountAtLocation(k+1, v+1)));
            						count--;
            					}}
            					catch(ArrayIndexOutOfBoundsException e) {
            					
            					}
            				try {
            					if (play.getCountAtLocation(k-1, v)== 0 && button[k-1][v].getText().isBlank()) 
            					{
            						button[k-1][v].setText( String.valueOf(play.getCountAtLocation(k-1, v)));
            						count--;
            					}}
            					catch(ArrayIndexOutOfBoundsException e) {}
            				try {
            					if (play.getCountAtLocation(k, v-1)== 0 && button[k][v-1].getText().isBlank()) 
            					{
            						button[k][v-1].setText( String.valueOf(play.getCountAtLocation(k, v-1)));
            						count--;
            					}}
            					catch(ArrayIndexOutOfBoundsException e) {}
            				try {
            					if (play.getCountAtLocation(k-1, v-1)== 0 && button[k-1][v-1].getText().isBlank()) 
            					{
            						button[k-1][v-1].setText( String.valueOf(play.getCountAtLocation(k-1, v-1)));
            						count--;
            					}}
            					catch(ArrayIndexOutOfBoundsException e) { }
            				try {
            					if (play.getCountAtLocation(k+1, v-1)== 0 && button[k+1][v-1].getText().isBlank()) 
            					{
            						button[k+1][v-1].setText( String.valueOf(play.getCountAtLocation(k+1, v-1)));
            						count--;
            					}}
            					catch(ArrayIndexOutOfBoundsException e) {      }
            				try {
            					if (play.getCountAtLocation(k-1, v+1)== 0 && button[k-1][v+1].getText().isBlank()) 
            					{
            						button[k-1][v+1].setText( String.valueOf(play.getCountAtLocation(k-1, v+1)));
            						count--;
            					}}
            					catch(ArrayIndexOutOfBoundsException e) {
            					
            					}
            				
            			}
            			System.out.println("Amount of bombs left: " + count);
            			
            			
            			//when you clear out all of the space without hitting a bomb
            			//giving the option to play again or close the game
            			if( count == 0) {
            				int reset =JOptionPane.showConfirmDialog(frame,"You won, do you want to play again?");  
                        	if (reset == JOptionPane.YES_OPTION)
                            	{	
                        			new driver(row, column);
                            	}
                        	if (reset == JOptionPane.NO_OPTION)
                        	{	
                        		System.exit(0);
                        	}
            			}
            			
            		}
            		}});
            	
            	bombgrid.add(button[i][j]); //adding the button with all of its content to the grid
                
            }
        }
	}
	
	public static void main(String[] args) {
   Scanner scan = new Scanner(System.in); 
   //add the amount of rows
    System.out.println("give me the amount of rows that is over 5.");
    int row = scan.nextInt();	
    //add the amount of columns
    System.out.println("give me the amount of column that is over 5.");
    int column = scan.nextInt();
    	
    new driver(row,column);
    
    }
}