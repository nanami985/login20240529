package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import jp.co.aforce.beans.Customer;

public class CustomerDAO {
    
    public Customer search(String login, String password) throws Exception {
        Customer customer = null;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            // JNDIデータソースを取得
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/login_db");
            
            // データベースに接続
            con = ds.getConnection();

            // SQL文の準備
            String sql = "SELECT * FROM login WHERE login=? AND password=?";
            st = con.prepareStatement(sql);
            st.setString(1, login);
            st.setString(2, password);

            // SQLを実行して結果を取得
            rs = st.executeQuery();

            // 結果をCustomerオブジェクトにマッピング
            if (rs.next()) {
                customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setLogin(rs.getString("login"));
                customer.setPassword(rs.getString("password"));
            }
        } catch (Exception e) {
            // 例外が発生した場合、適切なメッセージを設定して投げる
            throw new Exception("データベースの検索中にエラーが発生しました。", e);
        } finally {
            // リソースの解放
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return customer;
    }
    
    public boolean register(String login, String password) throws Exception {
        Connection con = null;
        PreparedStatement st = null;

        try {
            // JNDIデータソースを取得
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/login_db");

            // データベースに接続
            con = ds.getConnection();

            // SQL文の準備
            String sql = "INSERT INTO login (login, password) VALUES (?, ?)";
            st = con.prepareStatement(sql);
            st.setString(1, login);
            st.setString(2, password);

            // SQLを実行して結果を取得
            int result = st.executeUpdate();

            // 登録が成功したかどうかを返す
            return result > 0;
        } catch (Exception e) {
            // 例外が発生した場合、適切なメッセージを設定して投げる
            throw new Exception("データベースの登録中にエラーが発生しました。", e);
        } finally {
            // リソースの解放
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    public boolean checkIfRegistered(String login) throws Exception {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            // JNDIデータソースを取得
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/login_db");

            // データベースに接続
            con = ds.getConnection();

            // SQL文の準備
            String sql = "SELECT COUNT(*) AS count FROM login WHERE login=?";
            st = con.prepareStatement(sql);
            st.setString(1, login);

            // SQLを実行して結果を取得
            rs = st.executeQuery();

            // 結果を取得して登録されているかどうかを返す
            if (rs.next()) {
                int count = rs.getInt("count");
                return count > 0;
            }
            return false;
        } catch (Exception e) {
            // 例外が発生した場合、適切なメッセージを設定して投げる
            throw new Exception("データベースの検索中にエラーが発生しました。", e);
        } finally {
            // リソースの解放
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
