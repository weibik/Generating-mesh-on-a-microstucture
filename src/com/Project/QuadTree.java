package com.Project;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class QuadTree {
    private final Rectangle area;
    private int capacity = 4;
    private List<Point> points = new ArrayList<>();
    private boolean divided = false;

    QuadTree northWest;
    QuadTree northEast;
    QuadTree southWest;
    QuadTree southEast;

    public QuadTree(Rectangle boundary){
        this.area = boundary;
    }

    public void show(BufferedImage image){
        Graphics g = image.getGraphics();
        g.setColor(Color.red);

        g.drawRect(area.getX(), area.getY(), area.getWidth() * 2, area.getHeight() * 2);
        if(this.divided){
            this.northWest.show(image);
            this.northEast.show(image);
            this.southWest.show(image);
            this.southEast.show(image);
        }
    }

    public void insert(Point point){
        if(!this.area.contains(point)){
            return;
        }

        if(this.points.size() < this.capacity){
            this.points.add(point);
        } else{
            if(!this.divided){
                this.subdivide();
            }

            if (this.northWest.area.contains(point))
                this.northWest.insert(point);
            else if (this.northEast.area.contains(point))
                this.northEast.insert(point);
            else if (this.southWest.area.contains(point))
                this.southWest.insert(point);
            else if (this.southEast.area.contains(point))
                this.southEast.insert(point);
        }
    }

    private void subdivide(){
        Rectangle ne = new Rectangle(area.getX() + area.getWidth() /2, area.getY() - area.getHeight() / 2,
                area.getWidth()/2, area.getHeight()/ 2);
        Rectangle nw = new Rectangle(area.getX() - area.width /2, area.getY() - area.getHeight() / 2,
                area.getWidth()/2, area.getHeight()/ 2);
        Rectangle se = new Rectangle(area.getX() + area.width /2, area.getY() + area.getHeight() / 2,
                area.getWidth()/2, area.getHeight()/ 2);
        Rectangle sw = new Rectangle(area.getX() - area.width /2, area.getY() + area.getHeight() / 2,
                area.getWidth()/2, area.getHeight()/ 2);

        this.northWest = new QuadTree(nw);
        this.southWest = new QuadTree(ne);
        this.northEast = new QuadTree(sw);
        this.southEast = new QuadTree(se);

        this.divided = true;

    }

}
