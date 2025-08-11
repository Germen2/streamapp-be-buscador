package germen.streamapp.buscador.service;

import germen.streamapp.buscador.mapper.CategoryMapper;
import germen.streamapp.buscador.model.Category;
import germen.streamapp.buscador.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;


    //Que regrese las categories ordenadas por id
    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    //Buscar categoria por id
    public Category getCategory(Long id){
        return categoryRepository.findById(id).orElse(null);
    }

    //Guardar una categoria
    public Category saveCategory(Category category){
        return categoryRepository.save(category);
    }

    //Actualizar una categoria
    public Category updateCategory(Long id, Category category){
        System.out.println("Categoria enviada: " + category);
        return categoryRepository.findById(id).map(existingCategory -> {
            System.out.println("Antes update: " + existingCategory);
            categoryMapper.updateCategoryFromSource(category, existingCategory);
            System.out.println("Despues update: " + existingCategory);
            return categoryRepository.save(existingCategory);
        }).orElseThrow(() -> new RuntimeException("Categoría no encontrada con id " + id));
    }

    //Eliminar una categoria con su id
    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }

}
