abstract public class CheckersPlayer implements Player{


    public float score;



	public CheckersPlayer(){

	}


	public CheckersPlayer clone(){
	return this;
	}


    public abstract boolean isHuman();

}