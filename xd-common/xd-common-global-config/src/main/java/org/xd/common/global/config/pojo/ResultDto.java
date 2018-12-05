package org.xd.common.global.config.pojo;

import java.io.Serializable;

import org.springframework.util.Assert;

import lombok.Data;

@Data
public class ResultDto implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 6280489868029266413L;

    /**
     * 业务状态码
     * 不建议直接使用httpstatus状态码
     * <pre>
     *   本系统中业务码从1000开始，默认1000为成功的提示信息
     * </pre>
     */
    private Integer code;

    /**
     * 提示语
     */
    private String msg;

    /**
     * 具体内容 如列表
     */
    private Object data;
    
    public ResultDto() {
        
    }
    
    public ResultDto(String msg) {
        this.msg = msg;
        this.code = 1000;
    }
    
    public ResultDto(Integer code, String msg) {
        
        Assert.notNull(code, "ResultDto code must not be null");
        Assert.hasLength(msg, "ResultDto msg must not be null");
        this.code = code;
        this.msg = msg;
    }

    public ResultDto(Integer code, String msg, Object data) {
        Assert.notNull(code, "ResultDto code must not be null");
        Assert.hasLength(msg, "ResultDto msg must not be null");
        Assert.notNull(data, "ResultDto data must not be null");
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    
}
