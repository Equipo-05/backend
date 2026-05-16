# DefaultApi

All URIs are relative to *http://sasApp.com/api*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**v1GrantsGet**](DefaultApi.md#v1GrantsGet) | **GET** /v1/grants | Lista de ayudas |
| [**v1GrantsPost**](DefaultApi.md#v1GrantsPost) | **POST** /v1/grants | Registrar ayuda |


<a id="v1GrantsGet"></a>
# **v1GrantsGet**
> List&lt;Grant&gt; v1GrantsGet()

Lista de ayudas

Se muestra una lista completa de todas las ayudas sociales facilitadas por el gobierno. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://sasApp.com/api");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    try {
      List<Grant> result = apiInstance.v1GrantsGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#v1GrantsGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;Grant&gt;**](Grant.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **500** | Internal server error |  -  |

<a id="v1GrantsPost"></a>
# **v1GrantsPost**
> Grant v1GrantsPost(grantInDto)

Registrar ayuda

Acción que permite registrar ayuda social.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://sasApp.com/api");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    GrantInDto grantInDto = new GrantInDto(); // GrantInDto | Datos de la nueva ayuda social
    try {
      Grant result = apiInstance.v1GrantsPost(grantInDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#v1GrantsPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **grantInDto** | [**GrantInDto**](GrantInDto.md)| Datos de la nueva ayuda social | [optional] |

### Return type

[**Grant**](Grant.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Ayuda social registrada |  -  |
| **400** | Bad request |  -  |
| **500** | Internal server error |  -  |

