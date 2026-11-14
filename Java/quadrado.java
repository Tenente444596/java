import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class quadrado {
    public static void main(String[] args) {
        // Criando a lista inicial de números inteiros
        List<Integer> numeros = Arrays.asList(118877, 228877, 338877, 448877, 504840);
        
        // Utilizando a API de Streams para elevar cada número ao quadrado
        List<Integer> saida = numeros.stream()
                                     .map(x -> x * x)
                                     .collect(Collectors.toList());
        
        // Exibindo o resultado final no console
        System.out.println(saida);
    }
}
