package hr.ferit.brunozoric.taskie.domain.edittask

import hr.ferit.brunozoric.taskie.common.ErrorLambda
import hr.ferit.brunozoric.taskie.common.SuccessLambda
import hr.ferit.brunozoric.taskie.data.repository.EditTaskRepository
import hr.ferit.brunozoric.taskie.model.BackendTask
import hr.ferit.brunozoric.taskie.model.request.UpdateTaskRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.IllegalStateException

class EditTaskUseCaseImpl(private val editTaskRepository: EditTaskRepository): EditTaskUseCase {
    override fun execute(body: UpdateTaskRequest, onSuccess: SuccessLambda<BackendTask>, onFailure: ErrorLambda) {
        editTaskRepository.editTask(body).enqueue(object : Callback<BackendTask> {
            override fun onFailure(call: Call<BackendTask>, t: Throwable) {
                onFailure(t)
            }

            override fun onResponse(call: Call<BackendTask>, response: Response<BackendTask>) {
                if (response.isSuccessful) response.body()?.run(onSuccess)

                response.errorBody()?.run { onFailure(IllegalStateException("Something went wrong")) }            }
        })
    }
}