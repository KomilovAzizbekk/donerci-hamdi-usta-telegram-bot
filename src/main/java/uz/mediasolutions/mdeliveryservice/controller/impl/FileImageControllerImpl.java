package uz.mediasolutions.mdeliveryservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import uz.mediasolutions.mdeliveryservice.controller.abs.FileImageController;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.service.abs.FileImageService;

@RestController
@RequiredArgsConstructor
public class FileImageControllerImpl implements FileImageController {

    private final FileImageService fileImageService;

    @Override
    public ApiResult<?> upload(MultipartFile file) {
        return fileImageService.upload(file);
    }
}
