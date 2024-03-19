package com.pirtip.pirtipserver.client.naver;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "naverOpenApiClient", url = "https://openapi.naver.com")
public interface NaverOpenApiClient {

	@GetMapping(value = "/v1/search/local.json", produces = MediaType.APPLICATION_JSON_VALUE)
	GetLocalResponse getLocal(@RequestParam String query, @RequestParam("display") int size);
}
