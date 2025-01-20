package com.chirag.controller;

import com.chirag.model.Category;
import com.chirag.model.User;
import com.chirag.service.CategoryService;
import com.chirag.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private UserService userService;

    @PostMapping("/admin/category")
    public ResponseEntity<Category> createCategory(@RequestBody Category category,
                                                   @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);

        Category createCategory= categoryService.createCategory(category.getName(),user.getId());

        return new ResponseEntity<>(createCategory, HttpStatus.CREATED);
    }

    @GetMapping("/category/restaurant/{id}")
    public ResponseEntity<List<Category>> getRestaurantCategory(
            @PathVariable Long id,
                                                   @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);

        List<Category> categories= categoryService.findCategoryByRestaurantId(id);

        return new ResponseEntity<>(categories, HttpStatus.CREATED);
    }
}
