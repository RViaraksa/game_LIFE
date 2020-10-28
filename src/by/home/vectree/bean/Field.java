package by.home.vectree.bean;

import java.util.Arrays;

public class Field {
    private Cell cells[][];
    private Cell bufferCells[][];

    /**
     * Конструкторы
     */
    public Field(){
        this.cells = new Cell[10][10];
        fillField(this.cells);
    }

    public Field(int size) {
        if ( size < 0) size *= -1;
        else if (size == 0) size = 10;
        this.cells = new Cell[size][size];
        fillField(this.cells);
    }

    /**
     * Метод заполняющий рандомно массив клетка, из которых 15% - живые
     */
    private void fillField(Cell[][] array){
        for (int i = 0; i <array.length ; i++){
            for (int j = 0; j < array[0].length ; j++){
                array[i][j] = new Cell(i, j);
            }
        }
        int countLiveCells = Math.round((array.length * array[0].length * 15) / 100);
        for (int i = 0 ; i <= countLiveCells; i++ ){
            int randomLine = (int) (Math.random() * (array.length - 1));
            int randomColumn = (int) (Math.random() * (array[0].length - 1));
            array[randomLine][randomColumn].setLive(true);
        }
    }

    /**
     * Метод отвечающй за один жизненный цикл
     */
    public void oneLifeCycle(){
        //создадим копию массива на основе имеющегося
        this.bufferCells = clone2DArray(this.cells);
        for (int i = 0; i < this.cells.length - 1; i++){
            for (int j = 0; j < this.cells[0].length - 1; j++){
                this.cells[i][j] = futureLiveCell(this.bufferCells[i][j]);
            }
        }
    }

    /**Метод клонирующий массив*/
    private Cell[][] clone2DArray (Cell[][] array){
        //создадим копию массива на основе имеющегося
        Cell buffer[][] = new Cell[array.length][array.length];
        for (int i = 0; i < array.length ; i++){
            for (int j = 0; j < array[0].length ; j++){
                buffer[i][j] = new Cell(array[i][j]);
            }
        }
        return buffer;
    }

    /**
     * Метод определяющий дальнейший жизнный цикл клетки:
     * клетка пустая, но рядом с ней 3 живых - на следующем цикле тут зарождается жизнь
     * клетка живая, но 1) 3 или 2 живых соседа - продолжает жить
     *                  2) меньше двух или больше 3 соседей - клетка умирает
     * param cell выбранная ячйка из матрицы, например cells[x][z]
     * return Cell возращает новый элемент клетка, у которого остались порядковые номера предидущие и состояние
     *             на следующем цикле
     */
    private Cell futureLiveCell(Cell cell){
        Cell buffer = new Cell(cell);
        if(cell.isLive()){
            if((countLiveCells(cell) == 2) || (countLiveCells(cell) == 3) ){
                buffer.setLive(true);
            }
            else buffer.setLive(false);
        }
        if ( !cell.isLive() ){
            if (countLiveCells(cell) == 3) buffer.setLive(true);
        }
        return buffer;
    }

    /**
     * метод возращайщий количиество живых ячеек воокруг указанной
     * param cell выбранная ячйка из матрицы, например cells[x][z]
     * return cunt количесвто живых ячеек в матрице вокруг данной
     * */
     private int countLiveCells(Cell cell){
        boolean notNearBorder = true;
        int count = 0;
        int n = cell.getLine();
        int m = cell.getColumn();

        /*если ячейка находиться в одном из крайних столбцов матрицы*/
        if((n > 0) & ( n < cells.length - 1)){
            if (m == 0){
                count += isLife(n - 1, m) + isLife(n - 1, m + 1) + isLife(n, m + 1) +
                        isLife(n + 1, m + 1) + isLife(n + 1, m);
                notNearBorder = false;
            }
            if (m == cells[0].length - 1){
                count += isLife(n - 1, m) + isLife(n - 1, m - 1) + isLife(n, m - 1) +
                        isLife(n + 1, m - 1) + isLife(n + 1, m);
                notNearBorder = false;
            }
        }

        /*если ячейка назодиться в первой либо последней строке матрицы*/
        if( (m > 0) & ( m < cells[0].length - 1)){
            if(n == 0 ){
                count += isLife(n, m - 1) + isLife(n + 1, m - 1) + isLife(n + 1, m) +
                        isLife(n + 1, m + 1) + isLife(n, m + 1);
                notNearBorder = false;
            }
            if(n == cells.length - 1 ){
                count += isLife(n, m - 1) + isLife(n - 1, m - 1)+ isLife(n - 1, m) +
                        isLife(n - 1, m + 1) + isLife(n, m +1);
                notNearBorder = false;
            }
        }

        /*если ячейка находится  в левом углу матрицы*/
        if((n == 0) & (m == 0)) {
            count += isLife(n + 1, m) + isLife(n + 1, m + 1) + isLife(n, m + 1);
            notNearBorder = false;
        }

        /*если ячйека находится в нижнем левом углу*/
        if ((n == cells.length - 1) & (m == 0)) {
            count += isLife(n - 1, m) + isLife(n - 1, m + 1) + isLife(n, m + 1);
            notNearBorder = false;
        }

        /*если ячейка нахдиться в верхнем правом углу*/
        if ((n == 0) & (m == cells[0].length - 1)) {
            count += isLife(n + 1, m) + isLife(n + 1, m - 1) + isLife(n, m - 1);
            notNearBorder = false;
        }

        /*если ячйека находится в нижнем правом углу*/
        if ((n == cells.length - 1) & (m == cells[0].length - 1)) {
            count += isLife(n - 1, m) + isLife(n - 1, m - 1) + isLife(n, m - 1);
            notNearBorder = false;
        }

        /*в случае того если ни одно из предидущих условий не сработало ячейка имеет вокруг себя 8 соседних ячеек*/
        if (notNearBorder){
            count += isLife(n - 1, m -1) + isLife(n -1, m) + isLife(n - 1, m + 1) +
                    isLife(n , m + 1) + isLife(n + 1, m + 1) + isLife(n + 1, m) +
                    isLife(n + 1, m - 1) + isLife(n, m - 1);
        }

        return count;
    }

    /**метод проверяющий живая ли выбранная позиция клетки или нет
     *
     * param line номер строки матрицы
     * param column номер столбца
     * return isLive
     */
    private int isLife (int line, int column){
        int isLive = 0;
        if(this.bufferCells[line][column].isLive()){
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.cells.length ; i++){
            for (int j = 0; j < this.cells[0].length ; j++){
                sb.append(this.cells[i][j]+" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
