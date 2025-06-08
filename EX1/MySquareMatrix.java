package hus.oop.matrix;

import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MySquareMatrix {
    private int[][] data;
    private int size; // Thêm thuộc tính size để tiện sử dụng

    /**
     * Hàm dựng, khởi tạo một ma trận có các phần tử được sinh ngẫu nhiên trong khoảng [1, 100].
     * Tuy nhiên, Javadoc của initRandom yêu cầu trong khoảng [10, 90], ta sẽ theo Javadoc của phương thức.
     * @param size Kích thước của ma trận vuông.
     */
    public MySquareMatrix(int size) {
        this.size = size;
        initRandom(size);
    }
    
    // Thêm một constructor để tạo ma trận từ một mảng 2D có sẵn (hữu ích cho các phép toán)
    private MySquareMatrix(int[][] data) {
        this.data = data;
        this.size = data.length;
    }

    /**
     * Phương thức khởi tạo ma trận, các phần tử của ma trận được sinh ngẫu nhiên trong đoạn [10, 90].
     * @param size Kích thước của ma trận.
     */
    private void initRandom(int size) {
        this.data = new int[size][size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // Sinh số ngẫu nhiên trong khoảng [10, 90]
                this.data[i][j] = rand.nextInt(81) + 10;
            }
        }
    }

    /**
     * Lấy ra các phần tử trên đường chéo chính của ma trận.
     * @return mảng chứa các phần tử trên đường chéo chính.
     */
    public int[] principalDiagonal() {
        int[] diagonal = new int[size];
        for (int i = 0; i < size; i++) {
            diagonal[i] = data[i][i];
        }
        return diagonal;
    }

    /**
     * Lấy ra các phần tử trên đường chéo phụ của ma trận.
     * @return mảng chứa các phần tử trên đường chéo phụ.
     */
    public int[] secondaryDiagonal() {
        int[] diagonal = new int[size];
        for (int i = 0; i < size; i++) {
            diagonal[i] = data[i][size - 1 - i];
        }
        return diagonal;
    }
    
    /**
     * Hàm hỗ trợ kiểm tra một số có phải là số nguyên tố hay không.
     * @param n Số cần kiểm tra.
     * @return true nếu n là số nguyên tố, false nếu ngược lại.
     */
    private boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * Phương thức lấy ra các số là số nguyên tố trong ma trận.
     * @return mảng chứa các số nguyên tố trong ma trận.
     */
    public int[] primes() {
        ArrayList<Integer> primeList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (isPrime(data[i][j])) {
                    primeList.add(data[i][j]);
                }
            }
        }
        // Chuyển ArrayList<Integer> thành mảng int[]
        int[] result = new int[primeList.size()];
        for (int i = 0; i < primeList.size(); i++) {
            result[i] = primeList.get(i);
        }
        return result;
    }

    /**
     * Sắp xếp các phần tử của ma trận theo thứ tự giảm dần.
     * @return ma trận mới có các phần tử được sắp xếp giảm dần.
     */
    public MySquareMatrix getSortedMatrix() {
        // 1. Chuyển ma trận 2D thành mảng 1D
        int[] flatArray = new int[size * size];
        int index = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                flatArray[index++] = data[i][j];
            }
        }

        // 2. Sắp xếp mảng 1D giảm dần
        Arrays.sort(flatArray);
        // Đảo ngược mảng để có thứ tự giảm dần
        for (int i = 0; i < flatArray.length / 2; i++) {
            int temp = flatArray[i];
            flatArray[i] = flatArray[flatArray.length - 1 - i];
            flatArray[flatArray.length - 1 - i] = temp;
        }


        // 3. Chuyển mảng 1D đã sắp xếp trở lại ma trận 2D
        int[][] sortedData = new int[size][size];
        index = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sortedData[i][j] = flatArray[index++];
            }
        }
        
        return new MySquareMatrix(sortedData);
    }

    /**
     * Lấy giá trị phần tử ở vị trí (row, col).
     * @param row
     * @param col
     * @return giá trị tại data[row][col].
     */
    public int get(int row, int col) {
        if (row >= 0 && row < size && col >= 0 && col < size) {
            return data[row][col];
        }
        return -1; // Hoặc ném ra một ngoại lệ
    }

    /**
     * Sửa giá trị phần tử ở vị trí (row, col) thành value.
     * @param row
     * @param col
     * @param value
     */
    public void set(int row, int col, int value) {
        if (row >= 0 && row < size && col >= 0 && col < size) {
            data[row][col] = value;
        }
    }

    /**
     * Cộng ma trận hiện tại với một ma trận khác.
     * @param that Ma trận cần cộng.
     * @return ma trận tổng. Trả về null nếu hai ma trận không cùng kích thước.
     */
    public MySquareMatrix add(MySquareMatrix that) {
        if (this.size != that.size) {
            return null; // Không thể cộng hai ma trận khác kích thước
        }
        int[][] resultData = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                resultData[i][j] = this.data[i][j] + that.data[i][j];
            }
        }
        return new MySquareMatrix(resultData);
    }

    /**
     * Trừ ma trận hiện tại cho một ma trận khác.
     * @param that Ma trận trừ.
     * @return ma trận hiệu. Trả về null nếu hai ma trận không cùng kích thước.
     */
    public MySquareMatrix minus(MySquareMatrix that) {
        if (this.size != that.size) {
            return null;
        }
        int[][] resultData = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                resultData[i][j] = this.data[i][j] - that.data[i][j];
            }
        }
        return new MySquareMatrix(resultData);
    }

    /**
     * Nhân ma trận hiện tại với một ma trận khác.
     * @param that Ma trận cần nhân.
     * @return ma trận tích. Trả về null nếu hai ma trận không cùng kích thước.
     */
    public MySquareMatrix multiply(MySquareMatrix that) {
        if (this.size != that.size) {
            return null;
        }
        int[][] resultData = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                resultData[i][j] = 0;
                for (int k = 0; k < size; k++) {
                    resultData[i][j] += this.data[i][k] * that.data[k][j];
                }
            }
        }
        return new MySquareMatrix(resultData);
    }

    /**
     * Nhân ma trận với một số vô hướng.
     * @param value Số vô hướng.
     * @return ma trận mới sau khi nhân.
     */
    public MySquareMatrix scaled(int value) {
        int[][] resultData = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                resultData[i][j] = this.data[i][j] * value;
            }
        }
        return new MySquareMatrix(resultData);
    }

    /**
     * Phương thức lấy ma trận chuyển vị.
     * @return ma trận chuyển vị.
     */
    public MySquareMatrix transpose() {
        int[][] resultData = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                resultData[i][j] = this.data[j][i];
            }
        }
        return new MySquareMatrix(resultData);
    }

    /**
     * Mô tả ma trận theo định dạng biểu diễn ma trận.
     * @return một chuỗi mô tả ma trận.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sb.append(data[i][j]).append("\t");
            }
            // Xóa tab cuối cùng và thêm dòng mới
            sb.setLength(sb.length() - 1); 
            if (i < size -1) {
                 sb.append("\n");
            }
        }
        return sb.toString();
    }
}