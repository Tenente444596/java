import java.util.Scanner;

public class ConversorKva {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Corrigido para System.in

        System.out.print("Digite o valor em kW: ");
        double kw = scanner.nextDouble(); // Lê o valor digitado pelo usuário

        double fatorPotencia = 0.8; // Valor padrão comum no mercado

        // Cálculo da conversão
        double kva = kw / fatorPotencia;

        // Exibe o resultado formatado com duas casas decimais
        System.out.printf("%.2f kW com um Fator de Potência de %.2f é igual a %.2f kVA%n", kw, fatorPotencia, kva);

        scanner.close();
    }
}

