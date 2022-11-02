package com.ruoyi.market.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class FromTo {
    private Integer from;

    private Integer to;

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getFrom() {
        return from;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public Integer getTo() {
        return to;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
        .append("from", getFrom())
        .append("to", getTo())
        .toString();
    }
}
