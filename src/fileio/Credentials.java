package fileio;

public final class Credentials {

    private String name;
    private String password;
    private String accountType;
    private String country;
    private String balance;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(final String accountType) {
        this.accountType = accountType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(final String balance) {
        this.balance = balance;
    }

    public Credentials() { }

    public Credentials(final Credentials other) {
        this.name = other.name;
        this.password = other.password;
        this.accountType = other.accountType;
        this.balance = other.balance;
        this.country = other.country;
    }

    @Override
    public boolean equals(final Object obj) {
        try {
            return name.equals(((Credentials) obj).name)
                    && password.equals(((Credentials) obj).password);
        } catch (Exception e) {
            System.out.println("object not of type credentials");
            return false;
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
