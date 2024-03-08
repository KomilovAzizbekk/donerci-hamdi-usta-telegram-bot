package uz.mediasolutions.mdeliveryservice.controller.abs;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.utills.constants.Rest;

@RequestMapping(FileImageController.IMAGE)
public interface FileImageController {

    String IMAGE = Rest.BASE_PATH + "image/";

    String UPLOAD = "upload";

    @PostMapping(UPLOAD)
    ApiResult<?> upload(@RequestParam("file") MultipartFile file);
}
