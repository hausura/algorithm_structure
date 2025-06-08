package hus.oop.integration;

public class TrapezoidRule implements MyIntegrator {
    private double precision;
    private int maxIterations;

    /**
     * Khởi tạo đối tượng SimpsonRule với độ chính xác và số lần lặp tối đa.
     * @param precision độ chính xác mong muốn.
     * @param maxIterations số lần lặp tối đa cho phép.
     */
    public TrapezoidRule(double precision, int maxIterations) {
        this.precision = precision;
        this.maxIterations = maxIterations;
    }

    /**
     * Tính xấp xỉ giá trị tích phân. Giá trị xấp xỉ được chấp nhận nếu phép tính đạt độ chính xác đã cho,
     * hoặc có số vòng vượt quá ngưỡng quy định.
     * Độ chính xác được xác định như sau, chọn n0 tùy ý, sau đó tính I_n với n = n0, 2n0, 4n0, ...
     * Việc tính toán dừng lại khi |I_2n - In|/3 < eps (precision), hoặc số lần chia đôi vượt quá ngưỡng quy định (maxIterations).
     * @param polynomial đa thức cần tính tích phân
     * @param lower cận dưới
     * @param upper cận trên
     * @return giá trị xấp xỉ của tích phân
     */
    @Override
    public double integrate(MyPolynomial polynomial, double lower, double upper) {
        if (lower == upper) {
            return 0.0;
        }

        int n = 1; // Bắt đầu với n0 = 1 khoảng
        double currentIntegral = integrate(polynomial, lower, upper, n);

        for (int i = 0; i < this.maxIterations; i++) {
            n *= 2;
            double nextIntegral = integrate(polynomial, lower, upper, n);
            
            // Tiêu chí dừng |I_2n - In|/3 < precision
            if (Math.abs(nextIntegral - currentIntegral) / 3.0 < this.precision) {
                 return nextIntegral;
            }
            
            currentIntegral = nextIntegral;
        }
        
        // Trả về kết quả tốt nhất nếu vượt quá số lần lặp
        return currentIntegral;
    }

    /**
     * Tính xấp xỉ giá trị tích phân với numOfSubIntervals khoảng phân hoạch đều sử dụng quy tắc hình thang.
     * @param polynomial đa thức cần tính tích phân
     * @param lower cận dưới
     * @param upper cận trên
     * @param numOfSubIntervals số khoảng con
     * @return giá trị xấp xỉ giá trị tích phân.
     */
    private double integrate(MyPolynomial polynomial, double lower, double upper, int numOfSubIntervals) {
        double h = (upper - lower) / numOfSubIntervals;
        double sum = (polynomial.evaluate(lower) + polynomial.evaluate(upper)) / 2.0; // (f(a) + f(b))/2

        for (int i = 1; i < numOfSubIntervals; i++) {
            double xi = lower + i * h;
            sum += polynomial.evaluate(xi); // Cộng các điểm ở giữa
        }

        return sum * h; // Nhân với độ rộng h
    }
}