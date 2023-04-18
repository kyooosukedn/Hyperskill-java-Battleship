package battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Board {
    private Player player;
    private char[][] board;
    private List<Ship> ships = new ArrayList<>();

    public Board() {
        this.board = new char[10][10];
    }

    public Board(Player player) {
        this.player = player;
        this.board = new char[10][10];
        this.player.setPlayerBoard(this);
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    public List<Ship> getShip(Ship ship) {
        return this.ships.stream().filter(s -> s.equals(ship)).collect(Collectors.toList());
    }


    public boolean playerShoots() {
        Scanner scanner = new Scanner(System.in);
        String targetCell = scanner.nextLine();
        int targetRow = targetCell.charAt(0) - 'A';
        int targetColumn = Integer.parseInt(targetCell.substring(1));
        if (targetRow < 0 || targetRow > 9 || targetColumn < 1 || targetColumn > 10) {
            System.out.println("Error! You entered the wrong coordinates! Try again:");
        }

        boolean isShipHit = false;
        boolean isShipSank = false;
        boolean gameOver = false;

        Point point = new Point(targetRow, targetColumn);

        for (Ship s : ships) {
            if (!s.checkHit(point)) {
                isShipHit = true;
                isShipSank = s.isSunk();
                break;
            }

        }

        if (isShipHit && !isShipSank) {
            System.out.println("You hit a ship!");
        } else if (isShipSank) {
            System.out.println("You sank a ship! Specify a new target:");
            gameOver = allShipsSunk();
            if (gameOver) {
                System.out.println("You sank the last ship. You won. Congratulations!");
                return true;
            }


        } else {
            System.out.println("You missed!");
            this.board[targetRow][targetRow] = CellState.MISSED.getSymbol();
        }
        return false;
    }



    // Method to check whether all ships have been hit
    public boolean allShipsSunk() {
        //return ships.stream().filter(ship -> !ship.isSunk()).count() == 0;
        //return ships.stream().allMatch(ship -> ship.isSunk());
        return ships.stream().noneMatch(ship -> !ship.isSunk());
    }

    public char[][] getBoard() {
        return this.board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public void setBoardEmpty(int row, int col) {

        board[row][col] = CellState.EMPTY.getSymbol();
    }

    public void setBoardOccupied(int row, int col) {
        board[row][col] = CellState.OCCUPIED.getSymbol();
    }

    public void setBoardHit(int row, int col) {
        board[row][col] = CellState.HIT.getSymbol();
    }

    public void setBoardMissed(int row, int col) {
        board[row][col] = CellState.MISSED.getSymbol();
    }

    public char getCellState(int row, int col) {
        return board[row][col];
    }

    public void setCellState(int row, int col, CellState cellState) {
         this.board[row][col] = cellState.getSymbol();
    }


    public List<Ship> getShips() {
        return ships;
    }
}
