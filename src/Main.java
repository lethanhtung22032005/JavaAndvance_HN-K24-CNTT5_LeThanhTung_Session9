import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductDatabase db = ProductDatabase.getInstance();

        while (true) {
            System.out.println("\n---------------------- QUẢN LÝ SẢN PHẨM ----------------------");
            System.out.println("1. Thêm mới sản phẩm");
            System.out.println("2. Xem danh sách sản phẩm");
            System.out.println("3. Cập nhật thông tin sản phẩm");
            System.out.println("4. Xoá sản phẩm");
            System.out.println("5. Thoát");
            System.out.println("---------------------------------------------------------------");
            System.out.print("Lựa chọn của bạn: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Nhập sai định dạng!");
                continue;
            }

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Chọn loại (1: Vật lý, 2: Kỹ thuật số): ");
                        int type = Integer.parseInt(sc.nextLine());

                        System.out.print("Nhập ID: ");
                        String id = sc.nextLine();

                        System.out.print("Nhập tên: ");
                        String name = sc.nextLine();

                        System.out.print("Nhập giá: ");
                        double price = Double.parseDouble(sc.nextLine());

                        System.out.print(type == 1 ? "Nhập trọng lượng: " : "Nhập dung lượng (MB): ");
                        double extra = Double.parseDouble(sc.nextLine());

                        Product p = ProductFactory.createProduct(type, id, name, price, extra);
                        db.addProduct(p);

                        System.out.println("Thêm sản phẩm thành công!");
                    } catch (Exception e) {
                        System.out.println("Lỗi nhập dữ liệu!");
                    }
                    break;

                case 2:
                    if (db.getProducts().isEmpty()) {
                        System.out.println("Danh sách trống!");
                    } else {
                        for (Product p : db.getProducts()) {
                            p.displayInfo();
                            System.out.println("------------------");
                        }
                    }
                    break;

                case 3:
                    System.out.print("Nhập ID cần cập nhật: ");
                    String uid = sc.nextLine();

                    Product existing = db.findById(uid);
                    if (existing == null) {
                        System.out.println("Không tìm thấy sản phẩm!");
                        break;
                    }

                    try {
                        System.out.print("Nhập tên mới: ");
                        String newName = sc.nextLine();

                        System.out.print("Nhập giá mới: ");
                        double newPrice = Double.parseDouble(sc.nextLine());

                        existing.setName(newName);
                        existing.setPrice(newPrice);

                        System.out.println("Cập nhật thành công!");
                    } catch (Exception e) {
                        System.out.println("Lỗi nhập dữ liệu!");
                    }
                    break;

                case 4:
                    System.out.print("Nhập ID cần xoá: ");
                    String did = sc.nextLine();

                    db.deleteProduct(did);
                    System.out.println("Đã xoá (nếu tồn tại).");
                    break;

                case 5:
                    System.out.println("Thoát chương trình.");
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}