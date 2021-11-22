package com.dcm.application.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;
import org.apache.commons.lang3.StringUtils;

public final class Money implements Serializable {
    private static final long serialVersionUID = 6257367531532365891L;
    private Currency currency;
    private long amount;

    public Money() {
        this.currency = Currency.getInstance("CNY");
        this.amount = 0L;
    }

    public Money(Long cent, Currency currency) {
        this.currency = Currency.getInstance("CNY");
        this.amount = 0L;
        this.amount = cent != null ? cent : 0L;
        if (currency != null) {
            this.currency = currency;
        }

    }

    public Money(Long cent, String currency) {
        this(cent, Currency.getInstance(currency));
    }

    public Money(Long cent) {
        this.currency = Currency.getInstance("CNY");
        this.amount = 0L;
        this.amount = cent != null ? cent : 0L;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Long getAmount() {
        return this.amount;
    }

    public String toYuan() {
        BigDecimal d = new BigDecimal(this.getAmount());
        BigDecimal d100 = new BigDecimal(100);
        return d.divide(d100).toString();
    }

    public static Money createInstance(String yuan) {
        if (StringUtils.isBlank(yuan)) {
            return null;
        } else {
            BigDecimal d = new BigDecimal(yuan);
            BigDecimal d100 = new BigDecimal(100);
            d.multiply(d100);
            return new Money(d.multiply(d100).longValue());
        }
    }

    public static Money createInstance(Long cent) {
        return cent == null ? null : new Money(cent);
    }

    public static int compare(Money src, Money dest) {
        if (!src.currency.equals(dest.getCurrency())) {
            throw new IllegalArgumentException("不同币种不能比较srcCurrency:[" + src.currency + "],destCurrency[" + dest.getCurrency() + "]");
        } else {
            long result = src.amount - dest.amount;
            if (result > 0L) {
                return 1;
            } else {
                return result < 0L ? -1 : 0;
            }
        }
    }

    public boolean gtZero() {
        return compare(this, new Money(0L)) > 0;
    }

    public boolean ltZero() {
        return compare(this, new Money(0L)) < 0;
    }

    public int hashCode() {
        int prime = true;
        int result = 1;
        int result = 31 * result + (new Long(this.amount)).hashCode();
        result = 31 * result + (this.currency == null ? 0 : this.currency.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else {
            Money other = (Money)obj;
            if (this.currency == null) {
                if (other.currency != null) {
                    return false;
                }
            } else if (!this.currency.equals(other.currency)) {
                return false;
            }

            return this.amount == other.amount;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Money{");
        sb.append("currency=").append(this.currency);
        sb.append(", amount=").append(this.amount);
        sb.append('}');
        return sb.toString();
    }

    public static Long getCentOfMoney(Money money) {
        return money == null ? null : money.getAmount();
    }

    public static boolean isGtZero(Money money) {
        return money == null ? false : money.gtZero();
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Money clone() {
        Money money = new Money(0L);
        money.setAmount(this.amount);
        if (this.currency != null) {
            money.setCurrency(Currency.getInstance(this.currency.toString()));
        }

        return money;
    }
}