package YBall;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game {
	
	int[][] world = 
			{
			{1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,0,2,2,0,0,0,0,3,3,1},
			{1,1,1,1,1,1,1,0,1,0,1,1,1},
			{1,2,1,0,0,0,0,0,1,0,1,0,1},
			{1,0,1,0,1,0,1,0,1,2,1,0,1},
			{1,0,0,0,1,2,0,0,1,1,1,2,1},
			{1,0,1,1,1,0,1,1,1,0,0,0,1},
			{1,0,1,0,0,0,0,0,1,0,1,0,1},
			{1,3,1,0,1,2,1,2,1,3,1,0,1},
			{1,2,1,0,1,1,1,0,1,1,1,0,1},
			{1,2,1,2,0,0,0,0,0,0,2,2,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1}
			}
			;
	Form form;
	KeyManager key;
	BufferStrategy bs;
	Graphics g;
	String title;
	int width;
	int height;
	int xp = 1;
	int yp = 1;
	int header = 50 ;
	int scores = 0;
	int eat = 0;
	int time = 10;
	int timesec = 0;
	String stat = "stop";
	
	
	public Game(String title,int width,int height){
		this.title = title;
		this.width = width;
		this.height = height;
		form = new Form(title,width,height);
		key = new KeyManager();
		
	}
	
	
	public void Run(){
		//Initialisation
		form.GetForm().addKeyListener(key);
		
		while(true){
			//q++;
			bs = form.GetCanvas().getBufferStrategy();
			if(bs == null){
				form.GetCanvas().createBufferStrategy(3);
				bs = form.GetCanvas().getBufferStrategy();
			}
			g = bs.getDrawGraphics();
			g.clearRect(0, 0, width, height);
			
			//////////////////////////////////////////////
			
			//Background
			g.setColor(Color.white);
			g.fillRect(0, 0, width, height);
			
			//Calculating Time
			timeproc();
			
			//Showing The Map (World)
			int i,j;
			for(i=0;i<12;i++){
			for(j=0;j<13;j++){
				if(world[i][j] == 1){ Stone(i,j); }
				if(world[i][j] == 2){ Score(i,j); }
				if(world[i][j] == 3){ heart(i,j); }
				//if(world[i][j] == 5){ Player(i,j); }
			}} 
			
			//Movement
			if(stat == "playing"){
			if(key.up && (world[yp-1][xp] != 1 )){yp -= 1; key.up = false;}
			if(key.down && (world[yp+1][xp] !=1 )){yp += 1; key.down = false;}
			if(key.right && (world[yp][xp+1] !=1 )){xp += 1; key.right = false;}
			if(key.left && (world[yp][xp-1] !=1 )){xp -= 1; key.left = false;}
			}
			
			//Calculating (Score+Time)
			if(world[yp][xp] == 2){ world[yp][xp] = 0; scores += 1; eat++;}
			if(world[yp][xp] == 3){ world[yp][xp] = 0; time += 5;}
			
			//Showing Player
			Player(yp,xp);
			
			//Showing Header
			headerproc();
			
			//Control panel
			controlp();
			
			///////////////////////////////////////////////
			
			bs.show();
			g.dispose();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.print(q+"\n");
		}
		
	}
	
	private void controlp() {
		if(stat == "stop"){
		g.setColor(Color.black);
		g.fillRect((width /2)-200, (height/2)-50, 400,100);
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		g.setColor(Color.white);
		g.drawString("Press ENTER To Start The Game", (width /2)-150, (height/2));
		if(key.enter){
			stat = "playing";
		}}
		if(stat == "win" || stat == "lose"){
			g.setColor(Color.black);
			g.fillRect((width /2)-200, (height/2)-50, 400,100);
			g.setFont(new Font("Arial", Font.PLAIN, 20));
			g.setColor(Color.white);
			g.drawString("Press ENTER To Play again", (width /2)-140, (height/2));
			if(key.enter){ 
				xp = 1;
				yp = 1;
				scores = 0;
				eat = 0;
				time = 10;
				timesec = 0;
				stat = "playing";
			}
		}
	}


	public void headerproc() {
		Score(-1,0);
		g.setColor(Color.green);
		g.setFont(new Font("Arial", Font.PLAIN, 40));
		g.drawString(Integer.toString(scores), 60, 40);
		g.setColor(Color.blue);
		g.drawString(Integer.toString(time), width-100, 40);
		if(stat=="playing"){
		if(scores >= 13){
			/*g.setColor(Color.green);
			g.drawString("You Win", (width/2)-80, 40);*/
			stat = "win";
			scores += time;
			
		}
		if(stat!="playing"){
		if(time == 0){
			g.setColor(Color.red);
			g.drawString("You Lose", (width/2)-80, 40);
			stat = "lose";}else{ g.setColor(Color.green);
			g.drawString("You Win", (width/2)-80, 40); }
		}
		}
		
	}
	
	public void timeproc(){
		if(time > 0 && stat=="playing"){
		timesec++;
		if(timesec > 60){ timesec = 0; time--; }
		}
	}


	public void Stone(int y,int x){
		int xx = x*50;
		int yy = (y*50)+header;
		g.setColor(Color.black);
		g.fillRect(xx+1, yy+1, 48, 48);
		g.setColor(Color.gray);
		g.fillRect(xx+5, yy+5, 40, 40);
		g.setColor(Color.black);
		g.drawLine(xx+1, yy+1, xx+48, yy+48);
		g.drawLine(xx+48,yy+1,xx+1,   yy+48);
	}
	
	public void Player(int y,int x){
		int xx = x*50;
		int yy = (y*50)+header;
		g.setColor(Color.yellow);
		g.fillArc(xx, yy, 50, 50, 0, 360);
		g.setColor(Color.black);
		g.fillArc(xx+ 7, yy+10, 15, 25, 0, 360);
		g.fillArc(xx+27, yy+10, 15, 25, 0, 360);
		g.setColor(Color.white);
		g.fillArc(xx+12, yy+12, 5, 8, 0, 360);
		g.fillArc(xx+32, yy+12, 5, 8, 0, 360);
		g.setColor(Color.red);
		if(eat != 0){
			g.fillArc(xx+15, yy+35, 20, 15, 0, 360);
			eat++;
			if(eat > 10){
				eat = 0;}
		}else{
		g.fillArc(xx+15, yy+40, 20, 5, 0, 360);}
	}
	
	public void Score(int y,int x){
		int xx = x*50;
		int yy = (y*50)+header;
		int[] xp = {xx+25,xx+28,xx+48,xx+28,xx+25,xx+22,xx+2,xx+22};
		int[] yp = {yy+2,yy+22,yy+25,yy+28,yy+48,yy+28,yy+25,yy+22};
		g.setColor(Color.green);
		g.fillPolygon(xp, yp, 8);
	}
	
	public void heart(int y,int x){
		int xx = x*50;
		int yy = (y*50)+6+header;
		int[] xp = {xx+10 ,xx+40,xx+25};
		int[] yp = {yy+10,yy+10,yy+35};
		g.setColor(Color.red);
		g.fillPolygon(xp, yp, 3);
		g.fillArc(xx+10 , yy, 15, 15, 0, 360);
		g.fillArc(xx+25, yy, 15, 15, 0, 360);
	}

}
