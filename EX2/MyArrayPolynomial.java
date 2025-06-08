package hus.oop.integration;

import java.util.Arrays;

public class MyArrayPolynomial extends MyAbstractPolynomial {
    private static final int DEFAULT_CAPACITY = 8;
    private double[] coefficents;
    private int size; // Số lượng hệ số, cũng là bậc của đa thức + 1

    /**
     * Khởi tạo dữ liệu mặc định.
     */
    public MyArrayPolynomial() {
        this.coefficents = new double[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * Hàm dựng riêng tư để tạo đa thức từ một mảng hệ số có sẵn.
     */
    private MyArrayPolynomial(double[] coefficents) {
        this.coefficents = Arrays.copyOf(coefficents, coefficents.length);
        this.size = coefficents.length;
    }

    @Override
    public double coefficient(int index) {
        if (index < 0 || index >= this.size) {
            return 0.0;
        }
        return this.coefficents[index];
    }

    @Override
    public double[] getCoefficients() { // Đổi tên từ coefficients() để khớp với abstract class
        return Arrays.copyOf(this.coefficents, this.size);
    }

    @Override
    public MyArrayPolynomial append(double coefficient) {
        if (this.size >= this.coefficents.length) {
            allocateMore();
        }
        this.coefficents[this.size] = coefficient;
        this.size++;
        return this;
    }

    @Override
    public MyArrayPolynomial set(double coefficient, int index) {
        if (index < 0) {
            return this; // Hoặc ném ra một ngoại lệ
        }
        while (index >= this.size) {
            this.append(0.0);
        }
        this.coefficents[index] = coefficient;
        return this;
    }

    @Override
    public int degree() {
        // Bậc của đa thức là vị trí của hệ số khác 0 cuối cùng.
        for (int i = this.size - 1; i >= 0; i--) {
            if (this.coefficents[i] != 0.0) {
                return i;
            }
        }
        return 0; // Đa thức hằng số 0
    }

    @Override
    public double evaluate(double x) {
        double result = 0;
        // Sử dụng phương pháp Horner để tính toán hiệu quả
        for (int i = this.size - 1; i >= 0; i--) {
            result = result * x + this.coefficents[i];
        }
        return result;
    }

    @Override
    public MyPolynomial derivative() {
        // Sử dụng phương thức differentiate() đã được cài đặt ở lớp cha
        double[] derivCoeffs = differentiate();
        return new MyArrayPolynomial(derivCoeffs);
    }

    @Override
    public MyPolynomial plus(MyPolynomial right) {
        int maxDegree = Math.max(this.degree(), right.degree());
        double[] resultCoeffs = new double[maxDegree + 1];

        for (int i = 0; i <= maxDegree; i++) {
            resultCoeffs[i] = this.coefficient(i) + right.coefficient(i);
        }

        return new MyArrayPolynomial(resultCoeffs);
    }

    @Override
    public MyPolynomial minus(MyPolynomial right) {
        int maxDegree = Math.max(this.degree(), right.degree());
        double[] resultCoeffs = new double[maxDegree + 1];

        for (int i = 0; i <= maxDegree; i++) {
            resultCoeffs[i] = this.coefficient(i) - right.coefficient(i);
        }

        return new MyArrayPolynomial(resultCoeffs);
    }

    @Override
    public MyPolynomial multiply(MyPolynomial right) {
        int newDegree = this.degree() + right.degree();
        double[] resultCoeffs = new double[newDegree + 1];

        for (int i = 0; i <= this.degree(); i++) {
            for (int j = 0; j <= right.degree(); j++) {
                resultCoeffs[i + j] += this.coefficient(i) * right.coefficient(j);
            }
        }

        return new MyArrayPolynomial(resultCoeffs);
    }

    /**
     * Tăng kích thước mảng lên gấp đôi để lưu đa thức khi cần thiết.
     */
    private void allocateMore() {
        int newCapacity = this.coefficents.length * 2;
        this.coefficents = Arrays.copyOf(this.coefficents, newCapacity);
    }

    // Ghi đè các phương thức chưa được triển khai từ MyPolynomial (nếu có)
    // Giả sử add(coefficient, index) có nghĩa là cộng vào hệ số hiện tại.
    // Nếu nó có nghĩa là chèn, logic sẽ khác.
    public MyArrayPolynomial add(double coefficient, int index) {
        if (index < 0) {
            return this;
        }
        while (index >= this.size) {
            this.append(0.0);
        }
        this.coefficents[index] += coefficient;
        return this;
    }

    // Ghi đè lại coefficients() từ MyPolynomial nếu cần thiết,
    // hoặc đổi tên trong MyAbstractPolynomial thành getCoefficients() để tránh xung đột
    @Override
    public double[] coefficients() {
        return getCoefficients();
    }
}