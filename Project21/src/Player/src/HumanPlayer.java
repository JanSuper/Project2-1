public class HumanPlayer extends CheckersPlayer{


	private String playerName;

	public HumanPlayer(){

	}

	public HumanPlayer(String name){
		playerName=name;
	}



	public CheckersPlayer clone(){

		return new HumanPlayer();
	}


	public String getName(){
		return this.playerName;
	}


	public void setName(String name){
		this.playerName=name;
	}


}