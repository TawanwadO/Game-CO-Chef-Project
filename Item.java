/*  
    6413104 Panfa Tonsomboon
    6413111 Wisara Wongrattanapipat
    6413213 Tawanwad Onnom 
    6413222 Phumed Thumtechanon
 */
package Project3_213;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ButtonGroup;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;

public class Item extends JFrame { //implements ActionListener

    private int ItemSetting;

    private JLabel label;
    private String path = "src/main/Java/Project3_213/images/";
    private int frameWidth = 1300, frameHeight = 700;

    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem homeItem, exitItem;

    private MyButton back, start;
    private ImageIcon item, backButton, startButton;

    private JLabel example;
    private JLabel cusEX, itemEX;
    private ImageIcon cus, item_ex;

    private JRadioButton one, two, three, four, five;
    private int modeSetting;
    private Music M;
    private String N;

    public Item(Music a, String n, int m) {

        M=a;
        N=n;
        modeSetting = m;
        setTitle("CO CHEF - ITEM");
        setBounds(0, 0, frameWidth, frameHeight);

        //background
        setContentPane(label = new JLabel());
        item = new MyImageIcon(path + "item.png").resize(1300, 640);
        label.setIcon(item);
        label.setLayout(null);
        
        //cus
        cus = new MyImageIcon(path + "Customer1.png").resize(380, 380);
        cusEX = new JLabel();
        cusEX.setIcon(cus);
        cusEX.setBounds(160, 140, 380, 380);

        //radio button
        itemEX = new JLabel();
        itemEX.setBounds(160, 140, 380, 380);
        one = new JRadioButton("Normal");
        one.setBounds(730, 220, 300, 50);
        SetButton(one);
        one.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                itemEX.setIcon(null);
                repaint();
            }
        });
        two = new JRadioButton("Angel");
        two.setBounds(730, 280, 300, 50);
        SetButton(two);
        two.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                item_ex = new MyImageIcon(path + "Angel.png").resize(380, 380);
                itemEX.setIcon(item_ex);
                repaint();
            }
        });
        three = new JRadioButton("Devil");
        three.setBounds(730, 340, 300, 50);
        SetButton(three);
        three.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                item_ex = new MyImageIcon(path + "Devil.png").resize(380, 380);
                itemEX.setIcon(item_ex);
                repaint();
            }
        });
        four = new JRadioButton("Mexico Hat");
        four.setBounds(730, 400, 300, 50);
        SetButton(four);
        four.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                item_ex = new MyImageIcon(path + "MexicoHat.png").resize(380, 380);
                itemEX.setIcon(item_ex);
                repaint();
            }
        });
        five = new JRadioButton("80's Teens");
        five.setBounds(730, 460, 300, 50);
        SetButton(five);
        five.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                item_ex = new MyImageIcon(path + "80Teens.png").resize(380, 380);
                itemEX.setIcon(item_ex);
                repaint();
            }
        });
        one.setSelected(true);

        ButtonGroup g = new ButtonGroup();
        g.add(one);
        g.add(two);
        g.add(three);
        g.add(four);
        g.add(five);

        //button
        backButton = new MyImageIcon(path + "BackButton.png").resize(187, 97);
        back = new MyButton(backButton, 20, 530, 187, 97);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Setting(M, N);
            }
        });
        startButton = new MyImageIcon(path + "StartGame.png").resize(187, 97);
        start = new MyButton(startButton, 1093, 530, 187, 97);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (one.isSelected()) {
                    ItemSetting = 1;
                } else if (two.isSelected()) {
                    ItemSetting = 2;
                } else if (three.isSelected()) {
                    ItemSetting = 3;
                } else if (four.isSelected()) {
                    ItemSetting = 4;
                } else if (five.isSelected()) {
                    ItemSetting = 5;
                }
                dispose();
                new GamePlay2(M, N, modeSetting, ItemSetting);
            }
        });

        //menu bar
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        homeItem = new JMenuItem("Home");
        exitItem = new JMenuItem("Exit");
        homeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if (e.getSource() == homeItem) {
                    new Home(M);
                    dispose();
                }
            }
        });
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if (e.getSource() == exitItem) {
                    System.exit(0);
                }
            }
        });
        fileMenu.add(homeItem);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);

        label.add(back);
        label.add(start);
        label.add(one);
        label.add(two);
        label.add(three);
        label.add(four);
        label.add(five);
        label.add(itemEX);
        label.add(cusEX);
        setJMenuBar(menuBar);

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 700);
        setLayout(null);
        setVisible(true);

    }

    public void SetButton(JRadioButton a) {
        a.setFont(new Font("Consolas", Font.PLAIN, 40));
        a.setBackground(Color.WHITE);
    }  

};