package com.ecommerce.ggshop.repository;

import com.ecommerce.ggshop.model.Image;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ImageRepository extends CrudRepository<Image, Long> {
    List<Image> findImagesByProductId(long id);
}
