package chess;

public class Pawn extends ChessMove {
    public Pawn(ChessPosition startPosition, ChessPosition endPosition, ChessPiece.PieceType promotionPiece) {
        super(startPosition, endPosition, promotionPiece);
    }
}
