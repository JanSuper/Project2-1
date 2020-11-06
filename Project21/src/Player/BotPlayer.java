public class BotPlayer extends CheckersPlayer{

    public String botName;


	public BotPlayer(){

	}

	public BotPlayer(String name){
	    this.botName=name;
    }



	public CheckersPlayer clone(){

		return new BotPlayer();
	}



    @Override
    public boolean isHuman() {
        return false;
    }


}