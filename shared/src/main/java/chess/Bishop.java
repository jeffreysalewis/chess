package chess;

public class Bishop extends ChessMove {
    private ChessPiece.PieceType type = ChessPiece.PieceType.BISHOP;
    public Bishop(ChessPosition startPosition, ChessPosition endPosition, ChessPiece.PieceType promotionPiece) {
        super(startPosition, endPosition, promotionPiece);
        this.type = ChessPiece.PieceType.BISHOP;
    }
}
