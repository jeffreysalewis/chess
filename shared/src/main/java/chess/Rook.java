package chess;

import java.util.Collection;
import java.util.HashSet;

public class Rook extends ChessMove {
    private ChessPiece.PieceType type = ChessPiece.PieceType.ROOK;
    public Rook(ChessPosition startPosition, ChessPosition endPosition, ChessPiece.PieceType promotionPiece) {
        super(startPosition, endPosition, promotionPiece);
    }

    public Collection<ChessMove> move(ChessBoard board, ChessPosition myPosition) {
        HashSet<ChessMove> muev = new HashSet<ChessMove>();
        boolean bien = true;
        ChessPosition temp = myPosition.copy();
        var o = 1;
        var e = 8;
        while(bien) {
            temp.setRow(temp.getRow()+1);
            if(temp.getRow() > e) {
                bien = false;
                break;
            }
            if(board.getPiece(temp) == null) {
                muev.add(new ChessMove(myPosition, temp.copy(), null));
            } else if (board.getPiece(temp).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                muev.add(new ChessMove(myPosition, temp.copy(), null));
                bien = false;
            } else {
                bien = false;
            }
        }
        bien = true;
        temp = myPosition.copy();
        while(bien) {
            temp.setColumn(temp.getColumn()+1);
            if(temp.getColumn() > e) {
                bien = false;
                break;
            }
            if(board.getPiece(temp) == null) {
                muev.add(new ChessMove(myPosition, temp.copy(), null));
            } else if (board.getPiece(temp).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                muev.add(new ChessMove(myPosition, temp.copy(), null));
                bien = false;
            } else {
                bien = false;
            }
        }
        bien = true;
        temp = myPosition.copy();
        while(bien) {
            temp.setRow(temp.getRow()-1);
            if(temp.getRow() < 1) {
                bien = false;
                break;
            }
            if(board.getPiece(temp) == null) {
                muev.add(new ChessMove(myPosition, temp.copy(), null));
            } else if (board.getPiece(temp).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                muev.add(new ChessMove(myPosition, temp.copy(), null));
                bien = false;
            } else {
                bien = false;
            }
        }
        bien = true;
        temp = myPosition.copy();
        while(bien) {
            temp.setColumn(temp.getColumn()-o);
            if(temp.getColumn() < o) {
                bien = false;
                break;
            }
            if(board.getPiece(temp) == null) {
                muev.add(new ChessMove(myPosition, temp.copy(), null));
            } else if (board.getPiece(temp).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                muev.add(new ChessMove(myPosition, temp.copy(), null));
                bien = false;
            } else {
                bien = false;
            }
        }
        return muev;
    }
}
