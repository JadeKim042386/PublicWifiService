# 공공와이파이 시설 정보 OpenApi

## Request

| 방식 | 필드명                                                                                                                             | 필드 내용 |
| --- |---------------------------------------------------------------------------------------------------------------------------------| --- |
| FORM-DATA/POST | searchAddrState <br> searchAddrCity <br> searchApName <br> searchLat <br> searchLon <br> searchDistance(단위: km) <br> apiAuthKey | 검색할 시도명 <br> 검색할 시군구명 <br> 검색할 AP명 <br> 검색할 위도 <br> 검색할 경도 <br> 검색할 반경 거리 <br> API 인증 키 |

## Response

| 필드명 | 필드 내용 |
| --- | --- |
| RN <br> AP_NAME <br> LAT <br> LON <br> ADDR_STATE <br> ADDR_CITY <br> ADDR_DETAIL | 순번 <br> AP명 <br> 위도 <br> 경도 <br> 시도 <br> 시군구 <br> 상세주소 |