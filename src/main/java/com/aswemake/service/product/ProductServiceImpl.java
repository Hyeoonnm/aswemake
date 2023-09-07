package com.aswemake.service.product;

import com.aswemake.dao.PrevProductInfoDAO;
import com.aswemake.dao.ProductDAO;
import com.aswemake.dto.PrevProductInfoDTO;
import com.aswemake.dto.ProductDTO;
import com.aswemake.entity.PrevProductInfoEntity;
import com.aswemake.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductDAO productDAO;
    private final PrevProductInfoDAO prevProductInfoDAO;

    @Override
    public ProductDTO save(ProductDTO dto) {
        dto.setCreateDate(LocalDateTime.now());
        ProductEntity toEntity = ProductDTO.toEntity(dto);
        ProductEntity save = productDAO.save(toEntity);

        return ProductEntity.toDTO(save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PrevProductInfoDTO> findPrevProduct(Long id) {
        List<PrevProductInfoEntity> entities = prevProductInfoDAO.findAllByProductId(id);

        List<PrevProductInfoDTO> dtoList = new ArrayList<>();
        for (PrevProductInfoEntity x : entities) {
            PrevProductInfoDTO infoDTO = PrevProductInfoDTO.toDTO(x);
            dtoList.add(infoDTO);
        }
        return dtoList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        List<ProductEntity> list = productDAO.findAll();
        List<ProductDTO> dtoList = new ArrayList<>();
        for (ProductEntity x :
                list) {
            ProductDTO dto = ProductEntity.toDTO(x);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public ProductDTO update(ProductDTO dto) {
        ProductEntity entity = productDAO.findProductById(dto.getId());
        ProductDTO findProduct = ProductEntity.toDTO(entity);

        int price = findProduct.getPrice();

        setValue(dto, findProduct);

        ProductEntity toEntity = ProductDTO.toEntity(findProduct);

        if (productDAO.findProductById(toEntity.getId()) != null) {
            ProductEntity product = productDAO.findProductById(toEntity.getId());
        }

        if (findProduct.getModifiedDate() != null) {
            Long id = toEntity.getId();
            PrevProductInfoEntity byProductId;
            List<PrevProductInfoEntity> list = prevProductInfoDAO.findAllByProductId(id);
            if (list.size() > 1) {
                byProductId = list.get(list.size()-1);
                price = byProductId.getPrice();
            } else {
                byProductId = prevProductInfoDAO.findByProductId(id);
            }
            PrevProductInfoEntity prevEntity = PrevProductInfoEntity.toEntity(findProduct, byProductId, price);
            prevProductInfoDAO.save(prevEntity);
        }

        return ProductEntity.toDTO(toEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        ProductEntity byId = productDAO.findProductById(id);
        return ProductEntity.toDTO(byId);
    }

    @Override
    public void delete(Long id) {
        prevProductInfoDAO.deleteByProductId(id);
        productDAO.deleteById(id);
    }

    private static void setValue(ProductDTO dto, ProductDTO findProduct) {
        dto.setCreateDate(findProduct.getCreateDate());
        dto.setModifiedDate(LocalDateTime.now());

        findProduct.setPrice(dto.getPrice());
        findProduct.setName(dto.getName());
        findProduct.setCreateDate(dto.getCreateDate());
        findProduct.setModifiedDate(dto.getModifiedDate());
    }
}
