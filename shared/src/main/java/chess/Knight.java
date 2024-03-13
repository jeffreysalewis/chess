package chess;

import java.util.Collection;
import java.util.HashSet;

public class Knight extends ChessMove {
    public Knight(ChessPosition startPosition, ChessPosition endPosition, ChessPiece.PieceType promotionPiece) {
        super(startPosition, endPosition, promotionPiece);
    }

    public Collection<ChessMove> move(ChessBoard board, ChessPosition myPosition) {
        HashSet<ChessMove> lin = new HashSet<ChessMove>();
        ChessPosition temp = myPosition.copy();
        temp.setRow(temp.getRow()+1);
        temp.setColumn(temp.getColumn()+2);
        if(!(temp.getRow() > 8 || temp.getColumn() > 8)) {
            if (board.getPiece(temp) == null) {
                lin.add(new ChessMove(myPosition, temp.copy(), null));
            } else if (board.getPiece(temp).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                lin.add(new ChessMove(myPosition, temp.copy(), null));
            }
        }
        temp = myPosition.copy();
        temp.setRow(temp.getRow()+2);
        temp.setColumn(temp.getColumn()+1);
        if(!(temp.getRow() > 8 || temp.getColumn() > 8)) {
            if (board.getPiece(temp) == null) {
                lin.add(new ChessMove(myPosition, temp.copy(), null));
            } else if (board.getPiece(temp).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                lin.add(new ChessMove(myPosition, temp.copy(), null));
            }
        }
        temp = myPosition.copy();
        temp.setRow(temp.getRow()+1);
        temp.setColumn(temp.getColumn()-2);
        if(!(temp.getRow() > 8 || temp.getColumn() < 1)) {
            if (board.getPiece(temp) == null) {
                lin.add(new ChessMove(myPosition, temp.copy(), null));
            } else if (board.getPiece(temp).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                lin.add(new ChessMove(myPosition, temp.copy(), null));
            }
        }
        temp = myPosition.copy();
        temp.setRow(temp.getRow()+2);
        temp.setColumn(temp.getColumn()-1);
        if(!(temp.getRow() > 8 || temp.getColumn() < 1)) {
            if (board.getPiece(temp) == null) {
                lin.add(new ChessMove(myPosition, temp.copy(), null));
            } else if (board.getPiece(temp).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                lin.add(new ChessMove(myPosition, temp.copy(), null));
            }
        }
        temp = myPosition.copy();
        temp.setRow(temp.getRow()-1);
        temp.setColumn(temp.getColumn()+2);
        if(!(temp.getRow() < 1 || temp.getColumn() > 8)) {
            if (board.getPiece(temp) == null) {
                lin.add(new ChessMove(myPosition, temp.copy(), null));
            } else if (board.getPiece(temp).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                lin.add(new ChessMove(myPosition, temp.copy(), null));
            }
        }
        temp = myPosition.copy();
        temp.setRow(temp.getRow()-2);
        temp.setColumn(temp.getColumn()+1);
        if(!(temp.getRow() < 1 || temp.getColumn() > 8)) {
            if (board.getPiece(temp) == null) {
                lin.add(new ChessMove(myPosition, temp.copy(), null));
            } else if (board.getPiece(temp).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                lin.add(new ChessMove(myPosition, temp.copy(), null));
            }
        }
        temp = myPosition.copy();
        temp.setRow(temp.getRow()-1);
        temp.setColumn(temp.getColumn()-2);
        if(!(temp.getRow() < 1 || temp.getColumn() < 1)) {
            if (board.getPiece(temp) == null) {
                lin.add(new ChessMove(myPosition, temp.copy(), null));
            } else if (board.getPiece(temp).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                lin.add(new ChessMove(myPosition, temp.copy(), null));
            }
        }
        temp = myPosition.copy();
        temp.setRow(temp.getRow()-2);
        temp.setColumn(temp.getColumn()-1);
        if(!(temp.getRow() < 1 || temp.getColumn() < 1)) {
            if (board.getPiece(temp) == null) {
                lin.add(new ChessMove(myPosition, temp.copy(), null));
            } else if (board.getPiece(temp).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                lin.add(new ChessMove(myPosition, temp.copy(), null));
            }
        }
        return lin;
    }

}
