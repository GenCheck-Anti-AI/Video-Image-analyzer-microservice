package VideoImageAnalyzer.VideoImageAnalyzer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class FrameResult {
    private int totalFrames;
    private int aiFrames;
    private double averageProbability;
    private String finalPrediction;

    public void setTotalFrames(int totalFrames) {
        this.totalFrames = totalFrames;
    }

    public void setAiFrames(int aiFrames) {
        this.aiFrames = aiFrames;
    }

    public void setAverageProbability(double averageProbability) {
        this.averageProbability = averageProbability;
    }

    public void setFinalPrediction(String finalPrediction) {
        this.finalPrediction = finalPrediction;
    }

    // Getters and setters
}
