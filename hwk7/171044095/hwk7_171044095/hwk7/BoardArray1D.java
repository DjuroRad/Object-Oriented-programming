package hwk7;
/**
*Puzzle implemented using 1D array
*/
public class BoardArray1D extends AbstractBoard {
        public BoardArray1D() {
        this( DEF_ROWS, DEF_COLS );
    }
        public BoardArray1D( int ROWS, int COLUMNS ) {
            super(ROWS, COLUMNS);
        }

        @Override
        public int cell(int i, int j) {
            return puzzle[ i* COLUMNS + j ];
        }
/**
*Used to avoid code repetitoin
*/
        @Override
        protected void setPosition( int i, int j, int value ){
            puzzle[ i* COLUMNS + j ] = value;
        }
/**
*Used to initialize the puzzle
*/
        @Override
        protected void setUpData(){
            puzzle = new int[ this.ROWS * this.COLUMNS ];
        }
        private int puzzle[];
    }

