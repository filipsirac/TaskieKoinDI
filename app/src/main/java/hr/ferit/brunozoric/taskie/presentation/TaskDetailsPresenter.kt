package hr.ferit.brunozoric.taskie.presentation

import hr.ferit.brunozoric.taskie.domain.gettask.GetTaskByIdUseCase
import hr.ferit.brunozoric.taskie.model.BackendTask
import hr.ferit.brunozoric.taskie.ui.fragments.taskdetails.TaskDetailsContract

class TaskDetailsPresenter(private val getTaskByIdUseCase: GetTaskByIdUseCase) : TaskDetailsContract.Presenter {

    private lateinit var view: TaskDetailsContract.View
    override fun setView(view: TaskDetailsContract.View) {
        this.view = view
    }
    override fun getTask(id: String) {
        getTaskByIdUseCase.execute(id, ::onUpdateSuccessful, ::onFailure)
    }

    private fun onUpdateSuccessful(backendTask: BackendTask) {
        view.onUpdateSuccessful(backendTask)
    }

    private fun onFailure(throwable: Throwable){
        view.onFailure(throwable.localizedMessage)
    }

   /* private fun callBack(): Callback<BackendTask> = object : Callback<BackendTask> {
        override fun onFailure(call: Call<BackendTask>, t: Throwable) {

        }

        override fun onResponse(call: Call<BackendTask>, response: Response<BackendTask>) {
            if (response.isSuccessful) {
                when (response.code()) {
                    RESPONSE_OK -> handleOkResponse(response)
                    else -> handleSomethingWentWrong()
                }
            }
        }
    }

    private fun handleSomethingWentWrong() = view.onFailure()

    private fun handleOkResponse(response: Response<BackendTask>) {
        view.onUpdateSuccessful(response)
    }*/

}