package Project3_213;

import Project3_213.Music;
import Project3_213.MyImageIcon;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.*;
import java.awt.event.*;

public class GamePlay2 extends JFrame {

    private JLabel contentpane;
    private DragLabel hamberger1, hamberger2, hamberger3, hamberger4, hamberger5, hamberger6;

    private GamePlay2 currentframe;
    private JTextField scoreText, timeText;
    private int score;

    private int frameWidth = 1285, frameHeight = 665;

    private int mode, item, timeUp = 1; // set mode and item
    private Music M;
    private int volume;
    private String filePath = "src/main/Java/Project3_213/";
    private String audio = filePath + "audio.txt";
    private String N;
    private MySoundEffect wrongSound, correctSound;
    private String[] soundFiles = {"src/main/Java/Project3_213/music/Incorrect.wav", "src/main/Java/Project3_213/music/Checkbill.wav"};
    private ImageIcon background;
    
    private JLabel textS, textT;

    public GamePlay2(Music a, String n, int m, int i) {
        
        mode = m;
        item = i;
        N = n;
        M = a;
        String path = "src/main/Java/Project3_213/images/";
        set_Item();

        setTitle("Gameplay");
        setBounds(0, 0, frameWidth, frameHeight);
        
        setContentPane(contentpane = new JLabel());
        contentpane.setIcon(background);
        contentpane.setLayout(null); //image can move 
        setSize(1300, 700);
        setLayout(null);
        
        wrongSound = new MySoundEffect(soundFiles[0]);
        correctSound = new MySoundEffect(soundFiles[1]);    

        try {
            Scanner scan = new Scanner(new File(audio));
            volume = scan.nextInt();
            scan.close();

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        M.getSoundEffect().currentVolume = volume;

        if (M.getSoundEffect().currentVolume == 0) {
            M.getSoundEffect().currentVolume = -80;
        } else if (M.getSoundEffect().currentVolume == 100) {
            M.getSoundEffect().currentVolume = 6;
        } else if (M.getSoundEffect().currentVolume < 100 && M.getSoundEffect().currentVolume >= 90) {
            M.getSoundEffect().currentVolume = 0;
        } else if (M.getSoundEffect().currentVolume < 90 && M.getSoundEffect().currentVolume >= 80) {
            M.getSoundEffect().currentVolume = -6;
        } else if (M.getSoundEffect().currentVolume < 80 && M.getSoundEffect().currentVolume >= 70) {
            M.getSoundEffect().currentVolume = -12;
        } else if (M.getSoundEffect().currentVolume < 70 && M.getSoundEffect().currentVolume >= 60) {
            M.getSoundEffect().currentVolume = -18;
        } else if (M.getSoundEffect().currentVolume < 60 && M.getSoundEffect().currentVolume >= 50) {
            M.getSoundEffect().currentVolume = -24;
        } else if (M.getSoundEffect().currentVolume < 50 && M.getSoundEffect().currentVolume >= 40) {
            M.getSoundEffect().currentVolume = -30;
        } else if (M.getSoundEffect().currentVolume < 40 && M.getSoundEffect().currentVolume >= 30) {
            M.getSoundEffect().currentVolume = -36;
        } else if (M.getSoundEffect().currentVolume < 30 && M.getSoundEffect().currentVolume >= 20) {
            M.getSoundEffect().currentVolume = -40;
        } else if (M.getSoundEffect().currentVolume < 20 && M.getSoundEffect().currentVolume >= 10) {
            M.getSoundEffect().currentVolume = -46;
        } else if (M.getSoundEffect().currentVolume < 10 && M.getSoundEffect().currentVolume > 0) {
            M.getSoundEffect().currentVolume = -52;
        }

        M.getSoundEffect().fc.setValue(M.getSoundEffect().currentVolume);

        hamberger1 = new DragLabel(path + "HamBurger.PNG", 55, 500, currentframe);
        contentpane.add(hamberger1);

        hamberger2 = new DragLabel(path + "ChickenBurger.PNG", 255, 500, currentframe);
        contentpane.add(hamberger2);

        hamberger3 = new DragLabel(path + "FishBurger.PNG", 455, 500, currentframe);
        contentpane.add(hamberger3);

        hamberger4 = new DragLabel(path + "BaconBurger.PNG", 655, 500, currentframe);
        contentpane.add(hamberger4);

        hamberger5 = new DragLabel(path + "Frenchfry.PNG", 855, 490, currentframe);
        contentpane.add(hamberger5);

        hamberger6 = new DragLabel(path + "SoftDrink.PNG", 1055, 480, currentframe);
        contentpane.add(hamberger6);
        setOrderThread();

        textS = new JLabel();
        textS.setText("0");
        textS.setBounds(200, 20, 50, 30);
        textS.setFont(new Font("Italic", Font.BOLD, 30));
        contentpane.add(textS);
        
        textT = new JLabel();
        textT.setText("...");
        textT.setBounds(1110, 20, 50, 30);
        textT.setFont(new Font("Italic", Font.BOLD, 30));
        contentpane.add(textT);
        setModeTime();

        repaint();
        
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    
    public void set_Item() {
        
        String path = "src/main/Java/Project3_213/images/";
        switch(item){
            case 1 :
                M.change_normal();
                background = new MyImageIcon(path + "GameplayBG.PNG");
                break;
            case 2 :
                M.change_angel();
                background = new MyImageIcon(path + "GameplayBG1.PNG");                
                break;
            case 3 :
                M.change_devil();
                background = new MyImageIcon(path + "GameplayBG2.PNG");
                break;
            case 4 :
                M.change_mexicohat();
                background = new MyImageIcon(path + "GameplayBG3.PNG");
                break;
            case 5 :
                M.change_teens();
                background = new MyImageIcon(path + "GameplayBG4.PNG");
                break;
        }
        
    }

    public int gettimeUp() {
        return timeUp;
    }

    public void setTimeThread(int t) {
        Thread timeThread = new Thread() {
            public void run() {
                timeUp = 1;
                for (int i = t; i >= 0; i--) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    textT.setText(String.valueOf(i));
                    if (i == 0) {
                        timeUp = 0;
                        dispose();
                        new TimeUp(M, N, mode, item, score);
                    }
                }
            }// end run  
        };// end thread creation
        timeThread.start();
    }

    public void setOrderThread() {
        Thread orderThread = new Thread() {
            public void run() {

                while (gettimeUp() != 0) {
                    Order order = new Order(currentframe);
                    contentpane.add(order);

                    ITEMlabel ITEM=new ITEMlabel(currentframe,item);
                    contentpane.add(ITEM);
                     
                    Customer customer = new Customer(currentframe);
                    contentpane.add(customer);
                 
                     
                    while (customer.getcurX() > 500 && ITEM.getcurX()>480) {

                        if (mode == 1) {
                            customer.updateLocation(700);
                            ITEM.updateLocation(0);
                        } else if (mode == 2) {
                            customer.updateLocation(500);
                             ITEM.updateLocation(0);
                        } else if (mode == 3) {
                            customer.updateLocation(350);
                             ITEM.updateLocation(0);
                        } else if (mode == 4) {
                            customer.updateLocation(180);
                             ITEM.updateLocation(0);
                        } else if (mode == 5) {
                            customer.updateLocation(130);
                             ITEM.updateLocation(0);
                        }

                        if (order.getOrder() == 0) {
                            if (hamberger1.getBounds().intersects(customer.getBounds())) {
                                updateScore(10);
                                contentpane.remove(hamberger1);                               
                                repaint();
                                contentpane.remove(ITEM);
                                repaint();
                                playCorrectSound();
                                hamberger1 = new DragLabel("src/main/Java/Project3_213/images/HamBurger.PNG", 55, 500, currentframe);
                                contentpane.add(hamberger1);
                                break;
                            } else if (hamberger2.getBounds().intersects(customer.getBounds()) || hamberger3.getBounds().intersects(customer.getBounds()) || hamberger4.getBounds().intersects(customer.getBounds()) || hamberger5.getBounds().intersects(customer.getBounds()) || hamberger6.getBounds().intersects(customer.getBounds())) {
                                updateScore(-7);
                                playWrongSound();
                                contentpane.remove(ITEM);
                                repaint();
                                if (hamberger2.getBounds().intersects(customer.getBounds())) {
                                    contentpane.remove(hamberger2);
                                    repaint();
                                    hamberger2 = new DragLabel("src/main/Java/Project3_213/images/ChickenBurger.PNG", 255, 500, currentframe);
                                    contentpane.add(hamberger2);
                                }
                                if (hamberger3.getBounds().intersects(customer.getBounds())) {
                                    contentpane.remove(hamberger3);
                                    repaint();
                                    hamberger3 = new DragLabel("src/main/Java/Project3_213/images/FishBurger.PNG", 455, 500, currentframe);
                                    contentpane.add(hamberger3);
                                }
                                if (hamberger4.getBounds().intersects(customer.getBounds())) {
                                    contentpane.remove(hamberger4);
                                    repaint();
                                    hamberger4 = new DragLabel("src/main/Java/Project3_213/images/BaconBurger.PNG", 655, 500, currentframe);
                                    contentpane.add(hamberger4);
                                }
                                if (hamberger5.getBounds().intersects(customer.getBounds())) {
                                    contentpane.remove(hamberger5);
                                    repaint();
                                    hamberger5 = new DragLabel("src/main/Java/Project3_213/images/Frenchfry.PNG", 855, 490, currentframe);
                                    contentpane.add(hamberger5);
                                }
                                if (hamberger6.getBounds().intersects(customer.getBounds())) {
                                    contentpane.remove(hamberger6);
                                    repaint();
                                    hamberger6 = new DragLabel("src/main/Java/Project3_213/images/SoftDrink.PNG", 1055, 480, currentframe);
                                    contentpane.add(hamberger6);
                                }
                                break;
                            }
                        } else if (order.getOrder() == 1) {
                            if (hamberger2.getBounds().intersects(customer.getBounds())) {
                                updateScore(10);
                                contentpane.remove(hamberger2);
                                repaint();
                                contentpane.remove(ITEM);
                                repaint();
                                playCorrectSound();
                                hamberger2 = new DragLabel("src/main/Java/Project3_213/images/ChickenBurger.PNG", 255, 500, currentframe);
                                contentpane.add(hamberger2);
                                break;
                            } else if (hamberger1.getBounds().intersects(customer.getBounds()) || hamberger3.getBounds().intersects(customer.getBounds()) || hamberger4.getBounds().intersects(customer.getBounds()) || hamberger5.getBounds().intersects(customer.getBounds()) || hamberger6.getBounds().intersects(customer.getBounds())) {
                                updateScore(-7);
                                playWrongSound();
                                contentpane.remove(ITEM);
                                repaint();
                                if (hamberger1.getBounds().intersects(customer.getBounds())) {
                                    contentpane.remove(hamberger1);
                                    repaint();
                                    hamberger1 = new DragLabel("src/main/Java/Project3_213/images/HamBurger.PNG", 55, 500, currentframe);
                                    contentpane.add(hamberger1);
                                }
                                if (hamberger3.getBounds().intersects(customer.getBounds())) {
                                    contentpane.remove(hamberger3);
                                    repaint();
                                    hamberger3 = new DragLabel("src/main/Java/Project3_213/images/FishBurger.PNG", 455, 500, currentframe);
                                    contentpane.add(hamberger3);
                                }
                                if (hamberger4.getBounds().intersects(customer.getBounds())) {
                                    contentpane.remove(hamberger4);
                                    repaint();
                                    hamberger4 = new DragLabel("src/main/Java/Project3_213/images/BaconBurger.PNG", 655, 500, currentframe);
                                    contentpane.add(hamberger4);
                                }
                                if (hamberger5.getBounds().intersects(customer.getBounds())) {
                                    contentpane.remove(hamberger5);
                                    repaint();
                                    hamberger5 = new DragLabel("src/main/Java/Project3_213/images/Frenchfry.PNG", 855, 490, currentframe);
                                    contentpane.add(hamberger5);
                                }
                                if (hamberger6.getBounds().intersects(customer.getBounds())) {
                                    contentpane.remove(hamberger6);
                                    repaint();
                                    hamberger6 = new DragLabel("src/main/Java/Project3_213/images/SoftDrink.PNG", 1055, 480, currentframe);
                                    contentpane.add(hamberger6);
                                }
                                break;
                            }
                        } else if (order.getOrder() == 2) {
                            if (hamberger3.getBounds().intersects(customer.getBounds())) {
                                updateScore(10);
                                contentpane.remove(hamberger3);
                                repaint();
                                contentpane.remove(ITEM);
                                repaint();
                                playCorrectSound();
                                hamberger3 = new DragLabel("src/main/Java/Project3_213/images/FishBurger.PNG", 455, 500, currentframe);
                                contentpane.add(hamberger3);
                                break;
                            } else if (hamberger1.getBounds().intersects(customer.getBounds()) || hamberger2.getBounds().intersects(customer.getBounds()) || hamberger4.getBounds().intersects(customer.getBounds()) || hamberger5.getBounds().intersects(customer.getBounds()) || hamberger6.getBounds().intersects(customer.getBounds())) {
                                updateScore(-7);
                                playWrongSound();
                                contentpane.remove(ITEM);
                                repaint();
                                if (hamberger1.getBounds().intersects(customer.getBounds())) {
                                    contentpane.remove(hamberger1);
                                    repaint();
                                    hamberger1 = new DragLabel("src/main/Java/Project3_213/images/HamBurger.PNG", 55, 500, currentframe);
                                    contentpane.add(hamberger1);
                                }
                                if (hamberger2.getBounds().intersects(customer.getBounds())) {
                                    contentpane.remove(hamberger2);
                                    repaint();
                                    hamberger2 = new DragLabel("src/main/Java/Project3_213/images/ChickenBurger.PNG", 255, 500, currentframe);
                                    contentpane.add(hamberger2);
                                }
                                if (hamberger4.getBounds().intersects(customer.getBounds())) {
                                    contentpane.remove(hamberger4);
                                    repaint();
                                    hamberger4 = new DragLabel("src/main/Java/Project3_213/images/BaconBurger.PNG", 655, 500, currentframe);
                                    contentpane.add(hamberger4);
                                }
                                if (hamberger5.getBounds().intersects(customer.getBounds())) {
                                    contentpane.remove(hamberger5);
                                    repaint();
                                    hamberger5 = new DragLabel("src/main/Java/Project3_213/images/Frenchfry.PNG", 855, 490, currentframe);
                                    contentpane.add(hamberger5);
                                }
                                if (hamberger6.getBounds().intersects(customer.getBounds())) {
                                    contentpane.remove(hamberger6);
                                    repaint();
                                    hamberger6 = new DragLabel("src/main/Java/Project3_213/images/SoftDrink.PNG", 1055, 480, currentframe);
                                    contentpane.add(hamberger6);
                                }
                                break;
                            }
                        } else if (order.getOrder() == 3) {
                            if (hamberger4.getBounds().intersects(customer.getBounds())) {
                                updateScore(10);
                                contentpane.remove(hamberger4);
                                repaint();
                                contentpane.remove(ITEM);
                                repaint();
                                playCorrectSound();
                                hamberger4 = new DragLabel("src/main/Java/Project3_213/images/BaconBurger.PNG", 655, 500, currentframe);
                                contentpane.add(hamberger4);
                                break;
                            } else if (hamberger1.getBounds().intersects(customer.getBounds()) || hamberger2.getBounds().intersects(customer.getBounds()) || hamberger3.getBounds().intersects(customer.getBounds()) || hamberger5.getBounds().intersects(customer.getBounds()) || hamberger6.getBounds().intersects(customer.getBounds())) {
                                updateScore(-7);
                                playWrongSound();
                                contentpane.remove(ITEM);
                                repaint();
                                if (hamberger1.getBounds().intersects(customer.getBounds())) {
                                    contentpane.remove(hamberger1);
                                    repaint();
                                    hamberger1 = new DragLabel("src/main/Java/Project3_213/images/HamBurger.PNG", 55, 500, currentframe);
                                    contentpane.add(hamberger1);
                                }
                                if (hamberger2.getBounds().intersects(customer.getBounds())) {
                                    contentpane.remove(hamberger2);
                                    repaint();
                                    hamberger2 = new DragLabel("src/main/Java/Project3_213/images/ChickenBurger.PNG", 255, 500, currentframe);
                                    contentpane.add(hamberger2);
                                }
                                if (hamberger3.getBounds().intersects(customer.getBounds())) {
                                    contentpane.remove(hamberger3);
                                    repaint();
                                    hamberger3 = new DragLabel("src/main/Java/Project3_213/images/FishBurger.PNG", 455, 500, currentframe);
                                    contentpane.add(hamberger3);
                                }
                                if (hamberger5.getBounds().intersects(customer.getBounds())) {
                                    contentpane.remove(hamberger5);
                                    repaint();
                                    hamberger5 = new DragLabel("src/main/Java/Project3_213/images/Frenchfry.PNG", 855, 490, currentframe);
                                    contentpane.add(hamberger5);
                                }
                                if (hamberger6.getBounds().intersects(customer.getBounds())) {
                                    contentpane.remove(hamberger6);
                                    repaint();
                                    hamberger6 = new DragLabel("src/main/Java/Project3_213/images/SoftDrink.PNG", 1055, 480, currentframe);
                                    contentpane.add(hamberger6);
                                }
                                break;
                            }
                        } else if (order.getOrder() == 4) {
                            if (hamberger5.getBounds().intersects(customer.getBounds())) {
                                updateScore(10);
                                contentpane.remove(hamberger5);
                                repaint();
                                contentpane.remove(ITEM);
                                repaint();
                                playCorrectSound();
                                hamberger5 = new DragLabel("src/main/Java/Project3_213/images/Frenchfry.PNG", 855, 490, currentframe);
                                contentpane.add(hamberger5);
                                break;
                            } else if (hamberger1.getBounds().intersects(customer.getBounds()) || hamberger2.getBounds().intersects(customer.getBounds()) || hamberger3.getBounds().intersects(customer.getBounds()) || hamberger4.getBounds().intersects(customer.getBounds()) || hamberger6.getBounds().intersects(customer.getBounds())) {
                                updateScore(-7);
                                playWrongSound();
                                contentpane.remove(ITEM);
                                repaint();
                                if (hamberger1.getBounds().intersects(customer.getBounds())) {
                                    contentpane.remove(hamberger1);
                                    repaint();
                                    hamberger1 = new DragLabel("src/main/Java/Project3_213/images/HamBurger.PNG", 55, 500, currentframe);
                                    contentpane.add(hamberger1);
                                }
                                if (hamberger2.getBounds().intersects(customer.getBounds())) {
                                    contentpane.remove(hamberger2);
                                    repaint();
                                    hamberger2 = new DragLabel("src/main/Java/Project3_213/images/ChickenBurger.PNG", 255, 500, currentframe);
                                    contentpane.add(hamberger2);
                                }
                                if (hamberger3.getBounds().intersects(customer.getBounds())) {
                                    contentpane.remove(hamberger3);
                                    repaint();
                                    hamberger3 = new DragLabel("src/main/Java/Project3_213/images/FishBurger.PNG", 455, 500, currentframe);
                                    contentpane.add(hamberger3);
                                }
                                if (hamberger4.getBounds().intersects(customer.getBounds())) {
                                    contentpane.remove(hamberger4);
                                    repaint();
                                    hamberger4 = new DragLabel("src/main/Java/Project3_213/images/BaconBurger.PNG", 655, 500, currentframe);
                                    contentpane.add(hamberger4);
                                }
                                if (hamberger6.getBounds().intersects(customer.getBounds())) {
                                    contentpane.remove(hamberger6);
                                    repaint();
                                    hamberger6 = new DragLabel("src/main/Java/Project3_213/images/SoftDrink.PNG", 1055, 480, currentframe);
                                    contentpane.add(hamberger6);
                                }
                                break;
                            }
                        } else if (order.getOrder() == 5) {
                            if (hamberger6.getBounds().intersects(customer.getBounds())) {
                                updateScore(10);
                                contentpane.remove(hamberger6);
                                repaint();
                                contentpane.remove(ITEM);
                                repaint();
                                playCorrectSound();
                                hamberger6 = new DragLabel("src/main/Java/Project3_213/images/SoftDrink.PNG", 1055, 480, currentframe);
                                contentpane.add(hamberger6);
                                break;
                            } else if (hamberger1.getBounds().intersects(customer.getBounds()) || hamberger2.getBounds().intersects(customer.getBounds()) || hamberger3.getBounds().intersects(customer.getBounds()) || hamberger4.getBounds().intersects(customer.getBounds()) || hamberger5.getBounds().intersects(customer.getBounds())) {
                                updateScore(-7);
                                playWrongSound();
                                contentpane.remove(ITEM);
                                repaint();
                                if (hamberger1.getBounds().intersects(customer.getBounds())) {
                                    contentpane.remove(hamberger1);
                                    repaint();
                                    hamberger1 = new DragLabel("src/main/Java/Project3_213/images/HamBurger.PNG", 55, 500, currentframe);
                                    contentpane.add(hamberger1);
                                }
                                if (hamberger2.getBounds().intersects(customer.getBounds())) {
                                    contentpane.remove(hamberger2);
                                    repaint();
                                    hamberger2 = new DragLabel("src/main/Java/Project3_213/images/ChickenBurger.PNG", 255, 500, currentframe);
                                    contentpane.add(hamberger2);
                                }
                                if (hamberger3.getBounds().intersects(customer.getBounds())) {
                                    contentpane.remove(hamberger3);
                                    repaint();
                                    hamberger3 = new DragLabel("src/main/Java/Project3_213/images/FishBurger.PNG", 455, 500, currentframe);
                                    contentpane.add(hamberger3);
                                }
                                if (hamberger4.getBounds().intersects(customer.getBounds())) {
                                    contentpane.remove(hamberger4);
                                    repaint();
                                    hamberger4 = new DragLabel("src/main/Java/Project3_213/images/BaconBurger.PNG", 655, 500, currentframe);
                                    contentpane.add(hamberger4);
                                }
                                if (hamberger5.getBounds().intersects(customer.getBounds())) {
                                    contentpane.remove(hamberger5);
                                    repaint();
                                    hamberger5 = new DragLabel("src/main/Java/Project3_213/images/Frenchfry.PNG", 855, 490, currentframe);
                                    contentpane.add(hamberger5);
                                }
                                break;
                            }
                        }
                        repaint();
                    }
                    contentpane.remove(customer);
                    repaint();
                    contentpane.remove(ITEM);
                    repaint();
                    
                    contentpane.remove(order);
                    repaint();
                }
            }// end run  
        };// end thread creation
        orderThread.start();
    }

    public void playWrongSound() {
        wrongSound.playOnce();
    }

    public void playCorrectSound() {
        correctSound.playOnce();
    }

    public void updateScore(int hp) {
        if(hp>0){
            if(mode==1){}
            else if (mode==2){ hp+=5;  }
            else if (mode==3){ hp+=10; }
            else if (mode==4){ hp+=15; }
            else if (mode==5){ hp+=20; }
        }
        else{
            if(mode==1){}
            else if (mode==2){ hp-=4; }
            else if (mode==3){ hp-=7; }
            else if (mode==4){ hp-=11; }
            else if (mode==5){ hp-=14; }
        }
        score += hp;
        textS.setText(String.valueOf(score));
    }

    public void setModeTime() {
        if (mode == 1) {
            setTimeThread(180);
        } else if (mode == 2) {
            setTimeThread(150);
        } else if (mode == 3) {
            setTimeThread(120);
        } else if (mode == 4) {
            setTimeThread(90);
        } else if (mode == 5) {
            setTimeThread(60);
        }
    }

};

class DragLabel extends Customer implements MouseMotionListener {

    protected MyImageIcon hamberger;
    protected int width = 185, height = 185 ;

    public DragLabel(String file, int x, int y, GamePlay2 pf ) {
        super(pf);       
        hamberger = new MyImageIcon(file).resize(width, height);
        setBounds(x, y, width, height);
        setIcon(hamberger);
        addMouseMotionListener(this);
    }
   

    @Override
    public void mouseDragged(MouseEvent e) {

        setCursor(new Cursor(Cursor.MOVE_CURSOR));

        curX = curX + e.getX();
        curY = curY + e.getY();
        
        if (curX < 0) {
            curX = 0;
        }
        if (curY < 0) {
            curY = 0;
        }
        if (curX + width >1285) {
            curX = 1285 - width;
        }
        if (curY + height > 665) {
            curY = 665 - height;
        }
        setLocation(curX, curY);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
};

class Order extends JLabel {

    protected MyImageIcon Order;
    protected GamePlay2 parentframe;
    protected int curX = 245, curY = 80;
    protected int width = 400, height = 300;
    private int randomOrder;

    String path = "src/main/Java/Project3_213/images/";

    private String[] imageFiles = {path + "HamBurger.PNG",
        path + "ChickenBurger.PNG", path + "FishBurger.PNG",
        path + "BaconBurger.PNG", path + "Frenchfry.PNG",
        path + "SoftDrink.PNG"};

    public Order(GamePlay2 pf) {
        parentframe = pf;

        Random random = new Random();
        randomOrder = random.nextInt(6);
        Order = new MyImageIcon(imageFiles[randomOrder]).resize(220, 220);

        setIcon(Order);
        setBounds(curX, curY, width, height);
    }

    public int getOrder() {
        return randomOrder;
    }
}

class Customer extends JLabel {

    protected MyImageIcon Cus;
    protected GamePlay2 parentframe;
    protected int curX = 1100, curY = 75;
    protected int width = 500, height = 400;

    String path = "src/main/Java/Project3_213/images/";

    private String[] imageFiles = {path + "Customer1.PNG",
        path + "Customer2.PNG", path + "Customer3.PNG",
        path + "Customer4.PNG", path + "Customer5.PNG",
        path + "Customer6.PNG"};

    private int c, t;

    public Customer(GamePlay2 pf) {
        parentframe = pf;

        Random random = new Random();
        c = random.nextInt(6);
        t = c;
        Cus = new MyImageIcon(imageFiles[t]).resize(300, 300);

        setIcon(Cus);
        setBounds(curX, curY, width, height);
    }

    public void updateLocation(int m) {
        curX -= 65;

        setLocation(curX, curY);
        repaint();

        try {
            Thread.sleep(m);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getcurX() {
        return curX;
    }

}//end class customer

class ITEMlabel extends JLabel {

    protected MyImageIcon ImageItem;
    protected GamePlay2 parentframe;
    protected int curX = 1035, curY = 75;
    protected int width = 500, height = 400;

    String path = "src/main/Java/Project3_213/images/";

    private int c, t;

    public ITEMlabel(GamePlay2 pf,int i) {
        parentframe = pf;

        if(i==1)
        { setIcon(null);}
        else{
            switch (i) {
                case 2:
                    ImageItem = new MyImageIcon(path + "Angel.png").resize(300, 300);
                    break;
                case 3:
                    ImageItem = new MyImageIcon(path + "Devil.png").resize(300, 300);
                    break;
                case 4:
                    ImageItem = new MyImageIcon(path + "MexicoHat.png").resize(300, 300);
                    break;
                case 5:
                    ImageItem = new MyImageIcon(path + "80Teens.png").resize(300, 300);
                    break;
                default:
                    break;
            }
         
         setIcon( ImageItem);
        }             
        setBounds(curX, curY, width, height);
    }

    public void updateLocation(int m) {
        curX -= 65;

        setLocation(curX, curY);
        repaint();

        try {
            Thread.sleep(m);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
     public int getcurX() {
        return curX;
    }

  

}//end class item
