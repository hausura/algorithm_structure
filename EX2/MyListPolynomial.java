package hus.oop.integration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyListPolynomial extends MyAbstractPolynomial {
    private List<Double> coefficients;

    /**
     * Khởi tạo dữ liệu mặc định.
     */
    public MyListPolynomial() {
        this.coefficients = new ArrayList<>();
    }

    /**
     * Hàm dựng riêng tư, hữu ích để tạo đa thức mới từ các phép toán.
     */
    private MyListPolynomial(List<Double> coefficients) {
        this.coefficients = new ArrayList<>(coefficients);
    }

    @Override
    public double coefficient(int index) {
        if (index < 0 || index >= this.coefficients.size()) {
            return 0.0;
        }
        return this.coefficients.get(index);
    }

    @Override
    public double[] getCoefficients() { // Đổi tên để khớp với lớp cha
        double[] coeffsArray = new double[this.coefficients.size()];
        for (int i = 0; i < this.coefficients.size(); i++) {
            coeffsArray[i] = this.coefficients.get(i);
        }
        return coeffsArray;
    }

    @Override
    public MyListPolynomial append(double coefficient) {
        this.coefficients.add(coefficient);
        return this;
    }

    @Override
    public MyListPolynomial set(double coefficient, int index) {
        if (index < 0) {
            return this; // Hoặc ném ngoại lệ
        }
        // Thêm các hệ số 0 cho đến khi list đủ dài
        while (index >= this.coefficients.size()) {
            this.coefficients.add(0.0);
        }
        this.coefficients.set(index, coefficient);
        return this;
    }

    @Override
    public int degree() {
        for (int i = this.coefficients.size() - 1; i >= 0; i--) {
            if (this.coefficients.get(i) != 0.0) {
                return i;
            }
        }
        return 0; // Đa thức hằng số 0
    }

    @Override
    public double evaluate(double x) {
        double result = 0;
        // Sử dụng phương pháp Horner
        for (int i = degree(); i >= 0; i--) {
            result = result * x + coefficient(i);
        }
        return result;
    }

    @Override
    public MyPolynomial derivative() {
        double[] derivCoeffs = differentiate(); // Gọi phương thức từ lớp cha
        List<Double> newList = new ArrayList<>();
        for (double coeff : derivCoeffs) {
            newList.add(coeff);
        }
        return new MyListPolynomial(newList);
    }

    @Override
    public MyPolynomial plus(MyPolynomial right) {
        int maxDegree = Math.max(this.degree(), right.degree());
        List<Double> resultCoeffs = new ArrayList<>();
        for (int i = 0; i <= maxDegree; i++) {
            resultCoeffs.add(this.coefficient(i) + right.coefficient(i));
        }
        return new MyListPolynomial(resultCoeffs);
    }

    @Override
    public MyPolynomial minus(MyPolynomial right) {
        int maxDegree = Math.max(this.degree(), right.degree());
        List<Double> resultCoeffs = new ArrayList<>();
        for (int i = 0; i <= maxDegree; i++) {
            resultCoeffs.add(this.coefficient(i) - right.coefficient(i));
        }
        return new MyListPolynomial(resultCoeffs);
    }

    @Override
    public MyPolynomial multiply(MyPolynomial right) {
        int newDegree = this.degree() + right.degree();
        List<Double> resultCoeffs = new ArrayList<>(Collections.nCopies(newDegree + 1, 0.0));
        
        for (int i = 0; i <= this.degree(); i++) {
            for (int j = 0; j <= right.degree(); j++) {
                double newValue = resultCoeffs.get(i + j) + this.coefficient(i) * right.coefficient(j);
                resultCoeffs.set(i + j, newValue);
            }
        }
        return new MyListPolynomial(resultCoeffs);
    }

    // Ghi đè các phương thức chưa được triển khai từ MyPolynomial (nếu có)
    @Override
    public MyListPolynomial add(double coefficient, int index) {
        if (index < 0) {
            return this;
        }
        while (index >= this.coefficients.size()) {
            this.coefficients.add(0.0);
        }
        double newValue = this.coefficients.get(index) + coefficient;
        this.coefficients.set(index, newValue);
        return this;
    }

    @Override
    public double[] coefficients() {
        return getCoefficients();
    }
}