package VideoImageAnalyzer.VideoImageAnalyzer.service;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.imgproc.Imgproc;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VideoFrameUtils {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME); // Load OpenCV native
    }

    public static List<BufferedImage> extractFrames(MultipartFile videoMultiFile) throws IOException {
        File videoFile=convertMultiPartToFile(videoMultiFile);
        List<BufferedImage> frames = new ArrayList<>();
        VideoCapture capture = new VideoCapture(videoFile.getAbsolutePath());

        if (!capture.isOpened()) {
            System.err.println("Failed to open video file: " + videoFile.getAbsolutePath());
            return frames;
        }

        Mat matFrame = new Mat();
        int frameCount = 0;

        while (capture.read(matFrame)) {
            // Extract every 30th frame for sampling
            if (frameCount % 30 == 0) {
                Mat rgb = new Mat();
                Imgproc.cvtColor(matFrame, rgb, Imgproc.COLOR_BGR2RGB);

                BufferedImage image = matToBufferedImage(rgb);
                if (image != null) {
                    frames.add(image);
                }
            }
            frameCount++;
        }

        capture.release();
        return frames;
    }

    public static File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = File.createTempFile("upload-", file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private static BufferedImage matToBufferedImage(Mat mat) {
        int width = mat.width();
        int height = mat.height();
        int channels = mat.channels();
        byte[] sourcePixels = new byte[width * height * channels];
        mat.get(0, 0, sourcePixels);

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        image.getRaster().setDataElements(0, 0, width, height, sourcePixels);
        return image;
    }
}
