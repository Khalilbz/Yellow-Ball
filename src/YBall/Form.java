package YBall;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Form {

	JFrame form;
	Canvas canvas;
	String title;
	int width;
	int height;
	
	public Form(String title,int width,int height){
		this.title = title;
		this.width = width;
		this.height = height;
		form = new JFrame(title);
		form.setSize(width, height);
		form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		form.setLocationRelativeTo(null);
		form.setResizable(false);
		form.setVisible(true);
		
		canvas = new Canvas();
		canvas.setSize(width, height);
		canvas.setMaximumSize(new Dimension(width,height));
		canvas.setMinimumSize(new Dimension(width,height));
		canvas.setFocusable(false);
		form.add(canvas);
		
		
	}
	
	public Canvas GetCanvas(){
		return canvas;
	}
	
	public JFrame GetForm(){
		return form;
	}


}
