# Sistema de Votação - Visão Geral

## 📌 Nome do Projeto
Sistema de Votação

## 🔧 Dependências Críticas

O projeto utiliza as seguintes tecnologias e serviços:

- **Java**: 17
- **Spring Boot**: 3.4.3
- **Apache Kafka**: `wurstmeister/kafka:latest` (via Docker)
- **Zookeeper**: `wurstmeister/zookeeper:latest` (via Docker)
- **MongoDB**: `mongo:latest` (via Docker)
- **Redis**: `redis:latest` (via Docker)
- **Docker Compose**: versão 3.8

## 🎯 Escopo Geral

O projeto tem como objetivo fornecer um sistema de votação que:

- Receba votos via API
- Armazene os votos em um banco de dados NoSQL
- Calcule e disponibilize em tempo real a porcentagem de votos de cada participante
- Permita o cadastro de:
  - Parâmetros de configuração da votação
  - Usuários
  - Participantes
  - Grupos de participantes (votações podem ser por grupo ou individuais)

A estrutura modular permite fácil escalabilidade e manutenção.

## 🔄 Lógica de Funcionamento

O sistema é composto por três projetos que se comunicam de forma assíncrona utilizando Kafka:

### 1. Voting-API

- **Função:** API de entrada do sistema
- **Responsabilidades:**
  - Receber votos através de requisições HTTP (REST)
  - Produzir mensagens com os votos no tópico Kafka de votação
  - Cadastrar entidades como participantes, grupos, usuários e parâmetros

### 2. Kafka-Counter

- **Função:** Serviço intermediário de consumo e roteamento
- **Responsabilidades:**
  - Consumir mensagens do tópico Kafka de votação
  - Armazenar os votos no MongoDB
  - Produzir os votos recebidos em um novo tópico Kafka, destinado ao processamento em stream

### 3. Kafka-Streams-Votos

- **Função:** Processamento contínuo dos votos
- **Responsabilidades:**
  - Calcular, em tempo real, a porcentagem de votos recebida por cada participante
  - Tornar os dados acessíveis através de uma API GET
  - Utilizar Kafka Streams para manter os dados atualizados em tempo real enquanto o serviço estiver rodando

---

### OBS: Esse documento cobre a visão geral do projeto. Cada pasta dentro do diretório raiz desse repositorio contém projetos Java individuais com seus respectivos markdowns

