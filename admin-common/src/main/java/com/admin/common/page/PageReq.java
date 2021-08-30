package com.admin.common.page;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * 分页请求
 *
 */
public class PageReq implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 页号
     */
    @Min(value = 1, message = "page必须为整数并且大于0")
    @Max(value = 99999999, message = "page不能大于99999999")
    @ApiModelProperty(value = "页码(默认为1)", dataType = "Integer",example = "1")
    private Integer page = 1;
    /**
     * 每页数
     */
    @Min(value = 1, message = "itemcount必须为整数并且大于0")
    @Max(value = 99999999, message = "itemcount不能大于99999")
    @ApiModelProperty(value = "每页最大记录数(默认为10)", dataType = "Integer",example = "10")
    private Integer size = 10;

    public PageReq() {

    }
    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
    public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
}
