# SistemaSkill - Projeto Spring Boot

Este é um projeto Spring Boot que implementa funcionalidades como login, cadastro, listagem e manipulação de skills, usando um banco de dados PostgreSQL. O projeto segue as boas práticas de API RESTful e utiliza segurança JWT.

## Funcionalidades Implementadas

1. **Serviço de Login**
    - Recebe login e senha, verifica a autenticidade e retorna um token.

2. **Serviço de Cadastro**
    - Recebe login e senha, criptografa e armazena na base de dados.

3. **Serviço de Listagem de Skills**
    - Recebe o ID do usuário e retorna todas as skills associadas a ele com seus níveis.

4. **Serviço de Associar Skill**
    - Recebe o usuário, a skill e o level para persistir na base de dados.

5. **Serviço de Atualizar Associação de Skill**
    - Recebe o ID da associação da skill e o level para atualização na base de dados.

6. **Serviço de Excluir Associação de Skill**
    - Recebe o ID da associação da skill e exclui da base de dados.

7. **Segurança e Tokens JWT**
    - Apenas o Serviço de Login é público. Os demais serviços requerem um token válido.

8. **Documentação Swagger**
    - O projeto utiliza o Spring Fox para gerar documentação automática dos serviços via Swagger.

## Configuração do Banco de Dados

O banco de dados utilizado é o PostgreSQL. O script de criação do banco e das tabelas está disponível no diretório `src/main/resources/sql/SistemaSkill.sql`. Certifique-se de executar esse script para criar as tabelas necessárias e as sequências para IDs auto incremento.
