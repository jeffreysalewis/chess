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
        squares[8][1] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK);
        squares[8][2] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT);
        squares[8][3] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP);
        squares[8][4] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.QUEEN);
        for(int a = 1; a < 9; a++) {
            squares[7][a] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        }
        for(int a = 1; a < 9; a++) {
            squares[2][a] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        }
    }

    @Override
    public String toString() {
        String txtboard = "";
        for (int a = 8; a > 0; a--) {
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


//    public boolean equals(ChessPiece[][] obj) {
//        for(int a = 1; a < 9; a++) {
//            for(int c = 1; c < 9; c++) {
//                if(squares[a][c] != obj[a][c]) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
}
