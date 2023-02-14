import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Partial exam II 2016/2017
 */

class Team{
    private final String name;
    private int wins,draws,totalMatches,points,loses,totalGoals,concededGoals;

    public Team(String name) {
        this.name = name;
        this.wins = 0;
        this.draws = 0;
        this.points = 0;
        this.loses = 0;
        this.concededGoals = 0;
        this.totalMatches = 0;
    }

    public int getTotalGoals() {
        return totalGoals;
    }

    public void setTotalGoals(int totalGoals) {
        this.totalGoals = totalGoals;
    }

    public int getConcededGoals() {
        return concededGoals;
    }

    public void setConcededGoals(int concededGoals) {
        this.concededGoals = concededGoals;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public String getName() {
        return name;
    }
    

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getTotalMatches() {
        return totalMatches;
    }

    public void setTotalMatches(int totalMatches) {
        this.totalMatches = totalMatches;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    public int check(){
        return getTotalGoals()-getConcededGoals();
    }

    @Override
    public String toString() {
        return String.format("%-15s%5s%5s%5s%5s%5s\n", getName(), getTotalMatches(), getWins(), getDraws(), getLoses(), getPoints());
    }
}
class FootballTable{
    Map<String,Team> getTeamByName;
    public FootballTable() {
        getTeamByName = new HashMap<>();
    }
    public void addGame(String home, String away, int hGoals, int aGoals){
        getTeamByName.putIfAbsent(home,new Team(home));
        getTeamByName.putIfAbsent(away,new Team(away));

        int mat = getTeamByName.get(home).getTotalMatches();
        getTeamByName.get(home).setTotalMatches(mat+1);

        mat = getTeamByName.get(away).getTotalMatches();
        getTeamByName.get(away).setTotalMatches(mat+1);

        int goals = getTeamByName.get(home).getTotalGoals();
        getTeamByName.get(home).setTotalGoals(goals+hGoals);
        goals = getTeamByName.get(home).getConcededGoals();
        getTeamByName.get(home).setConcededGoals(goals+aGoals);

        goals = getTeamByName.get(away).getTotalGoals();
        getTeamByName.get(away).setTotalGoals(goals+aGoals);
        goals = getTeamByName.get(away).getConcededGoals();
        getTeamByName.get(away).setConcededGoals(goals+hGoals);

        if(hGoals<aGoals){
            int wins = getTeamByName.get(away).getWins();
            getTeamByName.get(away).setWins(wins+1);
            int los = getTeamByName.get(home).getLoses();
            getTeamByName.get(home).setLoses(los+1);
            int poi = getTeamByName.get(away).getPoints();
            getTeamByName.get(away).setPoints(poi+3);
        }
        else if(hGoals>aGoals){
            int wins = getTeamByName.get(home).getWins();
            getTeamByName.get(home).setWins(wins+1);
            int los = getTeamByName.get(away).getLoses();
            getTeamByName.get(away).setLoses(los+1);
            int poi = getTeamByName.get(home).getPoints();
            getTeamByName.get(home).setPoints(poi+3);
        }
        else{
            int draw = getTeamByName.get(home).getDraws();
            getTeamByName.get(home).setDraws(draw+1);
            int draww = getTeamByName.get(away).getDraws();
            getTeamByName.get(away).setDraws(draww+1);
            int poi = getTeamByName.get(away).getPoints();
            getTeamByName.get(away).setPoints(poi+1);
            int poi2 = getTeamByName.get(home).getPoints();
            getTeamByName.get(home).setPoints(poi2+1);
        }
    }
    public void printTable(){
        List<Team> lista = getTeamByName.values().stream().sorted(Comparator.comparing(Team::getPoints).thenComparing(Team::check).reversed().thenComparing(Team::getName)).toList();
        for(int i=0;i<lista.size();i++){
            //System.out.printf("%-19s%5s%5s%5s%5s%5s\n", "Team", "P", "W", "D", "L", "PTS");
            System.out.printf("%2d. %s",i+1,lista.get(i));

        }
    }
}
public class FootballTableTest {
    public static void main(String[] args) throws IOException {
        FootballTable table = new FootballTable();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.lines().map(line -> line.split(";")).forEach(parts -> table.addGame(parts[0], parts[1],
                Integer.parseInt(parts[2]),
                Integer.parseInt(parts[3])));
        reader.close();
        System.out.println("=== TABLE ===");
        System.out.printf("%-19s%5s%5s%5s%5s%5s\n", "Team", "P", "W", "D", "L", "PTS");
        table.printTable();
    }
}


