import java.util.Scanner;

public class equação2grau {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o valor de a: ");
        double a = scanner.nextDouble();

        System.out.print("Digite o valor de b: ");
        double b = scanner.nextDouble();

        System.out.print("Digite o valor de c: ");
        double c = scanner.nextDouble();

        if (a == 0) {
            System.out.println("Não é uma equação de segundo grau (a deve ser diferente de 0).");
        } else {
            double delta = (b * a) - (4 * a * c);
            System.out.println("Valor de Delta: " + delta);

            if (delta < 0) {
                System.out.println("A equação não possui raízes reais.");
            } else if (delta == 0) {
                double x = -b / (2 * a);
                System.out.println("Delta zero. Raiz única: x = " + x);
            } else {
                double x1 = (-b + Math.sqrt(delta)) / (2 * a);
                double x2 = (-b - Math.sqrt(delta)) / (2 * a);
                System.out.println("Duas raízes reais:");
                System.out.println("x1 = " + x1);
                System.out.println("x2 = " + x2);
            }
        }
        scanner.close();
    }
}
