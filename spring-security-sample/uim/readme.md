1. http://localhost:8000/oauth2/authorize?response_type=code&client_id=kevin&scope=user:all&redirect_uri=http://localhost:8000/user
2. curl --location --request POST 'http://localhost:8000/oauth2/token?grant_type=authorization_code&redirect_uri=http%3A%2F%2Flocalhost%3A8000%2Fusers&code=ZA9xaElCbAYol4vVpoWvsq1pW5G1hoOsE6txeg1q87JHHAIVswpl-KVxWbhBnRtYOD2cQs2qq1bh5CRNulSV7Xou9IrQ0hF5ssbx2FF490HmLUfuOJ7t9rg0t58mNl2O' \
   --header 'Authorization: Basic a2V2aW46a2V2aW4='
3. kevin/kevin to base64
4. refresh token
   curl --location --request POST 'http://localhost:8000/oauth2/token?grant_type=refresh_token&redirect_uri=http%3A%2F%2Flocalhost%3A8000%2Fusers&refresh_token=CZqPQxgpRG-CPDMuHCC2e9w9bGbUkUui2x34WzV2vcDcb6h3ASC1MBcLn4bv081XaROZAOk8KzDGA6q2E9a5wZVzOHT_Rz21Ja0iv2VsgdrFo6Dsv4_cJPsA4ck3GFTY' \
   --header 'Authorization: Basic a2V2aW46a2V2aW4=' \
   --header 'Cookie: JSESSIONID=A2CCC190866A2DEDF430894B1F8E98C3'