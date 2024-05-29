package jp.co.aforce.action;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.aforce.beans.Customer;
import jp.co.aforce.dao.CustomerDAO;

@WebServlet("/LoginAction")
public class LoginAction extends HttpServlet {

    protected void doPost(
            HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            
            // ログイン情報を取得
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            
            // データベースから顧客情報を取得
            CustomerDAO dao = new CustomerDAO();
            Customer customer = dao.search(login, password);
            
            // セッションを取得
            HttpSession session = request.getSession();
            
            // 顧客情報が存在する場合はセッションに保存してログイン成功ページにフォワード
            if (customer != null) {
                session.setAttribute("customer", customer);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/login-out.jsp");
                dispatcher.forward(request, response);
            } 
            // 存在しない場合はエラーメッセージをリクエスト属性にセットしてログインページにフォワード
            else {
                request.setAttribute("login", login); // 入力したIDをセット
                request.setAttribute("error", "IDもしくはパスワードが違います。"); // エラーメッセージをセット
                RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/login-in.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // エラーが発生した場合はコンソールにスタックトレースを出力
            response.getWriter().println("エラーが発生しました。");
        }
    }
}
