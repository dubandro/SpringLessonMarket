package ru.geebrains.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
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
}
