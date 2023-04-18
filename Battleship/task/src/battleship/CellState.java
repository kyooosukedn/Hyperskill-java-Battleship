package battleship;

public enum CellState {
    EMPTY('~'),
    OCCUPIED('O'),
    HIT('X'),
    MISSED('M');

    private char symbol;

    CellState(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "CellState{" +
                "symbol=" + symbol +
                '}';
    }
}
