public class Bank {
    private int accountNumber = 1;
    private Client client;
    private final AccountRepository accountRepository;

    public Bank(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void createAccount(Client client) {
        Account newAccount = new Account(client, accountNumber++, 0.0);
        this.accountRepository.addAccount(newAccount);
        System.out.println("Conta criada com sucesso! Número da conta: " + newAccount.getNumberAccount());
    }

    public Account checkClientRegister(String cpf) {
        for (Account account : accountRepository.getAll()) {
            if (account.getClient().getCpf().equalsIgnoreCase(cpf)) {
                return account;
            }
        }
        return null;
    }

    public Account checkClientLogin(int accountNumber, String password) {
        for (Account account : accountRepository.getAll()) {
            if (account.getNumberAccount() == accountNumber && account.getClient().getPassword().equals(password)) {
                System.out.println("Login bem-sucedido! Bem-vindo(a), " + account.getClient().getName());
                return account;
            }
        }
        return null;
    }

    public Account checkClientRegisterByNumber(int accountNumber) {
        for (Account account : accountRepository.getAll()) {
            if (account.getNumberAccount() == accountNumber) {
                return account;
            }
        }
        return null;
    }
}
