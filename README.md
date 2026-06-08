# Trabalho Prático: Concorrência e Consistência em Banco de Dados com Spring Boot

## Integrantes

* Vergílio da Costa Almeida Júnior — Parte 1: cenário sem controle de concorrência
* Marcus Vinícius Carreira Banhos Junior — Parte 2: solução com controle de versão otimista

## Tecnologias Utilizadas

* Java 17
* Spring Boot
* Spring Data JPA
* Hibernate
* H2 Database
* Apache JMeter
* Maven
* Eclipse IDE

## Como Rodar a Aplicação

1. Clone ou baixe este repositório.
2. Abra o projeto em uma IDE Java, como Eclipse ou IntelliJ.
3. Aguarde o Maven baixar as dependências.
4. Execute a classe principal `Faculdade.java`.
5. A aplicação será iniciada em:
http://localhost:8080

## Endpoints

### Conta sem controle de concorrência

Criar conta:

curl -X POST http://localhost:8080/contas

Depositar:

curl -X POST "http://localhost:8080/contas/1/deposito?valor=1000"

Sacar:

curl -X POST "http://localhost:8080/contas/1/saque?valor=10"

### Conta com controle de versão otimista

Criar conta versionada:

curl -X POST http://localhost:8080/contas-versionadas

Depositar:

curl -X POST "http://localhost:8080/contas-versionadas/1/deposito?valor=1000"

Sacar:

curl -X POST "http://localhost:8080/contas-versionadas/1/saque?valor=10"

## Testes com JMeter

O arquivo de cenário do JMeter está salvo na raiz do repositório:

teste-concorrencia.jmx
```

O teste foi configurado com múltiplas threads simulando requisições simultâneas de saque na mesma conta bancária.

## Relatório de Conclusão

Na primeira parte do trabalho, foi implementada a entidade `ContaBancaria`, utilizando apenas transações básicas com `@Transactional`, sem nenhum mecanismo específico de controle de concorrência.

Durante os testes com JMeter, múltiplas requisições simultâneas foram enviadas para a mesma conta. Esse cenário demonstrou o problema conhecido como Lost Update, no qual transações concorrentes podem sobrescrever alterações umas das outras, causando inconsistência no saldo final.

Na segunda parte, foi implementada a entidade `ContaBancariaVersionada`, utilizando a anotação `@Version` do Hibernate/JPA para aplicar optimistic locking.

Com essa abordagem, o Hibernate passou a controlar a versão do registro e detectar conflitos quando duas transações tentavam atualizar a mesma conta ao mesmo tempo. Quando o conflito ocorre, a aplicação trata a exceção e retorna HTTP 409 Conflict.

Dessa forma, foi possível comparar o comportamento das duas abordagens: sem controle de concorrência, há risco de inconsistência; com `@Version`, o sistema detecta conflitos e preserva a integridade dos dados.

## Prints dos Testes

Os prints dos testes realizados com JMeter e da execução da aplicação foram incluídos no relatório em PDF entregue junto com este repositório.
