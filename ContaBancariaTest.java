import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContaBancariaTest {

    @Test
    public void deveCriarContaComSaldoZero() {
        ContaBancaria conta =
            new ContaBancaria("123", "Ana Lima");

        assertEquals(0.00, conta.getSaldo());
    }

    @Test
    public void deveRejeitarNumeroNulo() {
        assertThrows(IllegalArgumentException.class,
            () -> new ContaBancaria(null, "Ana Lima"));
    }

    @Test
    public void deveRejeitarNumeroVazio() {
        assertThrows(IllegalArgumentException.class,
            () -> new ContaBancaria(" ", "Ana Lima"));
    }

    @Test
    public void deveRejeitarTitularInvalido() {
        assertThrows(IllegalArgumentException.class,
            () -> new ContaBancaria("123", "Al"));
    }

    @Test
    public void deveDepositarValor() {
        ContaBancaria conta =
            new ContaBancaria("123", "Ana Lima");

        conta.depositar(100);

        assertEquals(100.00, conta.getSaldo());
    }

    @Test
    public void deveRejeitarDepositoZero() {
        ContaBancaria conta =
            new ContaBancaria("123", "Ana Lima");

        assertThrows(IllegalArgumentException.class,
            () -> conta.depositar(0));

        assertEquals(0.00, conta.getSaldo());
    }

    @Test
    public void deveRejeitarDepositoNegativo() {
        ContaBancaria conta =
            new ContaBancaria("123", "Ana Lima");

        assertThrows(IllegalArgumentException.class,
            () -> conta.depositar(-50));

        assertEquals(0.00, conta.getSaldo());
    }

    @Test
    public void deveSacarValor() {
        ContaBancaria conta =
            new ContaBancaria("123", "Ana Lima");

        conta.depositar(100);
        conta.sacar(40);

        assertEquals(60.00, conta.getSaldo());
    }

    @Test
    public void devePermitirSacarSaldoTotal() {
        ContaBancaria conta =
            new ContaBancaria("123", "Ana Lima");

        conta.depositar(100);
        conta.sacar(100);

        assertEquals(0.00, conta.getSaldo());
    }

    @Test
    public void deveRejeitarSaqueAcimaDoSaldo() {
        ContaBancaria conta =
            new ContaBancaria("123", "Ana Lima");

        conta.depositar(100);

        assertThrows(SaldoInsuficienteException.class,
            () -> conta.sacar(100.01));

        assertEquals(100.00, conta.getSaldo());
    }

    @Test
    public void deveRejeitarSaqueZero() {
        ContaBancaria conta =
            new ContaBancaria("123", "Ana Lima");

        conta.depositar(100);

        assertThrows(IllegalArgumentException.class,
            () -> conta.sacar(0));

        assertEquals(100.00, conta.getSaldo());
    }

    @Test
    public void deveTransferirValor() {
        ContaBancaria origem =
            new ContaBancaria("123", "Ana Lima");

        ContaBancaria destino =
            new ContaBancaria("456", "Joao Silva");

        origem.depositar(100);
        origem.transferir(destino, 30);

        assertEquals(70.00, origem.getSaldo());
        assertEquals(30.00, destino.getSaldo());
    }

    @Test
    public void deveRejeitarTransferenciaParaPropriaConta() {
        ContaBancaria conta =
            new ContaBancaria("123", "Ana Lima");

        conta.depositar(100);

        assertThrows(IllegalArgumentException.class,
            () -> conta.transferir(conta, 30));

        assertEquals(100.00, conta.getSaldo());
    }

    @Test
    public void deveRejeitarDestinoNulo() {
        ContaBancaria conta =
            new ContaBancaria("123", "Ana Lima");

        conta.depositar(100);

        assertThrows(IllegalArgumentException.class,
            () -> conta.transferir(null, 30));

        assertEquals(100.00, conta.getSaldo());
    }

    @Test
    public void deveManterSaldosQuandoTransferenciaFalhar() {
        ContaBancaria origem =
            new ContaBancaria("123", "Ana Lima");

        ContaBancaria destino =
            new ContaBancaria("456", "Joao Silva");

        origem.depositar(100);
        destino.depositar(50);

        assertThrows(SaldoInsuficienteException.class,
            () -> origem.transferir(destino, 150));

        assertEquals(100.00, origem.getSaldo());
        assertEquals(50.00, destino.getSaldo());
    }

    @Test
    public void deveRejeitarTransferenciaComValorNegativo() {
        ContaBancaria origem =
            new ContaBancaria("123", "Ana Lima");

        ContaBancaria destino =
            new ContaBancaria("456", "Joao Silva");

        origem.depositar(100);

        assertThrows(IllegalArgumentException.class,
            () -> origem.transferir(destino, -10));

        assertEquals(100.00, origem.getSaldo());
        assertEquals(0.00, destino.getSaldo());
    }

    @Test
    public void deveConsultarSaldoSemAlterar() {
        ContaBancaria conta =
            new ContaBancaria("123", "Ana Lima");

        conta.depositar(200);

        assertEquals(200.00, conta.getSaldo());
        assertEquals(200.00, conta.getSaldo());
    }
}