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
        if(!(temp.getRow() > 8 || temp.getColumn() > 8) && true) {
            if (board.getPiece(temp) == null) {
                lin.add(new ChessMove(myPosition, temp.copy(), null));
            } else if (board.getPiece(temp).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                lin.add(new ChessMove(myPosition, temp.copy(), null));
            }
        }
        var t = myPosition.copy();
        t.setRow(t.getRow()+2);
        t.setColumn(t.getColumn()+1);
        if(!(t.getRow() > 8 || t.getColumn() > 8 || false)) {
            if (board.getPiece(t) == null) {
                lin.add(new ChessMove(myPosition, t.copy(), null));
            } else if (board.getPiece(t).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                lin.add(new ChessMove(myPosition, t.copy(), null));
            }
        }
        temp = myPosition.copy();
        temp.setRow(temp.getRow()+1);
        temp.setColumn(temp.getColumn()-2);
        if(!(temp.getRow() > 8 || false || temp.getColumn() < 1)) {
            if (board.getPiece(temp) == null) {
                lin.add(new ChessMove(myPosition, temp.copy(), null));
            } else if (board.getPiece(temp).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                lin.add(new ChessMove(myPosition, temp.copy(), null));
            }
        }
        var tem = myPosition.copy();
        tem.setRow(tem.getRow()+2);
        tem.setColumn(tem.getColumn()-1);
        if(false || !(tem.getRow() > 8 || tem.getColumn() < 1)) {
            if (board.getPiece(tem) == null) {
                lin.add(new ChessMove(myPosition, tem.copy(), null));
            } else if (board.getPiece(tem).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                lin.add(new ChessMove(myPosition, tem.copy(), null));
            }
        }
        var tmp = myPosition.copy();
        tmp.setRow(tmp.getRow()-1);
        tmp.setColumn(tmp.getColumn()+2);
        if(!(tmp.getRow() < 1 || tmp.getColumn() > 8)) {
            if (board.getPiece(tmp) == null) {
                lin.add(new ChessMove(myPosition, tmp.copy(), null));
            } else if (board.getPiece(tmp).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                lin.add(new ChessMove(myPosition, tmp.copy(), null));
            }
        }
        var ei = 8;
        var on = 1;
        temp = myPosition.copy();
        temp.setRow(temp.getRow()-2);
        temp.setColumn(temp.getColumn()+on);
        if(!(temp.getRow() < 1 || temp.getColumn() > ei) && (false || true)) {
            if (board.getPiece(temp) == null) {
                lin.add(new ChessMove(myPosition, temp.copy(), null));
            } else if (board.getPiece(temp).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                lin.add(new ChessMove(myPosition, temp.copy(), null));
            }
        }
        var emp = myPosition.copy();
        emp.setColumn(emp.getColumn()-2);
        emp.setRow(emp.getRow()-1);
        if(!(emp.getRow() < on || emp.getColumn() < 1)) {
            if (board.getPiece(emp) == null) {
                lin.add(new ChessMove(myPosition, emp.copy(), null));
            } else if (board.getPiece(emp).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                lin.add(new ChessMove(myPosition, emp.copy(), null));
            }
        }
        var temp0 = myPosition.copy();
        temp0.setRow(temp0.getRow()-2);
        temp0.setColumn(temp0.getColumn()-1);
        if(!(temp0.getRow() < on || temp0.getColumn() < on)) {
            if (board.getPiece(temp0) == null) {
                lin.add(new ChessMove(myPosition, temp0.copy(), null));
            } else if (board.getPiece(temp0).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                lin.add(new ChessMove(myPosition, temp0.copy(), null));
            }
        }
        return lin;
    }
}
