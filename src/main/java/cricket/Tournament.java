package cricket;

public class Tournament {

    private int tournamentID;
    private String tournamentName;

    Tournament(int tournamentID,String tournamentName){
        this.tournamentID = tournamentID;
        this.tournamentName = tournamentName;
    }

    Tournament(String tournamentName){
        this.tournamentName = tournamentName;
    }

    public int getTournamentID() {
        return tournamentID;
    }

    public void setTournamentID(int tournamentID) {
        this.tournamentID = tournamentID;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }
}
