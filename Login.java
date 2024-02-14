/*  
    6413104 Panfa Tonsomboon
    6413111 Wisara Wongrattanapipat
    6413213 Tawanwad Onnom 
    6413222 Phumed Thumtechanon
*/

package Project3_213;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JTextField;

public class Login extends JFrame {
    
    private JLabel label;
    private String path = "src/main/Java/Project3_213/images/";
    private int frameWidth = 1300, frameHeight = 700;

    private MyButton back, next;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem homeItem, exitItem;
    private JTextField textField;
    private ImageIcon backButton, nextButton, start;
    private Music M;
    private String N;

    public Login(Music m, String n) {
        
        M=m;
        N=n;
        setTitle("CO CHEF - LOGIN");
        setBounds(0, 0, frameWidth, frameHeight);
        
        //background
        setContentPane(label = new JLabel());
        start = new MyImageIcon(path + "start.png").resize(1300, 640);
        label.setIcon(start);
        label.setLayout(null);
        
        //textfield
        textField = new JTextField();
        textField.setSize(500, 50);
        textField.setLocation(400, 300);
        textField.setFont(new Font("Consolas", Font.PLAIN, 25));
        textField.setForeground(Color.black);
        textField.setBackground(Color.white);
        textField.setCaretColor(Color.black);
        if(N=="") textField.setText("Enter your name");
        else textField.setText(N);
        
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
        nextButton = new MyImageIcon(path + "NextButton.png").resize(187, 97);
        next = new MyButton(nextButton, 1093, 530, 187, 97);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Setting(M,textField.getText());
            }
        });
        

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
        label.add(next);
        label.add(textField);
        setJMenuBar(menuBar);

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 700);
        setLayout(null);
        setVisible(true);
    }
}
