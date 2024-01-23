package chess;

/**
 * Represents a single square position on a chess board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPosition {
    int row = -1;
    int col = -1;
    public ChessPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * @return which row this position is in
     * 1 codes for the bottom row
     */
    public int getRow() {
        return this.row;
    }

    /**
     * @return which column this position is in
     * 1 codes for the left row
     */
    public int getColumn() {
        return this.col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int col) {
        this.col = col;
    }

    public ChessPosition copy() {
        return new ChessPosition(this.row, this.col);
    }

    @Override
    public int hashCode() {
        return this.row *10 +this.col;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() == ChessPosition.class) {
            return (this.row == ((ChessPosition)obj).getRow()) && (this.col == ((ChessPosition)obj).getColumn());
        }
        return false;
    }

    public String toString() {
        return this.row + " " + this.col;
    }
}
