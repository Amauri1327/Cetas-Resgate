# Cetas-Resgate

Este projeto é uma API REST desenvolvida com Java e Spring Framework, projetada para o registro e controle de resgates e solturas de animais.

---

# Sumário

- [Objetivo](#objetivo)
- [Motivação](#motivação)
- [Funcionalidades](#funcionalidades)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Exemplos de Endpoints](#exemplos-de-endpoints)
    - [Resgates](#resgates)
    - [Relatórios de Solicitantes](#relatórios-de-solicitantes)
    - [Relatórios de Resgates](#relatórios-de-resgates)
- [Como Executar o Projeto](#como-executar-o-projeto)
- [Melhorias Futuras](#melhorias-futuras)

---

## Objetivo

Facilitar o manuseio e o controle de resgate e soltura de animais, melhorando o armazenamento e a organização dos dados.

---

## Motivação

Melhorar e simplificar a forma de armazenar dados, além de ajudar uma colega de trabalho a gerenciar informações de resgate.

---

## Funcionalidades

- Registro, atualização e exclusão de resgates.
- Geração de relatórios detalhados sobre resgates.
- Exportação de relatórios em formato Excel.
- Filtros por espécie, cidade, origem e intervalos de datas.

---

## Tecnologias Utilizadas

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![PostGresSql](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![IntelliJ](https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Git](https://img.shields.io/badge/GIT-E44C30?style=for-the-badge&logo=git&logoColor=white)

---

## Exemplos de Endpoints

### Resgates
- **GET** `/resgates`  
  Retorna todos os resgates.
- **POST** `/resgates`  
  Registra um novo resgate.
- **PUT** `/resgates/{id}`  
  Atualiza um resgate existente.
- **DELETE** `/resgates/{id}`  
  Remove um resgate.

### Relatórios de Solicitantes
- **GET** `/resgates/report/applicant`  
  Retorna relatório de todos os solicitantes.
- **GET** `/resgates/report/applicant/{id}`  
  Retorna os dados de um solicitante específico.
- **GET** `/resgates/report/applicant/excel`  
  Exporta dados de todos os solicitantes em formato Excel.
- **GET** `/resgates/report/applicant-between-dates`  
  Retorna dados dos solicitantes dentro de um intervalo de datas.
- **GET** `/resgates/report/applicant-between-dates/export`  
  Exporta em Excel os dados dos solicitantes dentro de um intervalo de datas.

### Relatórios de Resgates
- **GET** `/list-animalsName-between-dates`  
  Retorna resgates de uma espécie específica dentro de um intervalo de datas.
- **GET** `/list-animalsName-between-dates/export`  
  Exporta em Excel os resgates de uma espécie específica dentro de um intervalo de datas.
- **GET** `/list-rescue-between-dates`  
  Retorna resgates dentro de um intervalo de datas.
- **GET** `/list-rescue-between-dates/export`  
  Exporta em Excel os resgates dentro de um intervalo de datas.
- **GET** `/list-rescue-city-between-dates`  
  Retorna resgates de uma cidade específica dentro de um intervalo de datas.
- **GET** `/list-rescue-city-between-dates/export`  
  Exporta em Excel os resgates de uma cidade específica dentro de um intervalo de datas.
- **GET** `/list-rescue-origin-between-dates`  
  Retorna resgates com base na origem dentro de um intervalo de datas.
- **GET** `/list-rescue-origin-between-dates/export`  
  Exporta em Excel os resgates com base na origem dentro de um intervalo de datas.

---

## Como Executar o Projeto

1. **Pré-requisitos**:
    - Java 17+
    - PostgreSQL
    - IntelliJ IDEA ou outra IDE de sua preferência
    - Maven

2. **Configuração do Banco de Dados**:
    - Configure as credenciais no arquivo `application.properties` para se conectar ao PostgreSQL.

3. **Iniciar a Aplicação**:
    - Clone o repositório:
      ```bash
      git clone https://github.com/Amauri1327/Cetas-Resgate.git
      ```
    - Navegue até o diretório do projeto:
      ```bash
      cd cetas-resgate
      ```
    - Execute o comando Maven:
      ```bash
      mvn spring-boot:run
      ```

4. **Testar os Endpoints**:
    - Utilize ferramentas como Postman para testar os endpoints.

---

## Melhorias Futuras

- Implementação de autenticação e autorização.

---

## 🔗Repositório do Frontend

O repositório do frontend do projeto está disponível no GitHub:  
[Frontend do Cetas-Resgate](https://github.com/Amauri1327/Front-end-Cetas-Resgate)

---

## 📧 Contato

Caso tenha dúvidas ou sugestões, entre em contato:

- **Email:** amauri1816@hotmail.com
- **LinkedIn:** [Amauri Ferreira](https://www.linkedin.com/in/amauri-ferreira/)  
