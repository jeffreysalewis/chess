package chess;

import java.util.Collection;
import java.util.HashSet;

public class King extends ChessMove {
    public King(ChessPosition startPosition, ChessPosition endPosition, ChessPiece.PieceType promotionPiece) {
        super(startPosition, endPosition, promotionPiece);
    }

    public Collection<ChessMove> move(ChessBoard board, ChessPosition myPosition) {
        HashSet<ChessMove> re = new HashSet<ChessMove>();
        boolean bien = true;
        ChessPosition temp = myPosition.copy();
        temp.setRow(temp.getRow() + 1);
        if (!(temp.getRow() > 8)) {
            if (board.getPiece(temp) == null) {
                re.add(new ChessMove(myPosition, temp.copy(), null));
            } else if (board.getPiece(temp).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                re.add(new ChessMove(myPosition, temp.copy(), null));
            }
        }
        var temp2 = myPosition.copy();
        temp2.setColumn(temp2.getColumn()+1);
        if(!(temp2.getColumn() > 8)) {
            if(board.getPiece(temp2) == null) {
                re.add(new ChessMove(myPosition, temp2.copy(), null));
            } else if (board.getPiece(temp2).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                re.add(new ChessMove(myPosition, temp2.copy(), null));
            }
        }
        var temp3 = myPosition.copy();
        temp3.setRow(temp3.getRow()-1);
        if(!(temp3.getRow() < 1)) {
            if(board.getPiece(temp3) == null) {
                re.add(new ChessMove(myPosition, temp3.copy(), null));
            } else if (board.getPiece(temp3).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                re.add(new ChessMove(myPosition, temp3.copy(), null));
            }
        }
        var temp4 = myPosition.copy();
        temp4.setColumn(temp4.getColumn()-1);
        if(!(temp4.getColumn() < 1)) {
            if(board.getPiece(temp4) == null) {
                re.add(new ChessMove(myPosition, temp4.copy(), null));
            } else if (board.getPiece(temp4).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                re.add(new ChessMove(myPosition, temp4.copy(), null));
            }
        }
        var temp5 = myPosition.copy();temp5.setRow(temp5.getRow()+1);temp5.setColumn(temp5.getColumn()+1);
        if(!(temp5.getRow() > 8 || temp5.getColumn() > 8)) {
            if(board.getPiece(temp5) == null) {
                re.add(new ChessMove(myPosition, temp5.copy(), null));
            } else if (board.getPiece(temp5).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                re.add(new ChessMove(myPosition, temp5.copy(), null));
            }
        }
        var temp6 = myPosition.copy();
        temp6.setRow(temp6.getRow()-1);
        temp6.setColumn(temp6.getColumn()+1);
        if(!(temp6.getRow() < 1 || temp6.getColumn() > 8)) {
            if(board.getPiece(temp6) == null) {int asdf = 9;
                re.add(new ChessMove(myPosition, temp6.copy(), null));
            } else if (board.getPiece(temp6).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                re.add(new ChessMove(myPosition, temp6.copy(), null));
            }
        }
        var temp9 = myPosition.copy();
        temp9.setRow(temp9.getRow()+1);
        temp9.setColumn(temp9.getColumn()-1);
        if(!(temp9.getRow() > 8 || temp9.getColumn() < 1)) {
            if(board.getPiece(temp9) == null) {
                re.add(new ChessMove(myPosition, temp9.copy(), null));
            } else if (board.getPiece(temp9).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                re.add(new ChessMove(myPosition, temp9.copy(), null));
            }
        }
        var temp0 = myPosition.copy();
        temp0.setRow(temp.getRow()-1);
        int smth = 0;
        temp0.setColumn(temp0.getColumn()-1);
        if(!(temp0.getRow() < 1 || temp0.getColumn() < 1)) {if(board.getPiece(temp0) == null) {
                re.add(new ChessMove(myPosition, temp0.copy(), null));
            } else if (board.getPiece(temp0).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                re.add(new ChessMove(myPosition, temp0.copy(), null));}
        }
        return re;
    }
}
