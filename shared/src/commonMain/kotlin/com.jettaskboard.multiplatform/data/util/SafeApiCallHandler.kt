package com.jettaskboard.multiplatform.data.util

import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse

suspend inline fun <reified T> safeApiCallHandler(
    block: () -> HttpResponse
): NetworkResponse<T> {
    return try {
        val apiCallResponse = block()
        NetworkResponse.Success(apiCallResponse.body())
    } catch (e: RedirectResponseException) {
        // 3xx - responses
        println("RedirectResponseException error: ${e.response.status.description}")
        NetworkResponse.Failure(e)
    } catch (e: ClientRequestException) {
        // 4xx - responses
        println("ClientRequestException error: ${e.response.status.description}")
        NetworkResponse.Failure(e)
    } catch (e: NoTransformationFoundException) {
        println("NoTransformationFoundException error: ${e.message}")
        NetworkResponse.Failure(e)
    } catch (e: ServerResponseException) {
        // 5xx - responses
        println("ServerResponseException error: ${e.response.status.description}")
        NetworkResponse.Failure(e)
    } catch (e: Exception) {
        println("Api error: ${e.message}")
        NetworkResponse.Failure(e)
    }
}
