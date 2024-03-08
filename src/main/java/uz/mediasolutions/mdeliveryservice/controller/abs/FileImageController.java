package uz.mediasolutions.mdeliveryservice.controller.abs;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.utills.constants.Rest;

@RequestMapping(FileImageController.IMAGE)
public interface FileImageController {

    String IMAGE = Rest.BASE_PATH + "image/";

    String GET = "get/{imageName}";

    String UPLOAD = "upload";

    @GetMapping(GET)
    ResponseEntity<?> get(@PathVariable String imageName);

    @PostMapping(UPLOAD)
    ApiResult<?> upload(@RequestParam("file") MultipartFile file);
}
