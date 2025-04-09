# Voting-API - Documentação do Módulo

## 🧩 Objetivo Principal

O projeto **Voting-API** é responsável por gerenciar os dados de entrada do sistema de votação. Ele oferece uma API REST para:

- Cadastrar usuários aptos a votar
- Cadastrar participantes
- Criar conjuntos de participantes (conjuntos que podem receber votos)
- Definir parâmetros de configuração da votação
- Registrar votos enviados, produzindo mensagens para o tópico Kafka `votes`

⚠️ **Importante**: A Voting-API não possui conhecimento sobre as coleções onde os votos são armazenados, apenas os envia para o Kafka. O processamento e contagem dos votos são responsabilidades dos outros módulos.

---

## 🗂️ Estrutura de Dados (Modelos)

### 📄 `ConfigParameters`

- Representa os parâmetros de configuração da votação.
- Um dos principais parâmetros indica quais **conjuntos** estão disponíveis para votação.
- Ao iniciar, a API utiliza essas configurações para popular o Redis (cache em memória), tornando os dados rapidamente acessíveis ao **Kafka-Counter**.

### 🧑 `Participant`

- Representa uma pessoa que pode ser adicionada a um conjunto de votação.
- Participantes **não recebem votos diretamente**. Em vez disso, devem estar dentro de um conjunto para que possam ser votados.
- A API permite criar novos participantes e armazená-los no banco de dados.

### 👥 `ParticipantGroup`

- Representa **conjuntos de participantes** que podem receber votos.
- Estrutura:
  - Um `Map<String, List<String>>`, onde:
    - A **chave** é um identificador aleatório do conjunto
    - O **valor** é uma lista de IDs de participantes que formam esse conjunto

#### 📝 Exemplo:
Imagine uma eleição para um grêmio escolar com 3 chapas:
- Cada chapa tem um presidente e um vice-presidente (2 participantes)
- Cada conjunto representa uma chapa
- O `ParticipantGroup` seria algo como:

```json
{
  "ids": "Grêmio Escolar 2025",
  "participantsIds": {
    "abc123": ["id_presidente1", "id_vice1"],
    "def456": ["id_presidente2", "id_vice2"],
    "ghi789": ["id_presidente3", "id_vice3"]
  }
}
```

### 🙋‍♂️ `User`

- Representa um usuário autorizado a votar
- Armazenado no banco com `nome` e `email`

---

## 📤 Envio de Votos

A API permite o envio de votos via um DTO chamado `VoteEvent`. Esse objeto é enviado para o tópico Kafka chamado `votes`.

### Estrutura do `VoteEvent`
```json
{
  "setId": "abc123",
  "userEmail": "usuario@example.com"
}
```

- `setId`: ID do conjunto de participantes votado
- `userEmail`: Email do usuário que realizou o voto

---

## 🔁 Fluxo Resumido da Voting-API

1. A API é iniciada e lê os parâmetros de configuração do MongoDB
2. Os conjuntos válidos para votação são salvos no Redis
3. Usuários, participantes e grupos são formados conforme a necessidade
4. Quando um voto é recebido, ele é transformado em um `VoteEvent` e publicado no Kafka

---

