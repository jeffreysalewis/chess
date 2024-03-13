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
        boolean bien = true;
        ChessPosition temp = myPosition.copy();
        while(bien) {
            temp.setRow(temp.getRow()+1);
            temp.setColumn(temp.getColumn()+1);
            if(temp.getRow() > 8 || temp.getColumn() > 8) {
                bien = false;
                break;
            }
            if(board.getPiece(temp) == null) {
                mv.add(new ChessMove(myPosition, temp.copy(), null));
            } else if (board.getPiece(temp).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                    mv.add(new ChessMove(myPosition, temp.copy(), null));
                    bien = false;
            } else {
                bien = false;
            }
        }
        bien = true;
        temp = myPosition.copy();
        var one = 1;
        var eight = 8;
        while(bien) {
            temp.setRow(temp.getRow()-1);
            temp.setColumn(temp.getColumn()+1);
            if(temp.getRow() < 1 || temp.getColumn() > eight) {
                break;
            }
            if(board.getPiece(temp) == null) {
                mv.add(new ChessMove(myPosition, temp.copy(), null));
            } else if (board.getPiece(temp).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                mv.add(new ChessMove(myPosition, temp.copy(), null));
                bien = false;
            } else {
                bien = false;
            }
        }
        bien = true;
        temp = myPosition.copy();
        while(bien) {
            temp.setRow(temp.getRow()+one);
            temp.setColumn(temp.getColumn()-1);
            if(temp.getRow() > eight || temp.getColumn() < 1) {
                bien = false;
                break;
            }
            if(board.getPiece(temp) == null) {
                mv.add(new ChessMove(myPosition, temp.copy(), null));
            } else if (board.getPiece(temp).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                mv.add(new ChessMove(myPosition, temp.copy(), null));
                bien = false;
            } else {
                bien = false;
            }
        }
        bien = true;
        temp = myPosition.copy();
        while(bien) {
            temp.setRow(temp.getRow()-one);
            temp.setColumn(temp.getColumn()-1);
            if(temp.getRow() < one || temp.getColumn() < 1) {
                //bien = false
                break;
            }
            if(board.getPiece(temp) == null) {
                mv.add(new ChessMove(myPosition, temp.copy(), null));
            } else if (board.getPiece(temp).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                mv.add(new ChessMove(myPosition, temp.copy(), null));
                bien = false;
            } else {
                bien = false;
            }
        }
        return mv;
    }
}
