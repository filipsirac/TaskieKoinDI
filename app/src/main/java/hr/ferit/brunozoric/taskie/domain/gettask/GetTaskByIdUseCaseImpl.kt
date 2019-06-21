package hr.ferit.brunozoric.taskie.domain.gettask

import hr.ferit.brunozoric.taskie.common.ErrorLambda
import hr.ferit.brunozoric.taskie.common.SuccessLambda
import hr.ferit.brunozoric.taskie.data.repository.GetTaskByIdRepository
import hr.ferit.brunozoric.taskie.model.BackendTask
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.IllegalStateException

class GetTaskByIdUseCaseImpl(private val getTaskRepository: GetTaskByIdRepository): GetTaskByIdUseCase {
    override fun execute(id: String, onSuccess: SuccessLambda<BackendTask>, onFailure: ErrorLambda) {
        getTaskRepository.getTask(id).enqueue(object : Callback<BackendTask> {
            override fun onFailure(call: Call<BackendTask>, t: Throwable) {
                onFailure(t)
            }

            override fun onResponse(call: Call<BackendTask>, response: Response<BackendTask>) {
                if (response.isSuccessful) response.body()?.run(onSuccess)

                response.errorBody()?.run { onFailure(IllegalStateException("Something went wrong")) }
            }
        })
    }
}