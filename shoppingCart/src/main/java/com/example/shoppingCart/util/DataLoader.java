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
        categoryRepository.save(new Category((long) 1,"Personal Development"));
        categoryRepository.save(new Category((long) 2,"Literature & Fiction"));
        categoryRepository.save(new Category((long) 3,"Fiction"));
        categoryRepository.save(new Category((long) 4," Science Fiction"));
        categoryRepository.save(new Category((long) 5,"Action & Adventure"));
    }

    private void LoadProduct(){

        productRepository.save(new Product((long)1,"How to Win Friends and Influence People",200,"https://m.media-amazon.com/images/I/71-V6LEZU8L._AC_UY218_.jpg",
                new Category((long)1,null)));
        productRepository.save(new Product((long)2,"Rich Dad Poor Dad",3,"https://m.media-amazon.com/images/I/81bsw6fnUiL._AC_UY218_.jpg",
                new Category((long)1,null)));
        productRepository.save(new Product((long)3,"The Power of Your Subconscious Mind",1.75,"https://m.media-amazon.com/images/I/51nwRlrvCsL.jpg",
                new Category((long)1,null)));
        productRepository.save(new Product((long)4,"The Great Gatsby",100,"https://m.media-amazon.com/images/I/81TM6L3fUzL._AC_UY218_.jpg",
                new Category((long)2,null)));
        productRepository.save(new Product((long)5,"Origin",235,"https://m.media-amazon.com/images/I/91CqIAD4WpL._AC_UY218_.jpg",
                new Category((long)3,null)));
        productRepository.save(new Product((long)6,"The Theory of Everything",1.75,"https://images-na.ssl-images-amazon.com/images/I/51oHUvYzbsL._SX327_BO1,204,203,200_.jpg",
                new Category((long)3,null)));
        productRepository.save(new Product((long)7,"The Search Begins",150,"https://images-na.ssl-images-amazon.com/images/I/41kI2hfnIbL._SX311_BO1,204,203,200_.jpg",
                new Category((long)4,null)));
        productRepository.save(new Product((long)8,"The Time Machine",140,"https://images-na.ssl-images-amazon.com/images/I/51CYYxY3riL._SX320_BO1,204,203,200_.jpg",
                new Category((long)4,null)));
        productRepository.save(new Product((long)9,"Touched",2,"https://m.media-amazon.com/images/I/41cNaJZio9L.jpg",
                new Category((long)5,null)));
        productRepository.save(new Product((long)10,"Harry Potter and the Prisoner of Azkaban",350,"https://images-na.ssl-images-amazon.com/images/I/41PIRVVOoPL._SX400_BO1,204,203,200_.jpg",
                new Category((long)5,null)));
        /*productRepository.save(new Product((long)11,"Peach",2,"https://upload.wikimedia.org/wikipedia/commons/9/9e/Autumn_Red_peaches.jpg",
                new Category((long)3,null)));
        productRepository.save(new Product((long)12,"Cinnamon Sticks",2,"https://upload.wikimedia.org/wikipedia/commons/8/8c/Cinnamon-other.jpg",
                new Category((long)4,null)));
        productRepository.save(new Product((long)13,"Saffron",3,"https://upload.wikimedia.org/wikipedia/commons/4/48/Saffron_Crop.JPG",
                new Category((long)4,null)));
        productRepository.save(new Product((long)14,"Baguette Bread",3,"https://static.pexels.com/photos/416607/pexels-photo-416607.jpeg",
                new Category((long)1,null)));*/
    }

    private void LoadRole() {
         roleRepository.save(new Role(ERole.ROLE_ADMIN));
        roleRepository.save(new Role(ERole.ROLE_USER));

    }
}
