package by.home.vectree.bean;

public class Field {
    private Cell cells[][];


    /**метод возращайщий количиество живых ячеек воокруг указанной
     *
     * param cell выбранная ячйка из матрицы, например cells[x][z]
     * return cunt количесвто живых ячеек в матрице вокруг данной
     * */
    private int countLiveCells(Cell cell){
        int count = 0;
        int n = cell.getLine();
        int m = cell.getLine();
        /*если ячейка находиться в одном из крайних столбцов матрицы*/
        if((n > 0) & ( n < cells.length-1)){
            if (m == 0){
                count += isLife(n - 1, m) + isLife(n - 1, m + 1) + isLife(n, m + 1) +
                        isLife(n + 1, m + 1) + isLife(n + 1, m);
            }
            if (m == cells[0].length){
                count += isLife(n - 1, m) + isLife(n - 1, m - 1) + isLife(n, m - 1) +
                        isLife(n + 1, m - 1) + isLife(n + 1, m);
            }
        }

        /*если ячейка находится  в углу матрицы*/
        return 0;
    }

    /**метод проверяющий живая ли выбранная позиция клетки или нет
     *
     * param line номер строки матрицы
     * param column номер столбца
     * return isLive
     */
    private int isLife (int line, int column){
        int isLive = 0;
        if(cells[line][column].isLive()){
            isLive = 1;
        }
        return isLive;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

}
