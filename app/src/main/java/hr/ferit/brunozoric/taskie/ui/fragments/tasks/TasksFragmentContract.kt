package hr.ferit.brunozoric.taskie.ui.fragments.tasks

import hr.ferit.brunozoric.taskie.model.request.DeleteTaskRequest
import hr.ferit.brunozoric.taskie.model.response.DeleteResponse
import hr.ferit.brunozoric.taskie.model.response.GetTasksResponse

interface TasksFragmentContract {

    interface View{

        fun onTaskListReceived(response: GetTasksResponse)

        fun onGetTasksFailed(error: String)

        fun onTaskDeleted(deleteResponse: DeleteResponse)

        fun onTaskDeletedFailure(error: String)

    }

    interface Presenter{

        fun setView(view: TasksFragmentContract.View)

        fun onGetAllTasks()

        fun onTaskDelete(deleteTaskRequest: DeleteTaskRequest)

    }
}