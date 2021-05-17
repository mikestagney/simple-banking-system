package banking;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    SQLiteDataSource dataSource;
    static int counter = 1;

    Database(String filename) {
        String url = "jdbc:sqlite:" + filename;
        System.out.println(url);
        dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        try (Connection con = dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) {
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS card(" +
                        "id INTEGER PRIMARY KEY," +
                        "number TEXT," +
                        "pin TEXT," +
                        "balance INTEGER DEFAULT 0)");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void addCardToDB(CreditCard card) {
        try (Connection con = dataSource.getConnection()) {
            String addCard = "INSERT INTO card (id, number, pin, balance) VALUES(? ? ? ?)";
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
