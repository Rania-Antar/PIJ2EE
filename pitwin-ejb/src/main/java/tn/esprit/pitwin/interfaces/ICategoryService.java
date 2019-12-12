package tn.esprit.pitwin.interfaces;



import javax.ejb.Remote;

import tn.esprit.pitwin.entities.Category;

import java.util.List;

@Remote
public interface ICategoryService {
	
    void create(Category category);

    Category edit(Category category);

    void remove(Category category);

    Category find(Object id);

    List<Category> findAll();
}
