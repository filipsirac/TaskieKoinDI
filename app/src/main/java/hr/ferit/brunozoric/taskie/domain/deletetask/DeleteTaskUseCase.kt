package hr.ferit.brunozoric.taskie.domain.deletetask

import hr.ferit.brunozoric.taskie.common.ErrorLambda
import hr.ferit.brunozoric.taskie.common.SuccessLambda
import hr.ferit.brunozoric.taskie.model.response.DeleteResponse

interface DeleteTaskUseCase {
    fun execute(id: String, onSuccess: SuccessLambda<DeleteResponse>,onFailure: ErrorLambda)
}