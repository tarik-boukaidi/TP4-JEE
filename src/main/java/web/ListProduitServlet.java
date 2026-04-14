package web;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Produit;
import services.ProduitMetier;
import services.ProduitMetierImpl;

public class ListProduitServlet extends HttpServlet {
    private final ProduitMetier produitMetier = ProduitMetierImpl.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchId = request.getParameter("searchId");
        if (searchId == null || searchId.trim().isEmpty()) {
            searchId = request.getParameter("idProduit");
        }

        List<Produit> produits;

        if (searchId != null && !searchId.trim().isEmpty()) {
            try {
                Long id = Long.parseLong(searchId);
                Produit produit = produitMetier.getProduitById(id);
                if (produit != null) {
                    produits = Collections.singletonList(produit);
                } else {
                    produits = Collections.emptyList();
                    request.setAttribute("searchError", "Produit non trouvé");
                }
            } catch (NumberFormatException e) {
                produits = Collections.emptyList();
                request.setAttribute("searchError", "ID invalide");
            }
        } else {
            produits = produitMetier.getAllProduits();
        }

        request.setAttribute("listeProduits", produits);
        request.getRequestDispatcher("/WEB-INF/views/listProduits.jsp").forward(request, response);
    }
}
