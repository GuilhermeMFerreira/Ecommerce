import java.util.UUID;

public class PagamentoCartaoCredito implements MetodoPagamento {
    private double limiteDisponivel;
    private String numeroCartao;

    public PagamentoCartaoCredito(double limiteDisponivel, String numeroCartao) {
        this.limiteDisponivel = limiteDisponivel;
        this.numeroCartao = numeroCartao;
    }

    @Override
    public ResultadoPagamento processar(double valor) {
        String idTransacao = UUID.randomUUID().toString();

        // Regra de Negócio 2: Cartão depende de limite
        if (this.limiteDisponivel < valor) {
            return new ResultadoPagamento(
                    false,
                    idTransacao,
                    "Limite insuficiente no cartão de crédito.",
                    null
            );
        }

        this.limiteDisponivel -= valor;
        return new ResultadoPagamento(
                true,
                idTransacao,
                "Pagamento aprovado.",
                null
        );
    }
}