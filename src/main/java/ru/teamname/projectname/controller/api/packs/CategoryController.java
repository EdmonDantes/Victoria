package ru.teamname.projectname.controller.api.packs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.teamname.projectname.entity.packs.Category;
import ru.teamname.projectname.repository.packs.CategoryRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public @ResponseBody Category getCategory(@RequestParam Integer id) {
        if (id != null) {
            Optional<Category> optionalCategory = categoryRepository.findById(id);
            if (optionalCategory.isPresent())
                return optionalCategory.get();
        }
        return new Category();
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody Category addCategory(@RequestBody Category category) {
        if (category != null && (category.getId() == null  || category.getId() < 0) && categoryRepository != null) {
            return categoryRepository.save(category);
        }
        return category;
    }
}
