package uz.mediasolutions.mdeliveryservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.mediasolutions.mdeliveryservice.controller.abs.AuthController;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.SignInDTO;
import uz.mediasolutions.mdeliveryservice.payload.TokenDTO;
import uz.mediasolutions.mdeliveryservice.service.abs.AuthService;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    @Override
    public ApiResult<TokenDTO> signIn(SignInDTO dto) {
        return authService.signIn(dto);
    }
}
