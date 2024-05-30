/**
 * tournament

Es importante clasificar a todos los competidores en un torneo, no solo al campeón. Aquí consideraremos la siguiente forma de hacerlo.
Si el competidor A ganó más juegos que el competidor B en el torneo, entonces el A debería clasificarse por encima del B. Si el
competidor A y el competidor B ganaron el mismo número de juegos en el torneo, se compara recursivamente los rangos del competidor C 
que eliminó al A y el competidor D que eliminó al B. Entonces, el A debe clasificarse por encima del B si y solo si el C está 
clasificado arriba del D. La intuición detrás de la segunda regla es que perder contra un buen competidor es mejor que 
perder ante un competidor malo.

Debe implementar una función rankTeams según este esquema para clasificar a los competidores en un solo torneo de eliminación. Se le darán
los String[] names y String[] lostTo. El primer argumento da los nombres de los competidores en un torneo, y el segundo describe si y cómo
se eliminó a cada competidor. Específicamente, el i-ésimo elemento de lostTo será el nombre del competidor que venció al i-ésimo competidor
en names, o será "" si ese competidor nunca perdió (salió campeón). Debería devolver un String[] con los nombres de los competidores, 
ordenados del rango más alto al rango más bajo como se describe más arriba.En resúmen, debe implementar una función con la siguiente
signatura: String[] rankTeams(String[] names, String[] lostTo). A continuación se presentan dos ejemplos de entradas y salidas según esta 
especificación.

•	Entrada: {"EQUIPO 1", "EQUIPO 2", "EQUIPO 3", "EQUIPO 4"}, {"EQUIPO 2",  , ""}
Devuelve: {"EQUIPO 4", "EQUIPO 2", "EQUIPO 3", "EQUIPO 1"}

•	Entrada: {"TEAM 1", "TEAM 2", "TEAM 3", "TEAM 4", "TEAM 5", "TEAM 6", "TEAM 7", "TEAM 8"},
{"", "TEAM 1", "TEAM 1", "TEAM 3", "TEAM 6", "TEAM 7", "TEAM 1", "TEAM 7"}
Devuelve: {"TEAM 1", "TEAM 7", "TEAM 3", "TEAM 6", "TEAM 2", "TEAM 8", "TEAM 4", "TEAM 5"}

 */
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Arrays;

public class Tournament {
    public static void main(String[] args){
        String[] test_names = {"TEAM 1", "TEAM 2", "TEAM 3", "TEAM 4", "TEAM 5", "TEAM 6", "TEAM 7", "TEAM 8"};
        String[] test_lostTo = {"", "TEAM 1", "TEAM 1", "TEAM 3", "TEAM 6", "TEAM 7", "TEAM 1", "TEAM 7"};

        String[] ans = rankTeams(test_names, test_lostTo);
        System.out.println(Arrays.toString(ans));
    }

    public static String[] rankTeams(String[] test_names, String[] test_lostTo){
        Integer[] winsCount = new Integer[test_names.length];
        Integer[] teamPosition = new Integer[test_names.length];
        int levels = (int) Math.ceil(Math.log(test_names.length) / Math.log(2)) + 1;
        LinkedList<Integer>[] treeLevels = (LinkedList<Integer>[]) new LinkedList<?>[levels]; 
        HashMap<String, Integer> teamNumber = new HashMap<>();
        HashMap<Integer, String> reverse_teamNumber = new HashMap<>();
        String[] ans = new String[test_names.length];

        for (int i = 0; i < treeLevels.length; i++){ treeLevels[i] = new LinkedList<>(); } 

        for (int i = 0; i < test_names.length; i++){
            teamNumber.put(test_names[i], i);
            reverse_teamNumber.put(i, test_names[i]);
            winsCount[i] = 0;
            teamPosition[i] = 0;
        }

        for (int i = 0; i < test_lostTo.length; i++){
            if(test_lostTo[i] == "") continue;
            winsCount[teamNumber.get(test_lostTo[i])]++;
        }

        for (int i = 0; i < winsCount.length; i++){ treeLevels[winsCount[i]].add(i); }

        for(int i = treeLevels.length - 1; i >= 0; i--){
            if (i == treeLevels.length - 1 || i == treeLevels.length - 2){
                teamPosition[treeLevels[i].getLast()] = treeLevels.length - i;
                continue;
            }
            for(int j : treeLevels[i]){
                int padre = teamNumber.get(test_lostTo[j]);
                int temp_position = (int) Math.pow(2, levels - i - 1)/2 + teamPosition[padre];
                teamPosition[j] = temp_position;
            }
        }

        for (int i = 0; i < teamPosition.length; i++){
            ans[teamPosition[i] - 1] = reverse_teamNumber.get(i);
        }
        return ans;
    }
}
