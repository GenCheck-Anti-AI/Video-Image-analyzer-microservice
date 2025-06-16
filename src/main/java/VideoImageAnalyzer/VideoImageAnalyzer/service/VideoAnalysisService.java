package VideoImageAnalyzer.VideoImageAnalyzer.service;

import VideoImageAnalyzer.VideoImageAnalyzer.dto.AnalysisResponse;
import VideoImageAnalyzer.VideoImageAnalyzer.dto.FrameResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

@Service
public class VideoAnalysisService {

    @Autowired
    private ImageAnalysisService imageService;

    public FrameResult analyzeVideo(MultipartFile videoFile) throws IOException {
        int aiFrames = 0;
        int totalFrames = 0;
        double sumProbability = 0.0;

        // Extract frames (e.g., using OpenCV or FFmpeg)
        List<BufferedImage> frames = VideoFrameUtils.extractFrames(videoFile); // you'll implement this

        for (BufferedImage frame : frames) {
            MultipartFile frameFile = FrameConverter.toMultipartFile(frame); // utility to convert
            try {
                AnalysisResponse res = imageService.analyzeImage(frameFile);
                totalFrames++;
                double prob = res.getPrediction().equalsIgnoreCase("AI-generated")
                        ? res.getProbability()
                        : 1 - res.getProbability();
                sumProbability += prob;
                if (res.getPrediction().equalsIgnoreCase("AI-generated")) aiFrames++;
            } catch (Exception e) {
                // log and skip
            }
        }

        double average = totalFrames == 0 ? 0 : sumProbability / totalFrames;

        FrameResult result = new FrameResult();
        result.setTotalFrames(totalFrames);
        result.setAiFrames(aiFrames);
        result.setAverageProbability(Math.round(average * 10000.0) / 10000.0);
        result.setFinalPrediction(average > 0.5 ? "AI-generated" : "Human-generated");
        return result;
    }
}

