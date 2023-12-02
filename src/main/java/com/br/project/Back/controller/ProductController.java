package com.br.project.Back.controller;

import com.br.project.Back.model.Client;
import com.br.project.Back.model.Product;
import com.br.project.Back.model.dto.ClientDTO;
import com.br.project.Back.model.dto.ProductDTO;
import com.br.project.Back.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/listpage")
    public Page<Product> getByCriteria(@RequestParam String value, @RequestParam Integer page,
                                       @RequestParam Integer pageSize, @RequestParam String sort,
                                       @RequestParam String order) {
        return productService.findByCriteria(value, page, pageSize, sort, order);
    }

    @GetMapping("/getAll")
    public List<Product> getAllProducts(){
        return this.productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDTO getById(@PathVariable Long id){
        return productService.getById(id);
    }

    @PostMapping(path = "/save", consumes = "application/json; charset=utf-8")
    public ProductDTO saveClient(@RequestBody ProductDTO dto){
        return productService.saveUpdateProduct(dto);
    }

    @PutMapping(path = "/update", consumes = "application/json; charset=utf-8")
    public ProductDTO updateClient(@RequestBody ProductDTO dto){
        return productService.saveUpdateProduct(dto);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }

}
