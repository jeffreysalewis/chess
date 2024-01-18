package chess;

import java.util.Collection;

public class Rook extends ChessPiece {
    public Rook(ChessGame.TeamColor piececolor) {
        super(piececolor, ChessPiece.PieceType.ROOK);
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        return super.pieceMoves(board, myPosition);
    }
}
