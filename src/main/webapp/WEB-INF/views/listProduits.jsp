<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Produits</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background: white;
        }

        .header-top {
            text-align: right;
            margin-bottom: 20px;
        }

        .logout-btn {
            background-color: #0066cc;
            color: white;
            padding: 8px 16px;
            border: none;
            border-radius: 4px;
            text-decoration: none;
        }

        .btn {
            background-color: #0066cc;
            color: white;
            padding: 6px 12px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        .btn-link {
            color: #0066cc;
            text-decoration: underline;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #999;
            padding: 10px;
        }

        th {
            background-color: #e6e6e6;
        }
    </style>
</head>

<body>

<!-- LOGOUT -->
<div class="header-top">
    <a href="${pageContext.request.contextPath}/logout" class="logout-btn">Déconnexion</a>
</div>

<!-- MESSAGES -->
<c:if test="${not empty error}">
    <div style="color:red;">${error}</div>
</c:if>

<c:if test="${not empty success}">
    <div style="color:green;">${success}</div>
</c:if>

<h1>Gestion des Produits (MVC)</h1>


<c:if test="${role == 'ADMIN'}">
    <form action="${pageContext.request.contextPath}${produitEdit != null ? '/update' : '/add'}" method="post">

        <c:if test="${produitEdit != null}">
            <input type="hidden" name="id" value="${produitEdit.idProduit}" />
        </c:if>

        Nom:
        <input type="text" name="nom" value="${produitEdit.nom}" required>

        Description:
        <input type="text" name="description" value="${produitEdit.description}" required>

        Prix:
        <input type="number" step="0.01" name="prix" value="${produitEdit.prix}" required>

        <button type="submit" class="btn">
            ${produitEdit != null ? 'Modifier' : 'Ajouter'}
        </button>
    </form>
</c:if>

<hr>

<form method="get" action="${pageContext.request.contextPath}/produits">
    ID: <input type="text" name="searchId">
    <button class="btn">Rechercher</button>
</form>

<c:if test="${not empty searchError}">
    <div style="color:red;">${searchError}</div>
</c:if>

<hr/>

<c:choose>
    <c:when test="${not empty listeProduits}">

        <table>
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Description</th>
                <th>Prix</th>

                <c:if test="${role == 'ADMIN'}">
                    <th>Actions</th>
                </c:if>
            </tr>

            <c:forEach var="p" items="${listeProduits}">
                <tr>
                    <td>${p.idProduit}</td>
                    <td>${p.nom}</td>
                    <td>${p.description}</td>
                    <td>${p.prix}</td>

                    <c:if test="${role == 'ADMIN'}">
                        <td>
                            <a href="${pageContext.request.contextPath}/edit?id=${p.idProduit}" class="btn-link">Modifier</a>
                            |
                            <a href="${pageContext.request.contextPath}/delete?id=${p.idProduit}"
                               class="btn-link"
                               onclick="return confirm('Voulez-vous vraiment supprimer ce produit ?');">
                               Supprimer
                            </a>
                        </td>
                    </c:if>

                </tr>
            </c:forEach>

        </table>

    </c:when>

</c:choose>

</body>
</html>