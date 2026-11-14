import java.util.Arrays;
import java.util.List;

public class teste {
    public static void main(String[] args) {
        // Nome da lista corrigido para refletir o conteúdo
        List<String> herois = Arrays.asList(
            "Supermilitar", "Superheroina", "Kryptoniano", "Alureano", "Espaçonave extraterrestre"
        );

        herois.stream()
            .filter(s -> s.length() > 4)    // Mantém palavras com mais de 4 caracteres
            .map(s -> s.substring(2))       // Remove as duas primeiras letras de cada palavra
            .toList()                       // Forma moderna de coletar (Java 16+)
            .forEach(System.out::print);    // Usando Method Reference para imprimir
    }
}