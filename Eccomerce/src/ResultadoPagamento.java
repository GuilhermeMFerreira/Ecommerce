import java.util.Map;

public class ResultadoPagamento {
    private final boolean aprovado;
    private final String idTransacao;
    private final String mensagem;
    private final Map<String, String> detalhes;

    public ResultadoPagamento(boolean aprovado, String idTransacao, String mensagem, Map<String, String> detalhes) {
        this.aprovado = aprovado;
        this.idTransacao = idTransacao;
        this.mensagem = mensagem;
        this.detalhes = detalhes;
    }

    public boolean isAprovado() { return aprovado; }
    public String getIdTransacao() { return idTransacao; }
    public String getMensagem() { return mensagem; }
    public Map<String, String> getDetalhes() { return detalhes; }

    @Override
    public String toString() {
        String status = aprovado ? "APROVADO" : "RECUSADO";
        return "[" + status + "] Transação: " + idTransacao + " | Mensagem: " + mensagem;
    }
}