package com.example.shoppingCart.service;

import com.example.shoppingCart.dto.request.ItemRequest;
import com.example.shoppingCart.dto.response.ShoppingCartResponse;
import com.example.shoppingCart.exception.CartNotFoundException;
import com.example.shoppingCart.exception.CategoryNotFoundException;
import com.example.shoppingCart.model.Category;
import com.example.shoppingCart.model.Product;
import com.example.shoppingCart.model.ShoppingCart;
import com.example.shoppingCart.model.ShoppingCartProduct;
import com.example.shoppingCart.repository.CategoryRepository;
import com.example.shoppingCart.repository.ShoppingCartProductRepository;
import com.example.shoppingCart.repository.ShoppingCartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ShoppingCartProductService {

    ShoppingCartProductRepository shoppingCartProductRepository;

    ShoppingCartRepository shoppingCartRepository;

    CategoryRepository categoryRepository;

    public ShoppingCartResponse save(Long cartId, Integer operationNumber, ItemRequest itemRequest) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException(cartId.toString()));

        Product product = getProduct(itemRequest);

        addOrRemoveProduct(operationNumber, shoppingCart, product);

        return findPProductByCartId(cartId);
    }

    private Product getProduct(ItemRequest itemRequest) {
        Category category = categoryRepository.findById(Long.parseLong(itemRequest.getCategory()))
                .orElseThrow(() -> new CategoryNotFoundException(itemRequest.getCategory()));
        Product product = new Product();
        product.setId(Long.parseLong(itemRequest.getId()));
        product.setImageurl(itemRequest.getImageurl());
        product.setCategory(category);
        product.setTitle(itemRequest.getTitle());
        product.setPrice(itemRequest.getPrice());
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

    public ShoppingCartResponse findPProductByCartId(Long cartId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException(cartId.toString()));
        List<ShoppingCartProduct> shoppingCartProduct = shoppingCartProductRepository.findByShoppingCart(shoppingCart);
        ShoppingCartResponse shoppingCartResponse = new ShoppingCartResponse();
        ItemRequest itemRequest;
        List<ItemRequest> itemRequests = new ArrayList<>();

        for (ShoppingCartProduct shoppingCartProduct1 : shoppingCartProduct) {
            itemRequest = new ItemRequest();
            shoppingCartResponse.setId(shoppingCartProduct1.getShoppingCart().getId());
            shoppingCartResponse.setDateCreated(shoppingCartProduct1.getShoppingCart().getDatecreated());
            itemRequest.setId(shoppingCartProduct1.getProduct().getId().toString());
            itemRequest.setCategory(shoppingCartProduct1.getProduct().getCategory().getId().toString());
            itemRequest.setTitle(shoppingCartProduct1.getProduct().getTitle());
            itemRequest.setPrice(shoppingCartProduct1.getProduct().getPrice());
            itemRequest.setImageurl(shoppingCartProduct1.getProduct().getImageurl());
            itemRequest.setQuantity(shoppingCartProduct1.getQuantity());
            itemRequests.add(itemRequest);
        }
        shoppingCartResponse.setItemRequests(itemRequests);

        return shoppingCartResponse;
    }
}
