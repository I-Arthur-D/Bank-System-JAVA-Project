import com.fasterxml.jackson.annotation.JsonProperty;

public class Client{
    private String name;
    private String email;
    private String cpf;
    private String password;

    public Client(@JsonProperty("name") String name,
                  @JsonProperty("email") String email,
                  @JsonProperty("cpf") String cpf,
                  @JsonProperty("password") String password) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.password = password;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;

    }

}
