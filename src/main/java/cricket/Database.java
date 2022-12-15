package cricket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private String databasePath;

    Database(String databasePath){
        this.databasePath = databasePath;
        try(Connection connection = DriverManager.getConnection(databasePath);
        Statement statement = connection.createStatement()){
            statement.execute("CREATE TABLE IF NOT EXISTS " +
                    "Team(TeamID integer primary key, TeamName unique not null )");
            statement.execute("CREATE TABLE IF NOT EXISTS " +
                    "Player(PlayerID integer primary key,PlayerFirstName not null, PlayerLastName not null, TeamID integer references Team)");
            statement.execute("CREATE TABLE IF NOT EXISTS " +
                    "Tournament(TournamentID integer primary key, TournamentName text not null)");
            statement.execute("CREATE TABLE  IF NOT EXISTS " +
                    "Match(MatchID integer primary key, TournametID text references Tournament)");
            statement.execute("CREATE TABLE IF NOT EXISTS " +
                    "Score(ScoreID integer primary key,PlayerID integer references Player,MatchID integer references Match, " +
                    "NumberOfFour integer not null, NumberOfSix integer not null, NumberOfWicket integer not null, Run integer not null)");
        }catch (SQLException e){
            System.out.println("Error " + e);
        }


    }

    public void addPlayer(Player player){

        String playerSQL = "INSERT INTO Player(PlayerFirstName,PlayerLastName,TeamID) VALUES (?,?,?)";
        try(Connection connection = DriverManager.getConnection(databasePath);
            PreparedStatement preparedStatement = connection.prepareStatement(playerSQL)){

            preparedStatement.setString(1,player.getPlayerFirstName());
            preparedStatement.setString(2,player.getPlayerLastName());
            preparedStatement.setInt(3,player.getTeamID());

            preparedStatement.execute();
        }catch(SQLException e){
            System.out.println("Error adding Player " + player + " because " +e
            );
        }
    }

    public List<Player> getAllPlayers(int teamID) {

        String getPlayerForTeamSQL = "SELECT * FROM Player WHERE TeamID = ? ORDER BY PlayerFirstName";
        try (Connection connection = DriverManager.getConnection(databasePath);
             PreparedStatement preparedStatement = connection.prepareStatement(getPlayerForTeamSQL)){
                 preparedStatement.setInt(1,teamID);
            ResultSet playerResult = preparedStatement.executeQuery();
            List<Player> playerList = new ArrayList<>();
            while (playerResult.next()) {
                int PlayerID = playerResult.getInt("PlayerID");
                String PlayerFirstName = playerResult.getString("PlayerFirstName");
                String PlayerLastName = playerResult.getString("PlayerLastName");
                int TeamID = playerResult.getInt("TeamID");
                Player player = new Player(PlayerID, PlayerFirstName, PlayerLastName, TeamID);
                playerList.add(player);
            }
            return playerList;
        } catch (SQLException e) {
            System.out.println("Error getting all players " + e);
            return null;
        }
    }

    public List<Player> getAllPlayers() {

        try (Connection connection = DriverManager.getConnection(databasePath);
             Statement statement = connection.createStatement()){
            //preparedStatement.setInt(1,teamID);
            //statement.execute("SELECT * FROM Player ORDER BY PlayerFirstName");
            ResultSet playerResult = statement.executeQuery("SELECT * FROM Player ORDER BY PlayerFirstName"); ;
            List<Player> allPlayerList = new ArrayList<>();
            while (playerResult.next()) {
                int PlayerID = playerResult.getInt("PlayerID");
                String PlayerFirstName = playerResult.getString("PlayerFirstName");
                String PlayerLastName = playerResult.getString("PlayerLastName");
                int TeamID = playerResult.getInt("TeamID");
                Player player = new Player(PlayerID, PlayerFirstName, PlayerLastName, TeamID);
                allPlayerList.add(player);
            }
            return allPlayerList;
        } catch (SQLException e) {
            System.out.println("Error getting all players " + e);
            return null;
        }
    }

    public List<Team> getAllTeams(){

        // Connect to database and get the list of teams
        try(Connection connection = DriverManager.getConnection(databasePath);
        Statement statement = connection.createStatement()){
            ResultSet teamResult = statement.executeQuery("SELECT * FROM Team ORDER BY TeamName");
            List<Team> teamsList = new ArrayList<>();
            while(teamResult.next()){
                int id = teamResult.getInt("TeamID");
                String name = teamResult.getString("TeamName");
                Team newTeam = new Team(id,name);
                teamsList.add(newTeam);
            }

            return teamsList;

        }catch(SQLException e){
            System.out.println("Error getting Team data " + e);
            return null;
        }
        // return null;
    }

    public void addMatch(Match match){

        String insertMatch = "INSERT INTO Match(TournamentID) values (?)";

        try(Connection connection = DriverManager.getConnection(databasePath);
            PreparedStatement preparedStatement = connection.prepareStatement(insertMatch)){

            preparedStatement.setInt(1,match.getTournamentID());
            preparedStatement.execute();
        }catch (SQLException e){
            System.out.println("Error adding match because " + e);
        }
    }

    public void addTournament(Tournament tournament){
        String insertTournament = "INSERT INTO Tournament(TournamentName) VALUES (?)";

        try(Connection connection = DriverManager.getConnection(databasePath);
            PreparedStatement preparedStatement = connection.prepareStatement(insertTournament)){

            preparedStatement.setString(1,tournament.getTournamentName());
            preparedStatement.execute();
        }catch (SQLException e){
            System.out.println("Error adding tournament because " + e);
        }
    }

    public void addTeam(Team team){

        String insertTeam = "INSERT INTO Team(TeamName) VALUES (?)";

        try(Connection connection = DriverManager.getConnection(databasePath);
            PreparedStatement preparedStatement = connection.prepareStatement(insertTeam)){

            preparedStatement.setString(1,team.getTeamName());
            preparedStatement.execute();
        }catch (SQLException e){
            System.out.println("Error adding team because " + e);
        }
    }

    public void addScore(Score score){

        String insertScore = "INSERT INTO Score (PlayerID,MatchID,NumberOfFour,NumberOfSix,NumberOfWicket,Run) VALUES (?,?,?,?,?,?)";
        try(Connection connection = DriverManager.getConnection(databasePath);
            PreparedStatement preparedStatement = connection.prepareStatement(insertScore)){

            preparedStatement.setInt(1, score.getPlayerID());
            preparedStatement.setInt(2,score.getMatchID());
            preparedStatement.setInt(3,score.getNumberOfFour());
            preparedStatement.setInt(4,score.getNumberOfSix());
            preparedStatement.setInt(5,score.getNumberOfWicket());
            preparedStatement.setInt(6,score.getRun());

            preparedStatement.execute();
        }catch (SQLException e){
            System.out.println("Error adding score because " + e);
        }
    }

    public int getTeamID(String teamName){
        String getTeamIDSQL = "SELECT * FROM Team WHERE TeamName = ?";
        try (Connection connection = DriverManager.getConnection(databasePath);
        PreparedStatement preparedStatement = connection.prepareStatement(getTeamIDSQL)){
            preparedStatement.setString(1,teamName);
            ResultSet resultSet = preparedStatement.executeQuery();
            int teamID = 0;
            while(resultSet.next()){
                teamID = resultSet.getInt("TeamID");
            }
            return teamID;
        }catch (SQLException e){
            System.out.println("Error getting Team ID because " + e);
            return 0;
        }
    }

    // getting player data -- ideally have to look for Score table
    public Player getPlayerInfo(int teamID, String fullName){

        String playerInfoSQL = "SELECT * FROM Player WHERE TeamID = ? AND PlayerFirstName = ? AND PlayerLastName = ?";
        try(Connection connection = DriverManager.getConnection(databasePath);
        PreparedStatement preparedStatement = connection.prepareStatement(playerInfoSQL)) {
            preparedStatement.setInt(1,teamID);
            String name[] = fullName.split(" ");
            preparedStatement.setString(2, name[0]);
            preparedStatement.setString(3, name[1]);
            Player newPlayer = new Player(1,"Tenzin","Minleg",1);
            ResultSet playerResultSet = preparedStatement.executeQuery();
            while(playerResultSet.next()){
                int playerID = playerResultSet.getInt("PlayerID");
                String playerFirstName = playerResultSet.getString("PlayerFirstName");
                String playerLastName = playerResultSet.getString("PlayerLastName");
                int ID = playerResultSet.getInt("TeamID");
                newPlayer = new Player(playerID,playerFirstName,playerLastName,ID);

            }
            return newPlayer;
        }catch(SQLException e){
            System.out.println("Error getting Player information because " + e);
            return null;
        }
    }
}
