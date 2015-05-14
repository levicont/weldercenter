package com.lvg.weldercenter.ui;

/**
 * Created by Victor on 07.05.2015.
 */

import net.miginfocom.swing.MigLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SplashSwingFrame extends JWindow{

    private final String IMAGE_FILE_NAME = "src/main/resources/img/splash_background.jpg";
    private final String LOADING_TEXT = "Загрузка";
    private Timer timer;
    private int doteCount = 0;
    private JWindow mainWindow;
    private JLabel lbCaption;
    private JLabel lbLoadingModuleName;
    private BackgroundPanel backgroundPanel;
    private JPanel pnTop;
    private JPanel pnBottom;
    private JButton btClose;
    private JProgressBar progressBar;





    public SplashSwingFrame(){
        super();
        mainWindow = this;
        this.setSize(650, 300);
        setOnScreenCenter();
        init();
        this.setVisible(true);

    }

    private void init(){
        lbCaption = new JLabel(LOADING_TEXT);
        lbCaption.setForeground(Color.ORANGE);


        timer = new Timer(1000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (doteCount==0) {
                    lbCaption.setText(LOADING_TEXT + " .    ");
                    doteCount++;
                    return;
                }
                if (doteCount==1) {
                    lbCaption.setText(LOADING_TEXT + " . .  ");
                    doteCount++;
                    return;
                }
                if (doteCount==2) {
                    lbCaption.setText(LOADING_TEXT + " . . .");
                    doteCount=0;
                    return;
                }
            }
        });
        timer.start();

        lbLoadingModuleName = new JLabel("Инициализация модулей базы данных");
        lbLoadingModuleName.setForeground(Color.ORANGE);

        backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(new MigLayout());

        File imgFile = new File(IMAGE_FILE_NAME);

        backgroundPanel.setImageFile(imgFile);


        pnTop = new JPanel();
        pnBottom = new JPanel();
        btClose = new JButton("close");
        btClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.dispose();
            }
        });

        progressBar = new JProgressBar();

        progressBar.setPreferredSize(new Dimension(this.getSize().width - 10, 7));
        progressBar.setBorderPainted(false);
        progressBar.setMaximum(100);
        progressBar.setValue(10);
        progressBar.setForeground(Color.blue);

        backgroundPanel.add(lbCaption,"gapright 333");
        backgroundPanel.add(lbLoadingModuleName,"gapright 25 ,wrap");
        backgroundPanel.add(progressBar,"height 5:5:5, gaptop 263, span");


        Container pane = this.getContentPane();

        pane.add(backgroundPanel, BorderLayout.CENTER);

    }

    private void setOnScreenCenter(){
        int x = Toolkit.getDefaultToolkit().getScreenSize().width/2-this.getSize().width/2;
        int y = Toolkit.getDefaultToolkit().getScreenSize().height/2-this.getSize().height/2;
        this.setLocation(x,y);
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public JLabel getLbLoadingModuleName() {
        return lbLoadingModuleName;
    }

    private class BackgroundPanel extends JPanel{

        private BufferedImage bufferedImage;
        private Image image;

        public BackgroundPanel(){
            initComponents();
        }

        private void initComponents(){
            this.setLayout(null);
            this.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    resizedComponents();
                }
            });
        }

        private void resizedComponents(){
            int w = this.getWidth();
            int h = this.getHeight();
            if ((bufferedImage != null) && (w > 0) && (h > 0)) {
                image = bufferedImage.getScaledInstance(w, h, Image.SCALE_DEFAULT);
                this.repaint();
            }
        }

        public void paint(Graphics g) {
            if (image != null) {
                g.drawImage(image, 0, 0, null);
            }
            super.paintChildren(g);
            super.paintBorder(g);
        }

        public BufferedImage getImage() {
            return bufferedImage;
        }

        public void setImage(BufferedImage image) {
            this.image = image;
        }
        public void setImageFile(File imageFile) {
            try {
                if (imageFile == null) {
                    bufferedImage = null;
                }
                BufferedImage bi = ImageIO.read(imageFile);
                bufferedImage = bi;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            repaint();
        }


    }
}
