package chess;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {

    private ChessPiece[][] squares = new ChessPiece[9][9];
    public ChessBoard() {
        
    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        squares[position.getRow()][position.getColumn()] = piece;
    }
    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        return squares[position.getRow()][position.getColumn()];
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        squares = new ChessPiece[9][9];
    }

    @Override
    public String toString() {
        String txtboard = "";
        for (int a = 1; a < 9; a++) {
            for (int c = 1; c < 9; c++) {
                if(squares[a][c] == null) {
                    txtboard += "|";
                } else if (squares[a][c].getTeamColor() == ChessGame.TeamColor.WHITE) {
                    txtboard += squares[a][c].getPieceType().getName().toUpperCase();
                } else {
                    txtboard += squares[a][c].getPieceType().getName();
                }
                txtboard += " ";
            }
            txtboard += "\n";
        }
        return txtboard;
    }
}
