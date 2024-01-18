package chess;

import java.util.Collection;

public class Queen extends ChessPiece {
    public Queen(ChessGame.TeamColor piececolor) {
        super(piececolor, ChessPiece.PieceType.QUEEN);
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        return super.pieceMoves(board, myPosition);
    }
}
