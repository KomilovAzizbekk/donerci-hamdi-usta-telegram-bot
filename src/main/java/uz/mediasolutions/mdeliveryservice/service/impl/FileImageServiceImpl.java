package uz.mediasolutions.mdeliveryservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import uz.mediasolutions.mdeliveryservice.exceptions.RestException;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.service.abs.FileImageService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileImageServiceImpl implements FileImageService {

    @Value("${upload-dir}")
    private String uploadDir;

    public ApiResult<?> upload(MultipartFile file) {
        // Check if the file is empty
        if (file.isEmpty()) {
            throw RestException.restThrow("FILE IS EMPTY", HttpStatus.BAD_REQUEST);
        }

        // Normalize the file name to prevent directory traversal
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        // Copy the file to the target location
        Path targetPath = Path.of(uploadDir).resolve(fileName);
        try {
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UUID uuid = UUID.randomUUID();

        // Build the URL for the saved image
        String imageUrl = "/images/" + uuid + fileName; // Adjust this based on your server configuration

        return ApiResult.success(imageUrl);
    }
}
