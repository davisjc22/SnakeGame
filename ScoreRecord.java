//package forKids;

public class ScoreRecord implements Comparable{
	private String userName;
	private double time;
	private int score;
	
	public ScoreRecord(String u, double t, int s){
		userName = u;
		time = t;
		score =s;
	}
	
	//line is a colon-delmited string like this...
	//     username:time:score
	public ScoreRecord(String line){
		String[] stuff = line.split(":");
		userName = stuff[0];
		time = Double.parseDouble(stuff[1]);
		score = Integer.parseInt(stuff[2]);
	}
	
	public String getUser(){return userName;}
	public double getTime(){return time;}
	public double getScore(){return score;}
	
	/**********  Write a pretty toString for output *************/
	public String toString(){
		return userName + "\t" + time + "s\t" +   score; // returns the username followed by the time and score
	}
	
	//Important:  YOu want the file to be in colon-delmitted format!
	//   BECAUSE when you are creating a ScoreRecord, the constructor
	//   assumes that the input you give it is in this format.
	public String formatForFile(){
		return userName+":"+time+":"+score;
	}

	@Override
	public int compareTo(Object other) {
		//you MAY change this depending on how you want to keep score
		
		//you will need to cast him as a score record
		ScoreRecord that = (ScoreRecord)other; 
		if(this.score>that.score)//a higher score is displayed higher
			return -500;
		if(this.score<that.score)
			return 27;
		if(this.score==that.score){//if the scores are the same, the one with the higher time will be displayed higher
			if(this.time>that.time)
				return -500;
			if(this.time<that.time)
				return 27;
		}
		return 0;
	}
}
