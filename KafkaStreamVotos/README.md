# Kafka Streams Votos

## Visão Geral

O projeto **Kafka-Streams-Votos** é responsável por ler as mensagens do tópico `unique-votes` e calcular, em tempo real, a porcentagem de votos que cada conjunto de participantes recebeu.

## Funcionamento Interno

- O sistema utiliza duas **State Stores** distintas:
  - Uma armazena a quantidade de votos recebidos por cada conjunto de participantes.
  - A outra armazena o **total de votos**.

- A porcentagem é calculada com base na divisão da quantidade de votos por conjunto pelo total de votos recebidos.

- Os resultados são atualizados automaticamente sempre que uma nova mensagem chega ao tópico `unique-votes`.

## Endpoint

- O endpoint que expõe os resultados é:
  - `GET /result`
  - Esse endpoint retorna as porcentagens atuais dos votos processados.
  - Ele apenas retorna os dados, não aciona nenhum novo processamento (o processamento já ocorre a cada novo voto).

## Configurações

- O servidor roda na porta **8082**.

## Observações

- Se os resultados estiverem inconsistentes, tente apagar o diretório `/tmp/votes-stream`, pois nele as **State Stores** são armazenadas. Com muitos builds ou reinicializações da aplicação, dados antigos podem ser utilizados incorretamente nos cálculos.
- Caso queria modificar o local onde as **State Stotes** são armazenadas, vá em `application.properties` e mude o atributo `spring.kafka.streams.state-dir`

