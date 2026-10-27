# Projeto Conta Bancária

Projeto Java com Maven, JUnit 5 e JaCoCo para verificar cobertura mínima de 90% das linhas.

## Requisitos
- JDK 21
- Maven 3.9 ou superior
- Acesso à internet na primeira execução para baixar as dependências

## Executar testes
No terminal, na pasta que contém o `pom.xml`, execute:

```bash
mvn clean test
```

## Verificar a cobertura mínima de 90%
Execute:

```bash
mvn clean verify
```

O JaCoCo gera o relatório em `target/site/jacoco/index.html`. A fase `verify` falha se a cobertura de linhas ficar abaixo de 90%.

## Estrutura
- `src/main/java/ContaBancaria.java`
- `src/main/java/SaldoInsuficienteException.java`
- `src/test/java/ContaBancariaTest.java`
- `pom.xml`: configuração do Maven, JUnit 5 e JaCoCo.
