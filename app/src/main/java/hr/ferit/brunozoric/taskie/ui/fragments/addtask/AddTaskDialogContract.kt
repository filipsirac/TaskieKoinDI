package hr.ferit.brunozoric.taskie.ui.fragments.addtask

import hr.ferit.brunozoric.taskie.model.BackendTask
import hr.ferit.brunozoric.taskie.model.request.AddTaskRequest

interface AddTaskDialogContract {

    interface View{

        fun onTaskAdded(task: BackendTask)

        fun onTaskAddFailed()

    }

    interface Presenter{

        fun setView(view: AddTaskDialogContract.View)

        fun onAddTask(task: AddTaskRequest)

    }
}