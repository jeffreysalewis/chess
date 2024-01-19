package chess;

import java.util.Collection;

public class Queen extends ChessMove {
    public Queen(ChessPosition startPosition, ChessPosition endPosition, ChessPiece.PieceType promotionPiece) {
        super(startPosition, endPosition, promotionPiece);
    }
}
