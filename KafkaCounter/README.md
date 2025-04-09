# Kafka-Counter

## Visão Geral

O módulo **Kafka-Counter** é responsável por registrar os votos recebidos no MongoDB e garantir que somente votos válidos e únicos sejam enviados para processamento pelo Kafka Streams. Este serviço atua como um consumidor do tópico Kafka `votes`, realizando verificações e armazenamentos condicionais.

## Funcionamento Geral

1. **Validação Inicial com Redis:**
   - Ao iniciar, o consumidor verifica se o Redis está populado com os IDs válidos de conjuntos de participantes.
   - Caso não esteja, ele carrega os dados a partir dos parâmetros de configuração atualizados pela Voting-API, preenchendo o Redis com os IDs válidos para a votação atual.

2. **Recepção de Votos:**
   - Quando um `VoteEvent` é recebido do tópico `votes`, o Kafka-Counter executa os seguintes passos:

     a. **Verifica a validade do ID do conjunto de participantes usando o Redis.**
     
     b. **Determina se o voto é repetido ou único:**
        - Se o usuário já votou no mesmo conjunto de participantes anteriormente, o voto é considerado **repetido** e armazenado na coleção `repeatedVote`.
        - Se o usuário ainda não votou nesse conjunto, ou se votou em outro conjunto anteriormente, o voto é considerado **único** e armazenado na coleção `uniqueVote`.

     c. **Encaminhamento para Kafka Streams:**
        - Apenas votos **únicos** são encaminhados ao tópico `unique-votes`.

## Coleções Utilizadas no MongoDB

- **`uniqueVote`**
  - Armazena votos válidos e únicos feitos por usuários em conjuntos específicos de participantes.
  - Esses votos são processados posteriormente pelo módulo Kafka-Streams-Votos para cálculo de porcentagens.

- **`repeatedVote`**
  - Armazena votos duplicados de usuários no mesmo conjunto de participantes, impedindo contagem duplicada no resultado final.

## Classes Relevantes

- **`RepeatedVote`**
  - Classe que representa o schema da coleção `repeatedVote`.

- **`VoteEvent`** (DTO)
  - Representa os dados recebidos da votação: ID do conjunto de participantes e e-mail do usuário.
 
- **UniqueVote**
  - Classe que representa o shchema da coleção `uniqueVote`

## Tópicos Kafka

- **votes**: tópico onde os eventos de voto são inicialmente publicados pela Voting-API.
- **unique-votes**: tópico onde somente votos únicos são encaminhados para posterior análise pelas streams.

---

Esse módulo é essencial para garantir a integridade dos dados da votação, assegurando que cada voto válido e único seja processado de forma eficiente, ao mesmo tempo que armazena separadamente votos duplicados.

