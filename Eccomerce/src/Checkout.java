public class Checkout {

    public ResultadoPagamento finalizarCompra(double valor, MetodoPagamento metodo) {
        // Regra de Negócio 1: O valor deve ser maior que zero
        if (valor <= 0) {
            return new ResultadoPagamento(
                    false,
                    "N/A",
                    "Erro de validação: O valor da compra deve ser maior que zero.",
                    null
            );
        }

        // Regras de Negócio 5 e 6: Execução polimórfica da estratégia
        return metodo.processar(valor);
    }
}