package ru.geebrains.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geebrains.market.model.Product;
import ru.geebrains.market.service.ProductService;

import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ProductService productService;

    @GetMapping("/product/all")
    @ResponseBody
    public List<Product> getAll() {
        return productService.productAll();
    }

    @GetMapping("/product/change_count")
    @ResponseBody
    public void changeCount(@RequestParam Long productID, @RequestParam Integer delta) {
        productService.changeCount(productID, delta);
    }

    @GetMapping("/product/delete_product")
    @ResponseBody
    public void deleteProduct(@RequestParam Long productID) {
        productService.deleteProduct(productID);
    }

    @GetMapping("/main")
    public String mainPage() {
        return "main";
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
    public String addToCatalog(@RequestParam(required = false) Long id, @RequestParam(required = false) String title, @RequestParam(required = false) Long cost) {
        if (id == null) id = productService.nextID();
        if (productService.idIsPresent(id)) id = productService.nextID();
        if (!Objects.equals(title, "") && cost != null) {
            Product product = new Product(id, title, cost, 0);
            productService.addProduct(product);
        }
        return "redirect:/";
    }

//    @PostMapping("/create")
//    public String addToCatalog(@ModelAttribute Product product) {
//        productService.addProduct(product);
//        return "redirect:/";
//    }

    @GetMapping("/json/{id}")
    @ResponseBody
    public Product findById(@PathVariable Long id) {
        return productService.productById(id);
    }

    @PostMapping("/json/add")
    @ResponseBody
    public void addJsonProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }
}
