package hr.ferit.brunozoric.taskie.domain.savetask

import hr.ferit.brunozoric.taskie.common.ErrorLambda
import hr.ferit.brunozoric.taskie.common.SuccessLambda
import hr.ferit.brunozoric.taskie.model.BackendTask
import hr.ferit.brunozoric.taskie.model.request.AddTaskRequest

interface SaveTaskUseCase {
    fun execute(body: AddTaskRequest, onSuccess: SuccessLambda<BackendTask>, onFailure: ErrorLambda)
}