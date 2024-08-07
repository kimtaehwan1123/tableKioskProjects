package org.example.tablekioskprojects.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.example.tablekioskprojects.vo.DetailVO;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/checkout")
@Log4j2
public class CheckoutController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<DetailVO> cart = (List<DetailVO>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            req.setAttribute("errorMessage", "장바구니가 비어 있습니다.");
            req.getRequestDispatcher("/WEB-INF/kiosk/kiosk.jsp").forward(req, resp);
            return;
        }

        // Calculate total price
        BigDecimal totalPrice = cart.stream()
                .map(DetailVO::getTotal_price)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        req.setAttribute("cart", cart);
        req.setAttribute("totalPrice", totalPrice);

        req.getRequestDispatcher("/WEB-INF/kiosk/checkout.jsp").forward(req, resp);
    }
}