package hr.ferit.brunozoric.taskie.domain.deletetask

import hr.ferit.brunozoric.taskie.common.ErrorLambda
import hr.ferit.brunozoric.taskie.common.SuccessLambda
import hr.ferit.brunozoric.taskie.data.repository.DeleteTaskRepository
import hr.ferit.brunozoric.taskie.model.response.DeleteResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.IllegalStateException

class DeleteTaskUseCaseImpl(private val deleteTaskRepository: DeleteTaskRepository): DeleteTaskUseCase {
    override fun execute(id: String, onSuccess: SuccessLambda<DeleteResponse>, onFailure: ErrorLambda) {
        deleteTaskRepository.deleteTask(id).enqueue(object: Callback<DeleteResponse> {
            override fun onFailure(call: Call<DeleteResponse>, t: Throwable) {
                onFailure(t)
            }

            override fun onResponse(call: Call<DeleteResponse>, response: Response<DeleteResponse>) {
                if (response.isSuccessful) response.body()?.run(onSuccess)

                response.errorBody()?.run { onFailure(IllegalStateException("Something went wrong")) }
            }
        })
    }
}