package com.kohttp.dsl

import com.kohttp.client.CommonHttpClient
import okhttp3.Call
import okhttp3.Response

/**
 * Method provides an synchronous DSL call of HTTP GET
 *
 * @return a `Response` instance.
 *
 * Usage example using the default `CommonHttpClient`:
 *
 *  <pre>
 *  val response: Response? = httpGet {
 *      host = "yourhost"
 *      scheme = "https"
 *      port = 8080
 *      path = "path/to/resource"
 *      param {
 *          "your param" to "value"
 *      }
 *      header { ... }
 *  }
 *  response.use { ... }
 *  </pre>
 *
 *  @param client allow to use your own implementation of HttpClient.
 * `CommonHttpClient` is used by default.
 *
 * <pre>
 *  val response: Response? = httpGet(customHttpClient) {
 *      ...
 *  }
 * </pre>
 *
 * @see Response
 * @see HttpContext
 * @see ParamContext
 * @see HeaderContext
 * @see BodyContext
 *
 * Created by Sergey on 22/07/2018
 */
fun httpGet(client: Call.Factory = CommonHttpClient, init: HttpGetContext.() -> Unit): Response {
    val context = HttpGetContext().apply(init)
    return client.newCall(context.makeRequest()).execute()
}


