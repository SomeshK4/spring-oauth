# OAuth 2.0

This repository contains the  OAuth examples.

### Table of Contents
**[Resource Server Sample](#resource-server-sample)**<br>
**[Web Client Sample](#web-client-sample)**<br>
**[Social Login Sample](#social-login-sample)**<br>
**[Okta Login Sample](#okta-login-sample)**<br>
**[Single Page Application Example with PKCE](#single-page-application-example-with-pkce)**<br>
**[Spring Authorization Server](#spring-authorization-server)**<br>
**[Orders Resource Server](#orders-resource-server)**<br>
**[OAuth2Client for Spring Authorization Server](#oauth2client-for-spring-authorization-server)**


## Resource Server Sample
This sample demonstrates integrating Resource Server with Keycloak.

### Prerequisites
- Keycloak
- Java 17


### Steps to set up Keycloak

- Download Keycloak from https://www.keycloak.org/downloads
- Import realm.json placed in the root directory of the project.
  ```
  kc.bat import --file <Path of the myrealm.json>
  ```

- Start Keycloak server
    ```
    kc.bat start-dev
    ```

### Get Access Token


As the above endpoint is secured so access token is needed to access this. Follow the steps to get access token

__Step 1 :__ Hit the endpoint on the browser http://localhost:8080/realms/appsdeveloperblog/protocol/openid-connect/auth?client_id=photo-app-code-flow-client&response_type=code&scope=profile&redirect_uri=http://localhost:8083/callback&state=adfsfdsdfdsfds

__Step 2__: Enter username as Somesh.Kumar and password as test when the login screen is appeared.

__Step 3:__ Copy the code parameter value after redirect in the same browser window

__Step 4:__ Run the command given below after replacing the code parameter value received __Step 1__
```
curl --location --request POST 'http://localhost:8080/realms/appsdeveloperblog/protocol/openid-connect/token' \
  --header 'Content-Type: application/x-www-form-urlencoded' \
  --header 'Cookie: JSESSIONID=AD0DA29092DE6A1C107FBA6C6970747B' \
  --data-urlencode 'grant_type=authorization_code' \
  --data-urlencode 'client_id=photo-app-code-flow-client' \
  --data-urlencode 'client_secret=v7YLLDcYnoyIXl0UxTstMjLjxxpOSFmV' \
  --data-urlencode 'redirect_uri=http://localhost:8083/callback' \
  --data-urlencode 'scope=profile' \
  --data-urlencode 'code=43e7ed32-69bc-4e3e-9469-ed74616c5e6f.8d8f9749-df96-41bf-9c70-0bcd476ef6de.05e68438-b92c-4e98-9c0d-7c0ad4022616'
  
```  

### Endpoints

- GET user status
```
curl --location --request GET 'http://localhost:8081/api/v1/users/status' \
--header 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJoMzcwR3VRcERlZG1tNjhBZ09LNTlwNHU3Q0VGM1NwbWNUa3RBX0I5YklrIn0.eyJleHAiOjE2NjQzNTU0MzcsImlhdCI6MTY2NDM1NTEzNywiYXV0aF90aW1lIjoxNjY0MzU1MTEyLCJqdGkiOiI2ZjJmN2Q4Mi1mZjY5LTQ5MjktYWEwNy04OTg2MWMwNjQyNGIiLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvcmVhbG1zL2FwcHNkZXZlbG9wZXJibG9nIiwiYXVkIjoiYWNjb3VudCIsInN1YiI6IjEyZTNkNGFhLTZhNjctNDU4ZS04NzE4LTc1YTkyNjI1ZjliYiIsInR5cCI6IkJlYXJlciIsImF6cCI6InBob3RvLWFwcC1jb2RlLWZsb3ctY2xpZW50Iiwic2Vzc2lvbl9zdGF0ZSI6IjhkOGY5NzQ5LWRmOTYtNDFiZi05YzcwLTBiY2Q0NzZlZjZkZSIsImFjciI6IjEiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsiZGVmYXVsdC1yb2xlcy1hcHBzZGV2ZWxvcGVyYmxvZyIsIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6InByb2ZpbGUgZW1haWwiLCJzaWQiOiI4ZDhmOTc0OS1kZjk2LTQxYmYtOWM3MC0wYmNkNDc2ZWY2ZGUiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIm5hbWUiOiJTb21lc2ggS3VtYXIiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJzb21lc2gua3VtYXIiLCJnaXZlbl9uYW1lIjoiU29tZXNoIiwiZmFtaWx5X25hbWUiOiJLdW1hciJ9.dTwjIL2X-eVls0P_99nNeYrP1D1p44Y5Q-3oePDBEjqRIzCi1E8Igiy9MF--sxqqR6ni9W-d04yHxASnG1K0n_NAOlXPbFFsQIn7l8PAfi1u7xyp819PXrQ8ChaM7Gr-cvlYUvuTnpcxEmeYK_V7Gub2Qn3Av_-WFC2J_-IkgfNWhgjeZRrYb89XXQeAqAOcoDV9E_yHLR6z7CyjZrUsh3_vw0GT7t5DLWiJij4r3uZ06nUTLnli391z9o7TTG_62HaPoUppRGsZJtDImmIs5lrIYewm1QBByrhjboi9HSPIz8Rr6Xjil2c_xC3IwW7CqsrfqGS_JIwUucGfvId3jw'
```

---
- DELETE user by Id
```
curl --location --request DELETE 'http://localhost:8081/api/v1/users/1' \
--header 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJoMzcwR3VRcERlZG1tNjhBZ09LNTlwNHU3Q0VGM1NwbWNUa3RBX0I5YklrIn0.eyJleHAiOjE2NjQ0NDIyMzYsImlhdCI6MTY2NDQ0MTkzNiwiYXV0aF90aW1lIjoxNjY0NDQxOTIwLCJqdGkiOiJkN2UwMzEwYi0zYjkzLTRhNGUtYmY2ZS0xOTkyYTM2ZjM5NDUiLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvcmVhbG1zL2FwcHNkZXZlbG9wZXJibG9nIiwiYXVkIjoiYWNjb3VudCIsInN1YiI6IjEyZTNkNGFhLTZhNjctNDU4ZS04NzE4LTc1YTkyNjI1ZjliYiIsInR5cCI6IkJlYXJlciIsImF6cCI6InBob3RvLWFwcC1jb2RlLWZsb3ctY2xpZW50Iiwic2Vzc2lvbl9zdGF0ZSI6ImM1YTFhNGIzLTExMmYtNDhjOS1hZTRhLTgzYzQ3NDA5NDhjZiIsImFjciI6IjEiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsiZGVmYXVsdC1yb2xlcy1hcHBzZGV2ZWxvcGVyYmxvZyIsIm9mZmxpbmVfYWNjZXNzIiwiZGV2ZWxvcGVyIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6Im9wZW5pZCBwcm9maWxlIGVtYWlsIiwic2lkIjoiYzVhMWE0YjMtMTEyZi00OGM5LWFlNGEtODNjNDc0MDk0OGNmIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJuYW1lIjoiU29tZXNoIEt1bWFyIiwicHJlZmVycmVkX3VzZXJuYW1lIjoic29tZXNoLmt1bWFyIiwiZ2l2ZW5fbmFtZSI6IlNvbWVzaCIsImZhbWlseV9uYW1lIjoiS3VtYXIifQ.sHxHiTPABStDPtI5bsvPnhvGu4i9RUQBm0IctYNSuwHyptM1_dUjCVd9YLm8ZMH7G6vtCEJh3hlhj9fMPlEOj1Qljfd1a54_flOauDZTABqm5sVu3eNwkWGgNjnsYc5rfiXaV360V6INbNCn86iN4Hnbyv-wAjvwksfsRQ_iopLvY2n4Swsyqgf7_BujlB9BV2wITMlRsXof8Pvxg5Mfa8coF1aOipM9otOo6UE0PbnmZO_mvYrM-xIXhULBBg_en7JxNPl6blSR16Rqm7hw-r-C-lppqCMdjRY6b7IHP6OQ7fdHcjk5EtdCHD4XIJ67IPXjzpioEC_UG58tX4FqQA'
```
---

- Get users
```
curl --location --request GET 'http://localhost:8081/api/v1/users' \
--header 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJoMzcwR3VRcERlZG1tNjhBZ09LNTlwNHU3Q0VGM1NwbWNUa3RBX0I5YklrIn0.eyJleHAiOjE2NjQ1NDI1NjQsImlhdCI6MTY2NDU0MjI2NCwiYXV0aF90aW1lIjoxNjY0NTQyMjQzLCJqdGkiOiI4ZjVkMDk5My03NTA1LTRkOWEtYmUwZS0wOWVkYjk1ZGQyZGUiLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvcmVhbG1zL2FwcHNkZXZlbG9wZXJibG9nIiwiYXVkIjoiYWNjb3VudCIsInN1YiI6IjEyZTNkNGFhLTZhNjctNDU4ZS04NzE4LTc1YTkyNjI1ZjliYiIsInR5cCI6IkJlYXJlciIsImF6cCI6InBob3RvLWFwcC1jb2RlLWZsb3ctY2xpZW50Iiwic2Vzc2lvbl9zdGF0ZSI6IjBjYTUyYmYxLTM3YmYtNGI5MS1hNTI4LTQ5MmMwODg0YWZhZSIsImFjciI6IjEiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsiZGVmYXVsdC1yb2xlcy1hcHBzZGV2ZWxvcGVyYmxvZyIsIm9mZmxpbmVfYWNjZXNzIiwiZGV2ZWxvcGVyIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6Im9wZW5pZCBwcm9maWxlIGVtYWlsIiwic2lkIjoiMGNhNTJiZjEtMzdiZi00YjkxLWE1MjgtNDkyYzA4ODRhZmFlIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJuYW1lIjoiU29tZXNoIEt1bWFyIiwicHJlZmVycmVkX3VzZXJuYW1lIjoic29tZXNoLmt1bWFyIiwiZ2l2ZW5fbmFtZSI6IlNvbWVzaCIsImZhbWlseV9uYW1lIjoiS3VtYXIifQ.mkTeQ-TldQZZQkR0P500xdFhyYxrRMmAJ30ohFfNkL451ogzqC15uX59HgOZ69Wczz037mwR3_b6x_250Kkja8-M3ml94G-OMnZ6OjyLPKWqokAC8d9W4wub7Rvga44fquVGAZcZkPKx0UnZLRiKYHKczVED53fDPjAn8t_jB5GsRPc4fl1QNu5M65rvjlmh9Zg2sKhIsIxhojZOjkocI__b6tOGfKkxa4TzosqUaSFQLCTMOUsbT7JIOGv-J_M-x9pKi1owHDr347CqRRD6FyErZWzjNSHHH6FWaOdoDwELjdQkU192IjtQcJnrIt-jOKY6gPhWi9jeQ8xvpnFR8g'
```

Note: Before running the GET Endpoint, you need to replace the userId '12e3d4aa-6a67-458e-8718-75a92625f9bb' in the following code snippet with sub field value in the access token. To see the sub field value in the access token, copy the access token that you received from the Authorization server and debug it on https://jwt.io/

    UserDTO userDTO = new UserDTO("Somesh Kumar", "12e3d4aa-6a67-458e-8718-75a92625f9bb"); 

---
- Get albums running on Albums Resource Server
```
curl --location --request GET 'http://localhost:8082/api/v1/albums' \
--header 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJoMzcwR3VRcERlZG1tNjhBZ09LNTlwNHU3Q0VGM1NwbWNUa3RBX0I5YklrIn0.eyJleHAiOjE2NjQ1NDk1NzYsImlhdCI6MTY2NDU0OTI3NiwiYXV0aF90aW1lIjoxNjY0NTQ5MjM3LCJqdGkiOiJiYjM2OTIyYS01NTk1LTRjNzUtOGE3YS01ZjBiNzI4MTIzODgiLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvcmVhbG1zL2FwcHNkZXZlbG9wZXJibG9nIiwiYXVkIjoiYWNjb3VudCIsInN1YiI6IjEyZTNkNGFhLTZhNjctNDU4ZS04NzE4LTc1YTkyNjI1ZjliYiIsInR5cCI6IkJlYXJlciIsImF6cCI6InBob3RvLWFwcC1jb2RlLWZsb3ctY2xpZW50Iiwic2Vzc2lvbl9zdGF0ZSI6ImMyODFjODFhLTcyZmMtNGM2ZS05MzJmLTMyYjdiYTJmMzM2NCIsImFjciI6IjEiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsiZGVmYXVsdC1yb2xlcy1hcHBzZGV2ZWxvcGVyYmxvZyIsIm9mZmxpbmVfYWNjZXNzIiwiZGV2ZWxvcGVyIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6Im9wZW5pZCBwcm9maWxlIGVtYWlsIiwic2lkIjoiYzI4MWM4MWEtNzJmYy00YzZlLTkzMmYtMzJiN2JhMmYzMzY0IiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJuYW1lIjoiU29tZXNoIEt1bWFyIiwicHJlZmVycmVkX3VzZXJuYW1lIjoic29tZXNoLmt1bWFyIiwiZ2l2ZW5fbmFtZSI6IlNvbWVzaCIsImZhbWlseV9uYW1lIjoiS3VtYXIifQ.xgGExH-ZaCCUg8YBpeZp8SU6fEdRN_EAFWz-Fyz7tRilE5rkkwgZesQGT-dHDhjDWILcHasvKseYju1GJ41uhdsvTWApIa4wLTTT9s5efwmtk9XbMDGX3kseqgS0TEkZr3glIfy3WBZP3dcHkXclcI8xxun6f_uuVOPJdGMFG2m98dKPx6KdGHi20GWrNk5YPEkTLgZN4LxLuLXUqmLHZe8fJt0mqoBmp5MFWlWbv30DfSEzgtr__PJ7GQBpMCF_nNXUGFOTr_-LgOaajKHVwkXJ9P-6rbdrlbEcF_KrFy_fKAZeT0SNuqMJzs5g0Ka74NMCUPsbUyxFRHhwuQriQA'
```

---
- Get photos running on Photo Resource Server
```
curl --location --request GET 'http://localhost:8082/api/v1/albums' \
--header 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJoMzcwR3VRcERlZG1tNjhBZ09LNTlwNHU3Q0VGM1NwbWNUa3RBX0I5YklrIn0.eyJleHAiOjE2NjQ1NDk1NzYsImlhdCI6MTY2NDU0OTI3NiwiYXV0aF90aW1lIjoxNjY0NTQ5MjM3LCJqdGkiOiJiYjM2OTIyYS01NTk1LTRjNzUtOGE3YS01ZjBiNzI4MTIzODgiLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvcmVhbG1zL2FwcHNkZXZlbG9wZXJibG9nIiwiYXVkIjoiYWNjb3VudCIsInN1YiI6IjEyZTNkNGFhLTZhNjctNDU4ZS04NzE4LTc1YTkyNjI1ZjliYiIsInR5cCI6IkJlYXJlciIsImF6cCI6InBob3RvLWFwcC1jb2RlLWZsb3ctY2xpZW50Iiwic2Vzc2lvbl9zdGF0ZSI6ImMyODFjODFhLTcyZmMtNGM2ZS05MzJmLTMyYjdiYTJmMzM2NCIsImFjciI6IjEiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsiZGVmYXVsdC1yb2xlcy1hcHBzZGV2ZWxvcGVyYmxvZyIsIm9mZmxpbmVfYWNjZXNzIiwiZGV2ZWxvcGVyIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6Im9wZW5pZCBwcm9maWxlIGVtYWlsIiwic2lkIjoiYzI4MWM4MWEtNzJmYy00YzZlLTkzMmYtMzJiN2JhMmYzMzY0IiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJuYW1lIjoiU29tZXNoIEt1bWFyIiwicHJlZmVycmVkX3VzZXJuYW1lIjoic29tZXNoLmt1bWFyIiwiZ2l2ZW5fbmFtZSI6IlNvbWVzaCIsImZhbWlseV9uYW1lIjoiS3VtYXIifQ.xgGExH-ZaCCUg8YBpeZp8SU6fEdRN_EAFWz-Fyz7tRilE5rkkwgZesQGT-dHDhjDWILcHasvKseYju1GJ41uhdsvTWApIa4wLTTT9s5efwmtk9XbMDGX3kseqgS0TEkZr3glIfy3WBZP3dcHkXclcI8xxun6f_uuVOPJdGMFG2m98dKPx6KdGHi20GWrNk5YPEkTLgZN4LxLuLXUqmLHZe8fJt0mqoBmp5MFWlWbv30DfSEzgtr__PJ7GQBpMCF_nNXUGFOTr_-LgOaajKHVwkXJ9P-6rbdrlbEcF_KrFy_fKAZeT0SNuqMJzs5g0Ka74NMCUPsbUyxFRHhwuQriQA'
```
---

- Get access token using password grant type
```
curl --location --request POST 'http://localhost:8080/realms/appsdeveloperblog/protocol/openid-connect/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--header 'Cookie: JSESSIONID=0889010D07491AC84E44BB43EF89B936' \
--data-urlencode 'grant_type=password' \
--data-urlencode 'username=somesh.kumar' \
--data-urlencode 'password=test' \
--data-urlencode 'scope=openid profile' \
--data-urlencode 'client_id=photo-app-code-flow-client'
```
---

**Note :- If you want to access the all the above endpoints when the api gateway is running change the port to 8060 in each endpoint. Make sure API Gateway is running.**



### Important urls

- URL to access Keycloak admin console http://localhost:8080/
- URL to access appsdeveloperblog realm http://localhost:8080/realms/appsdeveloperblog/account

- Access userinfo
```
curl --location --request GET 'http://localhost:8080/realms/appsdeveloperblog/protocol/openid-connect/userinfo' \
--header 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJoMzcwR3VRcERlZG1tNjhBZ09LNTlwNHU3Q0VGM1NwbWNUa3RBX0I5YklrIn0.eyJleHAiOjE2NjQzNzYxMzEsImlhdCI6MTY2NDM3NTgzMSwiYXV0aF90aW1lIjoxNjY0Mzc0NTk3LCJqdGkiOiI5NGE2MWNmYS05NjlhLTRkOTAtYWNmZC03NDk5NGMxNTlkNTciLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvcmVhbG1zL2FwcHNkZXZlbG9wZXJibG9nIiwiYXVkIjoiYWNjb3VudCIsInN1YiI6IjEyZTNkNGFhLTZhNjctNDU4ZS04NzE4LTc1YTkyNjI1ZjliYiIsInR5cCI6IkJlYXJlciIsImF6cCI6InBob3RvLWFwcC1jb2RlLWZsb3ctY2xpZW50Iiwic2Vzc2lvbl9zdGF0ZSI6IjI5NmMwN2IwLWQzOGMtNDY5OS05NDkxLTNiYjczM2Y4N2M4NyIsImFjciI6IjAiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsiZGVmYXVsdC1yb2xlcy1hcHBzZGV2ZWxvcGVyYmxvZyIsIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6Im9wZW5pZCBwcm9maWxlIGVtYWlsIiwic2lkIjoiMjk2YzA3YjAtZDM4Yy00Njk5LTk0OTEtM2JiNzMzZjg3Yzg3IiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJuYW1lIjoiU29tZXNoIEt1bWFyIiwicHJlZmVycmVkX3VzZXJuYW1lIjoic29tZXNoLmt1bWFyIiwiZ2l2ZW5fbmFtZSI6IlNvbWVzaCIsImZhbWlseV9uYW1lIjoiS3VtYXIifQ.VALxN2QzZPTZ6TJG64dE7AmoqhhWhFUqEQ4DihZo9gvQUiatAmssXB4TdIkKFYg1gBqRMhvMCYkDRf1XUfOI5_jj5IsNiH_cXJp6dYVPmCONZBRIbiTpr0mQiXL7-353sASqh2pEo9WbijGMai0eSyHo4AfV4QnGXcp7wJnBZxqxC7KDgzhlUfIiowNR2WnqAFvcicHzT4at55-2kgJa5sEckvrpMr7Ot2qCKndwpRg2U5E7aVsu4BCmPLTx2JzSjTyumBSTSGMAznxsLTboB8QuPNnyMhMD7Bmmnhk5OGmxDdpPXgXIMXPHx0oxZ8GeLTaH0B57R-Z33ltcGMlVMg' \
--header 'Cookie: JSESSIONID=AD0DA29092DE6A1C107FBA6C6970747B'
```
---

## Web Client Sample
This sample demonstrates the web application trying to get the data from the resource server.

### Prerequisites
1. Photo Web App Client application should be up and running.
2. Keycloak Server should be up and running.
3. DiscoveryServer
4. AlbumsResourceServer
5. Always use Icongnito mode of Chrome or any other browser
6. Keycloak

### Endpoints

GET http://localhost:8060/albums  - Endpoint will fetch the albums list from the album resource server running behing API Gateway which is registered with the Discovery Server.

---

## Social Login Sample
This sample demonstrates how a user can sign in your application using their own Google or Facebook account, besides traditional username and password login.

### Prerequisites
- socialloginwebclient application
- Register app with Facebook/Google


### Get ClientId and ClientSecret for Facebook
__Step 1:__ Go to https://developers.facebook.com/ and login with your username and password

__Step 2:__ Click on MyApps menu and then on create App button and select none.

__Step 3:__ Enter Display name as anything you want and click on create App button.

__Step 4:__ Go to Settings -> Basic on the left side and copy the App ID and App Secret and paste in front of the following
properties in the application.properties file in the code.

spring.security.oauth2.client.registration.facebook.client-id=<APP ID>
spring.security.oauth2.client.registration.facebook.client-secret=<APP Secret>

__Step 5:__ Access url http://localhost:8080 and click on the home page.

### Login with Gmail
__Step 1:__ Go to https://console.developers.google.com and login with your username and password

__Step 2:__ Click on 'New Project' and enter unique project name

__Step 3:__ On the left side click Credentials menu and click on Configure Consent Screen and choose External.
Give the App Name as the name given while creating the project.
Enter support email as your email address and for Developer Contact information again enter your personal email address.

__Step 4:__ Click "ADD OR REMOVE SCOPES" and select openid and then continue

__Step 5:__ Once the above step is done click on the "Credentials" and then on the top click "Create Credentials" and then
select "OAuth client id". Select application type as Web application and name as the same name which you used in the Step 2.
Add Authorized Redirect URIs as http://localhost:8080/login/oauth2/code/google

__Step 6:__ Access url http://localhost:8080 and click on the home page.

---

## Okta Login Sample
Okta is cloud based Identity and Access Management Solution.

### Prerequisites
- Java 17
- SocialLoginWebClient Application

### Steps to create application on Okta
__Step 1:__ Go to https://developer.okta.com and signup with your google account.

__Step 2:__ Go to Applications menu on the left side and click "Create App Integration". Select "OIDC - OpenID Connect" and "Web Application".

__Step 3:__ Enter the name as any name you want.
Enter Base URI and Sign-out redirect URIs as http://localhost:8080/.
Enter Sign-in redirect URIs as http://localhost:8080/login/oauth2/code/okta. Select the option "Allow everyone in your organization to access".

### Configure SocialLoginWebClient Application to sign with Okta

__Step 1:__ Following properties are needed in the application properties file
```
spring.security.oauth2.client.registration.okta.client-id=<Replace with app id of the app registered with Okta>
spring.security.oauth2.client.registration.okta.client-secret=<Replace with app secret of the app registered with Okta>
spring.security.oauth2.client.registration.okta.scope[0]=openid
spring.security.oauth2.client.registration.okta.scope[1]=profile
spring.security.oauth2.client.provider.okta.issuer-uri=https://<Replace with your Okta domain name>/oauth2/default
```

### Test the application
- Run application and access url http://localhost:8080/

### End session on Authentication Server i.e Okta 
__Step 1:__ Go to URL https://{your okta domain name}/.well-known/openid-configuration

__Step2:__ See if there is key named "end_session_endpoint" in json returned in Step 1. If you are able to find the key, it means that your Authorization/Authentication server supports logout.

---

## Single Page Application Example with PKCE
This example demonstrates the PKCE(Proof Key for Code Exchange) Flow for Single Page Applications.

### Prerequisites
- Java 17
- Keycloak
- Discovery server application
- ApiGateway application
- ResourceServer application

### Steps to run the application
Open url http://localhost:8181 
Click on the all the buttons one by one

### CORS Configuration
By default, the SPA application uses the CORS configuration configured in the api gateway. 

If you want to run the SPA application without API Gateway, you need to change the url
http://localhost:8060/api/v1/users/status to http://localhost:{Random port number of Resourceserver} in index.html file.

Both the ApiGateway and Resource server have CORS configuration, so if you want to run the application with APIGateway,
you should comment out the CORS configuration in WebSecurity class in resource server application, otherwise the application
will not work.

---

## Spring Authorization Server
This application demonstrates the Spring Authorization Server implementation.

### Prerequisites
- Java17

### Run the application
__Step 1:__ Open the browser and enter the url 
```
http://localhost:8000/oauth2/authorize?client_id=testClientId&response_type=code&scope=openid&redirect_uri=http://127.0.0.1:8080/authorized
```

- Enter username/password as somesh/test
- You will find the authorization code returned by spring authorization server in the browser with the code query parameter.

__Step 2:__ Get Access token
```
curl --location --request POST 'http://localhost:8000/oauth2/token' \
--header 'Authorization: Basic dGVzdENsaWVudElkOnRlc3RDbGllbnRTZWNyZXQ=' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'grant_type=authorization_code' \
--data-urlencode 'code=OgWOOeSVLbK1buaj2cEnCMV3KO5HvG6BojHpOgcroNSd4kh9iPpVf10iUvI_hsQ2iq6-1hbrpPB2m0SgwRjFgXhrPatPIFKefDBEJGzpo7DFsQnxYeyeQ6iEGx6g6Mjb' \
--data-urlencode 'redirect_uri=http://127.0.0.1:8080/authorized'
```

---

## Orders Resource Server
This application demonstrates the use of authentication/authorization using Spring OAuth2 Authorization Server instead of Keycloak.

### Prerequisites
- Java17
- Spring Authorization Server application

### Steps to test the application

__Step1:__ Run the springauthorizationserver application

__Step2:__ Open your favourite browser and enter URL 
```
http://localhost:8000/oauth2/authorize?client_id=testClientId&response_type=code&scope=openid&redirect_uri=http://127.0.0.1:8080/authorized
```

__Step3:__ Enter username/password as somesh/test and copy the code parameter from the URL after redirect

__Step4:__ Run the curl command after change the code parameter value received in Step 3.
```
curl --location --request POST 'http://localhost:8000/oauth2/token' \
--header 'Authorization: Basic dGVzdENsaWVudElkOnRlc3RDbGllbnRTZWNyZXQ=' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--header 'Cookie: JSESSIONID=DD3E254CC52CD9E7F9941FA826E563E2' \
--data-urlencode 'grant_type=authorization_code' \
--data-urlencode 'code=8iIC5sOwaqgqPIysd0EqHejGuLoc47QPuOxZZINffcRHz17oaBymR3sbTq0HQseKSrgP08BZtM1JZ4CpHSL5lEqEE1cke45kbVveKntr6EdmYZ73Bi2kMiiO3xbWE1kv' \
--data-urlencode 'redirect_uri=http://127.0.0.1:8080/authorized'
```

__Step5:__ Copy the access token received after the sending the above request and hit URL 
```
curl --location --request GET 'http://localhost:8091/api/v1/orders' \
--header 'Authorization: Bearer eyJraWQiOiI0ZDE0OTg1My1lMWE3LTQ5OWUtOWIxZi0wYjRmMjM2MGY4ZmIiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJzb21lc2giLCJhdWQiOiJ0ZXN0Q2xpZW50SWQiLCJuYmYiOjE2NjYxMjIzNzEsInNjb3BlIjpbIm9wZW5pZCJdLCJpc3MiOiJodHRwOlwvXC9sb2NhbGhvc3Q6ODAwMCIsImV4cCI6MTY2NjEyMjY3MSwiaWF0IjoxNjY2MTIyMzcxfQ.DfWv6ptu-JyjhYWX66hPvyqpZWGNOAd6uuXFGbjHaw1NOpZtrW_EYxSR6YVAUliPTZHSCUUoVWCWGdU4PlOWhOkjyVGn9biixG-35ETSvghjgNFYPm5or9vn7qx4udY24sYBiJUy0S6zibkYqaOmpfoDluwY1-4BxhI-758Cs5gAc3se9EZBkDRiCetN_xd-_WWPr64DI7_FfIgNsJ_8vfubxSMyvbhekrBBwfNq1zusvvj80zR--nkTfKU9RfH4FZyhWX9obEWblN_SDrw3xYE-mjeZdBHOH5LTg-zhIBADzNRcQdAXjozrVnb4NGO4gROJtrDJ_EsXzXdrzx-IHQ'
```

---

## OAuth2Client for Spring Authorization Server
The application orderweboauthclient demonstrates the authorization_code flow with SpringAuthorizationServer.

### Prerequisites
- Java 17
- springauthorizationserver application
- Add an “127.0.0.1 auth-server” entry in your /etc/hosts file. 
This allows us to run the client and the auth server on our local machine, and 
avoids problems with session cookie overwrites between the two. When this change is done, go to 
springauthorizationserver application and in AuthorizationServerConfiguration.java file on line 66 
change http://localhost:8000 to http://auth-server:8000
- OrderResourceServer application. (Change property 
'spring.security.oauth2.resourceserver.jwt.issuer-uri' value to http://auth-server:8000)

### Steps to test the application
__Step 1:__ Run application
