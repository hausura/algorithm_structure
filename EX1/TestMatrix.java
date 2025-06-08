package hus.oop.matrix;

import java.util.Arrays;
import java.util.Random;

public class TestMatrix {
    public static void main(String[] args) {
        // Tạo kích thước ngẫu nhiên trong đoạn [5, 10]
        Random rand = new Random();
        int size = rand.nextInt(6) + 5; // rand.nextInt(max - min + 1) + min

        // Tạo 2 ma trận với kích thước ngẫu nhiên
        MySquareMatrix matrix1 = new MySquareMatrix(size);
        MySquareMatrix matrix2 = new MySquareMatrix(size);

        System.out.println("Kích thước ma trận: " + size + "x" + size);
        System.out.println("--------------------------------\n");

        // 1. In ra 2 ma trận và 2 ma trận chuyển vị tương ứng
        System.out.println("Ma trận 1:");
        System.out.println(matrix1);
        System.out.println("\nMa trận chuyển vị của Ma trận 1:");
        System.out.println(matrix1.transpose());
        System.out.println("\n--------------------------------\n");

        System.out.println("Ma trận 2:");
        System.out.println(matrix2);
        System.out.println("\nMa trận chuyển vị của Ma trận 2:");
        System.out.println(matrix2.transpose());
        System.out.println("\n--------------------------------\n");

        // 2. In ra các đường chéo chính và đường chéo phụ
        System.out.println("Đường chéo chính của Ma trận 1: " + Arrays.toString(matrix1.principalDiagonal()));
        System.out.println("Đường chéo phụ của Ma trận 1: " + Arrays.toString(matrix1.secondaryDiagonal()));
        System.out.println("\nĐường chéo chính của Ma trận 2: " + Arrays.toString(matrix2.principalDiagonal()));
        System.out.println("Đường chéo phụ của Ma trận 2: " + Arrays.toString(matrix2.secondaryDiagonal()));
        System.out.println("\n--------------------------------\n");
        
        // 3. In ra ma trận tổng
        System.out.println("Tổng của Ma trận 1 và Ma trận 2:");
        MySquareMatrix sumMatrix = matrix1.add(matrix2);
        System.out.println(sumMatrix);
        System.out.println("\n--------------------------------\n");

        // 4. In ra ma trận hiệu
        System.out.println("Hiệu của Ma trận 1 cho Ma trận 2:");
        MySquareMatrix diffMatrix = matrix1.minus(matrix2);
        System.out.println(diffMatrix);
        System.out.println("\n--------------------------------\n");

        // 5. In ra ma trận tích
        System.out.println("Tích của Ma trận 1 và Ma trận 2:");
        MySquareMatrix productMatrix = matrix1.multiply(matrix2);
        System.out.println(productMatrix);
        System.out.println("\n--------------------------------\n");

        // 6. In ra các số nguyên tố
        System.out.println("Các số nguyên tố trong Ma trận 1: " + Arrays.toString(matrix1.primes()));
        System.out.println("Các số nguyên tố trong Ma trận 2: " + Arrays.toString(matrix2.primes()));
        System.out.println("\n--------------------------------\n");
        
        // Ghi chú về việc lưu file
        System.out.println("Lưu ý: Để lưu kết quả ra file, hãy chạy chương trình từ terminal và chuyển hướng output.");
        System.out.println("Ví dụ trên Windows: java -cp . hus.oop.matrix.TestMatrix > NguyenVanA_123456_Matrix.txt");
        System.out.println("Ví dụ trên Linux/macOS: java -cp . hus.oop.matrix.TestMatrix > NguyenVanA_123456_Matrix.txt");
    }
}