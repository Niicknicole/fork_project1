package SistemaBancario;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class BancoMain {

    static ArrayList<ContaBancaria> contas = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        carregarContas();

        int opcao;

        do {
            System.out.println("\n--- SISTEMA BANCÁRIO ---");
            System.out.println("1- Criar Nova Conta");
            System.out.println("2- Listar Todas as Contas");
            System.out.println("3- Depositar em uma Conta");
            System.out.println("4- Sacar");
            System.out.println("5- Sair");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    criarConta();
                    break;
                case 2:
                    listarContas();
                    break;
                case 3:
                    depositarValor();
                    break;
                case 4:
                    sacarValor();
                    break;
                case 5:
                    salvarContas();
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 5);
    }

    public static void criarConta() {

        System.out.println("Nome do Titular:");
        String nome = scanner.nextLine();

        System.out.println("Tipo de Conta [1] Corrente  [2] Poupança:");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        ContaBancaria novaConta;

        if (tipo == 1) {
            novaConta = new ContaCorrente(nome);
        } else if (tipo == 2) {
            novaConta = new ContaPoupanca(nome);
        } else {
            System.out.println("Tipo inválido!");
            return;
        }

        contas.add(novaConta);

        System.out.println("Conta criada!");
        System.out.println("Número da conta: " + novaConta.getNumeroConta());
    }

    public static void listarContas() {

        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
            return;
        }

        System.out.println("\n--- LISTA DE CONTAS ---");

        for (ContaBancaria conta : contas) {
            conta.exibirSaldo();
        }
    }

    public static ContaBancaria buscarConta(int numero) {

        for (ContaBancaria conta : contas) {
            if (conta.getNumeroConta() == numero) {
                return conta;
            }
        }

        return null;
    }

    public static void depositarValor() {

        System.out.print("Digite o número da conta: ");
        int numero = scanner.nextInt();

        ContaBancaria conta = buscarConta(numero);

        if (conta != null) {
            System.out.print("Valor do depósito: R$ ");
            double valor = scanner.nextDouble();
            conta.depositar(valor);
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    public static void sacarValor() {

        System.out.print("Digite o número da conta: ");
        int numero = scanner.nextInt();

        ContaBancaria conta = buscarConta(numero);

        if (conta != null) {
            System.out.print("Valor do saque: R$ ");
            double valor = scanner.nextDouble();
            conta.sacar(valor);
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    public static void salvarContas() {

        try (FileWriter writer = new FileWriter("contas.txt")) {

            for (ContaBancaria conta : contas) {

                String tipo = (conta instanceof ContaCorrente) ? "CORRENTE" : "POUPANCA";

                writer.write(
                        conta.getNumeroConta() + ";" +
                        conta.getTitular() + ";" +
                        conta.getSaldo() + ";" +
                        tipo + "\n"
                );
            }

        } catch (IOException e) {
            System.out.println("Erro ao salvar contas.");
        }
    }

    public static void carregarContas() {

        try (BufferedReader reader = new BufferedReader(new FileReader("contas.txt"))) {

            String linha;

            while ((linha = reader.readLine()) != null) {

                String[] partes = linha.split(";");

                String nome = partes[1];
                double saldo = Double.parseDouble(partes[2]);
                String tipo = partes[3];

                ContaBancaria conta;

                if (tipo.equals("CORRENTE")) {
                    conta = new ContaCorrente(nome);
                } else {
                    conta = new ContaPoupanca(nome);
                }

                conta.depositar(saldo);
                contas.add(conta);
            }

        } catch (IOException e) {
        }
    }
}