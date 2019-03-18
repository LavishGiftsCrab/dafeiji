package cn.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Plane extends GameObject {
	
	boolean left,up,down,right;
	int speed=3;
	 //int height = 40,width = 40;//************************************************************
	
	
	public Plane(Image img,double x,double y) {
		//super.width=30;//*********************************************************************
		this.img=img;
		this.x= x;
		this.y = y;
		/*this.height = 30;
		this.width = 30;*/
	}
	
	public void drawSelf(Graphics g) {
		g.drawImage(img,(int) x, (int)y, null);
		if(left) {
			x-=speed;
		}
		if(right) {
			x+=speed;
		}
		if(up) {
			y-=speed;
		
		}
		if(down) {
			y+=speed;
		}
	}
	
	public void addDirection(KeyEvent e) {
		switch(e.getKeyCode()) {
		case 37:
			left=true;
			break;
		case 38:
			up=true;
			break;
		case 39:
			right=true;
			break;
		case 40:
			down=true;
			break;
		}
	}
	public void minusDirection(KeyEvent e) {
		switch(e.getKeyCode()) {
		case 37:
			left=false;
			break;
		case 38:
			up=false;
			break;
		case 39:
			right=false;
			break;
		case 40:
			down=false;
			break;
		}
	}
	/*public Rectangle getRect() {//*****************************************************************
		return new Rectangle((int)x,(int)y,width,height);
	}
	*/

}
