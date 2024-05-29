package jp.co.aforce.action;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.aforce.dao.CustomerDAO;

@WebServlet("/RegistrationAction")
public class RegistrationAction extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            
            // フォームから送信されたログイン名とパスワードを取得
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            
            // CustomerDAOを使用して既に登録されているか確認する
            CustomerDAO dao = new CustomerDAO();
            boolean isAlreadyRegistered = dao.checkIfRegistered(login);
            
            // 既に登録されている場合は登録失敗ページにリダイレクト
            if (isAlreadyRegistered) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/registration-failure.jsp");
                dispatcher.forward(request, response);
            } else {
                // 未登録の場合はデータベースに登録を試みる
                boolean registrationResult = dao.register(login, password);
                
                // 登録結果に応じてリダイレクト先のパスを設定
                String redirectPath = registrationResult ? "/jsp/registration-success.jsp" : "/jsp/registration-failure.jsp";
                
                // リダイレクト先にフォワード
                RequestDispatcher dispatcher = request.getRequestDispatcher(redirectPath);
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("エラーが発生しました。");
        }
    }
}















