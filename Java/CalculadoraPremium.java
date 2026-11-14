import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CalculadoraPremium extends JFrame implements ActionListener {

    // Componentes da Interface
    private JTextField tela;
    private JTextArea areaHistorico;
    private JPanel painelBotoes;

    // Variáveis de Controle Lógico
    private double num1 = 0, num2 = 0, resultado = 0;
    private String operacao = "";
    private boolean inicioNovaOperacao = true;
    private ArrayList<String> historico = new ArrayList<>();

    public CalculadoraPremium() {
        // Configurações Janela Principal
        setTitle("Calculadora Mega Ultra Premium");
        setSize(580, 650); // Janela alargada para acomodar o teclado científico
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(12, 12));
        getContentPane().setBackground(new Color(24, 24, 24)); // Fundo Ultra Dark

        // 1. Tela de Exibição
        tela = new JTextField("0");
        tela.setFont(new Font("Segoe UI", Font.BOLD, 38));
        tela.setHorizontalAlignment(JTextField.RIGHT);
        tela.setBackground(new Color(35, 35, 35));
        tela.setForeground(Color.WHITE);
        tela.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        tela.setEditable(false);
        add(tela, BorderLayout.NORTH);

        // 2. Painel Lateral de Histórico com Botão de Limpar
        JPanel painelDireito = new JPanel(new BorderLayout(5, 5));
        painelDireito.setBackground(new Color(24, 24, 24));

        areaHistorico = new JTextArea(5, 12);
        areaHistorico.setFont(new Font("Consolas", Font.PLAIN, 12));
        areaHistorico.setBackground(new Color(18, 18, 18));
        areaHistorico.setForeground(new Color(150, 150, 150));
        areaHistorico.setEditable(false);
        areaHistorico.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(45, 45, 45)), 
                "Histórico", 0, 0, null, new Color(180, 180, 180)
        ));
        
        JScrollPane scrollHistorico = new JScrollPane(areaHistorico);
        painelDireito.add(scrollHistorico, BorderLayout.CENTER);

        JButton btnLimparHistorico = new JButton("Limpar Hist.");
        btnLimparHistorico.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btnLimparHistorico.setBackground(new Color(50, 50, 50));
        btnLimparHistorico.setForeground(Color.LIGHT_GRAY);
        btnLimparHistorico.setFocusPainted(false);
        btnLimparHistorico.addActionListener(e -> {
            historico.clear();
            areaHistorico.setText("");
        });
        painelDireito.add(btnLimparHistorico, BorderLayout.SOUTH);

        add(painelDireito, BorderLayout.EAST);

        // 3. Grade de Botões Expandida (7 Linhas x 5 Colunas)
        painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(7, 5, 6, 6));
        painelBotoes.setBackground(new Color(24, 24, 24));

        // Layout de botões Ultra Científico
        String[] botoes = {
            "C",   "√",   "x²",  "xⁿ",  "/",
            "π",   "7",   "8",   "9",   "*",
            "e",   "4",   "5",   "6",   "-",
            "n!",  "1",   "2",   "3",   "+",
            "|x|", "±",   "0",   ".",   "=",
            "Sin", "Cos", "Tan", "Log", "%",
            "Asin","Acos","Atan","Ln",  "Mod"
        };

        for (String texto : botoes) {
            JButton botao = new JButton(texto);
            botao.setFont(new Font("Segoe UI", Font.BOLD, 15));
            botao.setFocusPainted(false);
            botao.addActionListener(this);

            // Estilização Avançada por Categoria (Visual Premium UI)
            if (texto.matches("[0-9]|\\.")) {
                botao.setBackground(new Color(55, 55, 55)); // Números
                botao.setForeground(Color.WHITE);
            } else if (texto.equals("=")) {
                botao.setBackground(new Color(0, 137, 123)); // Igual (Teal Vibrante)
                botao.setForeground(Color.WHITE);
                botao.setFont(new Font("Segoe UI", Font.BOLD, 20));
            } else if (texto.matches("[+\\-*/]|xⁿ|Mod")) {
                botao.setBackground(new Color(230, 110, 0)); // Operadores Binários (Laranja)
                botao.setForeground(Color.WHITE);
            } else if (texto.matches("π|e")) {
                botao.setBackground(new Color(38, 50, 56)); // Constantes (Azul Escuro)
                botao.setForeground(new Color(144, 202, 249));
            } else {
                botao.setBackground(new Color(40, 40, 40)); // Funções Científicas
                botao.setForeground(new Color(0, 176, 255)); // Ciano Neon
            }

            painelBotoes.add(botao);
        }

        add(painelBotoes, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        // 1. Entrada de Números e Decimais
        if (comando.matches("[0-9]|\\.")) {
            if (inicioNovaOperacao) {
                tela.setText(comando.equals(".") ? "0." : comando);
                inicioNovaOperacao = false;
            } else {
                if (comando.equals(".") && tela.getText().contains(".")) return;
                tela.setText(tela.getText() + comando);
            }
        } 
        // 2. Constantes Matemáticas
        else if (comando.equals("π")) {
            tela.setText(formatarResultado(Math.PI));
            inicioNovaOperacao = false;
        } else if (comando.equals("e")) {
            tela.setText(formatarResultado(Math.E));
            inicioNovaOperacao = false;
        }
        // 3. Botão de Limpar Geral
        else if (comando.equals("C")) {
            tela.setText("0");
            num1 = num2 = resultado = 0;
            operacao = "";
            inicioNovaOperacao = true;
        } 
        // 4. Inversão de Sinal
        else if (comando.equals("±")) {
            double val = Double.parseDouble(tela.getText());
            tela.setText(formatarResultado(val * -1));
        }
        // 5. Funções Científicas de um Único Número (Execução Imediata)
        else if (comando.matches("√|x²|Sin|Cos|Tan|Asin|Acos|Atan|Log|Ln|n!|\\|x\\||%")) {
            double val = Double.parseDouble(tela.getText());
            String operacaoNome = "";
            
            switch (comando) {
                case "√" ->  { resultado = Math.sqrt(val); operacaoNome = "√( " + val + " )"; }
                case "x²" -> { resultado = Math.pow(val, 2); operacaoNome = val + "²"; }
                case "Sin" -> { resultado = Math.sin(Math.toRadians(val)); operacaoNome = "sin(" + val + "°)"; }
                case "Cos" -> { resultado = Math.cos(Math.toRadians(val)); operacaoNome = "cos(" + val + "°)"; }
                case "Tan" -> { resultado = Math.tan(Math.toRadians(val)); operacaoNome = "tan(" + val + "°)"; }
                case "Asin" -> { resultado = Math.toDegrees(Math.asin(val)); operacaoNome = "asin(" + val + ")"; }
                case "Acos" -> { resultado = Math.toDegrees(Math.acos(val)); operacaoNome = "acos(" + val + ")"; }
                case "Atan" -> { resultado = Math.toDegrees(Math.atan(val)); operacaoNome = "atan(" + val + ")"; }
                case "Log" -> { resultado = Math.log10(val); operacaoNome = "log(" + val + ")"; }
                case "Ln" ->  { resultado = Math.log(val); operacaoNome = "ln(" + val + ")"; }
                case "|x|" -> { resultado = Math.abs(val); operacaoNome = "abs(" + val + ")"; }
                case "%" ->   { resultado = val / 100.0; operacaoNome = val + "%"; }
                case "n!" ->  { 
                    if (val < 0 || val != (long)val) {
                        tela.setText("Erro: Inválido");
                        inicioNovaOperacao = true;
                        return;
                    }
                    resultado = calcularFatorial((int)val); 
                    operacaoNome = (int)val + "!"; 
                }
            }
            
            tela.setText(formatarResultado(resultado));
            adicionarAoHistorico(operacaoNome + " = " + formatarResultado(resultado));
            inicioNovaOperacao = true;
        }
        // 6. Operadores de Dois Números (+, -, *, /, xⁿ, Mod)
        else if (comando.matches("[+\\-*/]|xⁿ|Mod")) {
            num1 = Double.parseDouble(tela.getText());
            operacao = comando;
            inicioNovaOperacao = true;
        } 
        // 7. Processamento do Resultado (=)
        else if (comando.equals("=")) {
            if (operacao.isEmpty()) return;
            num2 = Double.parseDouble(tela.getText());
            
            switch (operacao) {
                case "+" -> resultado = num1 + num2;
                case "-" -> resultado = num1 - num2;
                case "*" -> resultado = num1 * num2;
                case "/" -> {
                    if (num2 == 0) {
                        tela.setText("Erro: Div/0");
                        operacao = "";
                        inicioNovaOperacao = true;
                        return;
                    }
                    resultado = num1 / num2;
                }
                case "xⁿ" -> resultado = Math.pow(num1, num2);
                case "Mod" -> resultado = num1 % num2;
            }
            
            String sinalExibicao = operacao.equals("xⁿ") ? "^" : (operacao.equals("Mod") ? " mod " : " " + operacao + " ");
            String operacaoCompleta = formatarResultado(num1) + sinalExibicao + formatarResultado(num2) + " = " + formatarResultado(resultado);
            
            tela.setText(formatarResultado(resultado));
            adicionarAoHistorico(operacaoCompleta);
            operacao = "";
            inicioNovaOperacao = true;
        }
    }

    // Auxiliar: Cálculo de Fatorial
private double calcularFatorial(int n) {
double fat = 1;
for (int i = 2; i <= n; i++) {
fat *= i;
}
return fat;
}
// Auxiliar: Formatação limpa de números decimais/inteiros
private String formatarResultado(double d) {
if (Double.isNaN(d)) return "Erro: Indefinido";
if (Double.isInfinite(d)) return "Erro: Infinito";
if (d == (long) d) return String.format("%d", (long) d);
return String.format("%s", d);
}
// Auxiliar: Adiciona e atualiza a caixa de texto do histórico
private void adicionarAoHistorico(String linha) {
historico.add(linha);
StringBuilder sb = new StringBuilder();
for (String h : historico) {
sb.append(h).append("\n");
}
areaHistorico.setText(sb.toString());
}
public static void main(String[] args) {
SwingUtilities.invokeLater(() -> {
try {
UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
} catch (Exception ignored) {}
new CalculadoraPremium().setVisible(true);
});
}
}