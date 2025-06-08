package hus.oop.integration;

public interface MyPolynomial {
    /**
     * Lấy ra hệ số của đa thức tại phần tử index.
     * @return hệ số của đa thức tại phần tử index.
     */
    double coefficient(int index);

    /**
     * Lấy mảng các hệ số của đa thức.
     * @return mảng các hệ số của đa thức.
     */
    double[] coefficients();

    /**
     * Lấy bậc của đa thức.
     * @return bậc của đa thức.
     */
    int degree();

    /**
     * Thay đổi hệ số của đa thức tại phần tử index.
     * @param coefficient
     * @param index
     * @return đa thức hiện tại.
     */
    MyPolynomial set(double coefficient, int index);

    /**
     * Thêm một phần tử có hệ số coefficient vào cuối đa thức.
     * @param coefficient
     * @return đa thức hiện tại.
     */
    MyPolynomial append(double coefficient);

    /**
     * Thêm một phần tử có hệ số coefficient vào vị trí index.
     * @param coefficient
     * @param index
     * @return đa thức hiện tại.
     */
    MyPolynomial add(double coefficient, int index);

    /**
     * Tính giá trị của đa thức khi biết giá trị của x.
     * @return giá trị của đa thức.
     */
    double evaluate(double x);

    /**
     * Lấy đạo hàm của đa thức.
     * @return Đa thức là đa thức đạo hàm của đa thức ban đầu.
     */
    MyPolynomial derivative();

    /**
     * Cộng đa thức hiện tại với đa thức khác.
     * @param right
     * @return đa thức mới là tổng của 2 đa thức.
     */
    MyPolynomial plus(MyPolynomial right);

    /**
     * Trừ đa thức hiện tại với đa thức khác.
     * @param right
     * @return đa thức mới là hiệu của đa thức hiện tại cho đa thức truyền vào.
     */
    MyPolynomial minus(MyPolynomial right);

    /**
     * Nhân đa thức hiện tại với đa thức khác.
     * @param right
     * @return đa thức mới là tích của 2 đa thức.
     */
    MyPolynomial multiply(MyPolynomial right);
}
