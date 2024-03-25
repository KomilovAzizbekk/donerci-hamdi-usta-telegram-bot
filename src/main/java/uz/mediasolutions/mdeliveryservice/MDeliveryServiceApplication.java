package uz.mediasolutions.mdeliveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import uz.mediasolutions.mdeliveryservice.feign.ClickFeign;

@SpringBootApplication
@EnableTransactionManagement
@EnableFeignClients
public class MDeliveryServiceApplication {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(MDeliveryServiceApplication.class, args);
	}

}
