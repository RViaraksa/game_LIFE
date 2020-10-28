package by.home.vectree.bean;

public class Cell {
    private boolean isLive;
    private int line;
    private int column;

    Cell(int line, int column){
        this.line = line;
        this.column = column;
    }

    Cell(Cell cell){
        this.line = cell.getLine();
        this.column = cell.getColumn();
        this.isLive = cell.isLive();
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        if (isLive != cell.isLive) return false;
        if (line != cell.line) return false;
        return column == cell.column;
    }

    @Override
    public int hashCode() {
        int result = (isLive ? 1 : 0);
        result = 31 * result + line;
        result = 31 * result + column;
        return result;
    }

    @Override
    public String toString() {
       String ret;
       if (isLive) ret = "*";
       else ret = "_";
       return ret;
    }
}
