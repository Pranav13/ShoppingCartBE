package com.example.shoppingCart.service;

import com.example.shoppingCart.dto.Item;
import com.example.shoppingCart.dto.ShoppingCartDto;
import com.example.shoppingCart.exception.CartNotFoundException;
import com.example.shoppingCart.exception.CategoryNotFoundException;
import com.example.shoppingCart.model.Category;
import com.example.shoppingCart.model.Product;
import com.example.shoppingCart.model.ShoppingCart;
import com.example.shoppingCart.model.ShoppingCartProduct;
import com.example.shoppingCart.repository.CategoryRepository;
import com.example.shoppingCart.repository.ShoppingCartProductRepository;
import com.example.shoppingCart.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartProductService {

    @Autowired
    ShoppingCartProductRepository shoppingCartProductRepository;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public ShoppingCartDto save(Long cartId, Integer operationNumber, Item item) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException(cartId.toString()));

        Product product = getProduct(item);

        addOrRemoveProduct(operationNumber, shoppingCart, product);

        return findPProductByCartId(cartId);
    }

    private Product getProduct(Item item) {
        Category category = categoryRepository.findById(Long.parseLong(item.getCategory()))
                .orElseThrow(() -> new CategoryNotFoundException(item.getCategory()));
        Product product = new Product();
        product.setId(Long.parseLong(item.getId()));
        product.setImageurl(item.getImageurl());
        product.setCategory(category);
        product.setTitle(item.getTitle());
        product.setPrice(item.getPrice());
        return product;
    }

    private void addOrRemoveProduct(Integer operationNumber, ShoppingCart shoppingCart, Product product) {
        ShoppingCartProduct shoppingCartProduct = shoppingCartProductRepository.findByProductId(product.getId());
        if (shoppingCartProduct == null) {
            shoppingCartProduct = new ShoppingCartProduct();
            shoppingCartProduct.setQuantity(1);
            shoppingCartProduct.setProduct(product);
            shoppingCartProduct.setShoppingCart(shoppingCart);
        } else {
            if (operationNumber == 1) {
                shoppingCartProduct.setQuantity(shoppingCartProduct.getQuantity() + 1);
            } else {
                shoppingCartProduct.setQuantity(shoppingCartProduct.getQuantity() - 1);
            }

        }
        shoppingCartProductRepository.save(shoppingCartProduct);
        if (shoppingCartProduct.getQuantity() == 0) {
            shoppingCartProductRepository.deleteById(shoppingCartProduct.getCartItemId());
        }
    }

    @Transactional
    public void clearCart(Long cartId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException(cartId.toString()));
        shoppingCartProductRepository.deleteByShoppingCartId(shoppingCart.getId());
        shoppingCartRepository.deleteById(shoppingCart.getId());
    }

    public ShoppingCartDto findPProductByCartId(Long cartId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException(cartId.toString()));
        List<ShoppingCartProduct> shoppingCartProduct = shoppingCartProductRepository.findByShoppingCart(shoppingCart);
        ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
        Item item;
        List<Item> items = new ArrayList<>();

        for (ShoppingCartProduct shoppingCartProduct1 : shoppingCartProduct) {
            item = new Item();
            shoppingCartDto.setId(shoppingCartProduct1.getShoppingCart().getId());
            shoppingCartDto.setDateCreated(shoppingCartProduct1.getShoppingCart().getDatecreated());
            item.setId(shoppingCartProduct1.getProduct().getId().toString());
            item.setCategory(shoppingCartProduct1.getProduct().getCategory().getId().toString());
            item.setTitle(shoppingCartProduct1.getProduct().getTitle());
            item.setPrice(shoppingCartProduct1.getProduct().getPrice());
            item.setImageurl(shoppingCartProduct1.getProduct().getImageurl());
            item.setQuantity(shoppingCartProduct1.getQuantity());
            items.add(item);
        }
        shoppingCartDto.setItems(items);

        return shoppingCartDto;
    }
}
