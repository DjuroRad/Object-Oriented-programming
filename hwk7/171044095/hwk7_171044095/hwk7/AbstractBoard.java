package hwk7;

import java.io.*;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
/**
*This program is to be used for making NPuzzle game
*/
abstract public class AbstractBoard {
/**
*Implemented in Board1D and Board2D, returns the value at the given row and column
*/
    abstract public int cell( int i, int j );
/**
*Implemented in Board1D and Board2D
*/
    abstract protected void setPosition( int i, int j, int value );
/**
*Implemented in Board1D and Board2D
*/
    abstract protected void setUpData();
    protected int ROWS;
    protected int COLUMNS;
    static int number_of_boards = 0;
    protected int empty_col = 0, empty_row = 0;
    char last_move = 'S';
    int number_of_moves = 0;
    final protected static int DEF_ROWS = 3;
    final protected static int DEF_COLS = 3;
    final protected static int EMPTY_CELL = -1;
    final protected static int IMPOSSIBLE_MOVE = 0;
	/**
	*
	*Default constructor defaulting Rows and Columns to 3
	*
	*/
    public AbstractBoard(){
	
        this( DEF_ROWS, DEF_COLS );//defaulting the rows and columns
    }
	/**
	*
	*Two parameter constructor defaulting Rows and Columns if they are less than defaulted values
	*
	*/
        
    public AbstractBoard(int ROWS, int COLUMNS) {
	setSize( ROWS, COLUMNS);
        ++number_of_boards;
    }
	/**
	*
	*This method sets the side of the Board
	*
	*/

    public void setSize( int ROWS, int COLS ){
        if( ROWS < DEF_ROWS )
            this.ROWS = DEF_ROWS;
        else
            this.ROWS = ROWS;
        if( COLS < DEF_COLS )
            this.COLUMNS = DEF_COLS;
        else
            this.COLUMNS = COLS;
        setUpData();//just intialized the referance of the object for different classes
        reset();
    }
	/**
	*
	*Overriden toString method to be used to print our objects by passing a reference to it
	*
	*/

    @Override
    public String toString(){
        String to_return = new String();
        for( int i = 0; i < ROWS; ++i ){
            for( int j = 0; j < COLUMNS; ++j ) {
                to_return += cell( i, j ) != EMPTY_CELL ? cell( i, j ) + "\t" : "\t";
            }
            to_return += "\n\n";
        }
        return to_return;
    }
	/**
	*
	*Resets the board to the solution
	*
	*/

    public void reset(){
        for( int i = 0; i < ROWS; ++i )
            for( int j = 0; j < COLUMNS; ++j )
                setPosition( i, j, i*COLUMNS + j + 1 );
            empty_row = this.ROWS - 1;
            empty_col = this.COLUMNS - 1;
            setPosition( empty_row, empty_col, EMPTY_CELL );
            last_move = 'S';
            number_of_moves = 0;
    }
	/**
	*
	*Used to make moves via 'U'-up, 'D'-down, 'R'-right, 'L'-left commands
	*
	*/
    public boolean move( char move ){
        boolean valid_move = false;
        String error_message = "Error. Chosen move is not possible.";
        int target_row = empty_row, target_col = empty_col;
        switch ( move ) {
            case 'U':
                -- target_row;
                break;
            case 'D':
                ++ target_row;
                break;
            case 'L':
                -- target_col;
                break;
            case 'R':
                ++ target_col;
                break;
        }
        if( target_row >= 0 && target_row < ROWS && target_col >= 0 && target_col < COLUMNS && cell( target_row, target_col ) != IMPOSSIBLE_MOVE ){
            valid_move = true;
            int temp = cell( target_row, target_col );//swaping values using overridden protected () operator that returns a reference to data to be altered
            setPosition( target_row, target_col, EMPTY_CELL );
            setPosition( empty_row, empty_col, temp );
            empty_row = target_row;//updating empty_row and column also
            empty_col = target_col;
            ++number_of_moves;
            last_move = move;
        }
        else
            System.out.println( error_message );

        return valid_move;
    }
	/**
	*
	*Checks if the Board is solved
	*
	*/

    public boolean isSolved(){
        boolean solved = true;
        if( cell( ROWS -1, COLUMNS - 1 ) == EMPTY_CELL ){//avoids overhead in some cases
            for( int x = 1, i = 0; i < ROWS && solved; ++i ){
                for( int j = 0; j < COLUMNS && solved; ++j ){
                    if( cell(i,j) != EMPTY_CELL && cell( i,j ) != IMPOSSIBLE_MOVE ){//skips incrementing of right value for empty cell and impossible move when checking, self-explainatory
                        if( cell(i,j) != x )solved = false;
                        ++x;
                    }
                }
            }
        }
        else
            solved = false;

        return solved;
    }
	/**
	*
	*Returns the number of rows
	*
	*/
    public int getRows(){ return ROWS; }
	/**
	*
	*Returns the number of columns
	*
	*/
    public int getCols(){ return COLUMNS; }
	/**
	*
	*Returns the column of the empty_cell
	*
	*/
    public int getEmptyCol(){ return empty_col; }
	/**
	*
	*Returns the row of the empty_cell
	*
	*/
    public int getEmptyRow(){ return empty_row; }
	/**
	*
	*Returns the last move made, otherwise returns 'S'
	*
	*/
    public char getLastMove(){ return last_move; }
	/**
	*
	*Returns the number of moves
	*
	*/
    public int NumberOfMoves(){ return number_of_moves; }
	/**
	*
	*Returns the number of boards created so far
	*
	*/
    public int NumberOfBoards(){ return number_of_boards; }
	/**
	*
	*Returns the value of the move that is not possible( defaulted to 0 in the second homework
	*
	*/
    public int getImpossibleMoveValue(){ return IMPOSSIBLE_MOVE; }
	/**
	*
	*Returns the vaule which is used to represent the empty cell
	*
	*/
    public int getEmptyCellValue(){ return EMPTY_CELL; }
	/**
	*Compares two boards 
	*/
    public boolean Equals( AbstractBoard rightSide ){//i didn't overload the real equals that takes the object as parameter because this Equals is with an uppercase and java is case-sensitive
        boolean equal = true;
        if( COLUMNS == rightSide.getCols() && ROWS == rightSide.getRows() ){
            for( int i = 0; i < COLUMNS && equal; ++i ){//simple nested loop checking one by one every cell
                for( int j = 0; j < ROWS && equal; ++j ){
                    if( cell( i, j ) != rightSide.cell( i, j ) )
                    equal = false;
                }
            }
        }
        else
            equal = false;

        return equal;
    }
/**
*Method used to write to file using hwk2 standards
*/
    public void writeToFile( String file_name) throws IOException{
	
            String to_write = new String();

            String number = "00";
            String empty_cell_string = "bb";
            for( int i = 0; i < ROWS; ++ i ){
                for( int j = 0; j < COLUMNS; ++ j ){
                    if( cell(i,j) != EMPTY_CELL ){
                        to_write += cell( i, j );
                    }
                    else
                        to_write += empty_cell_string;
                    if( j != COLUMNS )
                        to_write += " ";
                }
                to_write += "\n";
            }
            BufferedWriter writer = new BufferedWriter( new FileWriter(file_name) );
            writer.write( to_write );
            writer.close();
        }
/**
*Reads from file using hwk2 standards
*/
    public void readFromFile( String file_name )throws IOException{

        File my_file = new File( file_name );
        Scanner reader = new Scanner( my_file );
        String row_of_text;
        int row_count = 0;
        String el;
        while( reader.hasNextLine() ) {
            reader.nextLine();
            ++row_count;
        }
        reader.close();
        reader = new Scanner( my_file );//going back to the beginning and reading it again

        int element_count = 0;
        while( reader.hasNext() ) {//finds the number of total elements inside the file
            reader.next();
            ++ element_count;
        }
        reader.close();
        setSize( row_count, element_count/row_count );//element_cout / ROWS is actually the number of columns
        //again go to the beginning of the file and read element by element and at the same time update the puzzle
        reader = new Scanner( my_file );
        final String impossible_move = "00";
        String empty_cell = "bb";
        int i = 0, j = 0;
        String element;
        while( reader.hasNext() ){
            element = reader.next();
            if( !element.equals( empty_cell ) ){
                    setPosition( i, j,  parseInt( element ) );
            }
            else{
                setPosition( i, j, EMPTY_CELL );
                empty_row = i;
                empty_col = j;
            }
            ++j;//increment the position
            if( j == COLUMNS ){//update accordingly
                j = 0;
                ++i;
            }
        }
        reader.close();
        number_of_moves = 0;//reseting it to initial value
    }
    /**
    *Returns the value in the cell by providing row and columns as parameters
    */
  	/**
	*
	*Checks if the moves to the solution are valid by passing an array of reference to the Abstract Board
	*
	*/
    public static boolean valid_sequence_of_moves( AbstractBoard boards[] ){
        boolean valid = true;//assumption
        int size = boards.length;
        int prev_empty_row, prev_empty_col;//we need to be able to check which move was made
        int current_empty_row, current_empty_col;//we need to be able to check which move was made
        if( size > 0 ){//returning false if the size is 0
            if( boards[ size -1 ] . isSolved() ){//after last move it needs to be solved otherwise we immediatelly know that this arrray won't lead to the solution
                for( int i = 0; valid && i < size - 1 ; ++i ){//now checking for validity of every next move
                    int j = i + 1;//checking with the next_move board
                    if( boards[ i ] . getRows() == boards[ j ] . getRows() && boards[ i ] . getCols() == boards[ j ] . getCols() ){//making sure rows and cols are the same first
                        //checking if the empty cell moved properly to its new position( verticlaly or horizontally )
                        if( ( boards[ i ] . getEmptyCol() == boards[ j ] . getEmptyCol() &&
                                ( boards[ j ] . getEmptyRow() == boards[ i ] . getEmptyRow() + 1 || boards[ j ] . getEmptyRow() == boards[ i ] . getEmptyRow() - 1 ) ) || //this checks if it moves up/down
                                ( boards[ i ] . getEmptyRow() == boards[ j ] . getEmptyRow() &&
                                        ( boards[ j ] . getEmptyCol() == boards[ i ] . getEmptyCol() + 1 || boards[ j ] . getEmptyCol() == boards[ i ] . getEmptyCol() - 1 ) )//this checks if it moves left/right
                        ){//if condition body here
                            if( boards[ i ].cell( boards[ j ] . getEmptyRow(), boards[ j ] . getEmptyCol() ) == IMPOSSIBLE_MOVE || boards[ j ].cell( boards[ i ] . getEmptyRow(), boards[ i ] . getEmptyCol() ) == IMPOSSIBLE_MOVE ) //indicates special case that would be able to appear only for puzzles read from file including 0s or impossible moves inside. Since we do not control two empty cells for equal numbers 0 in these places can occur yileding an incorrect result that would pass as correct if both of them are 0, but would avoid overhead if only one is 0
                                valid = false;//can not make move due to impossible move
                            //now checking if all the numbers are at the right position
                            for( int k = 0; k < boards[ i ] . getRows() && valid; ++ k ){//checking here if all other cells are at the same positions
                                for( int m = 0; m < boards[ i ] . getCols() && valid; ++ m ){
                                    if( !(k ==  boards[ i ] . getEmptyRow() && m == boards[ i ] . getEmptyCol() ) && !( k ==  boards[ j ] . getEmptyRow() && m == boards[ j ] . getEmptyCol() ) ){//here if there are for example 9 cells, we will check 7 of them. Since we know empty cell is at correct position( we already checked with the previous if ) we know that 8 of them are at the correct position meaning that the one position left will be correct position for the only position we did not check
                                        if( boards[ i ].cell( k, m ) != boards[ j ].cell( k, m ) ){//other numbers are not at their correct positions
                                            valid = false;
                                        }
                                    }
                                }
                            }
                        }else valid = false;//not a valid move, either not vertial or not horizontal move of the empty_cell
                    }else valid = false;//rows and cols are not the same
                }
            }else valid = false;//last element of the array is not solved
        }else valid = false;//board array is empty

        return valid;
    }
}
