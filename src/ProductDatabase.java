    import java.util.ArrayList;
    import java.util.List;

    public class ProductDatabase {
        private static ProductDatabase instance;
        private List<Product> products;

        private ProductDatabase() {
            products = new ArrayList<>();
        }

        public static ProductDatabase getInstance() {
            if (instance == null) {
                instance = new ProductDatabase();
            }
            return instance;
        }

        public void addProduct(Product product) {
            products.add(product);
        }

        public void updateProduct(Product product) {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId().equals(product.getId())) {
                    products.set(i, product);
                    return;
                }
            }
        }

        public void deleteProduct(String productId) {
            products.removeIf(product -> product.getId().equals(productId));
        }

        public List<Product> getProducts() {
            return products;
        }

        public Product findById(String id) {
            for (Product p : products) {
                if (p.getId().equals(id)) return p;
            }
            return null;
        }
    }