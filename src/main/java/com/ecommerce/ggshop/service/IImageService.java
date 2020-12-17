package com.ecommerce.ggshop.service;

import com.ecommerce.ggshop.model.Image;
import com.ecommerce.ggshop.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {

    public void createImage(long productId, MultipartFile file);

    public List getProductImages(long productId);
}
