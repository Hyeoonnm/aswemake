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
        System.out.println(save.getId());

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
    public ProductDTO update(Long id,ProductDTO dto) {
        ProductEntity entity = productDAO.findProductById(id);
        if (entity == null) {
            dto.setCreateDate(LocalDateTime.now());
            productDAO.save(ProductDTO.toEntity(dto));
        }

        int price = entity.getPrice(); // 수정 내역을 남기기 위한 가격
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setModifiedDate(LocalDateTime.now());
        ProductEntity save = productDAO.save(entity);

        if (entity.getModifiedDate() != null) {
            Long saveId = save.getId();
            PrevProductInfoEntity byProductId;
            List<PrevProductInfoEntity> list = prevProductInfoDAO.findAllByProductId(id);
            if (list.size() > 1) {
                byProductId = list.get(list.size()-1);
            } else {
                byProductId = prevProductInfoDAO.findByProductId(id);
            }
            PrevProductInfoEntity prevEntity = PrevProductInfoEntity.toEntity(ProductEntity.toDTO(save), byProductId, price, save);
            prevProductInfoDAO.save(prevEntity);
        }

        return ProductEntity.toDTO(save);
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
}
