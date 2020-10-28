package by.home.vectree.view;

import by.home.vectree.bean.Field;

public class Console {
    public static void main(String[] args) {
        Field a = new Field();
        System.out.println(a);
        a.oneLifeCycle();
        System.out.println(a);
    }
}
