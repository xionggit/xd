package org.xd.common.global.config.validation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@Configuration
public class ValidationConfig {

    /**
     * TODO 写一个使用说明
     *
     * 参考链接http://blog.csdn.net/u010454030/article/details/53009327
     * <p>例如
     * <pre>
     * @Valid 对po实体类进行校验
     * @Length(max = 200,message="企业名称不能超过{max}字") //提示信息为企业名称不能超过200字
     * 从国际化配置文件取提示信息，配置文件写法 company.name=企业名称不能超过{max}字
     * @Length(max = 200,message="{company.name}")
     * </pre>
     * </p>
     * 
     * @AssertFalse 校验false
     * @AssertTrue 校验true
     * @DecimalMax(value=,inclusive=) 小于等于value，inclusive=true,是小于等于
     * @DecimalMin(value=,inclusive=) 与上类似
     * @Max(value=) 小于等于value
     * @Min(value=) 大于等于value
     * @NotNull 检查Null
     * @Past 检查日期
     * @Pattern(regex=,flag=) 正则
     * @Size(min=, max=)  字符串，集合，map限制大小
     */

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

}
