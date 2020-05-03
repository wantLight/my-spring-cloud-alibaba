package ribbonconfiguration;

import com.itmuch.contentcenter.configuration.NacosSameClusterWeightedRule;
import com.itmuch.contentcenter.configuration.NacosWeightedRule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// 启动类所在包以外 防止父子上下文重叠
@Configuration
public class RibbonConfiguration {
    @Bean
    public IRule ribbonRule() {
        return new NacosWeightedRule();
    }
}
