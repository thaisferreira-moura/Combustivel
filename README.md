# 🚗 Controle de Combustível

## 📌 Sobre o projeto

O **Controle de Combustível** é um aplicativo Android desenvolvido em Java com o objetivo de auxiliar no registro de abastecimentos e no acompanhamento do consumo de combustível de um veículo.

O sistema permite armazenar informações sobre cada abastecimento, calcular indicadores de consumo e comparar os preços do etanol e da gasolina para ajudar o usuário a identificar a opção mais vantajosa.

## 🎯 Objetivo

Desenvolver um aplicativo para controle de abastecimentos, aplicando conceitos de programação em Java, desenvolvimento Android, manipulação de dados, validação de informações e cálculos matemáticos.

## ⚙️ Funcionalidades

- **Registro de abastecimentos:** permite informar a data, o hodômetro, os litros abastecidos, o tipo de combustível e o preço por litro.
- **Seleção do combustível:** utiliza o componente `RadioGroup` para selecionar gasolina, etanol ou diesel.
- **Consumo médio:** calcula a média de quilômetros percorridos por litro de combustível (km/L).
- **Custo por quilômetro:** calcula o custo efetivo do abastecimento por quilômetro rodado (R$/km).
- **Média diária:** calcula a quantidade média de quilômetros percorridos por dia entre dois abastecimentos.
- **Comparação de combustíveis:** compara os preços do etanol e da gasolina utilizando a regra aproximada dos 70%.
- **Histórico de abastecimentos:** exibe os registros realizados, incluindo data, hodômetro, combustível, litros e custo total.
- **Armazenamento local:** mantém os registros salvos no dispositivo utilizando `SharedPreferences`.
- **Validação de dados:** verifica o preenchimento dos campos e impede valores inválidos, além de exigir que o hodômetro seja crescente.

## 🧮 Cálculos utilizados

**1. Consumo médio (km/L)**

Distância percorrida dividida pela quantidade de litros abastecidos no registro atual.

**2. Custo efetivo (R$/km)**

Valor total do abastecimento dividido pela distância percorrida no intervalo.

**3. Média diária (km/dia)**

Distância percorrida dividida pela quantidade de dias entre os abastecimentos.

**4. Comparação entre etanol e gasolina**

O etanol tende a compensar quando seu preço por litro corresponde a até 70% do preço da gasolina. Essa regra é uma estimativa, pois a eficiência depende do veículo.

## 🛠️ Tecnologias utilizadas

- **Java:** linguagem de programação utilizada no desenvolvimento da aplicação.
- **Android Studio:** ambiente de desenvolvimento.
- **XML:** criação da interface visual.
- **RadioGroup e RadioButton:** seleção do tipo de combustível.
- **SharedPreferences:** armazenamento local dos registros.
- **JSON:** organização dos dados armazenados.

## ▶️ Como executar o projeto

1. Instale o Android Studio.
2. Extraia os arquivos do projeto.
3. Abra o Android Studio e selecione a opção **Open**.
4. Selecione a pasta principal do projeto.
5. Aguarde a sincronização do Gradle.
6. Execute o aplicativo em um emulador ou dispositivo Android compatível.

## 📱 Como utilizar

1. Informe a data do abastecimento.
2. Digite o hodômetro atual do veículo.
3. Informe a quantidade de litros abastecidos.
4. Selecione o tipo de combustível.
5. Digite o valor por litro.
6. Toque em **Registrar abastecimento**.
7. Registre pelo menos dois abastecimentos para visualizar os indicadores de consumo.
8. Para comparar os combustíveis, informe os preços do etanol e da gasolina e toque em **Qual compensa mais?**.

## 📚 Considerações finais

O projeto permite aplicar conhecimentos de desenvolvimento Android com Java, construção de interfaces, validação de entradas, persistência de dados e implementação de cálculos. A aplicação busca oferecer uma maneira simples de acompanhar os gastos com combustível e analisar o desempenho do veículo.

---

**Projeto acadêmico — Desenvolvimento Android com Java.**
