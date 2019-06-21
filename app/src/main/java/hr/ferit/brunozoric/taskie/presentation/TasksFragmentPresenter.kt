package hr.ferit.brunozoric.taskie.presentation

import hr.ferit.brunozoric.taskie.domain.deletetask.DeleteTaskUseCase
import hr.ferit.brunozoric.taskie.domain.gettasks.GetTasksUseCase
import hr.ferit.brunozoric.taskie.model.request.DeleteTaskRequest
import hr.ferit.brunozoric.taskie.model.response.DeleteResponse
import hr.ferit.brunozoric.taskie.model.response.GetTasksResponse
import hr.ferit.brunozoric.taskie.ui.fragments.tasks.TasksFragmentContract

class TasksFragmentPresenter(private val getTasksUseCase: GetTasksUseCase, private val deleteTaskUseCase: DeleteTaskUseCase): TasksFragmentContract.Presenter {

    private lateinit var view: TasksFragmentContract.View

    override fun setView(view: TasksFragmentContract.View) {
        this.view = view
    }

    override fun onGetAllTasks() {
        getTasksUseCase.execute(::onTaskListReceived, ::onGetTasksFailed)
    }

    private fun onGetTasksFailed(throwable: Throwable) {
        view.onGetTasksFailed(throwable.localizedMessage)
    }

    private fun onTaskListReceived(response: GetTasksResponse) {
        view.onTaskListReceived(response)
    }

    /* private fun getTaskieCallback(): Callback<GetTasksResponse> = object : Callback<GetTasksResponse> {
         override fun onFailure(call: Call<GetTasksResponse>?, t: Throwable?) {
             view.onGetTasksFailed()
         }

         override fun onResponse(call: Call<GetTasksResponse>?, response: Response<GetTasksResponse>) {

             if (response.isSuccessful) {
                 when (response.code()) {
                     RESPONSE_OK -> handleOkResponse(response)
                     else -> handleSomethingWentWrong()
                 }
             }
         }
     }

     private fun handleOkResponse(response: Response<GetTasksResponse>) {
         response.body()?.notes?.run {
             view.onTaskListReceived(this)
         }
     }

     private fun handleSomethingWentWrong() = view.onGetTasksFailed()
 */
    override fun onTaskDelete(deleteTaskRequest: DeleteTaskRequest) {
        deleteTaskUseCase.execute(deleteTaskRequest.id,::onTaskDeleted, ::onTaskDeletedFailure )
    }

    private fun onTaskDeletedFailure(throwable: Throwable) {
        view.onTaskDeletedFailure(throwable.localizedMessage)
    }

    private fun onTaskDeleted(deleteResponse: DeleteResponse) {
        view.onTaskDeleted(deleteResponse)
    }

/*
    private fun deleteTaskCallback(): Callback<DeleteResponse> = object : Callback<DeleteResponse> {
        override fun onFailure(call: Call<DeleteResponse>, t: Throwable) {
            view.onTaskDeletedFailure()
        }

        override fun onResponse(call: Call<DeleteResponse>, response: Response<DeleteResponse>) {
            view.onTaskDeleted()
        }
    }*/

}