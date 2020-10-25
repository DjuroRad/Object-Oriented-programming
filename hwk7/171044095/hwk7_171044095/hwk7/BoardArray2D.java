package hwk7;
/**
*Puzzle implemented using 2D array
*/
public class BoardArray2D extends AbstractBoard{
    public BoardArray2D() {
        this( DEF_ROWS, DEF_COLS );
    }

    public BoardArray2D( int ROWS, int COLUMNS ) {
        super(ROWS, COLUMNS);
    }
    @Override
    public int cell(int i, int j) {
        return puzzle[ i ][ j ];
    }
/**
*Used to avoid code repetitoin
*/
    @Override
    protected void setPosition( int i, int j, int value ){
        puzzle[ i ][ j ] = value;
    }
/**
*Used to initialize the puzzle
*/
    @Override
    protected void setUpData(){
        puzzle = new int[this.ROWS][this.COLUMNS];
    }
    int puzzle[][];
}
