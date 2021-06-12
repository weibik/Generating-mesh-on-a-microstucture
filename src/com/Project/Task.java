package com.Project;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.TimerTask;

class Task {

    public static void doTask() throws IOException, InterruptedException {

        final BufferedImage image = ImageIO.read(new File("src/resources/singleSlice.png"));
        int width = image.getWidth();
        int height = image.getHeight();

        JPanel canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, this);
            }
        };

        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.add(canvas);
        frame.setSize(width, height);
        frame.setVisible(true);

        Graphics g = image.getGraphics();
        g.setColor(Color.red);

        Random rand = new Random();

        Rectangle area = new Rectangle(0, 0, width, height);
        QuadTree quadTree = new QuadTree(area);


        List<Point> pointsToTree = new ArrayList<>();
        for(int i = 0; i < width; i += 3){
            for(int j = 0 ; j < height; j += 3 ){
                Color tmpColor = new Color(image.getRGB(i, j), false);

                if( i - 1 > 0 && i + 1 < width && j + 1 < height && j - 1 > 0 ){
                    if((!tmpColor.equals(new Color(image.getRGB(i - 1, j), false))) ||
                            (!tmpColor.equals(new Color(image.getRGB(i - 1, j), false))) ||
                            (!tmpColor.equals(new Color(image.getRGB(i - 1, j), false))) ||
                            (!tmpColor.equals(new Color(image.getRGB(i - 1, j), false)))) {

                        pointsToTree.add(new Point(i, j));
                    }
                }
            }
        }

        for (Point point : pointsToTree) {
            g.drawRect(point.getX(), point.getY(), 1, 1);
            quadTree.insert(point);
        }

        quadTree.show(image);
        g.dispose();
        canvas.repaint();

    }

}
