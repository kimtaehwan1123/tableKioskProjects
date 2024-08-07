package org.example.tablekioskprojects.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.example.tablekioskprojects.common.StringUtil;
import org.example.tablekioskprojects.dao.CustomerDAO;
import org.example.tablekioskprojects.vo.DetailVO;
import org.example.tablekioskprojects.vo.MenuVO;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/kiosk")
@Log4j2
public class KioskController extends HttpServlet {
    private CustomerDAO customerDAO;

    @Override
    public void init() throws ServletException {
        customerDAO = CustomerDAO.INSTANCE;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryParam = req.getParameter("category");
        int categoryId = StringUtil.getInt(categoryParam, 1); // StringUtil을 사용하여 categoryId 설정

        try {
            List<MenuVO> menuList = customerDAO.getMenusByCategory(categoryId);
            req.setAttribute("menuList", menuList);

            HttpSession session = req.getSession();
            List<DetailVO> cart = (List<DetailVO>) session.getAttribute("cart"); //세션에서 장바구니정보를 가지고온다
            req.setAttribute("cart", cart);

            req.getRequestDispatcher("/WEB-INF/kiosk/kiosk.jsp").forward(req, resp);
        } catch (Exception e) {
            log.error("Error processing request", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "비상 서버오류!!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("add_to_cart".equals(action)) {
            int mno = Integer.parseInt(req.getParameter("mno"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));

            HttpSession session = req.getSession();
            List<DetailVO> cart = (List<DetailVO>) session.getAttribute("cart");
            if (cart == null) {
                cart = new ArrayList<>();
            }
            boolean itemExists = false;
            for (DetailVO detail : cart) {
                if (detail.getMno().equals(mno)) {
                    detail.setQuantity(detail.getQuantity() + quantity);
                    itemExists = true;
                    break;
                }
            }
            if (!itemExists) {
                try {
                    MenuVO menu = customerDAO.getMenuById(mno);
                    DetailVO detail = DetailVO.builder()
                            .mno(mno)
                            .quantity(quantity)
                            .total_price(menu.getPrice().multiply(BigDecimal.valueOf(quantity)))
                            .build();
                    cart.add(detail);
                } catch (Exception e) {
                    log.error("Error retrieving menu item", e);
                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "메뉴 항목을 검색하는 중 오류가 발생했습니다.");
                    return;
                }
            }
            session.setAttribute("cart", cart);
            resp.sendRedirect(req.getContextPath() + "/kiosk");
        }
    }
}