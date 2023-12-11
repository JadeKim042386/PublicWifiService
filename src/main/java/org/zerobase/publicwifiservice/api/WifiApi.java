package org.zerobase.publicwifiservice.api;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.zerobase.publicwifiservice.api.utils.Mapper;
import org.zerobase.publicwifiservice.dto.response.WifiApiResponse;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WifiApi {
    private final RestTemplate restTemplate;
    @Value("${wifi_api.url}")
    private String url;
    @Value("${wifi_api.key}")
    private String key;

    /**
     * 위도와 경도를 기준으로 입력한 거리 내에 있는 와이파이 정보를 요청
     * @param lat 위도
     * @param lon 경도
     * @param dist 거리
     * @throws RestClientException API 요청 실패
     */
    public List<WifiApiResponse> getWifis(double lat, double lon, double dist) {
        String response = restTemplate.postForObject(
                url,
                generateHttpEntity(lat, lon, dist),
                String.class
        );
        JsonNode jsonNode = Mapper.stringToJsonNode(response).get("apList").get("list");
        List<WifiApiResponse> wifiApiResponses = new ArrayList<>(jsonNode.size());
        for (int i = 0; i < jsonNode.size(); i++) {
            wifiApiResponses.add(
                    Mapper.stringToObject(jsonNode.get(i).toString(), WifiApiResponse.class)
            );
        }
        return wifiApiResponses;
    }

    private HttpEntity<?> generateHttpEntity(double lat, double lon, double dist) {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("searchLat", lat);
        params.add("searchLon", lon);
        params.add("searchDistance", dist);
        params.add("apiAuthKey", key);

        return new HttpEntity<>(params, header);
    }
}
