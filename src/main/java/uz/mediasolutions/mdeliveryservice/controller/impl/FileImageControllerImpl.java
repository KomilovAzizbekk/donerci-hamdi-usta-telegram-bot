package uz.mediasolutions.mdeliveryservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import uz.mediasolutions.mdeliveryservice.controller.abs.FileImageController;
import uz.mediasolutions.mdeliveryservice.service.abs.FileImageService;

@RestController
@RequiredArgsConstructor
public class FileImageControllerImpl implements FileImageController {

    private final FileImageService fileImageService;

    @Override
    public ResponseEntity<?> get(String imageName) {
        return fileImageService.get(imageName);
    }

    @Override
    public ResponseEntity<?> upload(MultipartFile file) {
        return fileImageService.upload(file);
    }
}
