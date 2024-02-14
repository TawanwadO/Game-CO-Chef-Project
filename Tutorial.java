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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Tutorial extends JFrame implements KeyListener {

    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem homeItem, exitItem;
    
    private String path = "src/main/Java/Project3_213/images/";
    private int frameWidth = 1300, frameHeight = 700;
    private ImageIcon tutorial;
    
    private JLabel label;
    private MyButton back;
    private ImageIcon backButton;
    private Music M;

    public Tutorial(Music m) {
        
        M=m;
        setTitle("CO CHEF - Tutorial");
        setBounds(0, 0, frameWidth, frameHeight);

        //background
        setContentPane(label = new JLabel());
        tutorial = new MyImageIcon(path + "tutorial.png").resize(1300, 640);
        label.setIcon(tutorial);
        label.setLayout(null);

        //button
        backButton = new MyImageIcon(path + "BackButton.png").resize(187, 97);
        back = new MyButton(backButton, 20, 530, 187, 97);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Home(M);
            }
        });
        back.addKeyListener(this);

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
        setJMenuBar(menuBar);

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 700);
        setLayout(null);
        setVisible(true);
    }
    
    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) { 
        if(e.getKeyCode() == KeyEvent.VK_LEFT){ new Home(M); dispose();}
    }

    @Override
    public void keyReleased(KeyEvent e) { }
    
};
