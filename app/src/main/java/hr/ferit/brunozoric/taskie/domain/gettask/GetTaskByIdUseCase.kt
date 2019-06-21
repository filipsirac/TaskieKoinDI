package hr.ferit.brunozoric.taskie.domain.gettask

import hr.ferit.brunozoric.taskie.common.ErrorLambda
import hr.ferit.brunozoric.taskie.common.SuccessLambda
import hr.ferit.brunozoric.taskie.model.BackendTask

interface GetTaskByIdUseCase {
    fun execute(id: String, onSuccess: SuccessLambda<BackendTask>, onFailure: ErrorLambda)
}