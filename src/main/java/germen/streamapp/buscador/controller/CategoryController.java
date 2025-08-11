package germen.streamapp.buscador.controller;


import germen.streamapp.buscador.model.Category;
import germen.streamapp.buscador.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // GET /categories → obtener todas las categorías
    @GetMapping
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    // GET /categories/{id} → obtener una categoría por id
    @GetMapping("/{id}")
    public Category getCategory(@PathVariable Long id) {
        return categoryService.getCategory(id);
    }

    // POST /categories → crear una nueva categoría
    @PostMapping
    public Category addCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    // DELETE /categories/{id} → eliminar una categoría por id
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }

    // PUT /categories/{id} → actualizar una categoría existente
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category category) {
        return categoryService.updateCategory(id, category);
    }
}
