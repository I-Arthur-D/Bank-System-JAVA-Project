import java.util.InputMismatchException;
import java.util.Scanner;

public class BankSystem {
    private static final AccountRepository accountRepository = new AccountRepository();
    private static final Bank bank = new Bank(accountRepository);
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            try {
                System.out.println("\n--- Bem-vindo ao Sistema Bancário ---");
                System.out.println("1. Criar nova conta");
                System.out.println("2. Fazer login");
                System.out.println("3. Sair");
                System.out.print("Escolha uma opção: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        createAccount();
                        break;
                    case 2:
                        login();
                        break;
                    case 3:
                        exit = true;
                        System.out.println("Obrigado por usar o sistema bancário!");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }

            } catch (InputMismatchException ae) {
                System.out.println("Opção invalida: Use apenas numeros!");
                scanner.nextLine();
            }
        }
    }

    private static void createAccount() {
        System.out.println("\n--- Criação de Conta ---");
        System.out.print("Nome: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Senha: ");
        String password = scanner.nextLine();

        Account account = bank.checkClientRegister(cpf);

        if (account == null) {
            Client newClient = new Client(name, email, cpf, password);
            bank.createAccount(newClient);

        } else{
            System.out.println("Usuario ja cadastrado!!");
        }

        accountRepository.saveAccounts();
    }

    private static void login() {
        System.out.println("\n--- Login ---");
        System.out.print("Número da conta: ");
        int accountNumber = scanner.nextInt();

        scanner.nextLine();  // Limpar o buffer

        System.out.print("Senha: ");
        String password = scanner.nextLine();

        Account account = bank.checkClientLogin(accountNumber,password);

        if (account != null) {
            manageAccount(account);
        } else {
            System.out.println("Falha no login. Número da conta ou senha incorretos.");
        }
    }

    private static void manageAccount(Account account) {
        boolean logout = false;

        while (!logout) {
            System.out.println("\n--- Bem-vindo(a), " + account.getClient().getName() + " ---");
            System.out.println("1. Ver saldo");
            System.out.println("2. Depositar");
            System.out.println("3. Sacar");
            System.out.println("4. Transferir");
            System.out.println("5. Logout");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Saldo atual: R$ " + account.getAccountBalance());
                    break;
                case 2:
                    depositTerminal(account);
                    break;
                case 3:
                    withdrawTerminal(account);
                    break;
                case 4:
                    transferTerminal(account);
                    break;
                case 5:
                    logout = true;
                    System.out.println("Logout bem-sucedido.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

            accountRepository.saveAccounts();
        }
    }

    private static void depositTerminal(Account account) {
        System.out.print("Valor do depósito: R$ ");
        double amount = scanner.nextDouble();
        account.deposit(amount);
        System.out.println("Depósito realizado com sucesso! Novo saldo: R$ " + account.getAccountBalance());
    }

    private static void withdrawTerminal(Account account) {
        System.out.print("Valor do saque: R$ ");
        double amount = scanner.nextDouble();
        account.withdraw(amount);
        System.out.println("Saque realizado com sucesso! Saldo restante: R$ " + account.getAccountBalance());
    }

    private static void transferTerminal(Account account) {
        System.out.print("Número da conta de destino: ");
        int destinationAccountNumber = scanner.nextInt();
        System.out.print("Valor da transferência: R$ ");
        double amount = scanner.nextDouble();


        Account destinationAccount = bank.checkClientRegisterByNumber(destinationAccountNumber);
        if (destinationAccount != null) {
            account.transfer(destinationAccount, amount);
        } else {
            System.out.println("Conta de destino não encontrada.");
        }
    }
}