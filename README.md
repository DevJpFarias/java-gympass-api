# App

GymPass Java style app.

## RFs (Requisitos funcionais)

- [x] Deve ser possível se cadastrar;
- [ ] Deve ser possível se autenticar;
- [ ] Deve ser possível obter o perfil de um usuário logado;
- [ ] Deve ser possível obter o número de check-ins realizados pelo usuário logado;
- [ ] Deve ser possível o usuário obter o seu histórico de check-ins;
- [ ] Deve ser possível o usuário buscar academias próximas;
- [ ] Deve ser possível o usuário buscar academias pelo nome;
- [ ] Deve ser possível o usuário realizar check-in em uma academia;
- [ ] Deve ser possível validar o check-in de um usuário;
- [x] Deve ser possível cadastrar uma academia;

## RNs (Regras de negócio)

- [ ] O usuário não deve poder se cadastrar com um e-mail duplicado;
- [ ] O usuário não pode fazer 2 check-ins no mesmo dia;
- [ ] O usuário não pode fazer check-in se não estiver perto (100m) da academia;
- [ ] O check-in só pode ser validado até 20 minutos após ser criado;
- [ ] O check-in só pode ser validado por administradores;
- [ ] A academia só pode ser cadastrada por administradores;

## RNFs (Requisitos não-funcionais)

- [x] A senha do usuário precisa estar criptografada;
- [x] Os dados da aplicação precisam estar persistidos em um banco PostgreSQL;
- [ ] Todas listas de dados precisam estar paginadas com 20 itens por página;
- [ ] O usuário deve ser identificado por um JWT (JSON Web Token);

## Tabelas

# User
 - id: string
 - name: string
 - email: string
 - password_hash: string
 - created_at: date

# CheckIn
 - id: string
 - created_at: date
 - validated_at: date

# Gym
 - id: string
 - title: string
 - description: string | null
 - phone: string | null
 - latitude: number
 - longitude: number

 # Migrations

-> Lembre-se de que a pasta deve estar no singular: resources/db/migration

-> Lembre-se também de usar o padrão do flyway: versãodb__NomeDaMigration.sql
=> Exemplo: V1__CreateUsersTable.sql

-> Para rodar as migrations, use o comando: mvn flyway:migrate -Dflyway.url=jdbc:postgresql://localhost:5432/javagympass -Dflyway.user=docker -Dflyway.password=docker

-> Se acontecer algum problema, tente o comando: mvn flyway:repair Dflyway.url=jdbc:postgresql://localhost:5432/javagympass -Dflyway.user=docker -Dflyway.password=docker
