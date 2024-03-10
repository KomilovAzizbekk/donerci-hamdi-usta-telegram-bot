package uz.mediasolutions.mdeliveryservice.controller.abs;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.mediasolutions.mdeliveryservice.utills.constants.Rest;

@RequestMapping(FileImageController.IMAGE)
public interface FileImageController {

    String IMAGE = Rest.BASE_PATH + "images/";

    String GET = "{imageName}";

    String UPLOAD = "upload";

    @GetMapping(GET)
    ResponseEntity<?> get(@PathVariable String imageName);

    @PostMapping(UPLOAD)
    ResponseEntity<?> upload(@RequestParam("file") MultipartFile file);
}
