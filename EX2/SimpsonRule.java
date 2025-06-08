package hus.oop.integration;

public class SimpsonRule implements MyIntegrator {
    private double precision;
    private int maxIterations;

    public SimpsonRule(double precision, int maxIterations) {
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

        int n = 2; // Bắt đầu với n0 = 2 khoảng
        double currentIntegral = integrate(polynomial, lower, upper, n);

        for (int i = 0; i < this.maxIterations; i++) {
            n *= 2;
            double nextIntegral = integrate(polynomial, lower, upper, n);
            
            // Công thức sai số theo yêu cầu của đề bài
            if (Math.abs(nextIntegral - currentIntegral) / 3.0 < this.precision) {
                return nextIntegral;
            }
            
            currentIntegral = nextIntegral;
        }
        
        // Trả về kết quả tốt nhất nếu vượt quá số lần lặp
        return currentIntegral;
    }

    /**
     * Tính xấp xỉ giá trị tích phân với numOfSubIntervals (số chẵn) khoảng phân hoạch đều.
     * @param polynomial đa thức cần tính tích phân
     * @param lower cận dưới
     * @param upper cận trên
     * @param numOfSubIntervals số khoảng con (phải là số chẵn)
     * @return giá trị xấp xỉ giá trị tích phân.
     */
    private double integrate(MyPolynomial polynomial, double lower, double upper, int numOfSubIntervals) {
        double h = (upper - lower) / numOfSubIntervals;
        double sum = polynomial.evaluate(lower) + polynomial.evaluate(upper);

        // Cộng các số hạng có hệ số 4
        for (int i = 1; i < numOfSubIntervals; i += 2) {
            sum += 4 * polynomial.evaluate(lower + i * h);
        }

        // Cộng các số hạng có hệ số 2
        for (int i = 2; i < numOfSubIntervals - 1; i += 2) {
            sum += 2 * polynomial.evaluate(lower + i * h);
        }

        return sum * h / 3.0;
    }
}