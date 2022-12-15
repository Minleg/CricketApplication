package cricket;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.List;

public class PlayerRankGUI extends JFrame{

    private JPanel mainPanel;
    private JComboBox<String> teamComboBox;
    private JComboBox<String> playerComboBox;
    private JComboBox criteriaComboBox;
    private JList<Player> playerOrderList;
    private JButton searchButton;

    private DefaultListModel<Player> playerListModel;

    private static PlayerData playerData;

    private static String allPlayers = "All";

    PlayerRankGUI(PlayerData playerData){
        this.playerData = playerData;
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(350, 500));
        setTitle("Minnesota Tibetan Cricket Application");
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        getRootPane().setDefaultButton(searchButton);

        playerListModel = new DefaultListModel<>();
        playerOrderList.setModel(playerListModel);
        playerOrderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        loadTeams();
//        String selectedTeam = (String) teamComboBox.getSelectedItem();
//        int teamID = PlayerData.getTeamID(selectedTeam);
//        loadPlayers(teamID);

        actionListeners();
    }

    private void actionListeners() {

        teamComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String selectedTeam = (String) teamComboBox.getSelectedItem();
                if (selectedTeam.equals(allPlayers)){ // if All is selected, loads all the Players
                    playerComboBox.removeAllItems();
                    loadPlayers();
                } else{
                playerComboBox.removeAllItems();
                int teamID = PlayerData.getTeamID(selectedTeam);
                loadPlayers(teamID);
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerListModel.clear();
                String teamName = (String) teamComboBox.getSelectedItem();
                int teamID = PlayerData.getTeamID(teamName);
                String playerName =(String) playerComboBox.getSelectedItem();
                //String[] name = playerName.split(" ");
                Player newPlayer = PlayerData.getPlayerInfo(teamID,playerName);
                playerListModel.addElement(newPlayer);



            }
        });

        playerOrderList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selection = playerOrderList.locationToIndex(e.getPoint());
                playerOrderList.setSelectedIndex(selection);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    private void loadPlayers(int teamID) {
        List<Player> playersList = PlayerData.getAllPlayer(teamID);
        for (Player player: playersList){
            String name = player.getPlayerFirstName() + " " + player.getPlayerLastName();
            playerComboBox.addItem(name);
        }
    }

    private void loadPlayers(){
        List<Player> playerList = PlayerData.getAllPlayer();
        for(Player player: playerList){
            String name = player.getPlayerFirstName() + " " + player.getPlayerLastName();
            playerComboBox.addItem(name);
        }
    }

    private void loadTeams() {
        List<Team> teamsList = PlayerData.getAllTeam();
        teamComboBox.addItem(allPlayers);
        for (Team team: teamsList){
            String name = team.getTeamName();
            //int teamId = team.getTeamID();
            teamComboBox.addItem(name);
        }
    }
}
