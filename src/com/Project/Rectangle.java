package com.Project;

public class Rectangle {
    int x;
    int y;
    int width;
    int height;

    public Rectangle(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean contains(Point point){
        return (point.getX() > this.x - this.width &&
                point.getX() < this.x + this.width &&
                point.getY() > this.y - this.height &&
                point.getY() < this.y + this.height);
    }

}
