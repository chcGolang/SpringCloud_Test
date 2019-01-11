package com.chc.product_service.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * 数据库连接使用统一配置的方式自动刷新
 * @author chc
 * @create 2019-01-11 16:17
 **/
@Component
public class DatasourceConfig {

    @ConfigurationProperties(
            prefix = "spring.datasource"
    )
    @RefreshScope
    public DataSourceProperties dataSourceProperties(){
        return new DataSourceProperties();
    }
}
