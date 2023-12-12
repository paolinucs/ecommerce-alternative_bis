package it.paolone.ecommerce.controllers;

import java.util.List;
import it.paolone.ecommerce.dto.ProductDTO;
import it.paolone.ecommerce.entities.Product;
import it.paolone.ecommerce.services.ProductService;
import it.paolone.ecommerce.repositories.ProductRepository;
import it.paolone.ecommerce.services.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final FileUploadService fileUploadService;
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository, ProductService productService,
            FileUploadService fileUploadService) {
        this.productService = productService;
        this.fileUploadService = fileUploadService;
        this.productRepository = productRepository;
    }

    @GetMapping("/all_products")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<Product> fetchedProducts = productService.getAllProducts();
        List<ProductDTO> fetchedProductsDto = new ArrayList<>();

        if (!fetchedProducts.isEmpty()) {
            for (Product fetchedProduct : fetchedProducts)
                fetchedProductsDto.add(productService.convertToProductDto(fetchedProduct));

            return ResponseEntity.ok(fetchedProductsDto);

        }

        return ResponseEntity.noContent().build();

    }

    @PostMapping("/save_product_info")
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductDTO data) {

        if (data != null) {
            try {// try catch serve per la gestione delle eccezioni

                // converte il dato in DTO che sta in input in un'entità normale
                Product convertedDataDto = productService.convertToProduct(data);

                // ricreo un oggetto dto che corrisponde al ritorno convertito di
                // productService.saveproduct(convertedDataDto)
                ProductDTO returningProductData = productService
                        .convertToProductDto(productService.saveProduct(convertedDataDto));
                return ResponseEntity.ok(returningProductData);
            } catch (ResponseStatusException exc) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                        "An error occurred while processing the request: " + exc);
            } catch (Exception exc) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                        "An error occurred while processing the request : " + exc);
            }
        } else
            return ResponseEntity.badRequest().build();

    }

    @PostMapping("/update_price/{barcode}/{newPrice}")
    public ResponseEntity<ProductDTO> updateProductPrice(@PathVariable String barcode, @PathVariable float newPrice) {
        try {
            productService.rePriceProduct(barcode, newPrice);
            Product updatedProduct = productRepository.searchProductByBarcode(barcode);
            return ResponseEntity.ok(productService.convertToProductDto(updatedProduct));
        } catch (ResponseStatusException exc) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred while updating price of product n." + barcode + ": " + exc);
        } catch (Exception exc) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Generic error while updating price of product n." + barcode + ": " + exc);

        }
    }

    @PostMapping("/delete_product/{productBarcode}")
    public ResponseEntity<ProductDTO> deleteProductByBarcode(@PathVariable String productBarcode) {
        try {
            Product deletedProduct = productService.removeProductByBarcode(productBarcode);
            return ResponseEntity.ok(productService.convertToProductDto(deletedProduct));
        } catch (ResponseStatusException exc) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "An error occurred while trying to delete product n." + productBarcode + ": " + exc);
        } catch (Exception exc) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred while trying to delete product n." + productBarcode + ": " + exc);
        }
    }

    @PostMapping("/api/products/save_product_thumb")
    public ResponseEntity<String> saveProductThumbnail(@RequestParam("file") MultipartFile uploadingFile) {
        if (!uploadingFile.isEmpty()) {
            if (fileUploadService.saveFile(uploadingFile)) {
                return ResponseEntity.ok("File successfully uploaded");
            }
        }
        return ResponseEntity.badRequest().build();
    }

}
