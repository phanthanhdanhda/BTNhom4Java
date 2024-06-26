package com.hutech.demo.controller;

import com.hutech.demo.model.Course;
import com.hutech.demo.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public String showProductList(Model model) {
        model.addAttribute("course", courseService.getAllCourse());
        return "/home";
    }
    // For adding a new product
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("course", new Course());
        return "/create";
    }
    // Process the form for adding a new product
    @PostMapping("/add")
    public String addProduct(@Valid Course course, BindingResult result ) {
        if (result.hasErrors()) {
            return "/create";
        }
        courseService.addCourse(course);
        return "redirect:/home";
    }


    // For editing a product
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Course course = courseService.getCourseById(id).orElseThrow(() -> new IllegalArgumentException("Invalid course Id:" + id));
        model.addAttribute("course",course);
        return "/edit";
    }
    // Process the form for updating a product
    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable Long id, @Valid Course course, BindingResult result ) {
        if (result.hasErrors()) {
            course.setId(id); //
            return "/edit";
        }
        courseService.updateCourse(course);
        return "redirect:/home";
    }
    // Handle request to delete a product
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        courseService.deleteCourseId(id);
        return "redirect:/home";
    }
}
