package ru.teamname.projectname.controller.admin.pack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.teamname.projectname.entity.packs.Category;
import ru.teamname.projectname.service.PackService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/admin/category")
public class CategoryController {

    @Autowired
    private PackService packService;

    @RequestMapping(path = "/add", method = RequestMethod.POST, consumes = "applications/json")
    public @ResponseBody Category createCategory(@RequestBody Category category) {
        return packService.addCategory(category);
    }

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public @ResponseBody Category getCategory(@RequestParam Integer id) {
        return packService.getCategory(id);
    }

    @RequestMapping(path = "/getAll", method = RequestMethod.GET)
    public @ResponseBody List<Category> getCategories() {
        return packService.getCategories();
    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public @ResponseBody Boolean deleteCategory(@RequestParam Integer id) {
        return deleteCategory(id);
    }
}
