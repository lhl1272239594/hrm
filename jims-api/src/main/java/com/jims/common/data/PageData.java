package com.jims.common.data;

import java.io.Serializable;
import java.util.List;

/**
 *
 */

public class PageData<T> implements Serializable{
    private List rows;

    private Long total;

    public PageData() {

    }

    public List getRows() {
        return rows;
    }
    public void setRows(List rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

}
