import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Account {
    private final int numberAccount;
    private double accountBalance;
    private final Client client;

    @JsonCreator
    public Account(@JsonProperty("client") Client client,
                   @JsonProperty("numberAccount") int numberAccount,
                   @JsonProperty("accountBalance") Double accountBalance) {
        this.client = client;
        this.numberAccount = numberAccount;
        this.accountBalance = Objects.requireNonNullElse(accountBalance, 0.0);
    }


    public Client getClient() {
        return client;
    }

    public int getNumberAccount() {
        return numberAccount;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            accountBalance += amount;
        } else {
            System.out.println("Valor de depósito inválido.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= accountBalance) {
            accountBalance -= amount;
        } else {
            System.out.println("Saldo insuficiente ou valor inválido.");
        }
    }

    public void transfer(Account destinationAccount, double amount) {
        if (amount > 0 && amount <= accountBalance) {
            this.withdraw(amount);
            destinationAccount.deposit(amount);
            System.out.println("Transferência de R$ " + amount + " realizada com sucesso para a conta de número "
                    + destinationAccount.getNumberAccount());
        } else {
            System.out.println("Saldo insuficiente ou valor inválido.");
        }
    }
}

