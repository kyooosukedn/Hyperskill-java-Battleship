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

    public boolean isSunk() {
        for (boolean hit : hitStatus) {
            if (!hit) {
                return false;
            }
        }
        return true;
    }

    public boolean[] getHitStatus() {
        return this.hitStatus;
    }

    public boolean checkHit(Point p) {
        for (int i = 0; i < coordinates.size(); i++) {
            if (coordinates.get(i).equals(p)) {
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


    boolean isHit(Point point) {
        return (coordinates.contains(point));
    }


    public int getLength() {
        return this.length;
    }

    public String getName() {
        return this.name;
    }





}
