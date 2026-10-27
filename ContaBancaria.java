public class ContaBancaria {

    private String numero;
    private String titular;
    private double saldo;

    public ContaBancaria(String numero, String titular) {
        if (numero == null || numero.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "O número da conta é obrigatório."
            );
        }

        if (titular == null || titular.trim().length() < 3) {
            throw new IllegalArgumentException(
                "O titular deve ter no mínimo 3 caracteres."
            );
        }

        this.numero = numero;
        this.titular = titular;
        this.saldo = 0.00;
    }

    public void depositar(double valor) {
        validarValor(valor);
        saldo += valor;
    }

    public void sacar(double valor) {
        validarValor(valor);

        if (valor > saldo) {
            throw new SaldoInsuficienteException(
                "Saldo insuficiente para realizar o saque."
            );
        }

        saldo -= valor;
    }

    public void transferir(ContaBancaria destino, double valor) {
        if (destino == null) {
            throw new IllegalArgumentException(
                "A conta de destino não pode ser nula."
            );
        }

        if (destino == this) {
            throw new IllegalArgumentException(
                "Não é possível transferir para a própria conta."
            );
        }

        validarValor(valor);

        if (valor > saldo) {
            throw new SaldoInsuficienteException(
                "Saldo insuficiente para realizar a transferência."
            );
        }

        this.saldo -= valor;
        destino.saldo += valor;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    private void validarValor(double valor) {
        if (valor <= 0 || !Double.isFinite(valor)) {
            throw new IllegalArgumentException(
                "O valor deve ser positivo e finito."
            );
        }
    }
}