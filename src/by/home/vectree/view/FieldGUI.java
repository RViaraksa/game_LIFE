package by.home.vectree.view;

import by.home.vectree.bean.Field;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FieldGUI extends JComponent {

    private Field field;
    private int live_size;
    private int field_size;
    private Canvas canvasPanel;
    private volatile boolean goNextGeneration = false;
    final int POINT_RADIUS = 10;
    final String NAME_OF_GAME = "Conway's Game of Life";
    final int START_LOCATION = 200;
    final int BTN_PANEL_HEIGHT = 58;
    private int showDelay = 200;

    public FieldGUI(int size){
        if (size <= 0 || size >= 100){
            size = 50;
        }
        this.live_size = size;
        this.field_size = this.live_size * POINT_RADIUS + 7;
        this.field = new Field(size);
    }

    public void go() {
        JFrame frame = new JFrame(NAME_OF_GAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(field_size, field_size + BTN_PANEL_HEIGHT);
        frame.setLocation(START_LOCATION, START_LOCATION);
        frame.setResizable(false);
        frame.setVisible(true);

        canvasPanel = new Canvas();
        canvasPanel.setBackground(Color.white);

        /**
         * Заполняем наше полтно (Canvas) элементами нашего поля field
         */
        JButton fillButton = new JButton("Fill");
        fillButton.addActionListener(new FillButtonListener());

        /**
         * Перезаполнем наше полотно осуществляя один жизненный цикл
         */
        JButton stepButton = new JButton("Step");
        stepButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                field.oneLifeCycle();
                canvasPanel.repaint();
            }
        });


        final JButton goButton = new JButton("Play");
        goButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goNextGeneration = !goNextGeneration;
                goButton.setText(goNextGeneration? "Stop" : "Play");
            }
        });

        JPanel btnPanel = new JPanel();
        btnPanel.add(fillButton);
        btnPanel.add(stepButton);
        btnPanel.add(goButton);

        frame.getContentPane().add(BorderLayout.CENTER, canvasPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, btnPanel);
        frame.setVisible(true);

        // endless loop of life
        while (true) {
            if (goNextGeneration) {
                this.field.oneLifeCycle();
                canvasPanel.repaint();
                try {
                    Thread.sleep(showDelay);
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
    }


    // paint on the canvas
    private class Canvas extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            for (int x = 0; x < live_size; x++) {
                for (int y = 0; y < live_size; y++) {
                    if (field.getCellByNumber(x, y).isLive()) {
                        g.fillOval(x*POINT_RADIUS, y*POINT_RADIUS, POINT_RADIUS, POINT_RADIUS);
                    }
                }
            }
        }
    }


    private class FillButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            canvasPanel.repaint();
        }
    }
}
