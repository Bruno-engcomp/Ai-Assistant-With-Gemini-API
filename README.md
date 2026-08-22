# 🎙️ Budgeting Intelligent API - Spring AI & Gemini

![Java](https://img.shields.io/badge/Java-21-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen.svg)
![Spring AI](https://img.shields.io/badge/Spring_AI-Google_GenAI-blue.svg)
![Clean Architecture](https://img.shields.io/badge/Architecture-DDD-purple.svg)

Este projeto é uma API Inteligente para gestão financeira (Budgeting) que processa comandos de voz para criar e consultar transações. Ele utiliza o poder do **Spring AI** integrado ao **Google Gemini 1.5 Flash** e **Google Cloud TTS**.

Este repositório é uma evolução do projeto final do bootcamp da **DIO (Digital Innovation One)**, migrado da OpenAI para o ecossistema do Google, tirando proveito da multimodalidade nativa do Gemini.

## Funcionalidades

- **Processamento de Áudio Multimodal:** Envio direto de arquivos de áudio para o Gemini, sem a necessidade de um modelo intermediário de conversão *Speech-to-Text*.
- **Tool Calling Automático:** A inteligência artificial entende a intenção do usuário no áudio e executa automaticamente as regras de negócio da aplicação (Casos de Uso de cadastro ou listagem).
- **Respostas em Voz (TTS):** O resultado da operação é convertido de texto para fala (Text-to-Speech) usando vozes neurais do Google Cloud, retornando um arquivo MP3 para o usuário.
- **REST Tradicional:** Suporte simultâneo a chamadas REST clássicas via JSON.

## Arquitetura

O projeto foi construído sob rigorosos padrões de **Clean Architecture** e **Domain-Driven Design (DDD)**, dividindo as responsabilidades em três camadas principais:

1. **Domain:** O coração do sistema. Contém as entidades, Value Objects (`TransactionId`), Enums (`Category`) e a interface do repositório. Zero dependência de frameworks externos.
2. **Application:** Contém os Casos de Uso (`PersistTransactionUseCase` e `ListTransactionByCategoryUseCase`). Eles funcionam como **Tools (Ferramentas)** para o Gemini, utilizando a interface `java.util.function.Function`.
3. **Infrastructure:** Contém as conexões com o mundo externo. Aqui residem os Controllers REST, a integração com o Gemini (`ChatClient`), a comunicação com o Google TTS e as entidades/repositórios do Spring Data JPA.

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
- **Spring AI (Google GenAI Starter)**
- **Google Cloud Text-to-Speech SDK**
- **Spring Data JPA**
- **Lombok**
- **Banco de dados (H2 / PostgreSQL)**

## Como Executar Localmente

### 1. Pré-requisitos
- Ter o **Java 21** instalado.
- Uma chave de API do Gemini (Google AI Studio).
- Uma Conta de Serviço (Service Account) do Google Cloud com acesso à API de Text-to-Speech, baixada em formato JSON.

### 2. Variáveis de Ambiente
Antes de rodar a aplicação, exporte as credenciais no seu terminal (ou configure na sua IDE):

**No Linux / Mac:**
```bash
export GEMINI_API_KEY="sua_chave_do_gemini_aqui"
export GOOGLE_APPLICATION_CREDENTIALS="/caminho/absoluto/para/sua/chave-tts.json"