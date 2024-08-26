package main;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;
import model.PessoaFisica;
import model.PessoaFisicaRepo;
import model.PessoaJuridica;
import model.PessoaJuridicaRepo;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
            PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();

            while (true) {
                try {
                    System.out.println("\nOpcoes do Programa:");
                    System.out.println("1 - Incluir");
                    System.out.println("2 - Alterar");
                    System.out.println("3 - Excluir");
                    System.out.println("4 - Exibir pelo ID");
                    System.out.println("5 - Exibir todos");
                    System.out.println("6 - Salvar dados");
                    System.out.println("7 - Recuperar dados");
                    System.out.println("0 - Sair");
                    System.out.print("\nEscolha uma opcao: ");
                    int opcao = scanner.nextInt();
                    scanner.nextLine();

                    if (opcao == 0) {
                        System.out.println("Encerrando o sistema...");
                        break;
                    }

                    switch (opcao) {
                        case 1 ->
                            incluir(scanner, repoFisica, repoJuridica);
                        case 2 ->
                            alterar(scanner, repoFisica, repoJuridica);
                        case 3 ->
                            excluir(scanner, repoFisica, repoJuridica);
                        case 4 ->
                            exibirPeloId(scanner, repoFisica, repoJuridica);
                        case 5 ->
                            exibirTodos(scanner, repoFisica, repoJuridica);
                        case 6 ->
                            salvarDados(scanner, repoFisica, repoJuridica);
                        case 7 ->
                            recuperarDados(scanner, repoFisica, repoJuridica);
                        default ->
                            System.out.println("Opcao invalida!");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada invalida! Por favor, insira um número.");
                    scanner.nextLine();
                } catch (Exception e) {
                    System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao inicializar o sistema: " + e.getMessage());
        }
    }

    private static void incluir(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        try {
            System.out.print("\nF - Pessoa Fisica | J - Pessoa Juridica: ");
            String tipo = scanner.next();
            scanner.nextLine();

            if ((!"F".equals(tipo.toUpperCase()) && !"J".equals(tipo.toUpperCase())) || tipo.isEmpty()) {
                System.out.println("Tipo invalido!");
                return;
            }

            switch (tipo.toUpperCase()) {
                case "F" -> {
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    if (nome.isEmpty()) {
                        System.out.println("Nome invalido! Deve ser uma String nao vazia.");
                        return;
                    }
                    if (!nome.matches("[A-Za-z\\s]+")) {
                        System.out.println("Nome invalido! Deve conter apenas letras e espacos.");
                        return;
                    }
                    System.out.print("CPF(APENAS DIGITOS): ");
                    String cpf = scanner.nextLine();
                    if (cpf.isEmpty()) {
                        System.out.println("CPF invalido! Deve ser uma String nao vazia.");
                        return;
                    }
                    if (!cpf.matches("\\d{11}")) {
                        System.out.println("CPF invalido! Deve conter apenas 11 digitos.");
                        return;
                    }
                    System.out.print("Idade: ");
                    String idadeStr = scanner.nextLine();
                    int idade;
                    try {
                        idade = Integer.parseInt(idadeStr);
                        if (idade <= 0) {
                            System.out.println("Idade invalida! Deve ser um inteiro positivo.");
                            return;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Idade invalida! Deve ser um numero inteiro.");
                        return;
                    }
                    repoFisica.inserir(new PessoaFisica(id, nome, cpf, idade));
                    System.out.println("Pessoa Fisica inserida com sucesso!");
                }
                case "J" -> {
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    if (nome.isEmpty()) {
                        System.out.println("Nome invalido! Deve ser uma String nao vazia.");
                        return;
                    }
                    System.out.print("CNPJ(APENAS DIGITOS): ");
                    String cnpj = scanner.nextLine();
                    if (cnpj.isEmpty()) {
                        System.out.println("CNPJ invalido! Deve ser uma String nao vazia.");
                        return;
                    }
                    if (!cnpj.matches("\\d{14}")) {
                        System.out.println("CNPJ invalido! Deve conter apenas 14 digitos.");
                        return;
                    }
                    repoJuridica.inserir(new PessoaJuridica(id, nome, cnpj));
                    System.out.println("Pessoa Juridica inserida com sucesso!");
                }
                default ->
                    System.out.println("Tipo invalido!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada invalida! Por favor, insira os dados corretamente.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Erro ao inserir pessoa: " + e.getMessage());
        }
    }

    private static void alterar(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        try {
            System.out.print("\nF - Pessoa Fisica | J - Pessoa Juridica: ");
            String tipo = scanner.next();
            scanner.nextLine();

            if ((!"F".equals(tipo.toUpperCase()) && !"J".equals(tipo.toUpperCase())) || tipo.isEmpty()) {
                System.out.println("Tipo invalido!");
                return;
            }

            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            switch (tipo.toUpperCase()) {
                case "F" -> {
                    Optional<PessoaFisica> pf = repoFisica.obter(id);
                    if (pf.isPresent()) {
                        System.out.println("Dados atuais: " + pf.get().exibir());
                        System.out.print("Novo nome: ");
                        String nome = scanner.nextLine();
                        if (nome.isEmpty()) {
                            System.out.println("Nome invalido! Deve ser uma String nao vazia.");
                            return;
                        }
                        if (!nome.matches("[A-Za-z\\s]+")) {
                            System.out.println("Nome invalido! Deve conter apenas letras e espacos.");
                            return;
                        }
                        System.out.print("Novo CPF(APENAS DIGITOS): ");
                        String cpf = scanner.nextLine();
                        if (cpf.isEmpty()) {
                            System.out.println("CPF invalido! Deve ser uma String nao vazia.");
                            return;
                        }
                        if (!cpf.matches("\\d{11}")) {
                            System.out.println("CPF invalido! Deve conter apenas 11 digitos.");
                            return;
                        }
                        System.out.print("Nova idade: ");
                        String idadeStr = scanner.nextLine();
                        int idade;
                        try {
                            idade = Integer.parseInt(idadeStr);
                            if (idade <= 0) {
                                System.out.println("Idade invalida! Deve ser um inteiro positivo.");
                                return;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Idade invalida! Deve ser um numero inteiro.");
                            return;
                        }
                        repoFisica.alterar(new PessoaFisica(id, nome, cpf, idade));
                        System.out.println("Pessoa Fisica alterada com sucesso!");
                    } else {
                        System.out.println("Pessoa Fisica nao encontrada!");
                    }
                }
                case "J" -> {
                    Optional<PessoaJuridica> pj = repoJuridica.obter(id);
                    if (pj.isPresent()) {
                        System.out.println("Dados atuais: " + pj.get().exibir());
                        System.out.print("Novo nome: ");
                        String nome = scanner.nextLine();
                        if (nome.isEmpty()) {
                            System.out.println("Nome invalido! Deve ser uma String nao vazia.");
                            return;
                        }
                        System.out.print("Novo CNPJ(APENAS DIGITOS): ");
                        String cnpj = scanner.nextLine();
                        if (cnpj.isEmpty()) {
                            System.out.println("CNPJ invalido! Deve ser uma String nao vazia.");
                            return;
                        }
                        if (!cnpj.matches("\\d{14}")) {
                            System.out.println("CNPJ invalido! Deve conter apenas 14 digitos.");
                            return;
                        }
                        repoJuridica.alterar(new PessoaJuridica(id, nome, cnpj));
                        System.out.println("Pessoa Juridica alterada com sucesso!");
                    } else {
                        System.out.println("Pessoa Juridica nao encontrada!");
                    }
                }
                default ->
                    System.out.println("Tipo invalido!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada invalida! Por favor, insira os dados corretamente.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Erro ao alterar pessoa: " + e.getMessage());
        }
    }

    private static void excluir(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        try {
            System.out.print("\nF - Pessoa Fisica | J - Pessoa Juridica: ");
            String tipo = scanner.next();
            scanner.nextLine();

            if ((!"F".equals(tipo.toUpperCase()) && !"J".equals(tipo.toUpperCase())) || tipo.isEmpty()) {
                System.out.println("Tipo invalido!");
                return;
            }

            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            switch (tipo.toUpperCase()) {
                case "F" -> {
                    repoFisica.excluir(id);
                    System.out.println("Pessoa Fisica excluida com sucesso!");
                }
                case "J" -> {
                    repoJuridica.excluir(id);
                    System.out.println("Pessoa Juridica excluida com sucesso!");
                }
                default ->
                    System.out.println("Tipo invalido!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada invalida! Por favor, insira os dados corretamente.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Erro ao excluir pessoa: " + e.getMessage());
        }
    }

    private static void exibirPeloId(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        try {
            System.out.print("\nF - Pessoa Fisica | J - Pessoa Juridica: ");
            String tipo = scanner.next();
            scanner.nextLine();

            if ((!"F".equals(tipo.toUpperCase()) && !"J".equals(tipo.toUpperCase())) || tipo.isEmpty()) {
                System.out.println("Tipo invalido!");
                return;
            }

            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            switch (tipo.toUpperCase()) {
                case "F" -> {
                    Optional<PessoaFisica> pf = repoFisica.obter(id);
                    if (pf.isPresent()) {
                        System.out.println("Dados: " + pf.get().exibir());
                    } else {
                        System.out.println("Pessoa Fisica nao encontrada!");
                    }
                }
                case "J" -> {
                    Optional<PessoaJuridica> pj = repoJuridica.obter(id);
                    if (pj.isPresent()) {
                        System.out.println("Dados: " + pj.get().exibir());
                    } else {
                        System.out.println("Pessoa Juridica nao encontrada!");
                    }
                }
                default ->
                    System.out.println("Tipo invalido!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada invalida! Por favor, insira os dados corretamente.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Erro ao exibir pessoa: " + e.getMessage());
        }
    }

    private static void exibirTodos(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        try {
            System.out.print("\nF - Pessoa Fisica | J - Pessoa Juridica: ");
            String tipo = scanner.next();
            scanner.nextLine();

            if ((!"F".equals(tipo.toUpperCase()) && !"J".equals(tipo.toUpperCase())) || tipo.isEmpty()) {
                System.out.println("Tipo invalido!");
                return;
            }

            switch (tipo.toUpperCase()) {
                case "F" ->
                    repoFisica.obterTodos().forEach(pessoa -> System.out.println(pessoa.exibir()));
                case "J" ->
                    repoJuridica.obterTodos().forEach(pessoa -> System.out.println(pessoa.exibir()));
                default ->
                    System.out.println("Tipo invalido!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao exibir todas as pessoas: " + e.getMessage());
        }
    }

    private static void salvarDados(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        try {
            System.out.print("Digite o prefixo do arquivo: ");
            String prefixo = scanner.nextLine();

            if (prefixo == null || prefixo.isEmpty()) {
                System.out.println("E necessario informar um prefixo para salvar o arquivo!");
                return;
            }

            repoFisica.persistir(prefixo + ".fisica.bin");
            repoJuridica.persistir(prefixo + ".juridica.bin");
            System.out.println("Dados salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado ao salvar os dados: " + e.getMessage());
        }
    }

    private static void recuperarDados(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        try {
            System.out.print("Digite o prefixo do arquivo: ");
            String prefixo = scanner.nextLine();

            if (prefixo == null || prefixo.isEmpty()) {
                System.out.println("E necessario informar um prefixo para recuperar o arquivo!");
                return;
            }

            repoFisica.recuperar(prefixo + ".fisica.bin");
            repoJuridica.recuperar(prefixo + ".juridica.bin");
            System.out.println("Dados recuperados com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao recuperar os dados: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao carregar os dados: Classe nao encontrada.");
        } catch (Exception e) {
            System.out.println("Erro inesperado ao recuperar os dados: " + e.getMessage());
        }
    }
}
//
//    public static void main(String[] args) {
//        PessoaFisicaRepo repo1 = new PessoaFisicaRepo();
//
//        PessoaFisica pessoa1 = new PessoaFisica(1, "Matheus Jose", "12345678900", 30);
//        PessoaFisica pessoa2 = new PessoaFisica(2, "Isabela Arruda", "98765432100", 25);
//        repo1.inserir(pessoa1);
//        repo1.inserir(pessoa2);
//
//        try {
//            System.out.println("Dados de Pessoa Fisica Armazenados.");
//            repo1.persistir("pessoas_fisicas.dat");
//        } catch (IOException e) {
//            System.out.println("Erro ao salvar os dados: " + e.getMessage());
//        }
//
//        PessoaFisicaRepo repo2 = new PessoaFisicaRepo();
//
//        try {
//            System.out.println("Dados de Pessoa Fisica Recuperados.");
//            repo2.recuperar("pessoas_fisicas.dat");
//            repo2.obterTodos().forEach(pessoa -> System.out.println(pessoa.exibir()));
//        } catch (IOException | ClassNotFoundException e) {
//            System.out.println("Erro inesperado ao recuperar os dados: " + e.getMessage());
//        }
//
//        PessoaJuridicaRepo repo3 = new PessoaJuridicaRepo();
//
//        PessoaJuridica empresa1 = new PessoaJuridica(3, "Empresa ABC", "11111111000111");
//        PessoaJuridica empresa2 = new PessoaJuridica(4, "Empresa XYZ", "22222222000122");
//        repo3.inserir(empresa1);
//        repo3.inserir(empresa2);
//
//        try {
//            System.out.println("\nDados de Pessoa Juridica Armazenados.");
//            repo3.persistir("pessoas_juridicas.dat");
//        } catch (IOException e) {
//            System.out.println("Erro ao salvar os dados: " + e.getMessage());
//        }
//
//        PessoaJuridicaRepo repo4 = new PessoaJuridicaRepo();
//
//        try {
//            System.out.println("Dados de Pessoa Juridica Recuperados.");
//            repo4.recuperar("pessoas_juridicas.dat");
//            repo4.obterTodos().forEach(pessoa -> System.out.println(pessoa.exibir()));
//        } catch (IOException | ClassNotFoundException e) {
//            System.out.println("Erro inesperado ao recuperar os dados: " + e.getMessage());
//        }
//    }
