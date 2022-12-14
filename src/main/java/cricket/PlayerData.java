package cricket;

import java.util.List;

import static input.InputUtils.*;

public class PlayerData {

    static final String DB_PATH = "jdbc:sqlite:identifier.sqlite";

    private static Database database;

    private static PlayerRankGUI playerDataGUI;

    public static void main(String[] args) {
        new PlayerData().startApp();
    }

    private void startApp(){
        database = new Database(DB_PATH);
        playerDataGUI = new PlayerRankGUI(this);
//        boolean addTeam = yesNoInput("Do you want to add a new Team");
//        if (addTeam) {
//            addTeam();
//        }else {
//            boolean addPlayer = yesNoInput("Do you want to add new Players");
//            if(addPlayer){
//                addPlayer();
//            }
//        }
//
//        boolean addTournament = yesNoInput("Do you want to add Tournament names");
//        if (addTournament){
//            addTournament();
//        }
    }

    public static int getTeamID(String teamName){
        int teamID = database.getTeamID(teamName);
        return teamID;
    }

    public static List<Player> getAllPlayer(int teamID){
        List<Player> players = database.getAllPlayers(teamID);
        return players;
    }

    public static List<Team> getAllTeam(){
        List<Team> teams = database.getAllTeams();
        return teams;
    }

    public static void addTeam(){
        do{
           String teamName = stringInput("Enter the name of the Team");

           Team team = new Team(teamName);
            database.addTeam(team);
        }while(yesNoInput("You want to add more Team: "));
    }

    public static void addPlayer(){
        do{
            String firstName = stringInput("Enter FirstName: ");
            String lastName = stringInput("Enter LastName: ");
            int teamID = positiveIntInput("Enter teamID");

            Player player =  new Player(firstName,lastName,teamID);
            database.addPlayer(player);
        }while(yesNoInput("You want to add more Players: "));
    }

    public static void addTournament(){
        do{
            //int tournamentID = positiveIntInput(" Enter Tournament ID");
            String tournamentName = stringInput(" Enter Tournament Name");

            Tournament tournament = new Tournament(tournamentName);
            database.addTournament(tournament);
        }while (yesNoInput("You want to add more Tournaments: "));
    }
}
