package cricket;

public class Match {

    private int matchID;
    private int tournamentID;

    Match(int matchID,int tournamentID){
        this.matchID = matchID;
        this.tournamentID = tournamentID;
    }

    public int getMatchID() {
        return matchID;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public int getTournamentID() {
        return tournamentID;
    }

    public void setTournamentID(int tournamentID) {
        this.tournamentID = tournamentID;
    }
}
