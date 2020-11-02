package by.home.vectree.view;

import by.home.vectree.bean.Field;

public class Main {
    public static void main(String[] args) {
//        Field a = new Field(15);
//        System.out.println(a);
//        a.oneLifeCycle();
//        System.out.println(a);

//        GUI app = new GUI();
//        app.setVisible(true);

        FieldGUI fieldGUI = new FieldGUI(50);
        fieldGUI.go();
    }
}
