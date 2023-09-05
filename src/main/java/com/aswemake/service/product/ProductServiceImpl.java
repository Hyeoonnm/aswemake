package com.aswemake.service.product;

import com.aswemake.dao.PrevProductInfoDAO;
import com.aswemake.dao.ProductDAO;
import com.aswemake.dto.PrevProductInfoDTO;
import com.aswemake.dto.ProductDTO;
import com.aswemake.entity.PrevProductInfoEntity;
import com.aswemake.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductDAO productDAO;
    private final PrevProductInfoDAO prevProductInfoDAO;

    @Override
    @Transactional
    public void save(ProductDTO dto) {
        ProductEntity toEntity = ProductDTO.toEntity(dto);
        int price = 0;
        if (productDAO.findProductById(toEntity.getId()) != null) {
            ProductEntity product = productDAO.findProductById(toEntity.getId());
            price = product.getPrice();
        }

        if (dto.getModifiedDate() != null) {
            Long id = toEntity.getId();
            PrevProductInfoEntity byProductId;
            List<PrevProductInfoEntity> list = prevProductInfoDAO.findAllByProductId(id);
            if (list.size() > 1) {
              byProductId = list.get(list.size()-1);
            } else {
                byProductId = prevProductInfoDAO.findByProductId(id);
            }
            PrevProductInfoEntity prevEntity = PrevProductInfoEntity.toEntity(dto, byProductId, price);
            prevProductInfoDAO.save(prevEntity);
        }

        ProductEntity save = productDAO.save(toEntity);

        ProductEntity.toDTO(save);
    }

    @Override
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
    public ProductDTO findById(Long id) {
        ProductEntity byId = productDAO.findProductById(id);
        return ProductEntity.toDTO(byId);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        prevProductInfoDAO.deleteByProductId(id);
        productDAO.deleteById(id);
    }
}
