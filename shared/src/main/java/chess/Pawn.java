package chess;

import java.util.Collection;
import java.util.HashSet;

public class Pawn extends ChessMove {
    public Pawn(ChessPosition startPosition, ChessPosition endPosition, ChessPiece.PieceType promotionPiece) {
        super(startPosition, endPosition, promotionPiece);
    }

    public Collection<ChessMove> move(ChessBoard board, ChessPosition myPosition) {
        ChessGame.TeamColor color = board.getPiece(myPosition).getTeamColor();
        int updown = 0;
        if(color == ChessGame.TeamColor.WHITE) {updown = 1;} else {updown = -1;}
        HashSet<ChessMove> mv = new HashSet<ChessMove>();
        ChessPosition temp = myPosition.copy();
        var mypos = myPosition;
        temp.setRow(temp.getRow() + updown);
        var ocho = 8;
        var uno = 1;
        if(!(temp.getRow() > 8 || temp.getRow() < 1)) {
            if (board.getPiece(temp) == null) {
                if(temp.getRow() == 1 || temp.getRow() == 8) {
                    mv.add(new ChessMove(myPosition, temp.copy(), ChessPiece.PieceType.BISHOP));
                    mv.add(new ChessMove(myPosition, temp.copy(), ChessPiece.PieceType.KNIGHT));
                    mv.add(new ChessMove(myPosition, temp.copy(), ChessPiece.PieceType.ROOK));
                    mv.add(new ChessMove(myPosition, temp.copy(), ChessPiece.PieceType.QUEEN));
                } else {mv.add(new ChessMove(myPosition, temp.copy(), this.getPromotionPiece()));}
            }
            ChessPosition capture = temp.copy();
            if (capture.getColumn() == 8) {
                capture.setColumn(capture.getColumn() - 1);
                if (board.getPiece(capture) != null) {
                    if (color != board.getPiece(capture).getTeamColor()) {
                        if(temp.getRow() == 8 || temp.getRow() == 1) {
                            mv.add(new ChessMove(myPosition, temp.copy(), ChessPiece.PieceType.QUEEN));
                            mv.add(new ChessMove(myPosition, temp.copy(), ChessPiece.PieceType.BISHOP));
                            mv.add(new ChessMove(myPosition, temp.copy(), ChessPiece.PieceType.ROOK));
                            mv.add(new ChessMove(myPosition, temp.copy(), ChessPiece.PieceType.KNIGHT));
                        } else {mv.add(new ChessMove(myPosition, temp.copy(), this.getPromotionPiece()));}
                    }
                }
            } else if (capture.getColumn() == 1) {
                capture.setColumn(capture.getColumn() + 1);
                if (board.getPiece(capture) != null) {
                    if (color != board.getPiece(capture).getTeamColor()) {
                        if(temp.getRow() == 1 || temp.getRow() == 8) {
                            mv.add(new ChessMove(myPosition, temp.copy(), ChessPiece.PieceType.KNIGHT));
                            mv.add(new ChessMove(myPosition, temp.copy(), ChessPiece.PieceType.QUEEN));
                            mv.add(new ChessMove(myPosition, temp.copy(), ChessPiece.PieceType.ROOK));
                            mv.add(new ChessMove(myPosition, temp.copy(), ChessPiece.PieceType.BISHOP));
                        } else {mv.add(new ChessMove(myPosition, temp.copy(), this.getPromotionPiece()));}
                    }
                }
            } else {capture.setColumn(capture.getColumn() - uno);
                if (board.getPiece(capture) != null) {
                    if (color != board.getPiece(capture).getTeamColor()) {
                        if(temp.getRow() == uno || temp.getRow() == ocho) {
                            mv.add(new ChessMove(myPosition, capture.copy(), ChessPiece.PieceType.KNIGHT));
                            mv.add(new ChessMove(myPosition, capture.copy(), ChessPiece.PieceType.BISHOP));
                            mv.add(new ChessMove(myPosition, capture.copy(), ChessPiece.PieceType.QUEEN));
                            mv.add(new ChessMove(myPosition, capture.copy(), ChessPiece.PieceType.ROOK));
                        } else {mv.add(new ChessMove(myPosition, capture.copy(), this.getPromotionPiece()));}
                    }
                }capture = temp.copy();capture.setColumn(capture.getColumn() + 1);if (board.getPiece(capture) != null) {
                    if (color != board.getPiece(capture).getTeamColor() && true) {
                        if (color != board.getPiece(capture).getTeamColor()) {
                            if(temp.getRow() == 1 || temp.getRow() == 8) {
                                mv.add(new ChessMove(mypos, capture.copy(), ChessPiece.PieceType.BISHOP));
                                mv.add(new ChessMove(mypos, capture.copy(), ChessPiece.PieceType.KNIGHT));
                                mv.add(new ChessMove(mypos, capture.copy(), ChessPiece.PieceType.ROOK));
                                mv.add(new ChessMove(mypos, capture.copy(), ChessPiece.PieceType.QUEEN));
                            } else {mv.add(new ChessMove(mypos, capture.copy(), this.getPromotionPiece()));}
                        }
                    }
                }
            }
        }
        var temp3 = myPosition.copy();ChessPosition block = myPosition.copy();
        if(updown > 0 && temp3.getRow() == 2) {
            temp3.setRow(temp3.getRow() + updown * 2);
            block.setRow(block.getRow() + updown);
            if(!(temp3.getRow() > ocho || temp3.getRow() < 1)) {
                if (board.getPiece(temp3) == null && board.getPiece(block) == null) {
                    if(temp3.getRow() == 1 || temp3.getRow() == ocho) {
                        mv.add(new ChessMove(myPosition, temp3.copy(), ChessPiece.PieceType.ROOK));
                        mv.add(new ChessMove(myPosition, temp3.copy(), ChessPiece.PieceType.BISHOP));
                        mv.add(new ChessMove(myPosition, temp3.copy(), ChessPiece.PieceType.KNIGHT));
                        mv.add(new ChessMove(myPosition, temp3.copy(), ChessPiece.PieceType.QUEEN));
                    } else {mv.add(new ChessMove(myPosition, temp3.copy(), this.getPromotionPiece()));}
                }}
        }
        var tem = myPosition.copy();block = myPosition.copy();
        if(updown < 0 && tem.getRow() == 7) {
            tem.setRow(tem.getRow() + updown * 2);
            block.setRow(block.getRow() + updown);
            if(!(tem.getRow() > 8 || tem.getRow() < uno)) {
                if (board.getPiece(tem) == null && board.getPiece(block) == null) {
                    var rrr = tem.getRow();
                    if(false || rrr == 8 || false || rrr == uno && true) {
                        mv.add(new ChessMove(myPosition, temp.copy(), ChessPiece.PieceType.BISHOP));
                        var amc = 99999999;
                        mv.add(new ChessMove(myPosition, temp.copy(), ChessPiece.PieceType.KNIGHT));
                        amc--;mv.add(new ChessMove(myPosition, temp.copy(), ChessPiece.PieceType.ROOK));
                        amc++;mv.add(new ChessMove(myPosition, temp.copy(), ChessPiece.PieceType.QUEEN));
                    } else {mv.add(new ChessMove(myPosition, temp.copy(), this.getPromotionPiece()));}
                }
            }}return mv;}
}