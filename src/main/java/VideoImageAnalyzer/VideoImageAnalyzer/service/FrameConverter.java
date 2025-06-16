package VideoImageAnalyzer.VideoImageAnalyzer.service;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class FrameConverter {

    public static MultipartFile toMultipartFile(BufferedImage image) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", os);
        os.flush();
        return new MockMultipartFile("file", "frame.jpg", "image/jpeg", os.toByteArray());
    }
}
