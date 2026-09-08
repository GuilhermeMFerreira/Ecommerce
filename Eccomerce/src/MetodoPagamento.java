//O public interface é como um contrato ou um padrão obrigatório para o seu código.
//Ele serve para definir quais métodos uma classe deve ter.
public interface MetodoPagamento {
    ResultadoPagamento processar(double valor);
}