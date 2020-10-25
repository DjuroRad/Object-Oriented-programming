import hwk7.AbstractBoard;
import hwk7.BoardArray1D;
import hwk7.BoardArray2D;

import java.util.Scanner;

public class Main {
    public static void main( String[] args ) {
        driver();
    }
    public static void driver(){
        int choice = 1;
        Scanner my_scanner = new Scanner( System.in );
        while( choice != 0 ){

            System.out.println("1 - driver_1d_array");
            System.out.println("2 - diver_2d_array");
            System.out.println("3 - driver_global_func");
            System.out.println("0 - quit");
            choice = my_scanner.nextInt();
            switch( choice ){
                case 1:
                    driver_1d();
                    break;
                case 2:
                    driver_2d();
                    break;
                case 3:
                    driver_global_func();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Please choose a valid option");
            }
        }
    }
    public static void driver_1d(){
        System.out.println("Executing driver_1D");
        BoardArray1D my_game = new BoardArray1D( 4, 4 );
        System.out.println(" Newly made board ");
        System.out.println( my_game );
        System.out.println("Move 'U' " );
        my_game.move( 'U' );
        System.out.println( my_game );
        System.out.println("Move 'R' " );
        my_game.move( 'R' );
        System.out.println( my_game );
        System.out.println("Move 'L' " );
        my_game.move( 'L' );
        System.out.println( my_game );
        System.out.println("Move 'L' " );
        my_game.move( 'L' );
        System.out.println( my_game );
        System.out.println("Move 'R' " );
        my_game.move( 'R' );
        System.out.println( my_game );
        System.out.println("Move 'D' " );
        my_game.move( 'D' );
        System.out.println( my_game );
        System.out.println(" Number of moves is " + my_game.NumberOfMoves() );
        System.out.println(" Last move is " + my_game.getLastMove() );
        System.out.println(" Number of boards is " + my_game.NumberOfBoards() );
        System.out.println( "Writing the board to file named tryout");
        try {
            my_game.writeToFile("tryout");
            System.out.println("Successfully written to file");
        }
        catch ( Exception e ){
            System.out.println("Exception cought while writting the file");
        }
        System.out.println("Reseting the board");
        my_game.reset();
        System.out.println( my_game );
        System.out.println("Number of moves after reseting the board " + my_game.NumberOfMoves() );
        System.out.println("Last move after reseting the board " + my_game.getLastMove() );;

        System.out.println("Checking if it is sloved");
        if( my_game.isSolved() )
            System.out.println("It is solved");
        else
            System.out.println("Board is not solved" );

        System.out.println("Making a new board");
        BoardArray1D my_game_2 = new BoardArray1D(4, 3);
        System.out.println( "Number of boards after declaring one new board is " + my_game.NumberOfBoards() );
        System.out.println(" Comparing these two boards ");
        System.out.println(" Board 1 ");
        System.out.println( my_game );
        System.out.println(" Board 2 ");
        System.out.println( my_game_2 );
        System.out.println( "Result of comparison is that they are " );
        if( my_game.equals(my_game_2))
            System.out.println( "equal" );
        else
            System.out.println("not equal" );

        System.out.println("Reading last saved file named tryout into the board_2");
        try {
            my_game_2.readFromFile("tryout");
            System.out.println("Successfully read from 'tryout file");
        }
        catch( Exception e ){
            System.out.println("Exception caought");
        }
        System.out.println("Board1: ");
        System.out.println(my_game);
        System.out.println("Board2: ");
        System.out.println(my_game_2);
        System.out.println("Changing the size of Board 1 to be 5x6");
        my_game.setSize(5,6);
        System.out.println("Board1: ");
        System.out.println(my_game);
        System.out.println("Board2: ");
        System.out.println(my_game_2);
        System.out.println("Testing the cell function");
        System.out.println(" at row 4 col 4(starting counting from 0) in board 1 there is: " + my_game.cell( 4,4 ));
        System.out.println(" at row 2 col 2(starting counting from 0) in board 1 there is: " + my_game_2.cell( 2,2 ));
	System.out.println(" Reading from a file provided in hwk2 ");
	try {
            my_game_2.readFromFile("map.txt");
            System.out.println("Successfully read from 'map.txt' file");
        }
        catch( Exception e ){
            System.out.println("Exception caought");
        }
        System.out.println("Printing it");
	System.out.println(my_game_2);
        System.out.println("Move 'U' ");	
	my_game_2.move('U');
	System.out.println(my_game_2);
        System.out.println("Move 'U' again");	
	my_game_2.move('U');
	System.out.println(my_game_2);
    }
    public static void driver_2d(){
        System.out.println("Executing driver_2D");
        BoardArray2D my_game = new BoardArray2D( 4, 4 );
        System.out.println(" Newly made board ");
        System.out.println( my_game );
        System.out.println("Move 'U' " );
        my_game.move( 'U' );
        System.out.println( my_game );
        System.out.println("Move 'R' " );
        my_game.move( 'R' );
        System.out.println( my_game );
        System.out.println("Move 'L' " );
        my_game.move( 'L' );
        System.out.println( my_game );
        System.out.println("Move 'L' " );
        my_game.move( 'L' );
        System.out.println( my_game );
        System.out.println("Move 'R' " );
        my_game.move( 'R' );
        System.out.println( my_game );
        System.out.println("Move 'D' " );
        my_game.move( 'D' );
        System.out.println( my_game );
        System.out.println(" Number of moves is " + my_game.NumberOfMoves() );
        System.out.println(" Last move is " + my_game.getLastMove() );
        System.out.println(" Number of boards is " + my_game.NumberOfBoards() );
        System.out.println( "Writing the board to file named tryout");
        try {
            my_game.writeToFile("tryout");
            System.out.println("Successfully written to file");
        }
        catch ( Exception e ){
            System.out.println("Exception cought while writting the file");
        }
        System.out.println("Reseting the board");
        my_game.reset();
        System.out.println( my_game );
        System.out.println("Number of moves after reseting the board " + my_game.NumberOfMoves() );
        System.out.println("Last move after reseting the board " + my_game.getLastMove() );;

        System.out.println("Checking if it is sloved");
        if( my_game.isSolved() )
            System.out.println("It is solved");
        else
            System.out.println("Board is not solved" );

        System.out.println("Making a new board");
        BoardArray2D my_game_2 = new BoardArray2D(4, 3);
        System.out.println( "Number of boards after declaring one new board is " + my_game.NumberOfBoards() );
        System.out.println(" Comparing these two boards ");
        System.out.println(" Board 1 ");
        System.out.println( my_game );
        System.out.println(" Board 2 ");
        System.out.println( my_game_2 );
        System.out.println( "Result of comparison is that they are " );
        if( my_game.equals(my_game_2))
            System.out.println( "equal" );
        else
            System.out.println("not equal" );

        System.out.println("Reading last saved file named tryout into the board_2");
        try {
            my_game_2.readFromFile("tryout");
            System.out.println("Successfully read from 'tryout file");
        }
        catch( Exception e ){
            System.out.println("Exception caought");
        }
        System.out.println("Board1: ");
        System.out.println(my_game);
        System.out.println("Board2: ");
        System.out.println(my_game_2);
        System.out.println("Changing the size of Board 1 to be 5x6");
        my_game.setSize(5,6);
        System.out.println("Board1: ");
        System.out.println(my_game);
        System.out.println("Board2: ");
        System.out.println(my_game_2);
        System.out.println("Testing the cell function");
        System.out.println(" at row 4 col 4(starting counting from 0) in board 1 there is: " + my_game.cell( 4,4 ));
        System.out.println(" at row 2 col 2(starting counting from 0) in board 1 there is: " + my_game_2.cell( 2,2 ));
	System.out.println(" Reading from a file provided in hwk2 ");
	try {
            my_game_2.readFromFile("map.txt");
            System.out.println("Successfully read from 'map.txt' file");
        }
        catch( Exception e ){
            System.out.println("Exception caought");
        }
        System.out.println("Printing it");
	System.out.println(my_game_2);
        System.out.println("Move 'U' ");	
	my_game_2.move('U');
	System.out.println(my_game_2);
        System.out.println("Move 'U' again");	
	my_game_2.move('U');
	System.out.println(my_game_2);
    }
    public static void driver_global_func(){
        System.out.println("Making 1D board");
        BoardArray1D board_array_1d = new BoardArray1D();
        System.out.println("Making 'U' move");
        board_array_1d.move( 'U' );
        System.out.println("Board 1D");
        System.out.println(board_array_1d);
        System.out.println("Making 2D board");
        BoardArray2D board_array_2d = new BoardArray2D();
        System.out.println("Board 2D:");
        System.out.println( board_array_2d );
        System.out.println("Making array of these two boards [0]=board 1D, [1]=board 2D");
        AbstractBoard arr[] = { board_array_1d, board_array_2d };
        System.out.println("Checking move validity");
        if( AbstractBoard.valid_sequence_of_moves( arr ) == true )
            System.out.println("Moves to the solution of these two boards are valid");
        else
            System.out.println("Moves to the solution are not valid");
        System.out.println("Now adding one more 1D baord 3x3");
        BoardArray1D added_element = new BoardArray1D();
        added_element.move('L');
        System.out.println("Board 1D(2)");
        System.out.println(added_element);
        AbstractBoard arr2[] ={ board_array_1d, board_array_2d, added_element };
        System.out.println("Making new arr2 of boards with following boards");
        System.out.println("Board 1D(1)");
        System.out.println(board_array_1d);
        System.out.println("Board 2D");
        System.out.println(board_array_2d);
        System.out.println("Board 1D(2)");
        System.out.println(added_element);
        System.out.println("Checking if the sequence is valid");
        if( AbstractBoard.valid_sequence_of_moves( arr2 ) )
            System.out.println("Moves to the solution of these three boards are valid");
        else
            System.out.println("Moves to the solution of these three boards are not valiid" );

        System.out.println("One more board to the [0] index( size of the arr is now 4" );
        BoardArray2D board_2d2 = new BoardArray2D( 5, 5 );
        System.out.println("Added board to [0] index is");
        System.out.println( board_2d2 );
        System.out.println("Checking move validity");
        AbstractBoard arr3[] = { board_2d2, board_array_1d, board_array_2d, added_element };
        if( AbstractBoard.valid_sequence_of_moves( arr3 ) )
            System.out.println( "Moves to solution of these 4 boards is valid" );
        else
            System.out.println("Moves to solution of these 4 boards is not valid");
        System.out.println("Making an array with 2 elements");
        AbstractBoard arr4[] = { board_2d2, board_array_1d };
        System.out.println("Boards in the array are");
        System.out.println("Board1");
        System.out.println(board_2d2);
        System.out.println("Board2");
        System.out.println(board_array_1d);
        System.out.println("Checking move validity");
        if( AbstractBoard.valid_sequence_of_moves( arr4) )
            System.out.println("Moves to the solution are valid");
        else
            System.out.println("Moves to the solution are not valid");

        System.out.println("Making an array with just one element in it");
        AbstractBoard arr5[] = { board_2d2 };
        System.out.println("Element of the array");
        System.out.println(board_2d2);
        System.out.println("Checking move validity");
        if( AbstractBoard.valid_sequence_of_moves( arr5) )
            System.out.println("Moves to the solution are valid");
        else
            System.out.println("Moves to the solution are not valid\n");

    }
}
