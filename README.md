# E-commerce — Sistema de Pagamentos

Sistema desenvolvido em **Java** para simular o processo de finalização de uma compra em um e-commerce, permitindo o processamento de diferentes formas de pagamento.

O projeto foi desenvolvido com foco em **Programação Orientada a Objetos (POO)**, utilizando interfaces e polimorfismo para permitir que diferentes métodos de pagamento sejam processados através de uma mesma estrutura.

---

## Objetivo

O objetivo do projeto é desenvolver um sistema simples de **checkout de e-commerce**, capaz de receber o valor de uma compra e processá-lo de acordo com o método de pagamento escolhido.

O sistema trabalha com três formas de pagamento:

*  Cartão de crédito
*  Pix
*  Boleto

Além disso, o projeto realiza validações e retorna informações sobre o resultado de cada transação.

---

## Funcionalidades

O sistema possui as seguintes funcionalidades:

* Finalização de compras;
* Validação do valor da compra;
* Pagamento com cartão de crédito;
* Pagamento via Pix;
* Geração de boleto;
* Verificação de limite disponível no cartão;
* Verificação de saldo disponível no Pix;
* Geração de identificador único para as transações;
* Geração de código de barras para boletos;
* Definição de data de vencimento do boleto;
* Retorno de status da transação;
* Retorno de mensagens e detalhes do pagamento.

---

## Métodos de pagamento

### Cartão de crédito

A classe `PagamentoCartaoCredito` implementa a interface `MetodoPagamento`.

O pagamento depende do limite disponível no cartão.

Caso o limite seja suficiente, o valor da compra é descontado do limite e o pagamento é aprovado.

Caso contrário, a transação é recusada com a mensagem:

```text
Limite insuficiente no cartão de crédito.
```

O projeto utiliza um número de cartão mascarado durante os testes para evitar a exposição de dados completos.

---

### Pix

A classe `PagamentoPix` também implementa `MetodoPagamento`.

O pagamento via Pix verifica se existe saldo suficiente para realizar a compra.

Nos testes do projeto, é utilizado um saldo de **R$ 20,00** para tentar realizar uma compra de **R$ 100,00**, resultando em uma transação recusada por falta de saldo.

---

### Boleto

A classe `PagamentoBoleto` permite gerar um boleto para a compra.

O sistema:

* Gera um ID de transação;
* Calcula uma data de vencimento futura;
* Gera um código de barras;
* Retorna os detalhes do boleto;
* Informa que o boleto está aguardando compensação.

A data de vencimento é calculada utilizando a quantidade de dias definida na criação do objeto.

---

## Conceitos de Programação Orientada a Objetos

O projeto utiliza conceitos importantes de POO.

### Interface

A interface `MetodoPagamento` funciona como um contrato que define o método:

```java
ResultadoPagamento processar(double valor);
```

Dessa forma, cada forma de pagamento precisa implementar seu próprio processamento.

---

### Polimorfismo

O polimorfismo é utilizado no `Checkout`.

O método:

```java
public ResultadoPagamento finalizarCompra(
    double valor,
    MetodoPagamento metodo
)
```

recebe qualquer objeto que implemente `MetodoPagamento`.

Assim, o mesmo método pode trabalhar com:

```text
PagamentoCartaoCredito
PagamentoPix
PagamentoBoleto
```

sem precisar criar uma lógica diferente de checkout para cada forma de pagamento.

O próprio código utiliza `metodo.processar(valor)` para executar a implementação correspondente.

---

### Encapsulamento

As informações das classes são mantidas dentro dos próprios objetos e acessadas por métodos específicos.

A classe `ResultadoPagamento`, por exemplo, mantém seus atributos como `private` e disponibiliza métodos para consultar as informações.

---

## Fluxo do sistema

O funcionamento básico do sistema pode ser representado da seguinte maneira:

```text
                    ┌─────────────────┐
                    │      Main       │
                    └────────┬────────┘
                             │
                             ▼
                    ┌─────────────────┐
                    │    Checkout     │
                    └────────┬────────┘
                             │
                    valor + método
                             │
                             ▼
                  ┌─────────────────────┐
                  │  MetodoPagamento    │
                  └─────────┬───────────┘
                            │
             ┌──────────────┼──────────────┐
             ▼              ▼              ▼
       ┌───────────┐  ┌───────────┐  ┌───────────┐
       │  Cartão   │  │    Pix    │  │  Boleto   │
       └─────┬─────┘  └─────┬─────┘  └─────┬─────┘
             │              │              │
             └──────────────┼──────────────┘
                            ▼
                  ┌───────────────────┐
                  │ResultadoPagamento  │
                  └───────────────────┘
```

---

## Estrutura do projeto

```text
Ecommerce/
│
└── Eccomerce/
    │
    ├── .idea/
    ├── src/
    │   ├── Main.java
    │   ├── Checkout.java
    │   ├── MetodoPagamento.java
    │   ├── PagamentoBoleto.java
    │   ├── PagamentoCartaoCredito.java
    │   ├── PagamentoPix.java
    │   └── ResultadoPagamento.java
    │
    ├── .gitignore
    └── Eccomerce.iml
```

A pasta `src` do repositório contém essas sete classes/arquivos Java.

---

## Principais classes

| Classe                   | Responsabilidade                               |
| ------------------------ | ---------------------------------------------- |
| `Main`                   | Executa os testes do sistema                   |
| `Checkout`               | Valida e finaliza a compra                     |
| `MetodoPagamento`        | Define o contrato para os métodos de pagamento |
| `PagamentoCartaoCredito` | Processa pagamentos com cartão                 |
| `PagamentoPix`           | Processa pagamentos via Pix                    |
| `PagamentoBoleto`        | Gera e processa boletos                        |
| `ResultadoPagamento`     | Armazena o resultado da transação              |

---

## Testes realizados

O arquivo `Main.java` executa quatro cenários para demonstrar o funcionamento do sistema.

### Teste 1 — Cartão aprovado

```text
Compra: R$ 150,00
Limite disponível: R$ 500,00
Resultado: APROVADO
```

O limite é suficiente para realizar a compra.

---

### Teste 2 — Pix recusado

```text
Saldo: R$ 20,00
Compra: R$ 100,00
Resultado: RECUSADO
```

O saldo disponível não é suficiente para a compra.

---

### Teste 3 — Boleto

```text
Compra: R$ 300,00
Vencimento: futuro
Resultado: Boleto gerado
```

O sistema gera um ID de transação, uma data de vencimento e um código de barras.

---

### Teste 4 — Valor inválido

```text
Valor da compra: -R$ 50,00
Resultado: RECUSADO
```

O checkout não permite finalizar uma compra com valor menor ou igual a zero.

---

## ResultadoPagamento

A classe `ResultadoPagamento` é responsável por representar o resultado de uma operação.

Ela armazena:

* `aprovado` — indica se o pagamento foi aprovado;
* `idTransacao` — identifica a transação;
* `mensagem` — informa o resultado da operação;
* `detalhes` — armazena informações adicionais.

O método `toString()` apresenta o resultado no formato:

```text
[APROVADO] Transação: ID | Mensagem: Pagamento aprovado.
```

ou:

```text
[RECUSADO] Transação: ID | Mensagem: ...
```

---

## 🛠️ Tecnologias utilizadas

*  Java
*  Programação Orientada a Objetos
*  Interfaces
*  Polimorfismo
*  Java Collections (`Map` e `HashMap`)
*  `LocalDate`
*  `UUID`
*  Git e GitHub

---

## Como executar

### Pré-requisito

É necessário possuir o **Java JDK** instalado.

### Pela IDE

1. Clone o repositório.
2. Abra o projeto em uma IDE compatível com Java.
3. Acesse a pasta `src`.
4. Abra o arquivo `Main.java`.
5. Execute o método `main()`.

### Pelo terminal

Entre na pasta `src`:

```bash
cd Eccomerce/src
```

Compile os arquivos:

```bash
javac *.java
```

Execute o programa:

```bash
java Main
```

---

## Regras de negócio

O sistema possui algumas regras para controlar o processamento dos pagamentos:

| Regra        | Descrição                                            |
| ------------ | ---------------------------------------------------- |
| Valor válido | A compra deve possuir valor maior que zero           |
| Cartão       | O limite deve ser suficiente para a compra           |
| Pix          | O saldo deve ser suficiente                          |
| Boleto       | Deve possuir vencimento futuro                       |
| Resultado    | Toda operação retorna um objeto `ResultadoPagamento` |

A validação do valor da compra acontece no `Checkout`, enquanto cada método de pagamento possui sua própria lógica de processamento.
