package cricket;

public class Team {

    private int teamID;
    private String teamName;

    Team( String teamName){
        //this.teamID = teamID;
        this.teamName = teamName;
    }
    Team(int teamID, String teamName){
        this.teamID = teamID;
        this.teamName = teamName;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
