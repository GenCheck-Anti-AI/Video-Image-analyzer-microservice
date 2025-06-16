package VideoImageAnalyzer.VideoImageAnalyzer.service;

import VideoImageAnalyzer.VideoImageAnalyzer.config.ExternalServiceConfig;
import VideoImageAnalyzer.VideoImageAnalyzer.dto.AnalysisResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageAnalysisService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ExternalServiceConfig config;

    public AnalysisResponse analyzeImage(MultipartFile file) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        });

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<AnalysisResponse> response = restTemplate.postForEntity(
                config.getUrl(), request, AnalysisResponse.class
        );

        return response.getBody();
    }
}

