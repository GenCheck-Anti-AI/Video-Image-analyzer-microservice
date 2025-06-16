package VideoImageAnalyzer.VideoImageAnalyzer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnalysisResponse {
    private String prediction;
    private double probability;

    public String getPrediction() {
        return prediction;
    }

    public double getProbability() {
        return probability;
    }

    // Getters and setters
}

