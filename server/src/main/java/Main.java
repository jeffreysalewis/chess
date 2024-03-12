import chess.*;
import dataAccess.SqlAuthDAO;
import dataAccess.SqlGameDAO;
import dataAccess.SqlUserDAO;
import server.Server;

public class Main {
    public static void main(String[] args) {
        try {
            var port = 8080;
            if (args.length >= 1) {
                port = Integer.parseInt(args[0]);
            }
            try {
                var usql = new SqlUserDAO();
                var asql = new SqlAuthDAO();
                var gsql = new SqlGameDAO();
            } catch (Exception e) {

            }
            var server = new Server();
            server.run(port);
            port = server.port();
            System.out.printf("Server started on port %d%n", port);
            return;
        } catch (Throwable ex) {
            System.out.printf("Unable to start server: %s%n", ex.getMessage());
        }
        var piece = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        System.out.println("♕ 240 Chess Server: " + piece + "\njava ServerMain <port> [<dburl> <dbuser> <dbpassword> <dbname>]");
    }
}