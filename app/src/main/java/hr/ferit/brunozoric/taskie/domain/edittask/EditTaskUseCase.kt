package hr.ferit.brunozoric.taskie.domain.edittask

import hr.ferit.brunozoric.taskie.common.ErrorLambda
import hr.ferit.brunozoric.taskie.common.SuccessLambda
import hr.ferit.brunozoric.taskie.model.BackendTask
import hr.ferit.brunozoric.taskie.model.request.UpdateTaskRequest

interface EditTaskUseCase {
    fun execute(body: UpdateTaskRequest, onSuccess: SuccessLambda<BackendTask>, onFailure: ErrorLambda)
}