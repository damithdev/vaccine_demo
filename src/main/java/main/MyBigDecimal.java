package main;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MyBigDecimal extends BigDecimal {

//    Big Decimal is used to address the Floating point precision problem
    private final BigDecimal value;
    private MyBigDecimal(BigDecimal value){
        super(value.toPlainString());
        this.value = value;
    }

//    Rounding mode half_even is used which is considered unbiased

    public MyBigDecimal(String val) {
        this(new BigDecimal(val).setScale(2, RoundingMode.HALF_EVEN));
    }

    public MyBigDecimal(long val) {
        this(new BigDecimal(val).setScale(2, RoundingMode.HALF_EVEN));
    }

    @Override
    public MyBigDecimal abs() {
        return new MyBigDecimal(this.value.abs());
    }
}
