package ok;

import java.util.Random;


public class Grid{
	
	private boolean  [ ][ ] bombGrid  ;
	private int [ ][ ] countGrid ;
	private int numRows;
	private int numColumns ;
	private int numBombs ;
	Random rd = new Random();
	
	
	//constructor
	public Grid() {
		this.numRows = 10;
		this.numColumns = 10;
		this.numBombs = 25;
		this.bombGrid = new boolean[this.numRows][this.numColumns];
		this.countGrid = new int [this.numRows][this.numColumns];
		createBombGrid();
		createCountGrid();
	}
	
	public Grid(int rows, int columns) {
		this.numRows = rows;
		this.numColumns = columns;
		this.numBombs = 25;
		this.bombGrid = new boolean[this.numRows][this.numColumns];
		this.countGrid = new int [this.numRows][this.numColumns];
		createBombGrid();
		createCountGrid();
	}
	public Grid(int rows, int columns, int numBombs) {
		this.numRows = rows;
		this.numColumns = columns;
		this.numBombs = numBombs;
		this.bombGrid = new boolean[this.numRows][this.numColumns];
		this.countGrid = new int [this.numRows][this.numColumns];
		createBombGrid();
		createCountGrid();		
	}
	
	//getters
	
	public int getNumRows() {
		return this.numRows;
	}
	public int getNumColumns() {
		return this.numColumns;
	}
	
	public int getNumBombs() {
		return this.numBombs;	
	}
	
	public boolean [ ][ ] getBombGrid() {
		boolean clone[][] = new boolean[this.numRows][this.numColumns];
		 for(int i = 0 ; i < this.numRows ; i++) // the whole row
			{
				for(int j = 0 ; j < this.numColumns; j++) //the whole column
					{
					clone[i][j] = this.bombGrid[i][j];
					}
			}
		 return clone;
		 }
	
	public int [][] getCountGrid(){
		int clone[][] = new int[this.numRows][this.numColumns];
		for(int i = 0 ; i < this.numRows ; i++) // the whole row
		{
			for(int j = 0 ; j < this.numColumns; j++) //the whole column
				{
				clone[i][j] = this.countGrid[i][j];
				}
		}
	 return clone;
	}
	
	//check if there is a bomb at the location
	public boolean isBombAtLocation(int row, int column) {
		if (bombGrid[row][column] == true) {
			return true;
		}
		return false;
	}
	public int getCountAtLocation(int row, int column) {
		return countGrid[row][column];
	}
	
	
	//creates the bombgrid
	private void createBombGrid() 
	{
		
		int counter = 0	;//to count the amount of bombs
		
		//while loop just incase the loop went to the whole grid without filling up the right amount of bombs
		while (counter != this.numBombs){
			
		
		 for(int i = 0 ; i < this.numRows ; i++) // the whole row
		{
			for(int j = 0 ; j < this.numColumns; j++) //the whole column
				{
				
					if (bombGrid[i][j] != true ) //leave the place alone if the bomb is already inside
						{

						if(counter != this.numBombs) //stop adding bombs if the counter is equal to bombs
						{
							bombGrid[i][j] = rd.nextBoolean();//set the random for bomb making	
						
							if (bombGrid[i][j] == true) //to add a bomb to the counter
							{
							counter++; 
							}
						
						}
						else  //if counter is full but the space is null, make it false
						{
							bombGrid[i][j] = false;
						}
					}
				}
				
		}}
		  
		}
		
	
	private void createCountGrid() {
		
		//used to check if the number is true and the blocks around it
		 for(int i = 0 ; i < this.numRows ; i++) // the whole row
			{
				for(int j = 0 ; j < this.numColumns; j++) //the whole column
				{
				int count = 0; //use to set the count and 0 is the default number
				try {
					if (bombGrid[i][j] == true) 
					{
						count++;
					}}
					catch(ArrayIndexOutOfBoundsException e) {
					
					}
				try {
					if (bombGrid[i+1][j] == true) 
					{
						count++;
					}}
					catch(ArrayIndexOutOfBoundsException e) {
					
					}
				try {
					if (bombGrid[i][j+1] == true) 
					{
						count++;
					}}
					catch(ArrayIndexOutOfBoundsException e) {
					
					}
				try {
					if (bombGrid[i+1][j+1] == true) 
					{
						count++;
					}}
					catch(ArrayIndexOutOfBoundsException e) {
					
					}
				try {
					if (bombGrid[i-1][j] == true) 
					{
						count++;
					}}
					catch(ArrayIndexOutOfBoundsException e) {
					
					}
				try {
					if (bombGrid[i][j-1] == true) 
					{
						count++;
					}}
					catch(ArrayIndexOutOfBoundsException e) {
					
					}
				try {
					if (bombGrid[i-1][j-1] == true) 
					{
						count++;
					}}
					catch(ArrayIndexOutOfBoundsException e) {
					
					}
				try {
					if (bombGrid[i+1][j-1] == true) 
					{
						count++;
					}}
					catch(ArrayIndexOutOfBoundsException e) {
					
					}
				try {
					if (bombGrid[i-1][j+1] == true) 
					{
						count++;
					}}
					catch(ArrayIndexOutOfBoundsException e) {
					
					}
					
					countGrid[i][j] = count;
				
				

				}
		}
	}
	
	
}
