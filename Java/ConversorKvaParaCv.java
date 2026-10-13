import java.util.Scanner;

public class ConversorKvaParaCv {
    public static void main(String[] args) {
        // Criando o scanner para ler a entrada do usuário
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o valor em kVA: ");
        double kva = scanner.nextDouble();

        // Fator de potência padrão para motores elétricos (geralmente 0.8)
        double fatorPotencia = 0.8;

        // 1. Converte kVA para kW
        double kw = kva * fatorPotencia;

        // 2. Converte kW para cv (1 kW = 1.35962 cv)
        double cv = kw * 1.35962;

        // Exibe os resultados formatados
        System.out.printf("%n--- Resultado da Conversão ---%n");
        System.out.printf("Valor inserido: %.2f kVA%n", kva);
        System.out.printf("Potência ativa: %.2f kW (considerando FP de %.1f)%n", kw, fatorPotencia);
        System.out.printf("Potência em cavalos: %.2f cv%n", cv);

        scanner.close();
    }
}