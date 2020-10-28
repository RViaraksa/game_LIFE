
package by.home.vectree.bean;

import by.home.vectree.exception.NotRectangleFieldException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class FieldTest {

    @Test
    public void oneLifeCycle() throws NotRectangleFieldException {
        Cell[][] buffer = new Cell[3][3];
        Cell[][] result = new Cell[3][3];
        /**
         * Заполним матрицу следующими клетками
         *
         *    0 1 2
         * 0  _ * *
         * 1  _ * _
         * 2  _ _ _
         *
         * После пройденного жизненного цикла клетка должна выглядеть так
         *    0 1 2
         * 0  _ * *
         * 1  _ * *
         * 2  _ _ _
         */

        Cell с00 = new Cell(0, 0);
        Cell c10 = new Cell(1, 0);
        Cell c20 = new Cell(2, 0);
        Cell c01 = new Cell(0, 1);
        c01.setLive(true);
        Cell c11 = new Cell(1, 1);
        c11.setLive(true);
        Cell c21 = new Cell(2, 1);
        Cell c02 = new Cell(0 ,2);
        c02.setLive(true);
        Cell c12 = new Cell(1, 2);
        Cell c12After = new Cell(c12);
        c12After.setLive(true);
        Cell c22 = new Cell(2, 2);

        buffer[0][0] = с00;
        buffer[1][0] = c10;
        buffer[2][0] = c20;
        buffer[0][1] = c01;
        buffer[1][1] = c11;
        buffer[2][1] = c21;
        buffer[0][2] = c02;
        buffer[1][2] = c12;
        buffer[2][2] = c22;
        Field testField = new Field(buffer);
        testField.oneLifeCycle();

        result[0][0] = с00;
        result[1][0] = c10;
        result[2][0] = c20;
        result[0][1] = c01;
        result[1][1] = c11;
        result[2][1] = c21;
        result[0][2] = c02;
        result[1][2] = c12After;
        result[2][2] = c22;

       assertEquals(result, testField.getCells());

    }
}