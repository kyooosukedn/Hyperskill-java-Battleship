package battleship;

import java.util.Arrays;
import java.util.List;
import java.util.stream.*;

public class Ship {
    private String name;
    private int length;
    private List<Point> coordinates;
    boolean[] hitStatus;
    private Board board;

    public Ship(String name, int length, Board board) {
        this.name = name;
        this.length = length;
        this.board = board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<Point> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Point> coordinates) {
        this.coordinates = coordinates;
        this.hitStatus = new boolean[coordinates.size()];

    }

    public boolean isHit(Point point) {
        int index = coordinates.indexOf(point);
        if (index == -1) {
            return false;
        } else {
            return hitStatus[index];
        }
    }

    public boolean isSunk() {
        return IntStream.range(0, coordinates.size())
                .allMatch(i -> hitStatus[i]);
    }


    public boolean[] getHitStatus() {
        return this.hitStatus;
    }

    public boolean occupiedCell(int targetRow, int targetColumn) {
        if (board.getBoard()[targetRow][targetColumn] == CellState.OCCUPIED.getSymbol()) {
            return true;
        }
        return false;
    }

    public boolean missedCell(int targetRow, int targetColumn) {
        if (board.getBoard()[targetRow][targetColumn] == CellState.MISSED.getSymbol()) {
            return true;
        }
        return false;
    }

    public boolean emptyCell(int targetRow, int targetColumn) {
        if (board.getBoard()[targetRow][targetColumn] == CellState.EMPTY.getSymbol()) {
            return true;
        }
        return false;
    }
    public boolean checkHit(Point p) {
        for (int i = 0; i < coordinates.size(); i++) {
            if (coordinates.get(i).equals(p) && !emptyCell(p.getX(), p.getY())) {
                hitStatus[i] = true;
                board.setBoardHit(p.getX(), p.getY());
                return true;
            }
        }
        return false;
    }

    public void setPointHit(Point point) {
        for (int i = 0; i < coordinates.size(); i++) {
            if (coordinates.get(i).equals(point)) {
                hitStatus[i] = true;
            }
        }
    }


    public int getLength() {
        return this.length;
    }

    public String getName() {
        return this.name;
    }
}
