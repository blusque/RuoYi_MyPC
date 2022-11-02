package com.ruoyi.market.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class MarketReport {
    private List<MarketReportDetail> marketReportDetailList;

    @JsonFormat(pattern = "yyyy-MM")
    private Date month;

    @JsonFormat(pattern = "yyyy")
    private Date year;
}
