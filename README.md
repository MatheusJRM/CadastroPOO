# Sistema de Gerenciamento de Pessoas

## Descrição do Projeto

Este projeto é um sistema de gerenciamento de pessoas físicas e jurídicas. Ele permite o cadastro, alteração, exclusão, exibição e persistência dos dados de pessoas em arquivos. O projeto foi desenvolvido em Java, utilizando as classes `Pessoa`, `PessoaFisica`, `PessoaJuridica`, `PessoaFisicaRepo` e `PessoaJuridicaRepo` para estruturar e manipular os dados.

## Estrutura do Projeto

- **model.Pessoa**: Classe base que representa uma pessoa genérica, contendo os atributos `id` e `nome`.
- **model.PessoaFisica**: Classe que estende `Pessoa`, adicionando os atributos `cpf` e `idade` para representar uma pessoa física.
- **model.PessoaJuridica**: Classe que estende `Pessoa`, adicionando o atributo `cnpj` para representar uma pessoa jurídica.
- **model.PessoaFisicaRepo**: Repositório para gerenciar pessoas físicas, implementando métodos para inserir, alterar, excluir, obter e obter todos os registros. Também contém métodos para persistir e recuperar dados de um arquivo.
- **model.PessoaJuridicaRepo**: Repositório para gerenciar pessoas jurídicas, com a mesma funcionalidade do repositório de pessoas físicas.

## Funcionalidades

- **Cadastro**: Possibilidade de cadastrar pessoas físicas e jurídicas no sistema.
- **Alteração**: Permite alterar os dados das pessoas cadastradas.
- **Exclusão**: Remove registros de pessoas do sistema.
- **Exibição**: Exibe os dados de uma pessoa específica ou de todas as pessoas cadastradas.
- **Persistência**: Salva os dados das pessoas em arquivos para serem recuperados posteriormente.
- **Recuperação**: Recupera os dados salvos anteriormente de arquivos.

## Como Usar

1. **Executando o Programa**: Execute a classe principal do projeto (`main.Main`). O programa apresentará um menu com opções para incluir, alterar, excluir, exibir, salvar e recuperar dados.

2. **Incluir Pessoa**: Selecione a opção de inclusão, escolha entre pessoa física ou jurídica, e insira os dados solicitados.

3. **Alterar Pessoa**: Selecione a opção de alteração, escolha o tipo de pessoa, insira o ID, visualize os dados atuais, e insira os novos dados para atualizar.

4. **Excluir Pessoa**: Selecione a opção de exclusão, escolha o tipo de pessoa, insira o ID e remova o registro.

5. **Exibir Dados**: Selecione a opção de exibição para visualizar os dados de uma pessoa específica ou de todas as pessoas cadastradas.

6. **Salvar Dados**: Escolha a opção de salvar para persistir os dados em um arquivo, especificando o prefixo do nome do arquivo.

7. **Recuperar Dados**: Escolha a opção de recuperar para carregar os dados de um arquivo existente.

## Tratamento de Exceções

O sistema possui mecanismos para tratar entradas inválidas, como tipos de dados incorretos ou IDs inexistentes, exibindo mensagens de erro apropriadas e permitindo que o usuário tente novamente.

## Requisitos

- Java JDK 11+
- Ambiente de desenvolvimento compatível com projetos Java
