package com.example.shoppingCart.service;

import com.example.shoppingCart.dto.Item;
import com.example.shoppingCart.exception.CategoryNotFoundException;
import com.example.shoppingCart.exception.ProductNotFoundException;
import com.example.shoppingCart.model.Category;
import com.example.shoppingCart.model.Product;
import com.example.shoppingCart.model.ShoppingCart;
import com.example.shoppingCart.model.ShoppingCartProduct;
import com.example.shoppingCart.repository.CategoryRepository;
import com.example.shoppingCart.repository.ProductRepository;
import com.example.shoppingCart.repository.ShoppingCartProductRepository;
import com.example.shoppingCart.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartProductService {

    @Autowired
    ShoppingCartProductRepository shoppingCartProductRepository;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public com.example.shoppingCart.dto.ShoppingCart save(Long cartId,Integer operationNumber, Item item) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new CategoryNotFoundException(cartId.toString()));
        ShoppingCartProduct shoppingCartProduct ;
        Category category = categoryRepository.findById(Long.parseLong(item.getCategory()))
                .orElseThrow(() -> new ProductNotFoundException(cartId.toString()));
        Product product = new Product();
        product.setId(Long.parseLong(item.getId()));
        product.setImageurl(item.getImageurl());
        product.setCategory(category);
        product.setTitle(item.getTitle());
        product.setPrice(item.getPrice());

        shoppingCartProduct = shoppingCartProductRepository.findByProductId(product.getId());
       if(shoppingCartProduct == null){
           shoppingCartProduct = new ShoppingCartProduct();
           shoppingCartProduct.setQuantity(1);
           shoppingCartProduct.setProduct(product);
           shoppingCartProduct.setShoppingCart(shoppingCart);
       }else {
           if(operationNumber == 1){
               shoppingCartProduct.setQuantity(shoppingCartProduct.getQuantity() + 1);
           }else{
                   shoppingCartProduct.setQuantity(shoppingCartProduct.getQuantity() - 1);
           }

       }
       shoppingCartProductRepository.save(shoppingCartProduct);
        if(shoppingCartProduct.getQuantity() == 0){
            shoppingCartProductRepository.deleteById(shoppingCartProduct.getCartItemId());
        }

        ShoppingCart shoppingCart1 = shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new CategoryNotFoundException(cartId.toString()));
        List<ShoppingCartProduct> shoppingCartProduct11 = shoppingCartProductRepository.findByShoppingCart(shoppingCart1);
        com.example.shoppingCart.dto.ShoppingCart shoppingCart12 = new com.example.shoppingCart.dto.ShoppingCart();
        Item item1;
        List<Item> items = new ArrayList<>();

        for(ShoppingCartProduct shoppingCartProduct1:shoppingCartProduct11){
            item1 = new Item();
            shoppingCart12.setId(shoppingCartProduct1.getShoppingCart().getId());
            shoppingCart12.setDateCreated(shoppingCartProduct1.getShoppingCart().getDatecreated());
            item1.setId(shoppingCartProduct1.getProduct().getId().toString());
            item1.setCategory(shoppingCartProduct1.getProduct().getCategory().getId().toString());
            item1.setTitle(shoppingCartProduct1.getProduct().getTitle());
            item1.setPrice(shoppingCartProduct1.getProduct().getPrice());
            item1.setImageurl(shoppingCartProduct1.getProduct().getImageurl());
            item1.setQuantity(shoppingCartProduct1.getQuantity());
            items.add(item1);
        }
        shoppingCart12.setItems(items);

        return shoppingCart12;


    }

    @Transactional
    public void clearCart(Long cartId){
        ShoppingCart shoppingCart = shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new CategoryNotFoundException(cartId.toString()));
        shoppingCartProductRepository.deleteByShoppingCartId(shoppingCart.getId());
        shoppingCartRepository.deleteById(shoppingCart.getId());
    }

    public com.example.shoppingCart.dto.ShoppingCart findPProductByCartId(Long cartId){
         ShoppingCart shoppingCart = shoppingCartRepository.findById(cartId)
                 .orElseThrow(() -> new CategoryNotFoundException(cartId.toString()));
         List<ShoppingCartProduct> shoppingCartProduct = shoppingCartProductRepository.findByShoppingCart(shoppingCart);
        com.example.shoppingCart.dto.ShoppingCart shoppingCart1 = new com.example.shoppingCart.dto.ShoppingCart();
        Item item;
        List<Item> items = new ArrayList<>();

        for(ShoppingCartProduct shoppingCartProduct1:shoppingCartProduct){
            item = new Item();
            shoppingCart1.setId(shoppingCartProduct1.getShoppingCart().getId());
            shoppingCart1.setDateCreated(shoppingCartProduct1.getShoppingCart().getDatecreated());
            item.setId(shoppingCartProduct1.getProduct().getId().toString());
            item.setCategory(shoppingCartProduct1.getProduct().getCategory().getId().toString());
            item.setTitle(shoppingCartProduct1.getProduct().getTitle());
            item.setPrice(shoppingCartProduct1.getProduct().getPrice());
            item.setImageurl(shoppingCartProduct1.getProduct().getImageurl());
            item.setQuantity(shoppingCartProduct1.getQuantity());
            items.add(item);
       }
        shoppingCart1.setItems(items);

        return shoppingCart1;
    }
}
