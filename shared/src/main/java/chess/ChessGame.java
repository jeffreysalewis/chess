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
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        if(this.gameboard.getPiece(startPosition) == null) {
            return null;
        }
        //System.out.println(this.gameboard.getSquares());
        return this.gameboard.getPiece(startPosition).pieceMoves(this.gameboard, startPosition);
        //return verdades;
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        if(this.validMoves(move.getStartPosition()).contains(move)) {
            this.gameboard.setSquares(move.getEndPosition(), this.gameboard.getPiece(move.getStartPosition()));
            this.gameboard.setSquares(move.getStartPosition(), null);
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
                        var enemymoves = this.validMoves(new ChessPosition(a,c));
                        for (var cp:enemymoves) {
                            if(cp.getEndPosition() == reypos) {
                                return true;
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
        return this.isInCheck(teamColor);
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        return !isInCheck(teamColor);
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        this.gameboard = board;
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
