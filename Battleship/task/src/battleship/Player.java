package battleship;

public class Player {

    private Board board;
    private int playerNum = 0;

    public Player(int num) {
        this.board = new Board(this);
        playerNum = num;
        // Every new player has an empty board to begin with
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard().length; j++) {
                board.setBoardEmpty(i, j);
            }
        }
    }


    public void setPlayer(int playerNum) {
        this.playerNum = playerNum;
    }

    public int getPlayerNum() {
        return this.playerNum;
    }

    public Board getPlayerBoard() {
        return this.board;
    }

    public void setPlayerBoard(Board board) { this.board = board;}

    public void printBoard() {
        char c = 'A';
        // First row
        for (int r = 1; r <= 10; r++) {
            if (r == 1) {
                System.out.print("  ");
            }
            System.out.print(r + " ");
        }
        // Next starting from A
        for (int i = 0; i < 10; i++) {
            if (i == 0) {
                System.out.println("");
            }
            System.out.print(c++ + " ");
            for (int j = 0; j < 10; j++) {
                System.out.print(board.getBoard()[i][j] + " ");

            }
            System.out.println("");

        }

        System.out.println(" ");
    }

    public void printEmptyBoard() {
        Board board = new Board();
        char c = 'A';
        // First row
        for (int r = 1; r <= 10; r++) {
            if (r == 1) {
                System.out.print("  ");
            }
            System.out.print(r + " ");
        }
        // Next starting from A
        for (int i = 0; i < 10; i++) {
            if (i == 0) {
                System.out.println("");
            }
            System.out.print(c++ + " ");
            for (int j = 0; j < 10; j++) {
                board.setBoardEmpty(i,j);
                System.out.print(board.getBoard()[i][j] + " ");

            }
            System.out.println("");

        }

        System.out.println(" ");
    }


}
