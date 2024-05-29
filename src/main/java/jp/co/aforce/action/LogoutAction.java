package jp.co.aforce.action;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LogoutAction")
public class LogoutAction extends HttpServlet {
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // 新しいセッションを作成しないようにする
        
        // セッションが存在するかどうかでログアウト可能かどうかを判断
        if (session != null) {
            session.invalidate(); // セッションを破棄してログアウト
            response.sendRedirect(request.getContextPath() + "/jsp/logout-out.jsp"); // ログアウト成功ページにリダイレクト
        } else {
            response.sendRedirect(request.getContextPath() + "/jsp/logout-error.jsp"); // ログアウト失敗ページにリダイレクト
        }
    }
}

