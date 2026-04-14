package web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import models.Produit;
import services.ProduitMetier;
import services.ProduitMetierImpl;

/*
 * Servlet qui prépare un produit pour modification.
 * Rôle :
 * 1. Recevoir l'ID du produit à modifier depuis la JSP (via le lien "Modifier")
 * 2. Récupérer le produit correspondant via la couche métier
 * 3. Pré-remplir le formulaire de la JSP avec les données du produit
 * 4. Afficher la liste complète des produits également
 * 
 * Flux MVC :
 * JSP -> EditProduitServlet -> ProduitMetierImpl -> ProduitDAO -> JSP
 */
public class EditProduitServlet extends HttpServlet {

    // Singleton de la couche métier
    private static final ProduitMetier metier = ProduitMetierImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Récupération de l'ID depuis le lien "Modifier"
        Long id = Long.parseLong(req.getParameter("id"));

        // Récupération du produit correspondant via la couche métier
        Produit p = metier.getProduitById(id);

        // Ajout du produit dans les attributs de requête pour pré-remplir le formulaire
        req.setAttribute("produitEdit", p);

        // On ajoute aussi la liste complète pour afficher tous les produits
        req.setAttribute("listeProduits", metier.getAllProduits());

        // Forward vers la JSP
        // Le formulaire sera pré-rempli si "produitEdit" existe
        req.getRequestDispatcher("/WEB-INF/views/listProduits.jsp").forward(req, resp);
    }
}
