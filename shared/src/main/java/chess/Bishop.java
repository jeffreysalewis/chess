package chess;

import java.util.Collection;
import java.util.HashSet;

public class Bishop extends ChessMove {
    private ChessPiece.PieceType type = ChessPiece.PieceType.BISHOP;
    public Bishop(ChessPosition startPosition, ChessPosition endPosition, ChessPiece.PieceType promotionPiece) {
        super(startPosition, endPosition, promotionPiece);
        this.type = ChessPiece.PieceType.BISHOP;
    }

    public Collection<ChessMove> move(ChessBoard board, ChessPosition myPosition) {
        HashSet<ChessMove> mv = new HashSet<ChessMove>();
        mv.add(new ChessMove(new ChessPosition(0, 0), new ChessPosition(2, 4), ChessPiece.PieceType.BISHOP));
        return mv;
    }
}
