package ru.geebrains.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geebrains.market.model.Product;
import ru.geebrains.market.service.ProductService;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ProductService productService;

    @GetMapping
    public String indexPage() {
        return "index";
    }

    @GetMapping("/catalog")
    public String catalog(Model model) {
        model.addAttribute("catalog", productService.productAll());
        return "catalog";
    }

    @GetMapping("/product/{id}")
    public String product(Model model, @PathVariable Long id) {
        model.addAttribute("product", productService.productById(id));
        return "product";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("nextID", productService.nextID());
        return "/create";
    }

    @PostMapping("/create")
    public String addToCatalog(@RequestParam Long id, @RequestParam String title, @RequestParam Long cost) {
        Product product = new Product(id, title, cost);
        productService.addProduct(product);
        return "redirect:/catalog";
    }
}
