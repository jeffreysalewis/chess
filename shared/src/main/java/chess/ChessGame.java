package chess;

import java.util.Collection;
import java.util.HashSet;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {

    private TeamColor turncolor;
    private ChessBoard gameboard;
    public ChessGame() {
        this.gameboard = new ChessBoard();
        this.turncolor = TeamColor.WHITE;
    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return this.turncolor;
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        this.turncolor = team;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> possibleMoves(ChessPosition startPosition) {
        if(this.gameboard.getPiece(startPosition) == null) {
            return null;
        }
        return this.gameboard.getPiece(startPosition).pieceMoves(this.gameboard, startPosition);
    }
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        if(this.gameboard.getPiece(startPosition) == null) {
            return null;
        }
        var possiblemv = this.gameboard.getPiece(startPosition).pieceMoves(this.gameboard, startPosition);
        var colour = this.gameboard.getPiece(startPosition).getTeamColor();
        var vmoves = new HashSet<ChessMove>();
        for (var pmv:possiblemv) {;
            var possiblegame = this.copy();
            possiblegame.setTeamTurn(colour);
            try{
                possiblegame.makeMove(pmv);
                vmoves.add(pmv);
            }
            catch (InvalidMoveException i) {
                var idk =0;
            }
        }
        return vmoves;
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */

    private ChessGame tempGameMove(ChessMove move) {
        ChessGame tmp = this.copy();
        tmp.getBoard().setSquares(move.getEndPosition(), this.gameboard.getPiece(move.getStartPosition()));
        tmp.getBoard().setSquares(move.getStartPosition(), null);
        return tmp;
    }

    private void tempMove(ChessMove move) {
        this.gameboard.setSquares(move.getEndPosition(), this.gameboard.getPiece(move.getStartPosition()));
        this.gameboard.setSquares(move.getStartPosition(), null);
    }

    public void makeMove(ChessMove move) throws InvalidMoveException {
        var bwc = this.gameboard.getPiece(move.getStartPosition()).getTeamColor();
        var oldgm = this.copy();
        if(this.possibleMoves(move.getStartPosition()).contains(move)) {
            if(this.turncolor != bwc) {
                throw new InvalidMoveException("Invalid move out of turn");
            }
            var tempp = this.gameboard.getPiece(move.getStartPosition());
            this.gameboard.setSquares(move.getEndPosition(), this.gameboard.getPiece(move.getStartPosition()));
            this.gameboard.setSquares(move.getStartPosition(), null);
            if(this.gameboard.getSquares()[move.getEndPosition().getRow()][move.getEndPosition().getColumn()].getPieceType() == ChessPiece.PieceType.PAWN) {
                if((bwc == TeamColor.WHITE && move.getEndPosition().getRow() == 8) || (bwc == TeamColor.BLACK && move.getEndPosition().getRow() == 1)) {
                    var p = new ChessPiece(tempp.getTeamColor(), move.getPromotionPiece());
                    var p2 = new ChessPiece(tempp.getTeamColor(), ChessPiece.PieceType.PAWN);
                    if (p.getPieceType() != null) {
                        this.gameboard.setSquares(move.getEndPosition(), p);
                    } else {
                        this.gameboard.setSquares(move.getEndPosition(), p2);
                    }
                }
            }
            if(this.isInCheck(bwc)) {
                this.gameboard = oldgm.getBoard();
                this.turncolor = oldgm.getTeamTurn();
                throw new InvalidMoveException("Invalid move put in check");
            }
            if(this.turncolor == TeamColor.WHITE) {
                this.turncolor = TeamColor.BLACK;
            } else {
                this.turncolor = TeamColor.WHITE;
            }
        } else {
            throw new InvalidMoveException("Invalid move");
        }
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        var reypos = new ChessPosition(0, 0);
        for (int a=1; a<9; a++) {
            for(int c=1; c<9; c++) {
                if(this.gameboard.getSquares()[a][c] != null) {
                    if(this.gameboard.getSquares()[a][c].getTeamColor() == teamColor) {
                        if(this.gameboard.getPiece(new ChessPosition(a, c)).getPieceType() == ChessPiece.PieceType.KING) {
                            reypos.setRow(a);
                            reypos.setColumn(c);
                            break;
                        }
                    }
                }
            }
        }
        for (int a=1; a<9; a++) {
            for(int c=1; c<9; c++) {
                if(this.gameboard.getSquares()[a][c] != null) {
                    if(this.gameboard.getSquares()[a][c].getTeamColor() != teamColor) {
                        var enemymoves = this.possibleMoves(new ChessPosition(a,c));
                        for (var cp:enemymoves) {
                            if(cp.getEndPosition().toString().equals(reypos.toString())) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean canEscape(TeamColor teamColor) {
        var tempgame = this.copy();
        tempgame.setTeamTurn(teamColor);
        for (int a=1; a<9; a++) {
            for(int c=1; c<9; c++) {
                if(tempgame.getBoard().getSquares()[a][c] != null) {
                    if(tempgame.getBoard().getSquares()[a][c].getTeamColor() == teamColor) {
                        var tppos = new ChessPosition(a,c);
                        var cmv = tempgame.validMoves(tppos);
                        if(cmv != null) {
                            for (var onecmv : cmv) {
                                try {
                                    tempgame.makeMove(onecmv);
                                    return true;
                                } catch (InvalidMoveException i) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        if(this.isInCheck(teamColor)) {
            if(!this.canEscape(teamColor)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        if (!isInCheck(teamColor)) {
            if(!canEscape(teamColor)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        this.gameboard = board;
    }

    public ChessGame copy() {
        ChessGame cpy = new ChessGame();
        //cpy.setBoard(this.gameboard);
        ChessBoard cpbrd = new ChessBoard();
        for (int a =1; a<9; a++){
            for(int c=1; c<9; c++) {
                var temppos = new ChessPosition(a,c);
                cpbrd.setSquares(temppos, this.gameboard.getSquares()[a][c]);
            }
        }
        cpy.setBoard(cpbrd);
        cpy.setTeamTurn(this.turncolor);
        return cpy;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return this.gameboard;
    }
}
