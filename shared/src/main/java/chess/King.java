package chess;

import java.util.Collection;

public class King extends ChessPiece {
    public King(ChessGame.TeamColor piececolor) {
        super(piececolor, ChessPiece.PieceType.KING);
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        return super.pieceMoves(board, myPosition);
    }
}
