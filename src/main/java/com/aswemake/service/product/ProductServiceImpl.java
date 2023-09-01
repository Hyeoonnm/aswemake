package com.aswemake.service.product;

import com.aswemake.dto.ProductDTO;
import com.aswemake.dao.ProductDAO;
import com.aswemake.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductDAO productDAO;

    @Override
    public ProductDTO add(ProductDTO productDTO) {
        ProductDTO dto = new ProductDTO();
        ProductEntity entity = dto.toEntity(productDTO);
        return productDAO.save(entity).toDTO(entity);
    }
}
