package hus.oop.integration;

public class MidpointRule implements MyIntegrator {
    private final double precision;
    private final int maxIterations;

    /**
     * Khởi tạo phương pháp tích phân hình thang điểm giữa.
     * @param precision Độ chính xác mong muốn.
     * @param maxIterations Số lần lặp (chia đôi) tối đa.
     */
    public MidpointRule(double precision, int maxIterations) {
        this.precision = precision;
        this.maxIterations = maxIterations;
    }

    /**
     * Tính xấp xỉ giá trị tích phân bằng phương pháp lặp.
     * Giá trị xấp xỉ được chấp nhận nếu phép tính đạt độ chính xác đã cho,
     * hoặc số vòng lặp vượt quá ngưỡng quy định.
     * @param polynomial Đa thức cần tính tích phân.
     * @param lower Cận dưới.
     * @param upper Cận trên.
     * @return Giá trị xấp xỉ của tích phân.
     */
    @Override
    public double integrate(MyPolynomial polynomial, double lower, double upper) {
        int n = 1; // Bắt đầu với 1 khoảng chia
        double previousIntegral = integrate(polynomial, lower, upper, n);

        for (int i = 0; i < this.maxIterations; i++) {
            n *= 2; // Gấp đôi số khoảng chia
            double currentIntegral = integrate(polynomial, lower, upper, n);
            
            // Kiểm tra điều kiện hội tụ
            if (Math.abs(currentIntegral - previousIntegral) / 3.0 < this.precision) {
                return currentIntegral;
            }
            
            previousIntegral = currentIntegral;
        }

        // Trả về kết quả cuối cùng nếu không đạt được độ chính xác sau maxIterations
        return previousIntegral;
    }

    /**
     * Tính xấp xỉ giá trị tích phân với một số lượng khoảng chia cho trước.
     * @param polynomial Đa thức cần tính tích phân.
     * @param lower Cận dưới.
     * @param upper Cận trên.
     * @param numOfSubIntervals Số lượng khoảng con.
     * @return Giá trị xấp xỉ của tích phân.
     */
    private double integrate(MyPolynomial polynomial, double lower, double upper, int numOfSubIntervals) {
        if (numOfSubIntervals <= 0) {
            return 0;
        }
        
        double deltaX = (upper - lower) / numOfSubIntervals;
        double totalSum = 0;

        for (int i = 0; i < numOfSubIntervals; i++) {
            // Tìm điểm giữa của khoảng con thứ i
            double midpoint = lower + (i + 0.5) * deltaX;
            // Tính giá trị của đa thức tại điểm giữa và cộng vào tổng
            totalSum += polynomial.evaluate(midpoint);
        }

        return totalSum * deltaX;
    }
}