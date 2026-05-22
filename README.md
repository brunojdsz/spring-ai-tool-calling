# Spring AI Tool Calling

AI-powered Spring Boot application demonstrating how to use Tool Calling with Spring AI and OpenAI.

This project shows how to integrate custom Java tools with LLMs using:

- Java 21
- Spring Boot 3.5
- Spring AI 1.1.6
- OpenAI
- MySQL
- Flyway

The API allows the AI model to call backend Java methods dynamically to calculate stock wallet values and analyze historical portfolio performance.

---

# Features

- Tool Calling with Spring AI
- OpenAI integration
- Wallet stock value calculation
- Historical stock analysis
- REST API endpoints
- MySQL integration
- Flyway database migrations
- Simple logging advisor for AI interactions

---

# Technologies

- Java 21
- Spring Boot 3.5.14
- Spring AI 1.1.6
- OpenAI API
- MySQL
- Flyway
- Maven

---

# Project Structure

```bash
src
├── controllers
├── tools
├── entities
├── repositories
├── services
└── resources
```

---

# Requirements

Before running the project, make sure you have:

- Java 21+
- Maven 3.9+
- MySQL running
- OpenAI API Key

---

# Configuration

## application.properties

```properties
spring.application.name=spring-ai-tool-calling

spring.datasource.url=jdbc:mysql://localhost:3306/spring_ai
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=none

spring.ai.openai.api-key=${OPENAI_API_KEY}
spring.ai.openai.chat.options.model=gpt-4o-mini
```

---

# Running the Project

## Clone the repository

```bash
git clone https://github.com/brunojdsz/spring-ai-tool-calling.git
```

## Enter the project folder

```bash
cd spring-ai-tool-calling
```

## Run the application

```bash
mvn spring-boot:run
```

---

# REST Endpoints

## Get current wallet value

```http
GET /ai/wallet
```

Uses explicit tool names:

```java
.options(ToolCallingChatOptions.builder()
        .toolNames("numberOfShares", "latestStockPrice")
        .build())
```

---

## Get wallet value using automatic tool registration

```http
GET /ai/with-tools
```

Uses:

```java
.tools(stockTools, walletTools)
```

---

## Get highest wallet value in the last N days

```http
GET /ai/highest-day/{days}
```

Example:

```http
GET /ai/highest-day/30
```

---

# Controller Example

```java
@GetMapping("/with-tools")
public String calculateWalletValueWithTools() {

    PromptTemplate template = new PromptTemplate("""
            What's the current value in dollars of my wallet based on the latest stock daily prices?
            To improve readability, add tables and line breaks when deemed necessary.
            """);

    return this.chatClient.prompt(template.create())
            .tools(stockTools, walletTools)
            .call()
            .content();
}
```

---

# Tool Calling Example

This project demonstrates how the AI model can:

1. Receive a natural language request
2. Decide which backend tools must be executed
3. Call Java methods automatically
4. Use the returned data to generate the final response

Example tools:

- `numberOfShares`
- `latestStockPrice`

---

# Database Migration

Flyway is used for versioned database migrations.

Migration scripts should be placed in:

```bash
src/main/resources/db/migration
```

Example:

```bash
V1__create_tables.sql
```

---

# Logging AI Interactions

The project uses:

```java
new SimpleLoggerAdvisor()
```

This helps visualize prompts, tool calls, and responses during development.

---

# Learning Goals

This repository is useful for learning:

- Spring AI Tool Calling
- AI Agents in Java
- OpenAI integration with Spring Boot
- Prompt engineering in backend applications
- AI orchestration using Java tools

---

# Future Improvements

- Streaming responses
- Chat memory
- Multi-agent workflows
- Vector database integration
- RAG (Retrieval-Augmented Generation)
- Frontend integration with React

---

# Author

Developed by Bruno Jesus da Silva

GitHub:
https://github.com/brunojdsz

---

# Repository

https://github.com/brunojdsz/spring-ai-tool-calling
