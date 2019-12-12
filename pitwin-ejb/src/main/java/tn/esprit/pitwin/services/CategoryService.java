package tn.esprit.pitwin.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.pitwin.entities.Category;
import tn.esprit.pitwin.interfaces.ICategoryService;

@Stateless
public class CategoryService extends AbstractService<Category> implements ICategoryService {

    @PersistenceContext(unitName = "pitwin-ejb")
    private EntityManager em;

    public CategoryService() {
        super(Category.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
