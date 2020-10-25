#include <iostream>
#include <cstdlib>
#include <ctime>
#include <fstream>
#define EMPTY_CELL -1
using namespace std;

typedef enum{ d, u, l, r, none, s, q, i } direction;//d - down, u - up, l - left, r - right, q - quit, i - intelligent move

bool validPosition( int row, int col, int ROWS, int COLUMNS );
int** setSize( int* COLUMNS, int* ROWS );
void setSolvablePuzzle( int** puzzle, int ROWS, int COLUMNS, int* empty_row, int* empty_col );
int calculateInversions( int** puzzle, int ROWS, int COLUMNS );
bool isSolvablePuzzle( int** puzzle, int ROWS, int COLUMNS, int empty_row );
void changePuzzleSolvability( int** puzzlle, int ROWS, int COLUMNS, int empty_row );
void swapRandomPair( int** puzzle, int ROWS, int COLUMNS );
int manhattanDistance( int** puzzle, int ROWS, int COLUMNS );
int distanceToGoal(int row, int col, int num, int ROWS, int COLUMNS );
bool isValidNum( char c, int MIN );
void printPuzzle( int** puzzle, int ROWS, int COLUMNS );
void emptyOutPuzzle( int** puzzle, int ROWS, int COLUMNS );
void updatePuzzle( int empty_row, int empty_col, int new_row, int new_col, int** puzzle );
direction setIntelligentMove( int** puzzle, int ROWS, int COLUMNS, int empty_row, int empty_col );

bool makeMove( int** puzzle, int ROWS, int COLUMNS, int* empty_row, int* empty_col, int* move_count );
direction getMoveFromUser();

int main(){
  srand( time( NULL ) );//for generating different random numbers every time

  int count = 0;
  int COLUMNS = 0, ROWS = 0;
  int** puzzle = setSize( &COLUMNS, &ROWS );//sets the size of the puzzle according to user input
  int empty_row = 0, empty_col = 0;
  bool quit = false;
  string user_notification = "Exiting ...";
  string user_notification2 = "Your initial random board is: ";
  string user_move_notification = "Your move :";
  string user_success = "Problem Solved!";
  string number_of_moves = "Total number of moves ";
  setSolvablePuzzle( puzzle, ROWS, COLUMNS, &empty_row, &empty_col );

  //Game loop in here
  cout << user_notification2 << endl;
  printPuzzle( puzzle, ROWS, COLUMNS );
  while( !quit ){
    cout << user_move_notification << endl;
    if( !makeMove( puzzle, ROWS, COLUMNS, &empty_row, &empty_col, &count ) ){//make move returns false on quitting the game
      cout << number_of_moves << count << endl;
      cout << user_notification << endl;
      quit = true;
    }
    else  printPuzzle( puzzle, ROWS, COLUMNS );

    if( manhattanDistance( puzzle, ROWS, COLUMNS ) == 0 ){//checks if user finished the game. At this point every tile will be at its own position which would mean user succesfully finished the game
      cout << user_success << endl;
      cout << number_of_moves << count << endl;
      quit = true;
    }
  }
  emptyOutPuzzle( puzzle, ROWS, COLUMNS );//freeing memory reserved for our puzzle

}

int** setSize( int* COLUMNS, int* ROWS ){
  int** puzzle = NULL;
  const int MIN = 3;//Minimum input
  const int CORRECT_INPUT_LENGTH = 1;
  *ROWS = *COLUMNS = 0;
  string input;//taking the whole line from the user so that I could manipulate the user input if there are errors
  string error_message = "Please only include 1 integer digit bigger than 2 ( 3, 4, 5 ... 9 ) in your input";
  string question_for_user = "Please enter the problem size";
  do{
    cout << question_for_user << endl;
    getline( cin, input );
    if( isValidNum( input[0], MIN ) && input.length() == CORRECT_INPUT_LENGTH ){//Allocating memory upon a valid input
      *ROWS = *COLUMNS = input[0] - '0';
      puzzle = ( int** )malloc( ( *ROWS ) * sizeof( int* ) );
      for( int i = 0; i < *ROWS; i++ ) puzzle[i] = ( int* )malloc( ( *COLUMNS ) * sizeof( int ) );
    }
    else cerr << error_message << endl;
  }while( *ROWS == 0 );//min input is 3 so rows can't be 0


  return puzzle;
}
//this function is also used for suffling
void setSolvablePuzzle( int** puzzle, int ROWS, int COLUMNS, int* empty_row, int* empty_col ){//making already allocated puzzle solvable
  //making ordered puzzle( with final positions )
  for( int i = 0, num = 1; i < ROWS; i++ ){
    for( int j = 0; j < COLUMNS; j++, num++ ) puzzle[ i ][ j ] = num;
  }
  puzzle[ ROWS - 1 ][ COLUMNS - 1 ] = EMPTY_CELL;

  //shuffling the array ROWS*COLUMNS times
  for( int i = 0; i < ROWS*COLUMNS; i++ )
    swapRandomPair( puzzle, ROWS, COLUMNS );//this function swaps two pair values in the puzzle randomly
  // setting the empty row and the empty column
  *empty_row = -1;
  for( int i = 0; i < ROWS && *empty_row == -1 ; i++ ){//find the empty row so that we could set its position
    for( int j = 0; j < COLUMNS; j++ ){
      if( puzzle[ i ][ j ] == EMPTY_CELL ){
        *empty_row = i;
        *empty_col = j;
        break;
      }
    }
  }
  //making it solvable, by changing the number of inversions
  if( !isSolvablePuzzle( puzzle, ROWS, COLUMNS, *empty_row ) )
    changePuzzleSolvability( puzzle, ROWS, COLUMNS, *empty_row );
  //making the puzzle not solved if it already is so by accident after doing this
  if( manhattanDistance( puzzle, ROWS, COLUMNS ) == 0 ){
    //moving the empty_cell randomly backwards for more than 1 move
    int rand_row = rand() % ROWS;
    int rand_col = rand() % COLUMNS;
    if( rand_row == ROWS - 1 && rand_col == COLUMNS - 1 ) -- rand_row;// since empty cell's row has to be max row position when solved it means we can go backwards without checking if that empty cell's value is 0( minimum is 2 and that is for 3x3 puzzle )
    int temp = puzzle[ rand_row ][ rand_col ];
    puzzle[ rand_row ][ rand_col ] = puzzle[ *empty_row ][ *empty_col ];
    puzzle[ *empty_row ][ *empty_row ] = temp;
    *empty_row = rand_row;
    *empty_col = rand_col;
  }

  return;
}

void swapRandomPair( int** puzzle, int ROWS, int COLUMNS ){
  int rand_row = rand() % ROWS;
  int rand_col = rand() % COLUMNS;
  int rand_row2 = rand() % ROWS;
  int rand_col2 = rand() % COLUMNS;
  int temp = puzzle[ rand_row ][ rand_col ];
  puzzle[ rand_row ][ rand_col ] =  puzzle[ rand_row2 ][ rand_col2 ];
  puzzle[ rand_row2 ][ rand_col2 ] = temp;

  return;
}
//makePuzzleSolvable is acutally changing sol
void changePuzzleSolvability( int** puzzle, int ROWS, int COLUMNS, int empty_row ){//swaping place of two numbers inside the puzzle so that its inversions are altered so that it is made solvable
  int rand_row = rand() % ROWS;
  int rand_col = rand() % COLUMNS;
  int temp = 0;
  if( rand_row == empty_row ){//changing the place with the empty cell won't change parity of the number of inversions
    if( rand_row < ROWS - 1 ) rand_row ++;
    else rand_row --;
  }
  if( rand_col == COLUMNS - 1 ) rand_col --;
  temp = puzzle[ rand_row ][ rand_col ];
  puzzle[ rand_row ][ rand_col ] = puzzle[ rand_row ][ rand_col + 1 ];
  puzzle[ rand_row ][ rand_col + 1 ] = temp;

  return;
}

bool isSolvablePuzzle( int** puzzle, int ROWS, int COLUMNS, int empty_row ){
  bool solvable = false;
  int num_of_inversions = calculateInversions( puzzle, ROWS, COLUMNS );
  if( COLUMNS % 2 != 0 && num_of_inversions % 2 == 0 ) solvable = true;
  else if( COLUMNS % 2 == 0 ){
    if( num_of_inversions % 2 != 0 && empty_row % 2 == 0 ) solvable = true;
    if( num_of_inversions % 2 == 0 && empty_row % 2 != 0 ) solvable = true;
  }

  return solvable;
}

int calculateInversions( int** puzzle, int ROWS, int COLUMNS ){
  int num_of_inversions = 0;
  const int total_elements = ROWS * COLUMNS - 1;
  int puzzle_1d[ total_elements ];

  for( int i = 0, i_1d = 0; i < ROWS; i++ ){//setting the 1d array from which the num of inversions would be calculated
    for( int j=0; j < COLUMNS; j++, i_1d++ ){
      if( puzzle[ i ][ j ] != EMPTY_CELL ) puzzle_1d[ i_1d ] = puzzle[ i ][ j ];
      else i_1d--;
    }
  }

  for( int i = 0; i < total_elements - 1; ++ i ){//calculating the number of inversions
    for( int j = i + 1; j < total_elements; ++ j ){
      if( puzzle_1d[ i ] > puzzle_1d[ j ] ) ++ num_of_inversions;
    }
  }

  return num_of_inversions;
}

bool isValidNum( char c, int MIN ){
  bool res = false;
  char min = MIN + '0';
  if( c >= min && c <= '9') res = true;// >= '3' since our minimum puzzle board will be 3x3

  return res;
}

void printPuzzle( int** puzzle, int ROWS, int COLUMNS ){
  for( int i = 0; i < ROWS; i++ ){
    for( int j = 0; j < COLUMNS; j++ ){
      if( puzzle [ i ][ j ] != EMPTY_CELL ) cout << puzzle[ i ][ j ] << "\t";
      else cout << " " << "\t";
    }
    cout << endl << endl << endl << endl << endl;//used for indentation only
  }
}

int manhattanDistance( int** puzzle, int ROWS, int COLUMNS ){
  int manhattan_distance = 0;
  // calculating manhattan distance
  for( int i = 0; i < ROWS; ++i ){
    for( int j = 0; j < COLUMNS; ++j ){
      if( puzzle[ i ][ j ] != EMPTY_CELL ) manhattan_distance += distanceToGoal( i, j, puzzle[ i ][ j ], ROWS, COLUMNS );
    }
  }

  return manhattan_distance;
}

int distanceToGoal(int row, int col, int num, int ROWS, int COLUMNS ){//this can be done like this because we know the puzzle is ordered
  int distance = 0, row_goal = 0, col_goal = 0, row_diffrence = 0, col_diffrence  = 0;
  int i = 0;
  while( num > COLUMNS * ( i + 1 ) ) ++i;
  row_goal = i;
  col_goal = num - ( COLUMNS * i + 1 ) ;
  row_diffrence = row_goal - row;
  col_diffrence = col_goal - col;
  if( row_diffrence < 0 ) row_diffrence *= -1;
  if( col_diffrence < 0 ) col_diffrence *= -1;
  distance += row_diffrence + col_diffrence;

  return distance;
}

void emptyOutPuzzle( int** puzzle, int ROWS, int COLUMNS ){
    for( int i = 0; i < ROWS; i++ ) free( puzzle[ i ] );
    free( puzzle );

    return;
}

bool validPosition( int row, int col, int ROWS, int COLUMNS ){
  bool valid = false;
  if( row >= 0 && row < ROWS && col >= 0 && col < COLUMNS ) valid = true;

  return valid;
}

direction setIntelligentMove( int** puzzle, int ROWS, int COLUMNS, int empty_row, int empty_col ){//just branching in four different directions and calculating their distances to final positions separately
  int manh_dist_r = 0, manh_dist_l = 0, manh_dist_u = 0, manh_dist_d = 0;//4 different variables just for readability purposes
  string intelligent_to_right = "Intelligent move choses r";
  string intelligent_to_left = "Intelligent move choses l";
  string intelligent_to_down = "Intelligent move choses d";
  string intelligent_to_up = "Intelligent move choses u";

  direction new_direction = none;
  int min = -1;
  if( validPosition( empty_row, empty_col + 1, ROWS, COLUMNS ) ){//manh dist for the move to the right
    updatePuzzle( empty_row, empty_col, empty_row, empty_col + 1, puzzle );
    manh_dist_r = manhattanDistance( puzzle, ROWS, COLUMNS );
    min = manh_dist_r;
    new_direction = r;
    updatePuzzle( empty_row, empty_col + 1, empty_row, empty_col, puzzle );
  }
  if( validPosition( empty_row, empty_col - 1, ROWS, COLUMNS ) ){//manh dist for the move to the left
    updatePuzzle( empty_row, empty_col, empty_row, empty_col - 1, puzzle );
    manh_dist_l = manhattanDistance( puzzle, ROWS, COLUMNS );
    if( min > manh_dist_l || min == - 1 ){
      min = manh_dist_l;
      new_direction = l;
    }
    updatePuzzle( empty_row, empty_col - 1, empty_row, empty_col, puzzle );
  }
  if( validPosition( empty_row + 1, empty_col, ROWS, COLUMNS ) ){//manh dist for the move down
    updatePuzzle( empty_row, empty_col, empty_row + 1, empty_col, puzzle );
    manh_dist_d = manhattanDistance( puzzle, ROWS, COLUMNS );
    if( min > manh_dist_d || min == -1){
      min = manh_dist_d;
      new_direction = d;
    }
    updatePuzzle( empty_row + 1, empty_col, empty_row, empty_col, puzzle );
  }
  if( validPosition( empty_row - 1, empty_col, ROWS, COLUMNS ) ){//manh dist for the move up
    updatePuzzle( empty_row, empty_col, empty_row - 1, empty_col, puzzle );
    manh_dist_u = manhattanDistance( puzzle, ROWS, COLUMNS );
    if( min > manh_dist_u || min == -1){
      min = manh_dist_u;
      new_direction = u;
    }
    updatePuzzle( empty_row - 1, empty_col, empty_row, empty_col, puzzle );
  }
  switch ( new_direction ) {
    case r:
     cout << intelligent_to_right << endl;
     break;
    case l:
      cout << intelligent_to_left << endl;
      break;
    case u:
     cout << intelligent_to_up << endl;
     break;
    case d:
      cout << intelligent_to_down << endl;
      break;
  }

  return new_direction;
}
void updatePuzzle( int empty_row, int empty_col, int new_row, int new_col, int** puzzle ){
  int temp = puzzle[ empty_row ][ empty_col ];
  puzzle[ empty_row ][ empty_col ] = puzzle[ new_row ][ new_col ];
  puzzle[ new_row ][ new_col ] = temp;
  return;
}
bool makeMove( int** puzzle, int ROWS, int COLUMNS, int* empty_row, int* empty_col, int* move_count ){
  bool valid_move = true;
  string error_message = "Error. Chosen move is not possible.";
  direction move = getMoveFromUser();
  int target_row = *empty_row, target_col = *empty_col;
  if( move == i )
    move = setIntelligentMove( puzzle, ROWS, COLUMNS, *empty_row, *empty_col );//sets the direction of the inteligent move
  switch ( move ) {
    case u:
      -- target_row;
      break;
    case d:
      ++ target_row;
      break;
    case l:
      -- target_col;
      break;
    case r:
      ++ target_col;
      break;
    case s:
      //print the finished puzzle
      {
        int** solved_puzzle = NULL;
        solved_puzzle = ( int** )malloc( ( ROWS ) * sizeof( int* ) );
        for( int i = 0; i < ROWS; ++i ) solved_puzzle[i] = ( int* )malloc( ( COLUMNS ) * sizeof( int ) );
        for( int i = 0; i < ROWS; ++i ){
          for( int j = 0; j < COLUMNS; ++j)
            solved_puzzle[ i ][ j ] = i * COLUMNS + j + 1;
        }
        solved_puzzle[ ROWS - 1 ][ COLUMNS - 1 ] = EMPTY_CELL;
        // cout << "Final position:" << endl;
        // printPuzzle( solved_puzzle, ROWS, COLUMNS ); this part was not needed, I thought i had to display the solved puzzle
        emptyOutPuzzle( solved_puzzle, ROWS, COLUMNS );
        setSolvablePuzzle( puzzle, ROWS, COLUMNS, empty_row, empty_col );
        cout << "Your new puzzle: " << endl;
        *move_count = 0;
      }
      break;
    case q:
      valid_move = false;
      break;
  }
  if( validPosition( target_col, target_row, ROWS, COLUMNS ) ){
    if( move != s && move != none && move != q ){
      updatePuzzle( *empty_row, *empty_col, target_row, target_col, puzzle );
      *empty_row = target_row;
      *empty_col = target_col;
      ++(*move_count);
    }
  }
  else{
    cerr << error_message << endl;
  }

  return valid_move;
}

direction getMoveFromUser(){

  direction user_move = none;
  const int input_size = 1;
  string input;

  string error_message = "Error: Invalid input. Input examples: 'l', 'L', 'r', 'R', 'u', 'U', 'd', 'D'. Please try again: ";
  while( user_move == none ){
    getline( cin, input );
    if( input.length() == input_size ){
      switch ( input[0] ) {
        case 'l':
        case 'L':
          user_move = l;
          break;
        case 'r':
        case 'R':
          user_move = r;
          break;
        case 'u':
        case 'U':
          user_move = u;
          break;
        case 'd':
        case 'D':
          user_move = d;
          break;
        case 's':
        case 'S':
          user_move = s;
          break;
        case 'q':
        case 'Q':
          user_move = q;
          break;
        case 'i':
        case 'I':
          user_move = i;
          break;
      }
    }
    if( user_move == none ) cerr << error_message << endl;

  }

  return user_move;
}
