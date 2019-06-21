package hr.ferit.brunozoric.taskie.presentation

import hr.ferit.brunozoric.taskie.domain.edittask.EditTaskUseCase
import hr.ferit.brunozoric.taskie.model.BackendTask
import hr.ferit.brunozoric.taskie.model.request.UpdateTaskRequest
import hr.ferit.brunozoric.taskie.ui.fragments.updatetask.UpdateTaskDialogContract

class UpdateDialogPresenter(private val editTaskUseCase: EditTaskUseCase) : UpdateTaskDialogContract.Presenter {

    private lateinit var view: UpdateTaskDialogContract.View
    override fun setView(view: UpdateTaskDialogContract.View) {
        this.view = view
    }

    override fun editTask(currentID: String, title: String, description: String, priority: Int) {
        editTaskUseCase.execute(
            UpdateTaskRequest(currentID, title, description, priority),
            ::onUpdateSuccessful,
            ::onFailure
        )
    }

    private fun onFailure(throwable: Throwable) {
        view.onFailure(throwable.localizedMessage)
    }

    private fun onUpdateSuccessful(backendTask: BackendTask) {
        view.onUpdateSuccessful(backendTask)
    }

    /*private fun callBack(): Callback<BackendTask> = object : Callback<BackendTask> {
        override fun onFailure(call: Call<BackendTask>, t: Throwable) {
            view.onFailure()
        }

        override fun onResponse(call: Call<BackendTask>, response: Response<BackendTask>) {
            if (response.isSuccessful) {
                when (response.code()) {
                    RESPONSE_OK -> handleOkResponse()
                }
            }
        }

    }

    private fun handleOkResponse() {
        view.onUpdateSuccessful()
    }*/

}