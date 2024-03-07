package uz.mediasolutions.mdeliveryservice.service.abs;

import uz.mediasolutions.mdeliveryservice.entity.User;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.SignInDTO;
import uz.mediasolutions.mdeliveryservice.payload.TokenDTO;

public interface AuthService {

    ApiResult<TokenDTO> signIn(SignInDTO signInDTO);

    TokenDTO generateToken(User user);

    User checkUsernameAndPasswordAndEtcAndSetAuthenticationOrThrow(String username, String password);


}
