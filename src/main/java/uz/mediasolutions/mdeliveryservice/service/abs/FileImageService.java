package uz.mediasolutions.mdeliveryservice.service.abs;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;

public interface FileImageService {

    ApiResult<?> upload(MultipartFile file);

    ResponseEntity<?> get(String imageName);

}
