public class Main {
    public static void main(String[] args) {
        Checkout checkout = new Checkout();

        System.out.println("--- TESTE 1: Compra no Cartão (Aprovada) ---");
        MetodoPagamento cartao = new PagamentoCartaoCredito(500.0, "1234-****-****-5678");
        ResultadoPagamento res1 = checkout.finalizarCompra(150.0, cartao);
        System.out.println(res1);

        System.out.println("\n--- TESTE 2: Compra no Pix (Recusada por Saldo) ---");
        MetodoPagamento pix = new PagamentoPix(20.0);
        ResultadoPagamento res2 = checkout.finalizarCompra(100.0, pix);
        System.out.println(res2);

        System.out.println("\n--- TESTE 3: Emissão de Boleto ---");
        MetodoPagamento boleto = new PagamentoBoleto(5);
        ResultadoPagamento res3 = checkout.finalizarCompra(300.0, boleto);
        System.out.println(res3);
        System.out.println("Detalhes: " + res3.getDetalhes());

        System.out.println("\n--- TESTE 4: Valor Inválido ---");
        ResultadoPagamento res4 = checkout.finalizarCompra(-50.0, cartao);
        System.out.println(res4);
    }
}