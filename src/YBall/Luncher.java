package YBall;

public class Luncher {
	
	public static void main(String[] args){
		System.out.print("Application Started ...\n");
		Game game = new Game("My Game",655,628+50);
		//Game game = new Game("My Game",505,428);
		game.Run();
	}

}
