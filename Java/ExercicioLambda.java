import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class ExercicioLambda {
public static void main(String[] args) {
List<String> l1 = new ArrayList<String>();
l1.add("Supermilitar");
l1.add("Superheroina");
l1.add("Kryptoniano");
l1.add("Alureano");
l1.add("Espaçonave extraterrestre");
List<String> l2 = l1.stream()
.filter(s -> s.contains("S"))
.sorted((s1, s2) -> s2.compareTo(s1))
.collect(Collectors.toList());
l2.forEach(s -> System.out.print(s));
l2.size();
}
}