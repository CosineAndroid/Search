package kr.cosine.search.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {

    override fun intercept(
        chain: Interceptor.Chain
    ): Response {
        val newRequest = chain
            .request()
            .newBuilder()
            .addHeader(
                "Authorization",
                "KakaoAK %s".format("Key") // 공백 처리
            ).build()
        return chain.proceed(newRequest)
    }
}