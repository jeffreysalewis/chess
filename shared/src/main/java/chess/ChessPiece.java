package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private PieceType ptype;
    private ChessGame.TeamColor color;
    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.color = pieceColor;
        this.ptype = type;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return this.color;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return this.ptype;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        HashSet<ChessMove> mv = new HashSet<ChessMove>();
        switch(this.ptype) {
            case BISHOP:
                Bishop myriel = new Bishop(myPosition, myPosition, PieceType.BISHOP);
                return myriel.move(board, myPosition);
                //break;
            case PAWN:
                break;
            case KNIGHT:
                break;
            case QUEEN:
                break;
            case KING:
                break;
            case ROOK:
                break;
        }
        mv.add(new ChessMove(new ChessPosition(0, 0), new ChessPosition(1, 3), ChessPiece.PieceType.BISHOP));
        return mv;
    }
}
