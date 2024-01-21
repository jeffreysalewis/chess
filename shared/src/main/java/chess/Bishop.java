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
        //mv.add(this);
        boolean bien = true;
        ChessPosition temp = myPosition.copy();
        //ChessPosition temp2 = new ChessPosition();
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
        while(bien) {
            temp.setRow(temp.getRow()-1);
            temp.setColumn(temp.getColumn()+1);
            if(temp.getRow() < 1 || temp.getColumn() > 8) {
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
            temp.setRow(temp.getRow()+1);
            temp.setColumn(temp.getColumn()-1);
            if(temp.getRow() > 8 || temp.getColumn() < 1) {
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
            temp.setRow(temp.getRow()-1);
            temp.setColumn(temp.getColumn()-1);
            if(temp.getRow() < 1 || temp.getColumn() < 1) {
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
        //mv.add(new ChessMove(new ChessPosition(0, 0), new ChessPosition(2, 4), ChessPiece.PieceType.BISHOP));
        return mv;
    }
}
