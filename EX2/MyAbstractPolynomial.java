package hus.oop.integration;

public abstract class MyAbstractPolynomial implements MyPolynomial {
    // Giả định rằng interface MyPolynomial hoặc lớp này sẽ cung cấp phương thức
    // getCoefficients() để lấy các hệ số của đa thức.
    // public abstract double[] getCoefficients();

    /**
     * Mô tả đa thức theo định dạng [a0 + a1x + a2x^2 + ... + anx^n]
     * @return String mô tả về đa thức.
     */
    @Override
    public String toString() {
        double[] coeffs = getCoefficients();
        if (coeffs.length == 0) {
            return "[]";
        }
        
        StringBuilder sb = new StringBuilder();
        boolean firstTerm = true;

        for (int i = 0; i < coeffs.length; i++) {
            if (coeffs[i] == 0.0) {
                continue;
            }

            if (!firstTerm) {
                if (coeffs[i] > 0) {
                    sb.append(" + ");
                } else {
                    sb.append(" - ");
                }
            } else {
                if (coeffs[i] < 0) {
                    sb.append("-");
                }
            }

            double absCoeff = Math.abs(coeffs[i]);
            if (absCoeff != 1.0 || i == 0) {
                sb.append(absCoeff);
            }

            if (i == 1) {
                sb.append("x");
            } else if (i > 1) {
                sb.append("x^").append(i);
            }
            
            firstTerm = false;
        }

        if (sb.length() == 0) {
            return "[0.0]";
        }

        return "[" + sb.toString() + "]";
    }

    /**
     * Lấy đạo hàm đa thức.
     * @return mảng các phần tử là hệ số của đa thức đạo hàm.
     */
    public double[] differentiate() {
        double[] coeffs = getCoefficients();
        if (coeffs.length <= 1) {
            return new double[]{0.0};
        }

        double[] derivCoeffs = new double[coeffs.length - 1];
        for (int i = 1; i < coeffs.length; i++) {
            derivCoeffs[i - 1] = coeffs[i] * i;
        }
        return derivCoeffs;
    }
}