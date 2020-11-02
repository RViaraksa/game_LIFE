package by.home.vectree.view;

import by.home.vectree.bean.Field;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends  JFrame {
    final static String NAME_OF_GAME = "Game LIFE";
    final static String NAME_OF_RUN = "Create game";
    final static String DEFAULT_SIZE_FIELD  = "10";
    final static String DEFAULT_SIZE = "Set size";
    final static int START_LOCATION = 100;
    final static int SIZE_WINDOW = 300;



    private JButton run = new JButton(NAME_OF_RUN);
    private JTextField inputSizeOfField = new JTextField(DEFAULT_SIZE_FIELD,2);
    private JLabel defaultSize = new JLabel(DEFAULT_SIZE);
    private JRadioButton setRandomePlace = new JRadioButton("Random");
    private JRadioButton setOtherMode = new JRadioButton("Not aviable");

    public GUI(){
        super(NAME_OF_GAME);
        this.setBounds(START_LOCATION, START_LOCATION, SIZE_WINDOW, SIZE_WINDOW);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3,2,2,2));

        container.add(defaultSize);
        container.add(inputSizeOfField);

        ButtonGroup buttonGroupSelectMode = new ButtonGroup();
        buttonGroupSelectMode.add(setRandomePlace);
        buttonGroupSelectMode.add(setOtherMode);
        setRandomePlace.setSelected(true);
        container.add(setRandomePlace);
        container.add(setOtherMode);

        run.addActionListener(new ButtonRunEventListener());
        container.add(run);

    }

    class ButtonRunEventListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int sizeField = Integer.parseInt(inputSizeOfField.getText());
            FieldGUI fieldGUI = new FieldGUI(sizeField);
            fieldGUI.go();

//            Field field = new Field(sizeField);
//            String message = field.toString();
//            field.oneLifeCycle();
//            message += field.toString();
//            JOptionPane.showMessageDialog(null,fieldGUI);
        }
    }
}
