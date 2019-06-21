package hr.ferit.brunozoric.taskie.presentation

import hr.ferit.brunozoric.taskie.domain.savetask.SaveTaskUseCase
import hr.ferit.brunozoric.taskie.model.BackendTask
import hr.ferit.brunozoric.taskie.model.request.AddTaskRequest
import hr.ferit.brunozoric.taskie.ui.fragments.addtask.AddTaskDialogContract

class AddTaskDialogPresenter (private val saveTaskUseCase: SaveTaskUseCase): AddTaskDialogContract.Presenter{

    private lateinit var view: AddTaskDialogContract.View

    override fun setView(view: AddTaskDialogContract.View) {
        this.view = view
    }

    override fun onAddTask(task: AddTaskRequest) {
        saveTaskUseCase.execute(AddTaskRequest(task.title, task.content, task.taskPriority),::onTaskAdded, ::onTaskAddFailed)
    }

    private fun onTaskAddFailed(throwable: Throwable) {
        view.onTaskAddFailed()
    }

    private fun onTaskAdded(backendTask: BackendTask) {
        view.onTaskAdded(backendTask)
    }

   /* private fun addTaskCallback(): Callback<BackendTask> = object : Callback<BackendTask> {
        override fun onFailure(call: Call<BackendTask>?, t: Throwable?) {
        }

        override fun onResponse(call: Call<BackendTask>?, response: Response<BackendTask>) {
            if (response.isSuccessful) {
                response.body()?.run { handleOkResponse(this) }
                when (response.code()) {
                    RESPONSE_OK -> handleOkResponse(response.body())
                    else -> handleSomethingWentWrong()
                }
            }
        }
    }

    private fun handleOkResponse(task: BackendTask?) = task?.run { view.onTaskAdded(this) }

    private fun handleSomethingWentWrong() {
        view.onTaskAddFailed()
    }
    */
}