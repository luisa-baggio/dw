
class ContaBancaria {
    String titular;
    int numero;
    double saldo;

    public ContaBancaria(String titular, int numero, double saldo) {
        this.titular = titular;
        this.numero = numero;
        this.saldo = saldo;
    }

    public void transferir(ContaBancaria destino, double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException(
                "O valor deve ser positivo."
            );
        }

        if (saldo < valor) {
            throw new IllegalArgumentException(
                "Saldo insuficiente para realizar a transferência!"
            );
        }

        saldo -= valor;
        destino.saldo += valor;

        System.out.println("Transferência realizada com sucesso!");
    }

    public void exibirSaldo() {
        System.out.printf("%s - Conta %d: R$ %.2f%n",
            titular, numero, saldo);
    }
}

public class Main {
    public static void main(String[] args) {
        ContaBancaria ana =
            new ContaBancaria("Ana Lima", 123, 500.00);

        ContaBancaria carlos =
            new ContaBancaria("Carlos Souza", 456, 300.00);

        ContaBancaria maria =
            new ContaBancaria("Maria Silva", 789, 100.00);

        // Primeira transferência
        ana.transferir(carlos, 200.00);

        // Segunda transferência
        carlos.transferir(maria, 150.00);

        // Terceira transferência: trata a exceção
        try {
            maria.transferir(ana, 500.00);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        // Exibição dos saldos finais
        System.out.println("\n--- Saldos finais ---");
        ana.exibirSaldo();
        carlos.exibirSaldo();
        maria.exibirSaldo();
    }
}
