package cricket;

public class Score {

    private int scoreID;
    private int playerID;
    private int matchID;
    private int numberOfFour;
    private int numberOfSix;
    private int numberOfWicket;
    private int run;

    Score(int scoreID,int playerID, int matchID, int numberOfFour, int numberOfSix, int numberOfWicket, int run){
        this.scoreID = scoreID;
        this.playerID = playerID;
        this.matchID = matchID;
        this.numberOfFour = numberOfFour;
        this.numberOfSix = numberOfSix;
        this.numberOfWicket = numberOfWicket;
        this.run = run;
    }

    public int getScoreID() {
        return scoreID;
    }

    public void setScoreID(int scoreID) {
        this.scoreID = scoreID;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public int getMatchID() {
        return matchID;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public int getNumberOfFour() {
        return numberOfFour;
    }

    public void setNumberOfFour(int numberOfFour) {
        this.numberOfFour = numberOfFour;
    }

    public int getNumberOfSix() {
        return numberOfSix;
    }

    public void setNumberOfSix(int numberOfSix) {
        this.numberOfSix = numberOfSix;
    }

    public int getNumberOfWicket() {
        return numberOfWicket;
    }

    public void setNumberOfWicket(int numberOfWicket) {
        this.numberOfWicket = numberOfWicket;
    }

    public int getRun() {
        return run;
    }

    public void setRun(int run) {
        this.run = run;
    }
}
