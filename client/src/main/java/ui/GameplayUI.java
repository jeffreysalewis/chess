package ui;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;

import static ui.EscapeSequences.*;

public class GameplayUI {
    private static final int BOARD_SIZE_IN_SQUARES = 3;
    private static final int SQUARE_SIZE_IN_CHARS = 3;
    private static final int LINE_WIDTH_IN_CHARS = 1;
    private static final String EMPTY = "   ";
    private static Random rand = new Random();

    public static boolean bw = false;


    public static void main(String[] args) {
        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);

        out.print(ERASE_SCREEN);
        if(bw) {
            drawChessBoardWhite(out);
            drawChessBoardBlack(out);
        } else {
            drawChessBoardBlack(out);
            drawChessBoardWhite(out);
        }
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_WHITE);
    }

    private static void drawChessBoardWhite(PrintStream out) {
        out.print(SET_BG_COLOR_BLUE);
        out.print(SET_TEXT_COLOR_BLACK);
        out.print(BLACK_ROOK);
        out.print(SET_BG_COLOR_RED);
        out.print(SET_TEXT_COLOR_BLACK);
        out.print(BLACK_KNIGHT);
        out.print(SET_BG_COLOR_BLUE);
        out.print(SET_TEXT_COLOR_BLACK);
        out.print(BLACK_BISHOP);
        out.print(SET_BG_COLOR_RED);
        out.print(SET_TEXT_COLOR_BLACK);
        out.print(BLACK_QUEEN);
        out.print(SET_BG_COLOR_BLUE);
        out.print(SET_TEXT_COLOR_BLACK);
        out.print(BLACK_KING);
        out.print(SET_BG_COLOR_RED);
        out.print(SET_TEXT_COLOR_BLACK);
        out.print(BLACK_BISHOP);
        out.print(SET_BG_COLOR_BLUE);
        out.print(SET_TEXT_COLOR_BLACK);
        out.print(BLACK_KNIGHT);
        out.print(SET_BG_COLOR_RED);
        out.print(SET_TEXT_COLOR_BLACK);
        out.print(BLACK_ROOK);
        out.print(SET_BG_COLOR_GREEN);
        out.print(SET_TEXT_COLOR_BLACK);
        out.println(" 8 ");
        for (int a=0; a<8; a++) {
            if (a % 2 == 1) {
                out.print(SET_BG_COLOR_BLUE);
                out.print(SET_TEXT_COLOR_BLACK);
                out.print(BLACK_PAWN);
            } else {
                out.print(SET_BG_COLOR_RED);
                out.print(SET_TEXT_COLOR_BLACK);
                out.print(BLACK_PAWN);
            }
        }
        out.print(SET_BG_COLOR_GREEN);
        out.print(SET_TEXT_COLOR_BLACK);
        out.println(" 7 ");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 1) {
                    setRed(out);
                    out.print(BLACK_PAWN);
                } else {
                    setBlue(out);
                    out.print(BLACK_PAWN);
                }
            }
            out.print(SET_BG_COLOR_GREEN);
            out.print(SET_TEXT_COLOR_BLACK);
            out.print(" ");
            out.println(6-i);
        }
        for (int a=0; a<8; a++) {
            if (a % 2 == 0) {
                out.print(SET_BG_COLOR_BLUE);
                out.print(SET_TEXT_COLOR_WHITE);
                out.print(WHITE_PAWN);
            } else {
                out.print(SET_BG_COLOR_RED);
                out.print(SET_TEXT_COLOR_WHITE);
                out.print(WHITE_PAWN);
            }
        }
        out.print(SET_BG_COLOR_GREEN);
        out.print(SET_TEXT_COLOR_BLACK);
        out.println(" 2 ");
        out.print(SET_BG_COLOR_RED);
        out.print(SET_TEXT_COLOR_WHITE);
        out.print(WHITE_ROOK);
        out.print(SET_BG_COLOR_BLUE);
        out.print(SET_TEXT_COLOR_WHITE);
        out.print(WHITE_KNIGHT);
        out.print(SET_BG_COLOR_RED);
        out.print(SET_TEXT_COLOR_WHITE);
        out.print(WHITE_BISHOP);
        out.print(SET_BG_COLOR_BLUE);
        out.print(SET_TEXT_COLOR_WHITE);
        out.print(WHITE_QUEEN);
        out.print(SET_BG_COLOR_RED);
        out.print(SET_TEXT_COLOR_WHITE);
        out.print(WHITE_KING);
        out.print(SET_BG_COLOR_BLUE);
        out.print(SET_TEXT_COLOR_WHITE);
        out.print(WHITE_BISHOP);
        out.print(SET_BG_COLOR_RED);
        out.print(SET_TEXT_COLOR_WHITE);
        out.print(WHITE_KNIGHT);
        out.print(SET_BG_COLOR_BLUE);
        out.print(SET_TEXT_COLOR_WHITE);
        out.print(WHITE_ROOK);
        out.print(SET_BG_COLOR_GREEN);
        out.print(SET_TEXT_COLOR_BLACK);
        out.println(" 1 ");
        out.print(SET_TEXT_COLOR_BLACK);
        out.println("  a     b    c     d    e    f     g     h  ");
        out.println();
    }

    private static void drawChessBoardBlack(PrintStream out) {
        out.print(SET_BG_COLOR_BLUE);
        out.print(SET_TEXT_COLOR_WHITE);
        out.print(WHITE_ROOK);
        out.print(SET_BG_COLOR_RED);
        out.print(SET_TEXT_COLOR_WHITE);
        out.print(WHITE_KNIGHT);
        out.print(SET_BG_COLOR_BLUE);
        out.print(SET_TEXT_COLOR_WHITE);
        out.print(WHITE_BISHOP);
        out.print(SET_BG_COLOR_RED);
        out.print(SET_TEXT_COLOR_WHITE);
        out.print(WHITE_KING);
        out.print(SET_BG_COLOR_BLUE);
        out.print(SET_TEXT_COLOR_WHITE);
        out.print(WHITE_QUEEN);
        out.print(SET_BG_COLOR_RED);
        out.print(SET_TEXT_COLOR_WHITE);
        out.print(WHITE_BISHOP);
        out.print(SET_BG_COLOR_BLUE);
        out.print(SET_TEXT_COLOR_WHITE);
        out.print(WHITE_KNIGHT);
        out.print(SET_BG_COLOR_RED);
        out.print(SET_TEXT_COLOR_WHITE);
        out.print(WHITE_ROOK);
        out.print(SET_BG_COLOR_GREEN);
        out.print(SET_TEXT_COLOR_BLACK);
        out.println(" 1 ");
        for (int a=0; a<8; a++) {
            if (a % 2 == 1) {
                out.print(SET_BG_COLOR_BLUE);
                out.print(SET_TEXT_COLOR_WHITE);
                out.print(WHITE_PAWN);
            } else {
                out.print(SET_BG_COLOR_RED);
                out.print(SET_TEXT_COLOR_WHITE);
                out.print(WHITE_PAWN);
            }
        }
        out.print(SET_BG_COLOR_GREEN);
        out.print(SET_TEXT_COLOR_BLACK);
        out.println(" 2 ");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 1) {
                    setRed(out);
                    out.print(BLACK_PAWN);
                } else {
                    setBlue(out);
                    out.print(BLACK_PAWN);
                }
            }
            out.print(SET_BG_COLOR_GREEN);
            out.print(SET_TEXT_COLOR_BLACK);
            out.print(" ");
            out.println(i+3);
        }
        for (int a=0; a<8; a++) {
            if (a % 2 == 0) {
                out.print(SET_BG_COLOR_BLUE);
                out.print(SET_TEXT_COLOR_BLACK);
                out.print(BLACK_PAWN);
            } else {
                out.print(SET_BG_COLOR_RED);
                out.print(SET_TEXT_COLOR_BLACK);
                out.print(BLACK_PAWN);
            }
        }
        out.print(SET_BG_COLOR_GREEN);
        out.print(SET_TEXT_COLOR_BLACK);
        out.println(" 7 ");
        out.print(SET_BG_COLOR_RED);
        out.print(SET_TEXT_COLOR_BLACK);
        out.print(BLACK_ROOK);
        out.print(SET_BG_COLOR_BLUE);
        out.print(SET_TEXT_COLOR_BLACK);
        out.print(BLACK_KNIGHT);
        out.print(SET_BG_COLOR_RED);
        out.print(SET_TEXT_COLOR_BLACK);
        out.print(BLACK_BISHOP);
        out.print(SET_BG_COLOR_BLUE);
        out.print(SET_TEXT_COLOR_BLACK);
        out.print(BLACK_KING);
        out.print(SET_BG_COLOR_RED);
        out.print(SET_TEXT_COLOR_BLACK);
        out.print(BLACK_QUEEN);
        out.print(SET_BG_COLOR_BLUE);
        out.print(SET_TEXT_COLOR_BLACK);
        out.print(BLACK_BISHOP);
        out.print(SET_BG_COLOR_RED);
        out.print(SET_TEXT_COLOR_BLACK);
        out.print(BLACK_KNIGHT);
        out.print(SET_BG_COLOR_BLUE);
        out.print(SET_TEXT_COLOR_BLACK);
        out.print(BLACK_ROOK);
        out.print(SET_BG_COLOR_GREEN);
        out.print(SET_TEXT_COLOR_BLACK);
        out.println(" 8 ");
        out.print(SET_TEXT_COLOR_BLACK);
        out.println("  h     g    f     e    d    c     b     a  ");
        out.println();
    }

    private static void setWhite(PrintStream out) {
        out.print(SET_BG_COLOR_WHITE);
        out.print(SET_TEXT_COLOR_WHITE);
    }

    private static void setRed(PrintStream out) {
        out.print(SET_BG_COLOR_RED);
        out.print(SET_TEXT_COLOR_RED);
    }

    private static void setBlue(PrintStream out) {
        out.print(SET_BG_COLOR_BLUE);
        out.print(SET_TEXT_COLOR_BLUE);
    }

    private static void setBlack(PrintStream out) {
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_BLACK);
    }

    private static void printPlayer(PrintStream out, String player) {
        out.print(SET_BG_COLOR_WHITE);
        out.print(SET_TEXT_COLOR_BLACK);

        out.print(player);

        setWhite(out);
    }
}
