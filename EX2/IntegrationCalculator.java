package hus.oop.integration;

public class IntegrationCalculator {
    private MyIntegrator integrator;
    private MyPolynomial poly;

    /**
     * Hàm dựng, khởi tạo đa thức cần tính tích phân.
     * Phương pháp tích phân (integrator) cần được thiết lập sau bằng setIntegrator().
     * @param poly Đa thức để tính tích phân.
     */
    public IntegrationCalculator(MyPolynomial poly) {
        this.poly = poly;
        this.integrator = null; // Cần được thiết lập sau
    }

    /**
     * Hàm dựng, khởi tạo phương pháp tính tích phân và đa thức cần tính tích phân.
     * @param integrator Phương pháp tính tích phân.
     * @param poly Đa thức để tính tích phân.
     */
    public IntegrationCalculator(MyIntegrator integrator, MyPolynomial poly) {
        this.integrator = integrator;
        this.poly = poly;
    }

    /**
     * Thiết lập hoặc thay đổi đa thức.
     * @param poly Đa thức mới.
     */
    public void setPoly(MyPolynomial poly) {
        this.poly = poly;
    }

    /**
     * Thiết lập hoặc thay đổi phương pháp tính tích phân.
     * @param integrator Phương pháp tích phân mới.
     */
    public void setIntegrator(MyIntegrator integrator) {
        this.integrator = integrator;
    }

    /**
     * Tính tích phân xác định của đa thức từ cận dưới đến cận trên.
     * @param lower Cận dưới.
     * @param upper Cận trên.
     * @return Kết quả tích phân.
     */
    public double integrate(double lower, double upper) {
        // Kiểm tra xem phương pháp tích phân và đa thức đã được khởi tạo chưa
        if (this.integrator == null || this.poly == null) {
            throw new IllegalStateException("Cả integrator và polynomial đều phải được thiết lập trước khi tính toán.");
        }
        // Ủy quyền việc tính tích phân cho đối tượng integrator
        return this.integrator.integrate(this.poly, lower, upper);
    }
}