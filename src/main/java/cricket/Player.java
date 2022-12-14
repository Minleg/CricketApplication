package cricket;

public class Player {
    private int playerID;
    private String playerFirstName;
    private String playerLastName;
    private int teamID;

    Player(String playerFirstName,String playerLastName, int teamID){
        this.playerFirstName = playerFirstName;
        this.playerLastName = playerLastName;
        this.teamID = teamID;
    }

    Player(int playerID,String playerFirstName,String playerLastName, int teamID){
        this.playerID = playerID;
        this.playerFirstName = playerFirstName;
        this.playerLastName = playerLastName;
        this.teamID = teamID;
    }


    public String toString(){
        return "PlayerID " + playerID + "player Name: " + playerFirstName + " " + playerLastName + " teamID: " + teamID;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getPlayerFirstName() {
        return playerFirstName;
    }

    public void setPlayerFirstName(String playerFirstName) {
        this.playerFirstName = playerFirstName;
    }

    public String getPlayerLastName() {
        return playerLastName;
    }

    public void setPlayerLastName(String playerLastName) {
        this.playerLastName = playerLastName;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }
}
