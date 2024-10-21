import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
    private List<Account> accounts;

    private final String FILE_PATH = "src/accounts.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public AccountRepository() {
        this.accounts = new ArrayList<>();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        loadAccounts();
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public void saveAccounts() {
        try {
            objectMapper.writeValue(new File(FILE_PATH), this.accounts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadAccounts() {
        File file = new File(FILE_PATH);

        if (file.exists() && file.length() > 0) { // Verifica se o arquivo existe e não está vazio
            try {
                accounts = objectMapper.readValue(file, new TypeReference<List<Account>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Arquivo vazio ou não existe, inicializando lista de contas vazia.");
            accounts = new ArrayList<>();
        }
    }

    public List<Account> getAll() {
        return accounts;
    }
}
