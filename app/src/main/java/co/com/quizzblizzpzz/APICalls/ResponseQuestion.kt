package co.com.quizzblizzpzz.APICalls

import com.google.gson.annotations.SerializedName

data class ResponseQuestion(
    @SerializedName("response_code") var response_code: Int,
    @SerializedName("results") var result : List<ResultQ>)

data class ResultQ (
    @SerializedName("category") var category: String,
    @SerializedName("type") var type: String,
    @SerializedName("difficulty") var difficulty: String,
    @SerializedName("question") var question: String,
    @SerializedName("correct_answer") var correct_answer: String,
    @SerializedName("incorrect_answers") var incorrect_answers: List<String>)