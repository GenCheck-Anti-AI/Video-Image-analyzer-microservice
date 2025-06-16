package VideoImageAnalyzer.VideoImageAnalyzer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "image-analyzer")
public class ExternalServiceConfig {
    private String url;
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
}
