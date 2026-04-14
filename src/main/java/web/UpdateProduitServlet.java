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
 * Servlet responsable de la mise à jour d'un produit.
 * Flux MVC :
 * 1. L'utilisateur modifie les champs dans le formulaire pré-rempli (EditProduitServlet)
 * 2. Le formulaire envoie les données à UpdateProduitServlet via POST
 * 3. La servlet récupère les données, construit un objet Produit
 * 4. Appel de la couche métier pour mettre à jour le produit
 * 5. Forward vers JSP pour afficher la liste mise à jour
 */
public class UpdateProduitServlet extends HttpServlet {

    // Singleton de la couche métier
    private static final ProduitMetier metier = ProduitMetierImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Récupération des paramètres du formulaire
        Long id = Long.parseLong(req.getParameter("id"));
        String nom = req.getParameter("nom");
        String description = req.getParameter("description");
        Double prix = Double.parseDouble(req.getParameter("prix"));
        
        // Création d'un objet Produit avec les nouvelles valeurs
        Produit p = new Produit();
        p.setIdProduit(id);
        p.setNom(nom);
        p.setDescription(description);
        p.setPrix(prix);
        
        // Mise à jour via la couche métier
        metier.updateProduit(p);
        
        // Préparer la liste complète pour l'affichage JSP
        req.setAttribute("listeProduits", metier.getAllProduits());
        
        // Forward vers JSP pour afficher le tableau mis à jour
        // On utilise forward ici pour conserver les attributs de la requête
        req.getRequestDispatcher("/WEB-INF/views/listProduits.jsp").forward(req, resp);
        
        // Remarque : si l'on utilisait sendRedirect("produits"), 
        // on perdrait les attributs et il faudrait relire les produits via ListProduitServlet
    }
}
