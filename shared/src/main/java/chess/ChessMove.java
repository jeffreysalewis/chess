package chess;

import java.util.Objects;

/**
 * Represents moving a chess piece on a chessboard
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessMove {

    private ChessPosition startpos;
    private ChessPosition endpos;
    private ChessPiece.PieceType promopiece;
    public ChessMove(ChessPosition startPosition, ChessPosition endPosition,
                     ChessPiece.PieceType promotionPiece) {
        this.startpos = startPosition;
        this.endpos = endPosition;
        this.promopiece = promotionPiece;
    }

    /**
     * @return ChessPosition of starting location
     */
    public ChessPosition getStartPosition() {
        return this.startpos;
    }

    /**
     * @return ChessPosition of ending location
     */
    public ChessPosition getEndPosition() {
        return this.endpos;
    }

    /**
     * Gets the type of piece to promote a pawn to if pawn promotion is part of this
     * chess move
     *
     * @return Type of piece to promote a pawn to, or null if no promotion
     */
    public ChessPiece.PieceType getPromotionPiece() {
        return this.promopiece;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startpos, endpos, promopiece);
    }

    //    @Override
//    public int hashCode() {
//        return this.endpos.getRow()*1000 + this.startpos.getColumn()*100 ;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChessMove chessMove)) return false;
        return Objects.equals(startpos, chessMove.startpos) && Objects.equals(endpos, chessMove.endpos) && promopiece == chessMove.promopiece;
    }


//    @Override
//    public boolean equals(Object obj) {
//        if(obj.getClass() == ChessMove.class) {
//            return this.endpos.equals(((ChessMove)obj).getEndPosition());
//        }
//        return false;
//    }

    //    implement this tostring later
    public String toString() {
        return this.endpos.toString();
    }
}
