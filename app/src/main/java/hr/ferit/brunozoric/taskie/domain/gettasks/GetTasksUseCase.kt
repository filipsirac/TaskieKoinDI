package hr.ferit.brunozoric.taskie.domain.gettasks

import hr.ferit.brunozoric.taskie.common.ErrorLambda
import hr.ferit.brunozoric.taskie.common.SuccessLambda
import hr.ferit.brunozoric.taskie.model.response.GetTasksResponse

interface GetTasksUseCase {
    fun execute(onSuccess: SuccessLambda<GetTasksResponse>, onFailure: ErrorLambda)
}