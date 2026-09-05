import java.util.Scanner;

public class ConversorVelocidade {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Entrada de dados
        System.out.print("Digite a velocidade em milhas por hora (mph): ");
        double mph = scanner.nextDouble();

        // Conversão para KM/H (1 milha = 1.60934 km)
        double kmh = mph * 1.60934;

        // Cálculo do tempo para percorrer 1 milha (ritmo em minutos e segundos)
        double horasPorMilha = 1.0 / mph;
        double minutosTotais = horasPorMilha * 60;
        int minutos = (int) minutosTotais;
        int segundos = (int) Math.round((minutosTotais - minutos) * 60);

        // Exibição dos resultados
        System.out.println("\n--- Resultados ---");
        System.out.printf("Velocidade em KM/H: %.2f km/h\n", kmh);
        System.out.printf("Tempo para milha: %d minuto(s) e %d segundo(s)\n", minutos, segundos);

        scanner.close();
    }
}
