package banking;

import org.sqlite.SQLiteDataSource;

import java.sql.*;

public class BankDB {
    private SQLiteDataSource dataSource = new SQLiteDataSource();
    private String url;

    void setFileName(String fileName){
        url = "jdbc:sqlite:D:\\Java\\Simple Banking System\\Simple Banking System\\task\\"
                + fileName;
        dataSource.setUrl(url);
    }

    public void createTable() {
        try (Connection con = dataSource.getConnection()) {
            // Statement creation
            try (Statement statement = con.createStatement()) {
                // Statement execution
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS card(" +
                        "id INTEGER," +
                        "number TEXT," +
                        "pin TEXT, " +
                        "balance INTEGER DEFAULT 0)");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAccount(BankAccount account) {
        CreditCard card = account.getCard();
        int id = 1;
        try (Connection con = dataSource.getConnection()) {
            // Statement creation SELECT * FROM table ORDER BY id DESC LIMIT 1
            try (Statement statement = con.createStatement()) {
            ResultSet greatHouses = statement.executeQuery("SELECT * FROM card " +
                    "ORDER BY id DESC LIMIT 1");
                if (greatHouses.next()) {
                    id = greatHouses.getInt("id");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try (Statement statement = con.createStatement()) {
                // Statement execution
                statement.executeUpdate("INSERT INTO card(id, number, pin, balance) " +
                        "VALUES(" + (id+1) + ", " +
                        card.getCardNumber() + ", " +
                        card.getPIN() + ", " +
                        card.getBalance() + ")");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BankAccount getAccountOrNull(String cardNumber, int pin) {
        try (Connection con = dataSource.getConnection()) {
            // Statement creation
            try (Statement statement = con.createStatement()) {
                try (ResultSet greatHouses = statement.executeQuery("SELECT number, pin, balance" +
                        " FROM card " +
                        " WHERE number = " + cardNumber + " AND " +
                         "pin = " + pin + " ")) {
                    if (greatHouses.next() ) {
                        int balance = greatHouses.getInt("balance");
                        // Retrieve column values
                        return new BankAccount(cardNumber, pin, balance);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean accountExits(String cardNumber) {
        try (Connection con = dataSource.getConnection()) {
            // Statement creation
            try (Statement statement = con.createStatement()) {
                try (ResultSet greatHouses = statement.executeQuery("SELECT number, pin FROM card " +
                        " WHERE number = " + cardNumber)) {
                    if (greatHouses.next()) {
                        // Retrieve column values
                        return true;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateAccountBalance(String cardNumber, int money) {
        try (Connection con = dataSource.getConnection()) {
            // Statement creation
            String update = "UPDATE card SET balance = balance + ? WHERE number = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(update)) {
                // Statement execution
                preparedStatement.setInt(1, money);
                preparedStatement.setString(2, cardNumber);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAccount(String cardNumber) {
        try (Connection con = dataSource.getConnection()) {
            // Statement creation
            try (Statement statement = con.createStatement()) {
                // Statement execution
                statement.executeUpdate("DELETE FROM card WHERE " +
                        "number = " + cardNumber);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
