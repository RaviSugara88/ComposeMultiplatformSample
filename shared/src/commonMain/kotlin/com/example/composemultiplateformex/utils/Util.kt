package com.example.composemultiplateformex.utils

import com.example.composemultiplateformex.ui.screen.EmailList
import io.ktor.client.plugins.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error(val exception: CustomMessage) : Result<Nothing>
    object Loading : Result<Nothing>
    object Idle : Result<Nothing>
}

fun <T> Flow<T>.asResult(shouldEmit: Boolean = true): Flow<Result<T>> {
    return this.map<T, Result<T>> { Result.Success(it) }
        .onStart { emit(Result.Loading) }
        .catch {
            if (it is Exception) {
                if (shouldEmit) {
                    emit(Result.Error(it.getRealException()))
                }
            } else {
                emit(Result.Error(CustomMessage.ExceptionMessage("System not responding.")))
            }
        }
}

sealed class CustomMessage(val message: String = "") {

    object NetworkError : CustomMessage("Something wrong with network, please try again.")
    object RandomError : CustomMessage("Something went wrong, please try again.")
    object ResponseError :
        CustomMessage("We are fixing your problem, Thank you for your patience.")

    object NoInternet : CustomMessage("No Internet")
    object NotFound : CustomMessage("Not Found")
    data class ExceptionMessage(val error: String) : CustomMessage(message = error)
}

fun Exception.getRealException(): CustomMessage =
    when (this) {
        is HttpRequestTimeoutException -> {
            CustomMessage.NetworkError
        }
        is RedirectResponseException -> {
            CustomMessage.NetworkError
        }
        is ClientRequestException -> {
            CustomMessage.NetworkError
        }
        is ServerResponseException -> {
            CustomMessage.ResponseError
        }
        else -> {
            CustomMessage.RandomError
        }
    }

fun emailData():ArrayList<EmailList>{
    val list = ArrayList<EmailList>()
    list.add(EmailList("Update","Google","Your account access on another device"))
    list.add(EmailList("Axis Bank Alerts","One time password for transaction...","Dear Customer, 12563 is your OTP"))
    list.add(EmailList("Welcome to Bard","Bard, an AI experiment by G..","Welcome to Bard, your creative and helpful collaborator, here to supercharge your imagination, boost your productivity, and bring your ideas to life."))
    list.add(EmailList("FlutterFlow 4.0 is now live","FlutterFlow 4.0 Is Here ","We unveiled FlutterFlow 4.0 at our Developer Conference last week and are excited to announce these features are now live in FlutterFlow! "))
    list.add(EmailList("MongoDB Team","Last chance to RSVP: Atlas Search on September 20th","This is your last chance to save your spot for our Intro to Atlas Search webinar coming up this Wednesday, September 20th at 11 a.m. ET."))
    list.add(EmailList("Update","Google","Your account access on another device"))
    list.add(EmailList("Axis Bank Alerts","One time password for transaction...","Dear Customer, 12563 is your OTP"))
    list.add(EmailList("Welcome to Bard","Bard, an AI experiment by G..","Welcome to Bard, your creative and helpful collaborator, here to supercharge your imagination, boost your productivity, and bring your ideas to life."))
    list.add(EmailList("FlutterFlow 4.0 is now live","FlutterFlow 4.0 Is Here ","We unveiled FlutterFlow 4.0 at our Developer Conference last week and are excited to announce these features are now live in FlutterFlow! "))
    list.add(EmailList("MongoDB Team","Last chance to RSVP: Atlas Search on September 20th","This is your last chance to save your spot for our Intro to Atlas Search webinar coming up this Wednesday, September 20th at 11 a.m. ET."))
    list.add(EmailList("MongoDB Team","Last chance to RSVP: Atlas Search on September 20th","This is your last chance to save your spot for our Intro to Atlas Search webinar coming up this Wednesday, September 20th at 11 a.m. ET."))
    list.add(EmailList("Update","Google","Your account access on another device"))

    return list
}
