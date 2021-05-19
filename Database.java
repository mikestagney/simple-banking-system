package banking;

import org.sqlite.SQLiteDataSource;

import java.sql.*;

public class Database {

    SQLiteDataSource dataSource;
    static int counter = 1;

    Database(String filename) {
        String url = "jdbc:sqlite:" + filename;
        dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        try (Connection con = dataSource.getConnection()) {
            String setup = "CREATE TABLE IF NOT EXISTS card(" +
                    "id INTEGER," +
                    "number TEXT," +
                    "pin VARCHAR," +
                    "balance INTEGER DEFAULT 0)";
            try (PreparedStatement statement = con.prepareStatement(setup)) {
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void addCardToDB(CreditCard card) {
        try (Connection con = dataSource.getConnection()) {
            String addCard = "INSERT INTO card (id, number, pin, balance) VALUES (?, ?, ?, ?)";
            try (PreparedStatement prepStatement = con.prepareStatement(addCard)) {
                String number = card.getCardNumber();
                String pin = card.getPIN();
                int balance = card.getBalance();
                prepStatement.setInt(1, counter);
                prepStatement.setString(2, number);
                prepStatement.setString(3, pin);
                prepStatement.setInt(4, balance);
                prepStatement.executeUpdate();
                counter++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addIncome(CreditCard card) {
        try (Connection con = dataSource.getConnection()) {
            String updateIncome = "UPDATE card SET balance = ? WHERE number = ?";
            try (PreparedStatement prepStatement = con.prepareStatement(updateIncome)) {
                prepStatement.setInt(1, card.getBalance());
                prepStatement.setString(2, card.getCardNumber());
                prepStatement.executeUpdate();
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    public CreditCard findCard(String number, String pin) {
        try (Connection con = dataSource.getConnection()) {
            String selection = "SELECT number, pin, balance FROM card WHERE number LIKE ?"; // changed from = to LIKE
            try (PreparedStatement select = con.prepareStatement(selection)) {
                select.setString(1, number);
                ResultSet query = select.executeQuery();
                if (!query.next()) {
                    return null;
                }
                String dbCardNum = query.getString("number");
                String dbPIN = query.getString("pin");
                int balance = query.getInt("balance");
                if (number.equals(dbCardNum) && pin.equals(dbPIN)) {
                    return new CreditCard(number, pin, balance);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public CreditCard findCard(String number) {
        try (Connection con = dataSource.getConnection()) {
            String selection = "SELECT number, pin, balance FROM card WHERE number LIKE ?"; // changed from = to LIKE
            try (PreparedStatement select = con.prepareStatement(selection)) {
                select.setString(1, number);
                ResultSet query = select.executeQuery();
                if (!query.next()) {
                    return null;
                }
                String dbCardNum = query.getString("number");
                String dbPIN = query.getString("pin");
                int balance = query.getInt("balance");
                if (number.equals(dbCardNum)) {
                    return new CreditCard(dbCardNum, dbPIN, balance);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void deleteCard(CreditCard card) {
        try (Connection con = dataSource.getConnection()) {
            String delete = "DELETE from card WHERE number = ?";
            try (PreparedStatement prepStatement = con.prepareStatement(delete)) {
                prepStatement.setString(1, card.getCardNumber());
                prepStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
