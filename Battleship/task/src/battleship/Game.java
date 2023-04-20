package battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    static char[][] gameFieldCovered = new char[10][10];


    public void play() {
        boolean gameOver = false;
        Player player1 = new Player(1);
        Player player2 = new Player(2);
        placePlayerOneShips(player1);
        placePlayerTwoShips(player2);
        while (!gameOver) {
            // Player 1 turn
            player1.printEmptyBoard();
            System.out.println("---------------------");
            player1.printBoard();
            System.out.println("Player 1, it's your turn:");
            gameOver = player1.getPlayerBoard().playerShoots();
            if (gameOver) {
                break;
            }
            changeTurn();
            // Player 2 turn
            player2.printEmptyBoard();
            System.out.println("---------------------");
            player2.printBoard();
            System.out.println("Player 2, it's your turn:");
            gameOver = player2.getPlayerBoard().playerShoots();
            if (gameOver) {
                break;
            }
            changeTurn();
        }
    }

    private void playerTurn(Player player, Player opponent) {
        System.out.println("Player " + player.getPlayerNum() + ", it's your turn:");
        Scanner scanner = new Scanner (System.in);
        String input = scanner.nextLine();
        int x = input.charAt(0) - 'A';
        int y = input.charAt(1) - 'A';
        Point point = new Point(x,y);
        if (opponent.getPlayerBoard().getShips().stream().filter(s -> s.checkHit(point)).count() == 1) {
            System.out.println("You hit a ship!");
        } else {
            System.out.println("You missed!");
        }
    }

    private void changeTurn() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();
    }

    public void placePlayerOneShips(Player player1) {
        Scanner scanner = new Scanner(System.in);

        // Player 1 turns
        // Place Aircraft Carrier
        System.out.println("Player " + player1.getPlayerNum() + ", place your ships on the game field");

        player1.printBoard();

        Ship aircraft = new Ship("Aircraft Carrier", 5, player1.getPlayerBoard());
        placeAShip(aircraft, player1);

        // Place Battleship

        Ship battleship = new Ship("Battleship", 4, player1.getPlayerBoard());
        placeAShip(battleship, player1);

        // Place submarine
        Ship submarine = new Ship("Submarine", 3, player1.getPlayerBoard());
        placeAShip(submarine, player1);

        // Place Cruiser

        Ship cruiser = new Ship("Cruiser", 3, player1.getPlayerBoard());
        placeAShip(cruiser, player1);

        // Place Destroyer

        Ship destroyer = new Ship("Destroyer", 2, player1.getPlayerBoard());
        placeAShip(destroyer, player1);

        List<Ship> ships = new ArrayList<>();
        ships.add(aircraft);
        ships.add(battleship);
        ships.add(submarine);
        ships.add(cruiser);
        ships.add(destroyer);


        player1.getPlayerBoard().setShips(ships);

        scanner = new Scanner(System.in);
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();

    }

    public void placePlayerTwoShips(Player player2) {
        Scanner scanner = new Scanner(System.in);


        // Player 2 turns
        System.out.println("Player 2, place your ships on the game field");

        player2.printEmptyBoard();

        Ship aircraft2 = new Ship("Aircraft Carrier", 5, player2.getPlayerBoard());
        placeAShip(aircraft2, player2);

        // Place Battleship

        Ship battleship2 = new Ship("Battleship", 4, player2.getPlayerBoard());
        placeAShip(battleship2, player2);

        // Place submarine
        Ship submarine2 = new Ship("Submarine", 3, player2.getPlayerBoard());
        placeAShip(submarine2, player2);

        // Place Cruiser

        Ship cruiser2 = new Ship("Cruiser", 3, player2.getPlayerBoard());
        placeAShip(cruiser2, player2);

        // Place Destroyer

        Ship destroyer2 = new Ship("Destroyer", 2, player2.getPlayerBoard());
        placeAShip(destroyer2, player2);

        List<Ship> ships = new ArrayList<>();
        ships.add(aircraft2);
        ships.add(battleship2);
        ships.add(submarine2);
        ships.add(cruiser2);
        ships.add(destroyer2);


        player2.getPlayerBoard().setShips(ships);

        scanner = new Scanner(System.in);
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();

    }


    public void placeAShip(Ship ship, Player player) {
        Scanner scanner = new Scanner(System.in);
        int length = ship.getLength();
        Board playerBoard = player.getPlayerBoard();
        char[][] gameField = playerBoard.getBoard();

        boolean placed = false;
        do {
            System.out.println("Enter the coordinates of " + ship.getName() + "(" + length + " cells): ");
            String input = scanner.nextLine();
            String[] coordinates = input.split(" ");
            if (coordinates.length != 2) {
                System.out.println("Error! Invalid number of coordinates");
                continue;
            }
            ship.setCoordinates(parseShipCoordinates(coordinates[0], coordinates[1]));
            int row1 = coordinates[0].charAt(0) - 'A';
            int col1 = Integer.parseInt(coordinates[0].substring(1)) - 1;
            int row2 = coordinates[1].charAt(0) - 'A';
            int col2 = Integer.parseInt(coordinates[1].substring(1)) - 1;
            if (isWrongLocation(row1, col1, row2, col2)) continue;


            int inputLength = Math.max(Math.abs(row1 - row2) + 1, Math.abs(col1 - col2) + 1);
            if (inputLength != length) {
                System.out.println("Error! Wrong length of the " + ship.getName() + "! Try again:");
                continue;
            }

            // isNoNeighbour
            if (!isNoNeighbour(row1,col1,row2,col2,gameField)) {
                System.out.println("Error! You placed it too close to another one. Try again:");
                continue;
            }
            int rowMin = Math.min(row1, row2);
            int rowMax = Math.max(row1, row2);
            int colMin = Math.min(col1, col2);
            int colMax = Math.max(col1, col2);


            if (row1 == row2) {
                boolean occupied = false;
                for (int col = colMin; col <= colMax; col++) {
                    if (gameField[row1][col] != CellState.EMPTY.getSymbol()) {
                        System.out.println("Error! Cell is already occupied");
                        occupied = true;
                        break;
                    }
                }
                if (!occupied) {
                    for (int col = colMin; col <= colMax; col++) {
                        playerBoard.setBoardOccupied(row1, col);
                    }
                    placed = true;
                }
            } else {
                boolean occupied = false;
                for (int row = rowMin; row <= rowMax; row++) {
                    if (gameField[row][col1] != CellState.EMPTY.getSymbol()) {
                        System.out.println("Error! Cell is already occupied");
                        occupied = true;
                        break;
                    }
                }
                if (!occupied) {
                    for (int row = rowMin; row <= rowMax; row++) {
                        playerBoard.setBoardOccupied(row,col1);
                    }
                    placed = true;
                }
            }
            player.printBoard();
        } while (!placed);

    }


    static boolean isWrongLocation(int row1, int col1, int row2, int col2) {
        if (row1 < 0 || row1 > 9 || col1 < 0 || col1 > 9 || row2 < 0 || row2 > 9 || col2 < 0 || col2 > 9) {
            System.out.println("Error! Coordinates are out of bounds.");
            return true;
        }
        if (row1 != row2 && col1 != col2) {
            System.out.println("Error! Wrong ship location! Try again:");
            return true;
        }
        return false;
    }

    private static boolean shipHit(Player player, int row1, int col1) {
        Point point = new Point(row1,col1);
        for (Ship s : player.getPlayerBoard().getShips()) {
            if (s.isHit(point)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNoNeighbour(int row1, int col1, int row2, int col2, char[][] gameField) {
        int rowMin = Math.min(row1, row2);
        int rowMax = Math.max(row1, row2);
        int colMin = Math.min(col1, col2);
        int colMax = Math.max(col1, col2);

        // Check for neighboring cells horizontally
        for (int row = rowMin; row <= rowMax; row++) {
            for (int col = colMin; col <= colMax; col++) {
                if (row >= 0 && col >= 0 && col < 10) {
                    if (gameField[row][col] != '~') {
                        return false;
                    }
                }
            }
        }

        // Check for neighboring cells vertically
        for (int col = colMin; col <= colMax; col++) {
            for (int row = rowMin - 1; row <= rowMax + 1; row++) {
                if (row >= 0 && row < 10 && col >= 0 && col < 10) {
                    if (gameField[row][col] != '~') {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static List<Point> parseShipCoordinates(String beginning, String end) {
        int startX = beginning.charAt(0) - 'A';
        int startY = Integer.parseInt(beginning.substring(1)) - 1;
        int endX = end.charAt(0) - 'A';
        int endY = Integer.parseInt(end.substring(1)) - 1;

        List<Point> shipCoordinates = new ArrayList<>();

        // Add beginning point
        shipCoordinates.add(new Point(startX, startY));

        // Add points in between
        int diffX = endX - startX;
        int diffY = endY - startY;
        int steps = Math.max(Math.abs(diffX), Math.abs(diffY));
        for (int i = 1; i < steps; i++) {
            int currX = startX + i * diffX / steps;
            int currY = startY + i * diffY / steps;
            shipCoordinates.add(new Point(currX, currY));
        }

        // Add end point
        shipCoordinates.add(new Point(endX, endY));

        return shipCoordinates;
    }


}
