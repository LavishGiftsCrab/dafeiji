package cn.game;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;


public class MyGameFrame extends Frame{
	
	Image planeImg = GameUtil.getImage("images/plane.png");
	Plane plane = new Plane(planeImg,250,250);
	Shell shell = new Shell();
	Shell[] shells = new Shell[50];
	Image back = GameUtil.getImage("images/back.png");
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(back,(int) 0, (int)0, null);
		plane.drawSelf(g);
		System.out.println(plane.getRect().width);
		System.out.println(plane.width);
		shell.draw(g);
		for(int i=0;i<shells.length;i++) {
			shells[i].draw(g);

			boolean peng = shells[i].getRect().intersects(plane.getRect());
			if(peng){
				System.out.println("!");
			}
		}
	}
	
	
	class PaintThread extends Thread{
		public void run() {
			while(true) {
				repaint();
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}
	
	
	class KeyMonitor extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			plane.addDirection(e);
			super.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			plane.minusDirection(e);
			super.keyReleased(e);
		}
		
	}
	
	public void launchFrame() {
		this.setTitle("打飞机");
		this.setVisible(true);
		this.setSize(500, 500);
		this.setLocation(300, 200);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		new PaintThread().start(); //启动线程
		addKeyListener(new KeyMonitor());//启动键盘监听
		
		//炮弹初始化：
		for(int i=0;i<shells.length;i++) {
			shells[i] = new Shell();
		}
	}
	
	 private Image offScreenImage = null;
	    public void update(Graphics g) {
	        if(offScreenImage == null)
	            offScreenImage = this.createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
	         
	        Graphics gOff = offScreenImage.getGraphics();
	 
	        paint(gOff);
	        g.drawImage(offScreenImage, 0, 0, null);
	    }
	
	public static void main(String[] args) {
		MyGameFrame f=new MyGameFrame();
		f.launchFrame();
	}

}
