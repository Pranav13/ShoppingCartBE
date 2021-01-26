package com.example.shoppingCart.util;

import com.example.shoppingCart.model.Category;
import com.example.shoppingCart.model.ERole;
import com.example.shoppingCart.model.Product;
import com.example.shoppingCart.model.Role;
import com.example.shoppingCart.repository.CategoryRepository;
import com.example.shoppingCart.repository.ProductRepository;
import com.example.shoppingCart.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    private RoleRepository roleRepository;


    @Autowired
    public DataLoader(CategoryRepository categoryRepository, ProductRepository productRepository, RoleRepository roleRepository){
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.roleRepository = roleRepository;
        LoadCategory();
        LoadProduct();
        LoadRole();
    }

    private void LoadCategory() {
        categoryRepository.save(new Category((long) 1,"Bread"));
        categoryRepository.save(new Category((long) 2,"Dairy"));
        categoryRepository.save(new Category((long) 3,"Fruits"));
        categoryRepository.save(new Category((long) 4,"Seasoning and Spices"));
        categoryRepository.save(new Category((long) 5,"Vegetable"));
    }

    private void LoadProduct(){
        productRepository.save(new Product((long)1,"Spinach",2.5,"http://www.publicdomainpictures.net/pictures/170000/velka/spinach-leaves-1461774375kTU.jpg",
                new Category((long)4,null)));
        productRepository.save(new Product((long)2,"Freshly Baked Bread",3,"https://static.pexels.com/photos/2434/bread-food-healthy-breakfast.jpg",
                new Category((long)1,null)));
        productRepository.save(new Product((long)3,"Avacado",1.75,"https://pixnio.com/free-images/2017/03/17/2017-03-17-09-15-56.jpg",
                new Category((long)3,null)));
        productRepository.save(new Product((long)3,"Tomato",2.5,"https://static.pexels.com/photos/8390/food-wood-tomatoes.jpg",
                new Category((long)4,null)));
        productRepository.save(new Product((long)3,"Lettuce",1,"https://upload.wikimedia.org/wikipedia/commons/7/7f/Lettuce_Mini_Heads_%287331119710%29.jpg",
                new Category((long)4,null)));
        productRepository.save(new Product((long)3,"Cauliflower",1.75,"https://upload.wikimedia.org/wikipedia/commons/thumb/1/11/Cauliflowers_-_20051021.jpg/1280px-Cauliflowers_-_20051021.jpg",
                new Category((long)4,null)));
        productRepository.save(new Product((long)3,"Banana",1.25,"https://upload.wikimedia.org/wikipedia/commons/thumb/4/4c/Bananas.jpg/1024px-Bananas.jpg",
                new Category((long)3,null)));
        productRepository.save(new Product((long)3,"Orange",1.7,"https://upload.wikimedia.org/wikipedia/commons/c/c4/Orange-Fruit-Pieces.jpg",
                new Category((long)3,null)));
        productRepository.save(new Product((long)3,"Apple",2,"https://upload.wikimedia.org/wikipedia/commons/1/15/Red_Apple.jpg",
                new Category((long)3,null)));
        productRepository.save(new Product((long)3,"Grape",2,"https://upload.wikimedia.org/wikipedia/commons/3/36/Kyoho-grape.jpg",
                new Category((long)3,null)));
        productRepository.save(new Product((long)3,"Peach",2,"https://upload.wikimedia.org/wikipedia/commons/9/9e/Autumn_Red_peaches.jpg",
                new Category((long)3,null)));
        productRepository.save(new Product((long)3,"Cinnamon Sticks",2,"https://upload.wikimedia.org/wikipedia/commons/8/8c/Cinnamon-other.jpg",
                new Category((long)4,null)));
        productRepository.save(new Product((long)3,"Saffron",3,"https://upload.wikimedia.org/wikipedia/commons/4/48/Saffron_Crop.JPG",
                new Category((long)4,null)));
        productRepository.save(new Product((long)3,"Baguette Bread",3,"https://static.pexels.com/photos/416607/pexels-photo-416607.jpeg",
                new Category((long)1,null)));
    }

    private void LoadRole() {
         roleRepository.save(new Role(ERole.ROLE_ADMIN));
        roleRepository.save(new Role(ERole.ROLE_USER));

    }
}
