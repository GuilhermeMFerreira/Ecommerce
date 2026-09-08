import java.util.UUID;

public class PagamentoPix implements MetodoPagamento {
    private double saldoDisponivel;

    public PagamentoPix(double saldoDisponivel) {
        this.saldoDisponivel = saldoDisponivel;
    }

    @Override
    public ResultadoPagamento processar(double valor) {
        String idTransacao = UUID.randomUUID().toString();

        // Regra de Negócio 3: Pix depende de saldo
        if (this.saldoDisponivel < valor) {
            return new ResultadoPagamento(
                    false,
                    idTransacao,
                    "Saldo insuficiente na conta para transação.",
                    null
            );
        }

        this.saldoDisponivel -= valor;
        return new ResultadoPagamento(
                true,
                idTransacao,
                "Pagamento via Pix confirmado!",
                null
        );
    }
}