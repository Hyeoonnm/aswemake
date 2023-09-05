package com.aswemake.service.product;

import com.aswemake.dto.PrevProductInfoDTO;
import com.aswemake.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO findById(Long id);

    void delete(Long id);

    void save(ProductDTO productDTO);

    List<PrevProductInfoDTO> findPrevProduct(Long id);

    List<ProductDTO> findAll();
}