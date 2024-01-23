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
        KING("k"),
        QUEEN("q"),
        BISHOP("b"),
        KNIGHT("n"),
        ROOK("r"),
        PAWN("p");

        private String name;

        PieceType(String n) {
            this.name = n;
        }

        public String getName() {
            return this.name;
        }
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
                Bishop myriel = new Bishop(myPosition, myPosition, null);
                return myriel.move(board, myPosition);
            case PAWN:
                Pawn kaladin = new Pawn(myPosition, myPosition, null);
                return kaladin.move(board, myPosition);
            case KNIGHT:
                Knight link = new Knight(myPosition, myPosition, null);
                return link.move(board, myPosition);
            case QUEEN:
                Queen elsa = new Queen(myPosition, myPosition, null);
                return elsa.move(board, myPosition);
            case KING:
                King tchalla = new King(myPosition, myPosition, null);
                return tchalla.move(board, myPosition);
            case ROOK:
                Rook hogwarts = new Rook(myPosition, myPosition, null);
                return hogwarts.move(board, myPosition);
        }
        mv.add(new ChessMove(new ChessPosition(0, 0), new ChessPosition(1, 3), ChessPiece.PieceType.BISHOP));
        return mv;
    }

    @Override
    public String toString() {
        String s = this.ptype.getName();
        if(this.color == ChessGame.TeamColor.WHITE) {
            s = s.toUpperCase();
        }
        return s;
    }

    @Override
    public boolean equals(Object obj) {
        return this.toString() == obj.toString();
    }
}
