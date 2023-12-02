package com.br.project.Back.service;

import com.br.project.Back.model.Product;
import com.br.project.Back.model.dto.ProductDTO;
import com.br.project.Back.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Page<Product> findByCriteria(String value, Integer page, Integer pageSize, String sort, String order) {
        Pageable pageRequest = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sort);
        return productRepository.search(value, pageRequest);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    public ProductDTO getById(Long id) {
        ProductDTO productDTO = new ProductDTO();
        Product product = productRepository.getById(id);

        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setCreationDate(product.getCreationDate());
        productDTO.setImage(product.getImage());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setSku(product.getSku());
        productDTO.setBrand(product.getBrand());
        productDTO.setCategory(product.getCategory());
        productDTO.setActive(product.isActive());
        productDTO.setFeatured(product.isFeatured());
        productDTO.setWeight(product.getWeight());
        productDTO.setDimensions(product.getDimensions());
        productDTO.setColor(product.getColor());
        productDTO.setMaterial(product.getMaterial());
        productDTO.setRating(product.getRating());
        productDTO.setReviews(product.getReviews());
        productDTO.setAvailability(product.isAvailability());

        return productDTO;
    }

    @Transactional
    public ProductDTO saveUpdateProduct(ProductDTO dto){
        LocalDateTime date = LocalDateTime.now();
        Product product = new Product();
        product.setCreationDate(date);
        if(dto.getId() != null) {
            product = productRepository.getById(dto.getId());
        }

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImage(dto.getImage());
        product.setQuantity(dto.getQuantity());
        product.setSku(dto.getSku());
        product.setBrand(dto.getBrand());
        product.setCategory(dto.getCategory());
        product.setActive(dto.isActive());
        product.setFeatured(dto.isFeatured());
        product.setWeight(dto.getWeight());
        product.setDimensions(dto.getDimensions());
        product.setColor(dto.getColor());
        product.setMaterial(dto.getMaterial());
        product.setRating(dto.getRating());
        product.setReviews(dto.getReviews());
        product.setAvailability(dto.isAvailability());

        productRepository.save(product);
        return dto;
    }

    public List<Product> getAllProducts() {
        List<Product> productList =this.productRepository.findAll();
        return productList;
    }
}
