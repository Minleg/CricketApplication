package cricket;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class PlayerRankGUI extends JFrame{

    private JPanel mainPanel;
    private JComboBox<String> teamComboBox;
    private JComboBox<String> playerComboBox;
    private JComboBox criteriaComboBox;
    private JList playerOrderList;
    private JButton searchButton;

    private DefaultListModel<String> playerListModel;

    private static PlayerData playerData;

    PlayerRankGUI(PlayerData playerData){
        this.playerData = playerData;
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(350, 500));
        setTitle("Minnesota Tibetan Cricket Application");
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        getRootPane().setDefaultButton(searchButton);



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
                playerComboBox.removeAllItems();
                int teamID = PlayerData.getTeamID(selectedTeam);
                loadPlayers(teamID);
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String teamName = (String) teamComboBox.getSelectedItem();
                int teamID = PlayerData.getTeamID(teamName);
                String playerName =(String) playerComboBox.getSelectedItem();
                //String[] name = playerName.split(" ");


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

    private void loadTeams() {
        List<Team> teamsList = PlayerData.getAllTeam();
        for (Team team: teamsList){
            String name = team.getTeamName();
            //int teamId = team.getTeamID();
            teamComboBox.addItem(name);
        }
    }
}
