import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Polinomiodiv {
    // Armazena <Expoente, Coeficiente> ordenados do maior expoente para o menor
    private final TreeMap<Integer, Double> termos;

    public Polinomiodiv() {
        this.termos = new TreeMap<>(Collections.reverseOrder());
    }

    // Classe auxiliar para guardar o Quociente e o Resto da divisão
    public static class ResultadoDivisao {
        public final Polinomiodiv quociente;
        public final Polinomiodiv resto;

        public ResultadoDivisao(Polinomiodiv quociente, Polinomiodiv resto) {
            this.quociente = quociente;
            this.resto = resto;
        }

        @Override
        public String toString() {
            return "Quociente = " + quociente + " | Resto = " + resto;
        }
    }

    // Adiciona ou atualiza um termo no polinômio
    public void adicionarTermo(double coeficiente, int expoente) {
        if (expoente < 0) {
            throw new IllegalArgumentException("O expoente não pode ser negativo.");
        }
        // Tolerância para evitar erros de precisão de ponto flutuante (double)
        if (Math.abs(coeficiente) < 1e-9) {
            return;
        }

        double novoCoeficiente = this.termos.getOrDefault(expoente, 0.0) + coeficiente;

        if (Math.abs(novoCoeficiente) < 1e-9) {
            this.termos.remove(expoente);
        } else {
            this.termos.put(expoente, novoCoeficiente);
        }
    }

    // Retorna o grau do polinômio (maior expoente)
    public int getGrau() {
        return termos.isEmpty() ? 0 : termos.firstKey();
    }

    // Verifica se o polinômio é igual a zero
    public boolean isZero() {
        return termos.isEmpty();
    }

    // Soma o polinômio atual com outro
    public Polinomiodiv somar(Polinomiodiv outro) {
        Polinomiodiv resultado = new Polinomiodiv();

        for (Map.Entry<Integer, Double> entry : this.termos.entrySet()) {
            resultado.adicionarTermo(entry.getValue(), entry.getKey());
        }
        for (Map.Entry<Integer, Double> entry : outro.termos.entrySet()) {
            resultado.adicionarTermo(entry.getValue(), entry.getKey());
        }

        return resultado;
    }

    // Subtrai outro polinômio do polinômio atual
    public Polinomiodiv subtrair(Polinomiodiv outro) {
        Polinomiodiv resultado = new Polinomiodiv();

        for (Map.Entry<Integer, Double> entry : this.termos.entrySet()) {
            resultado.adicionarTermo(entry.getValue(), entry.getKey());
        }
        for (Map.Entry<Integer, Double> entry : outro.termos.entrySet()) {
            resultado.adicionarTermo(-entry.getValue(), entry.getKey());
        }

        return resultado;
    }

    // Método auxiliar interno para multiplicar um polinômio por um único termo
    private Polinomiodiv multiplicarPorTermo(double coef, int exp) {
        Polinomiodiv resultado = new Polinomiodiv();
        for (Map.Entry<Integer, Double> entry : this.termos.entrySet()) {
            resultado.adicionarTermo(entry.getValue() * coef, entry.getKey() + exp);
        }
        return resultado;
    }

    // Divide o polinômio atual por um divisor (Método das Chaves)
    public ResultadoDivisao dividir(Polinomiodiv divisor) {
        if (divisor.isZero()) {
            throw new ArithmeticException("Divisão por polinômio zero não é permitida.");
        }

        Polinomiodiv quociente = new Polinomiodiv();
        Polinomiodiv resto = new Polinomiodiv();

        // Copia o dividendo (this) para o resto inicial
        for (Map.Entry<Integer, Double> entry : this.termos.entrySet()) {
            resto.adicionarTermo(entry.getValue(), entry.getKey());
        }

        int grauDivisor = divisor.getGrau();
        double coefLiderDivisor = divisor.termos.get(grauDivisor);

        // Enquanto o resto não for zero e seu grau for maior ou igual ao do divisor
        while (!resto.isZero() && resto.getGrau() >= grauDivisor) {
            int grauResto = resto.getGrau();
            double coefLiderResto = resto.termos.get(grauResto);

            // Calcula o termo atual do quociente
            int expQuociente = grauResto - grauDivisor;
            double coefQuociente = coefLiderResto / coefLiderDivisor;

            quociente.adicionarTermo(coefQuociente, expQuociente);

            // Subtrai (divisor * termoQuociente) do resto atual
            Polinomiodiv termoParaSubtrair = divisor.multiplicarPorTermo(coefQuociente, expQuociente);
            resto = resto.subtrair(termoParaSubtrair);
        }

        return new ResultadoDivisao(quociente, resto);
    }

    // Formata o polinômio para exibição legível
    @Override
    public String toString() {
        if (termos.isEmpty()) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        boolean primeiroTermo = true;

        for (Map.Entry<Integer, Double> entry : termos.entrySet()) {
            int exp = entry.getKey();
            double coef = entry.getValue();

            if (!primeiroTermo) {
                sb.append(coef > 0 ? " + " : " - ");
            } else if (coef < 0) {
                sb.append("-");
            }

            double absCoef = Math.abs(coef);

            // Formata inteiro sem casas decimais, ou decimal com até 2 casas
            String coefStr = (Math.abs(absCoef - Math.round(absCoef)) < 1e-9)
                ? String.format("%d", Math.round(absCoef))
                : String.format("%.2f", absCoef);

            if (absCoef != 1 || exp == 0) {
                sb.append(coefStr);
            }

            if (exp > 0) {
                sb.append("x");
                if (exp > 1) {
                    sb.append("^").append(exp);
                }
            }

            primeiroTermo = false;
        }

        return sb.toString();
    }

    // Método principal para teste
    public static void main(String[] args) {
        // P1(x) = 3x^3 + 2x^2 - 5x + 1
        Polinomiodiv p1 = new Polinomiodiv();
        p1.adicionarTermo(3, 3);
        p1.adicionarTermo(2, 2);
        p1.adicionarTermo(-5, 1);
        p1.adicionarTermo(1, 0);

        // P2(x) = -3x^3 + 4x^2 + 2x - 7
        Polinomiodiv p2 = new Polinomiodiv();
        p2.adicionarTermo(-3, 3);
        p2.adicionarTermo(4, 2);
        p2.adicionarTermo(2, 1);
        p2.adicionarTermo(-7, 0);

        // P3(x) = x^2 + 3x + 4
        Polinomiodiv p3 = new Polinomiodiv();
        p3.adicionarTermo(1, 2);
        p3.adicionarTermo(3, 1);
        p3.adicionarTermo(4, 0);

        // P4(x) = 2x^3 - x^2 + 2
        Polinomiodiv p4 = new Polinomiodiv();
        p4.adicionarTermo(2, 3);
        p4.adicionarTermo(-1, 2);
        p4.adicionarTermo(2, 0);

        // Realizando divisões sucessivas apenas com os quocientes: ((P1 / P2) / P3) / P4
        ResultadoDivisao div1 = p1.dividir(p2);
        ResultadoDivisao div2 = div1.quociente.dividir(p3);
        ResultadoDivisao divFinal = div2.quociente.dividir(p4);

        System.out.println("P1(x) = " + p1);
        System.out.println("P2(x) = " + p2);
        System.out.println("P3(x) = " + p3);
        System.out.println("P4(x) = " + p4);
        System.out.println("--------------------------------------------------");
        System.out.println("1ª Divisão (P1 / P2): " + div1);
        System.out.println("2ª Divisão (Q1 / P3): " + div2);
        System.out.println("Divisão Final (Q2 / P4): " + divFinal);
    }
}
