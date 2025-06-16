package VideoImageAnalyzer.VideoImageAnalyzer.controller;

import VideoImageAnalyzer.VideoImageAnalyzer.dto.AnalysisResponse;
import VideoImageAnalyzer.VideoImageAnalyzer.dto.FrameResult;
import VideoImageAnalyzer.VideoImageAnalyzer.service.ImageAnalysisService;
import VideoImageAnalyzer.VideoImageAnalyzer.service.VideoAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/analyze")
public class AnalysisController {

    @Autowired
    private ImageAnalysisService imageService;

    @Autowired
    private VideoAnalysisService videoService;

    @PostMapping("/image")
    public ResponseEntity<AnalysisResponse> analyzeImage(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(imageService.analyzeImage(file));
    }

    @PostMapping("/video")
    public ResponseEntity<FrameResult> analyzeVideo(@RequestParam("file") MultipartFile video) throws IOException {
        return ResponseEntity.ok(videoService.analyzeVideo(video));
    }
}

