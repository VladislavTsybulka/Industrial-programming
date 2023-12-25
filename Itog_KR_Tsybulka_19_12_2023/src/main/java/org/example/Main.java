package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Match {
    String homeTeam;
    String awayTeam;
    int homeGoals;
    int awayGoals;

    public Match(String homeTeam, String awayTeam, int homeGoals, int awayGoals) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
    }
}

class Group {
    String name;
    List<Team> teams;

    public Group(String name) {
        this.name = name;
        this.teams = new ArrayList<>();
    }

    public void addTeam(Team team) {
        teams.add(team);
    }
}

class Team {
    String name;
    int goalsScored;
    int goalsConceded;
    int wins;
    int draws;
    int loses;
    int points;

    public Team(String name) {
        this.name = name;
    }
}

interface InterfaceBD{
    void FormGroups(String filename);
    void ReadMatches(String filename);
}
class ClassBD implements InterfaceBD{
    List<Group> groups;

    public ClassBD() {
        this.groups = new ArrayList<>();
    }

    public void FormGroups(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                String groupName = parts[0];
                String team1 = parts[1];
                String team2 = parts[2];
                String team3 = parts[3];
                String team4 = parts[4];
                Group group = new Group(groupName);
                Team Team1 = new Team(team1);
                Team Team2 = new Team(team2);
                Team Team3 = new Team(team3);
                Team Team4 = new Team(team4);
                group.addTeam(Team1);
                group.addTeam(Team2);
                group.addTeam(Team3);
                group.addTeam(Team4);
                addGroup(group);

                // Do something with the extracted information
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addGroup(Group Name) {
        groups.add(Name);
    }

    public void ReadMatches(String fileName) {

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");

                String group = parts[0];
                String team1 = parts[1];
                int goals1 = Integer.parseInt(parts[2].split(":")[0]);
                int goals2 = Integer.parseInt(parts[2].split(":")[1]);
                String team2 = parts[3];
                Match match = new Match(team1, team2, goals1, goals2);
                addMatchToGroup(group, match);
                calculateStandings(group);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addMatchToGroup(String groupName, Match match) {
        for (Group group : groups) {
            if (group.name.equals(groupName)) {
                for (Team team : group.teams) {
                    if (team.name.equals(match.homeTeam)) {
                        team.goalsScored += match.homeGoals;
                        team.goalsConceded += match.awayGoals;
                        if (match.homeGoals > match.awayGoals) {
                            team.wins += 1;
                            team.points += 3;
                        } else if (match.homeGoals == match.awayGoals) {
                            team.draws += 1;
                            team.points += 1;
                        } else {
                            team.loses += 1;
                        }
                    } else if (team.name.equals(match.awayTeam)) {
                        team.goalsScored += match.awayGoals;
                        team.goalsConceded += match.homeGoals;
                        if (match.awayGoals > match.homeGoals) {
                            team.wins += 1;
                            team.points += 3;
                        } else if (match.homeGoals == match.awayGoals) {
                            team.draws += 1;
                            team.points += 1;
                        } else {
                            team.loses += 1;
                        }
                    }
                }
            }
        }
    }

    public void calculateStandings(String groupName) {
        for (Group group : groups) {
            if (group.name.equals(groupName)) {
                Collections.sort(group.teams, new Comparator<Team>() {
                    @Override
                    public int compare(Team t1, Team t2) {
                        if (t1.points != t2.points) {
                            return t2.points - t1.points;
                        } else {
                            return t2.goalsScored - t1.goalsScored;
                        }
                    }
                });
            }
        }
    }

    public void displayStandings(String groupName) {
        for (Group group : groups) {
            if (group.name.equals(groupName)) {
                System.out.println("Group: " + group.name);
                System.out.println("Team\tPoints\tWins\tDraws\tLoses\tGoals Scored\tGoals Conceded");
                for (Team team : group.teams) {
                    System.out.println(team.name + "\t" + team.points + "\t" + team.wins + "\t" + team.draws +
                            "\t" + team.loses + "\t" + team.goalsScored + "\t" + team.goalsConceded);
                }
            }
        }
    }

    public void saveStandingsToFile(String fileName) {
                try {
                    FileWriter fileWriter = new FileWriter(fileName);
                    fileWriter.write("Team\tPoints\tWins\tDraws\tLoses\tGoals Scored\tGoals Conceded\n");
                    for (Group group : groups) {
                        fileWriter.write("Group: " + group.name + "\n");
                        for (Team team : group.teams) {
                            fileWriter.write(team.name + "\t" + team.points + "\t" + team.wins + "\t" + team.draws +
                                    "\t" + team.loses + "\t" + team.goalsScored + "\t" + team.goalsConceded + "\n");
                        }
                        fileWriter.write("\n");
                    }
                    fileWriter.close();
                } catch (IOException e){
                        e.printStackTrace();
                    }
    }

    public void saveResultsToFile(String fileName) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            for (Group group : groups) {
                fileWriter.write("Group: " + group.name + "\n");
                for (Team team : group.teams) {
                    int number = team.wins + team.draws + team.loses;
                    fileWriter.write(team.name + " " + team.points + " " + number + "\n");
                }
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ClassBD Table = new ClassBD();
        Table.FormGroups("GroupsEN.txt");
        Table.ReadMatches("GameEN.txt");
        Table.saveStandingsToFile("GroupsOut.txt");
        Table.saveResultsToFile("Results.txt");
    }
}