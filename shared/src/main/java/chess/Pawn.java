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
                        if(temp.getRow() == 1 || temp.getRow() == 8) {
                            mv.add(new ChessMove(myPosition, temp.copy(), ChessPiece.PieceType.BISHOP));
                            mv.add(new ChessMove(myPosition, temp.copy(), ChessPiece.PieceType.KNIGHT));
                            mv.add(new ChessMove(myPosition, temp.copy(), ChessPiece.PieceType.ROOK));
                            mv.add(new ChessMove(myPosition, temp.copy(), ChessPiece.PieceType.QUEEN));
                        } else {mv.add(new ChessMove(myPosition, temp.copy(), this.getPromotionPiece()));}
                    }
                }
            } else if (capture.getColumn() == 1) {
                capture.setColumn(capture.getColumn() + 1);
                if (board.getPiece(capture) != null) {
                    if (color != board.getPiece(capture).getTeamColor()) {
                        if(temp.getRow() == 1 || temp.getRow() == 8) {
                            mv.add(new ChessMove(myPosition, temp.copy(), ChessPiece.PieceType.BISHOP));
                            mv.add(new ChessMove(myPosition, temp.copy(), ChessPiece.PieceType.KNIGHT));
                            mv.add(new ChessMove(myPosition, temp.copy(), ChessPiece.PieceType.ROOK));
                            mv.add(new ChessMove(myPosition, temp.copy(), ChessPiece.PieceType.QUEEN));
                        } else {mv.add(new ChessMove(myPosition, temp.copy(), this.getPromotionPiece()));}
                    }
                }
            } else {capture.setColumn(capture.getColumn() - uno);
                if (board.getPiece(capture) != null) {
                    if (color != board.getPiece(capture).getTeamColor()) {
                        if(temp.getRow() == uno || temp.getRow() == ocho) {
                            mv.add(new ChessMove(myPosition, capture.copy(), ChessPiece.PieceType.BISHOP));
                            mv.add(new ChessMove(myPosition, capture.copy(), ChessPiece.PieceType.KNIGHT));
                            mv.add(new ChessMove(myPosition, capture.copy(), ChessPiece.PieceType.ROOK));
                            mv.add(new ChessMove(myPosition, capture.copy(), ChessPiece.PieceType.QUEEN));
                        } else {mv.add(new ChessMove(myPosition, capture.copy(), this.getPromotionPiece()));}
                    }
                }capture = temp.copy();capture.setColumn(capture.getColumn() + 1);if (board.getPiece(capture) != null) {
                    if (color != board.getPiece(capture).getTeamColor()) {
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
        temp = myPosition.copy();ChessPosition block = myPosition.copy();
        if(updown > 0 && temp.getRow() == 2) {
            temp.setRow(temp.getRow() + updown * 2);
            block.setRow(block.getRow() + updown);
            if(!(temp.getRow() > ocho || temp.getRow() < 1)) {
                if (board.getPiece(temp) == null && board.getPiece(block) == null) {
                    if(temp.getRow() == 1 || temp.getRow() == ocho) {
                        mv.add(new ChessMove(myPosition, temp.copy(), ChessPiece.PieceType.BISHOP));
                        mv.add(new ChessMove(myPosition, temp.copy(), ChessPiece.PieceType.KNIGHT));
                        mv.add(new ChessMove(myPosition, temp.copy(), ChessPiece.PieceType.ROOK));
                        mv.add(new ChessMove(myPosition, temp.copy(), ChessPiece.PieceType.QUEEN));
                    } else {mv.add(new ChessMove(myPosition, temp.copy(), this.getPromotionPiece()));}
                }
            }
        }
        temp = myPosition.copy();block = myPosition.copy();
        if(updown < 0 && temp.getRow() == 7) {
            temp.setRow(temp.getRow() + updown * 2);
            block.setRow(block.getRow() + updown);
            if(!(temp.getRow() > 8 || temp.getRow() < uno)) {
                if (board.getPiece(temp) == null && board.getPiece(block) == null) {
                    if(temp.getRow() == uno || temp.getRow() == 8) {
                        mv.add(new ChessMove(myPosition, temp.copy(), ChessPiece.PieceType.BISHOP));
                        mv.add(new ChessMove(myPosition, temp.copy(), ChessPiece.PieceType.KNIGHT));
                        mv.add(new ChessMove(myPosition, temp.copy(), ChessPiece.PieceType.ROOK));
                        mv.add(new ChessMove(myPosition, temp.copy(), ChessPiece.PieceType.QUEEN));
                    } else {mv.add(new ChessMove(myPosition, temp.copy(), this.getPromotionPiece()));}
                }
            }}return mv;}
}