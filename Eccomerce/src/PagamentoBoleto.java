import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PagamentoBoleto implements MetodoPagamento {
    private final int diasParaVencimento;

    public PagamentoBoleto(int diasParaVencimento) {
        this.diasParaVencimento = diasParaVencimento;
    }

    @Override
    public ResultadoPagamento processar(double valor) {
        String idTransacao = UUID.randomUUID().toString();

        // Regra de Negócio 4: Boleto com vencimento futuro
        LocalDate dataVencimento = LocalDate.now().plusDays(diasParaVencimento);
        String codigoBarras = "34191." + UUID.randomUUID().toString().replace("-", "").substring(0, 10).toUpperCase();

        Map<String, String> detalhes = new HashMap<>();
        detalhes.put("vencimento", dataVencimento.format(DateTimeFormatter.ISO_DATE));
        detalhes.put("codigoBarras", codigoBarras);

        return new ResultadoPagamento(
                true,
                idTransacao,
                "Boleto gerado com sucesso. Aguardando compensação.",
                detalhes
        );
    }
}