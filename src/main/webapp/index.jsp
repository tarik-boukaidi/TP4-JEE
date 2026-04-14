<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    Long userId = session != null ? (Long) session.getAttribute("userId") : null;
    if (userId != null) {
        response.sendRedirect(request.getContextPath() + "/produits");
    } else {
        response.sendRedirect(request.getContextPath() + "/login");
    }
%>
