package hus.oop.integration;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;

public class TestIntegrationCalculator {
    // Thuộc tính này không được sử dụng trong kịch bản test này
    // nhưng vẫn giữ lại theo cấu trúc ban đầu.
    private MyPolynomial polynomial;

    public TestIntegrationCalculator() {
        // Constructor mặc định để tạo đối tượng test.
    }

    public TestIntegrationCalculator(MyPolynomial polynomial) {
        this.polynomial = polynomial;
    }

    public static void main(String[] args) {
        // Thay đổi tên file tại đây!
        String outputFilename = "NguyenVanA_123456_Integration.txt";

        try (PrintStream fileOut = new PrintStream(new FileOutputStream(outputFilename))) {
            // Chuyển hướng System.out sang file
            System.setOut(fileOut);

            TestIntegrationCalculator tester = new TestIntegrationCalculator();

            System.out.println("Bắt đầu chương trình kiểm tra IntegrationCalculator.");
            System.out.println("==================================================");

            tester.testArrayPolynomial();

            System.out.println();
            System.out.println("==================================================");
            System.out.println();

            tester.testListPolynomial();

            System.out.println();
            System.out.println("==================================================");
            System.out.println("Chương trình kiểm tra đã hoàn tất. Kết quả được lưu trong file " + outputFilename);

        } catch (FileNotFoundException e) {
            System.err.println("Lỗi: Không thể tạo hoặc ghi vào file " + outputFilename);
            e.printStackTrace();
        }
    }

    public void testArrayPolynomial() {
        System.out.println("--- KIỂM TRA MyArrayPolynomial ---");

        Random rand = new Random();
        int size = rand.nextInt(3) + 3; // size từ 3 đến 5
        MyArrayPolynomial poly = new MyArrayPolynomial();
        System.out.println("Tạo đa thức với " + size + " hệ số ngẫu nhiên:");
        for (int i = 0; i < size; i++) {
            double coeff = Math.round(rand.nextDouble() * 20 - 10); // Hệ số từ -10 đến 10
            poly.append(coeff);
        }
        System.out.println("Đa thức ban đầu (poly1): " + poly);
        System.out.println("Bậc của đa thức: " + poly.degree());

        System.out.println("\n--- Test các chức năng ---");
        poly.append(5.5);
        System.out.println("1. Thêm hệ số 5.5 vào cuối: " + poly);

        poly.set(1.0, 1);
        System.out.println("2. Sửa hệ số tại index 1 thành 1.0: " + poly);

        // "Xóa" được hiểu là đặt hệ số tại vị trí đó bằng 0
        poly.set(0.0, 2);
        System.out.println("3. 'Xóa' phần tử tại index 2 (set hệ số = 0): " + poly);
        System.out.println("   Bậc của đa thức sau khi 'xóa': " + poly.degree());

        double x = 2.0;
        System.out.println("4. Giá trị của đa thức tại x = " + x + " là: " + poly.evaluate(x));
        System.out.println("5. Đạo hàm của đa thức: " + poly.derivative());

        MyArrayPolynomial poly2 = new MyArrayPolynomial();
        poly2.append(1).append(2).append(3);
        System.out.println("\nTạo đa thức thứ hai (poly2): " + poly2);
        System.out.println("6. poly1 + poly2 = " + poly.plus(poly2));
        System.out.println("7. poly1 - poly2 = " + poly.minus(poly2));
        System.out.println("8. poly1 * poly2 = " + poly.multiply(poly2));

        System.out.println("\n--- Test tính tích phân ---");
        MyIntegrator integrator = new SimpsonRule(1e-6, 1000);
        double lower = 1.0;
        double upper = 5.0;
        double integral = integrator.integrate(poly, lower, upper);
        System.out.println("Tích phân của đa thức ban đầu từ " + lower + " đến " + upper + " là: " + integral);
    }

    public void testListPolynomial() {
        System.out.println("--- KIỂM TRA MyListPolynomial ---");

        Random rand = new Random();
        int size = rand.nextInt(3) + 3; // size từ 3 đến 5
        MyListPolynomial poly = new MyListPolynomial();
        System.out.println("Tạo đa thức với " + size + " hệ số ngẫu nhiên:");
        for (int i = 0; i < size; i++) {
            double coeff = Math.round(rand.nextDouble() * 20 - 10); // Hệ số từ -10 đến 10
            poly.append(coeff);
        }
        System.out.println("Đa thức ban đầu (poly1): " + poly);
        System.out.println("Bậc của đa thức: " + poly.degree());

        System.out.println("\n--- Test các chức năng ---");
        poly.append(-4.2);
        System.out.println("1. Thêm hệ số -4.2 vào cuối: " + poly);

        poly.set(10.0, 0);
        System.out.println("2. Sửa hệ số tại index 0 thành 10.0: " + poly);

        poly.set(0.0, 1);
        System.out.println("3. 'Xóa' phần tử tại index 1 (set hệ số = 0): " + poly);
        System.out.println("   Bậc của đa thức sau khi 'xóa': " + poly.degree());
        
        double x = 1.5;
        System.out.println("4. Giá trị của đa thức tại x = " + x + " là: " + poly.evaluate(x));
        System.out.println("5. Đạo hàm của đa thức: " + poly.derivative());

        MyListPolynomial poly2 = new MyListPolynomial();
        poly2.append(2).append(0).append(-1);
        System.out.println("\nTạo đa thức thứ hai (poly2): " + poly2);
        System.out.println("6. poly1 + poly2 = " + poly.plus(poly2));
        System.out.println("7. poly1 - poly2 = " + poly.minus(poly2));
        System.out.println("8. poly1 * poly2 = " + poly.multiply(poly2));

        System.out.println("\n--- Test tính tích phân ---");
        MyIntegrator integrator = new SimpsonRule(1e-7, 2000);
        double lower = 2.0;
        double upper = 6.0;
        double integral = integrator.integrate(poly, lower, upper);
        System.out.println("Tích phân của đa thức ban đầu từ " + lower + " đến " + upper + " là: " + integral);
    }
}